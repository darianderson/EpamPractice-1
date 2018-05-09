package ua.nure.veretelnyk.QuestionPractice.controller;

import ua.nure.veretelnyk.QuestionPractice.constants.XML;
import ua.nure.veretelnyk.QuestionPractice.entity.Answer;
import ua.nure.veretelnyk.QuestionPractice.entity.Question;
import ua.nure.veretelnyk.QuestionPractice.entity.Test;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.stream.StreamSource;

public class STAX {

    private String xmlFileName;
    private Test test;
    public Test getTest() { return test; }

    public STAX(String xmlFileName) { this.xmlFileName = xmlFileName; }

    public void parse() throws XMLStreamException {
        Question question = null;
        Answer answer = null;

        String currentElement = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
        XMLEventReader reader = factory.createXMLEventReader(new StreamSource(xmlFileName));

        while (reader.hasNext()){
            XMLEvent event = reader.nextEvent();
            if (event.isCharacters() && event.asCharacters().isWhiteSpace())
                continue;

            if (event.isStartElement()){
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();

                if(XML.TEST.equalsTo(currentElement)){
                    test = new Test();
                    continue;
                }

                if (XML.QUESTIOIN.equalsTo(currentElement)) {
                    question = new Question();
                    continue;
                }

                if (XML.ANSWER.equalsTo(currentElement)){
                    answer = new Answer();
                    Attribute attribute = startElement.getAttributeByName(
                            new QName(XML.CORRECT.value()));
                    if(attribute != null)
                        answer.setCorrect(Boolean.parseBoolean(attribute.getValue()));
                }
            }
            if (event.isCharacters()){
                Characters characters = event.asCharacters();

                if (XML.QUESTION_TEXT.equalsTo(currentElement)){
                    question.setQuestionText(characters.getData());
                    continue;
                }

                if(XML.ANSWER.equalsTo(currentElement)){
                    answer.setContent(characters.getData());
                    continue;
                }
            }

            if (event.isEndElement()){
                EndElement endElement = event.asEndElement();
                String localName = endElement.getName().getLocalPart();

                if(XML.QUESTIOIN.equalsTo(localName)){
                    test.getQuestions().add(question);
                    continue;
                }

                if(XML.ANSWER.equalsTo(localName))
                    question.getAnswers().add(answer);
            }
        }

        reader.close();
    }
}
