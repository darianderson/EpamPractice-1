package ua.nure.veretelnyk.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.veretelnyk.constants.Constants;
import ua.nure.veretelnyk.constants.XML;
import ua.nure.veretelnyk.entity.Answer;
import ua.nure.veretelnyk.entity.Question;
import ua.nure.veretelnyk.entity.Test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DOM {
    private String xmlFileName;
    private Test test;

    public DOM(String xmlFileName) { this.xmlFileName = xmlFileName; }

    public Test getTest() { return test; }



    /**
     * Parses XML document.
     *
     * @param validate
     *            If true validate XML document against its XML schema.
     */
    public void parse(boolean validate) throws ParserConfigurationException, IOException, SAXException {
        // obtain DOM parser
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // XML document contains namespaces
        dbf.setNamespaceAware(true);

        //make parser validation on
        if (validate){
            //turn validation on
            dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);

            // turn on xsd validation
            dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        DocumentBuilder db = dbf.newDocumentBuilder();

        //set error handler
        db.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(SAXParseException e) throws SAXException {
                // throw error if document is not valid
                throw  e;
            }
        });

        Document document = db.parse(xmlFileName);
        Element root = document.getDocumentElement();
        test = new Test();
        NodeList questionNodes = root.getElementsByTagName(XML.QUESTIOIN.value());

        for(int i=0; i< questionNodes.getLength(); ++i){
            Question question = getQuestion(questionNodes.item(i));
            test.getQuestions().add(question);
        }
    }
    /**
     * Extracts question object from the question XML node.
     *
     * @param qNode
     *            Question node.
     * @return Question object.
     */
    private Question getQuestion(Node qNode) {
        Question question = new Question();
        Element qElement = (Element) qNode;

        //process question text
        Node qtNode = qElement.getElementsByTagName(XML.QUESTION_TEXT.value()).item(0);
        //set question text
        question.setQuestionText(qtNode.getTextContent());

        //process ansers
        NodeList nodeList = qElement.getElementsByTagName(XML.ANSWER.value());
        for(int i=0; i< nodeList.getLength(); ++i){
            Answer answer =getAnswer(nodeList.item(i));
            question.getAnswers().add(answer);
        }
        return question;
    }
    /**
     * Extracts answer object from the answer XML node.
     *
     * @param aNode
     *            Answer node.
     * @return Answer object.
     */
    private Answer getAnswer(Node aNode) {
        Answer answer = new Answer();
        Element element = (Element) aNode;

        //process correct
        String correct = element.getAttribute(XML.CORRECT.value());
        answer.setCorrect(Boolean.valueOf(correct));

        //process content
        String content = element.getTextContent();
        answer.setContent(content);

        return answer;
    }


    /**
     * Creates and returns DOM of the Test container.
     *
     * @param test
     *            Test object.
     * @throws ParserConfigurationException
     */
    public static Document getDocument(Test test) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        Element element = document.createElement(XML.TEST.value());
        document.appendChild(element);

        for(Question question : test.getQuestions()){

            // add question
            Element qElement = document.createElement(XML.QUESTIOIN.value());
            element.appendChild(qElement);

            // add question text
            Element qtElement = document.createElement(XML.QUESTION_TEXT.value());
            qtElement.setTextContent(question.getQuestionText());
            qElement.appendChild(qtElement);

            // add answers
            for(Answer answer : question.getAnswers()){
                Element aElement = document.createElement(XML.ANSWER.value());
                aElement.setTextContent(answer.getContent());

                //set attribute if required
                if (answer.isCorrect())
                    aElement.setAttribute(XML.CORRECT.value(), "true");

                qElement.appendChild(aElement);
            }
        }

        return document;
    }
    /**
     * Saves Test object to XML file.
     *
     * @param test
     *            Test object to be saved.
     * @param xmlFileName
     *            Output XML file name.
     */
    public static void saveToXML(Test test, String xmlFileName) throws ParserConfigurationException, TransformerException {
        saveToXML(getDocument(test), xmlFileName);
    }

    /**
     * Save DOM to XML.
     *
     * @param document
     *            DOM to be saved.
     * @param xmlFileName
     *            Output XML file name.
     */
    public static void saveToXML(Document document, String xmlFileName) throws TransformerException {
        StreamResult result = new StreamResult(new File(xmlFileName));

        //set up transformation
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // run transform
        transformer.transform(new DOMSource(document), result);

    }

}
