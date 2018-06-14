package ua.nure.veretelnyk.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.veretelnyk.constants.Constants;
import ua.nure.veretelnyk.constants.XML;
import ua.nure.veretelnyk.entity.Answer;
import ua.nure.veretelnyk.entity.Question;
import ua.nure.veretelnyk.entity.Test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAX extends DefaultHandler {
    private String xmlFileName;
    private String currentElement;

    private Test test;
    private Question question;
    private Answer answer;

    public SAX(String xmlFileName) { this.xmlFileName = xmlFileName; }

    public void parse(boolean validate) throws SAXException, ParserConfigurationException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        factory.setNamespaceAware(true);

        if (validate){
            factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        SAXParser parser = factory.newSAXParser();
        parser.parse(xmlFileName, this);
    }

    @Override
    public void error(SAXParseException e) throws SAXException { throw e; }

    public Test getTest() { return test; }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = localName;
        if (XML.TEST.equalsTo(currentElement)){
            test = new Test();
            return;
        }
        if (XML.QUESTIOIN.equalsTo(currentElement)){
            question = new Question();
            return;
        }
        if(XML.ANSWER.equalsTo(currentElement)){
            answer=new Answer();
            if (attributes.getLength() > 0)
                answer.setCorrect(Boolean.parseBoolean(
                        attributes.getValue(uri, XML.CORRECT.value())));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String elementText = new String(ch,start,length).trim();
        if (elementText.isEmpty()) return;

        if(XML.QUESTION_TEXT.equalsTo(currentElement)){
            question.setQuestionText(elementText);
            return;
        }

        if(XML.ANSWER.equalsTo(currentElement)){
            answer.setContent(elementText);
            return;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (XML.QUESTIOIN.equalsTo(localName)){
            test.getQuestions().add(question);
            return;
        }

        if(XML.ANSWER.equalsTo(localName)){
            question.getAnswers().add(answer);
            return;
        }
    }
}
