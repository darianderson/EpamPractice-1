package ua.nure.veretelnyk.QuestionPractice;

import org.xml.sax.SAXException;
import ua.nure.veretelnyk.QuestionPractice.constants.Constants;
import ua.nure.veretelnyk.QuestionPractice.controller.DOM;
import ua.nure.veretelnyk.QuestionPractice.controller.SAX;
import ua.nure.veretelnyk.QuestionPractice.controller.STAX;
import ua.nure.veretelnyk.QuestionPractice.entity.Test;
import ua.nure.veretelnyk.QuestionPractice.util.Sorter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException, XMLStreamException {
        if (args.length != 1) {
            System.out.println("Enter file with questions.");
            return;
        }

        String xmlFileName = args[0];

        /////////////////////////////////////// DOM

        //get
        DOM dom = new DOM(xmlFileName);
        dom.parse(true);
        Test test = dom.getTest();

        //sort
        Sorter.sortQuestionsByQuestionText(test);

        //save
        DOM.saveToXML(test, Constants.OUTPUT_DOM_XML_FILE);


        /////////////////////////////////////// SAX

        //get
        SAX sax = new SAX(xmlFileName);
        sax.parse(true);
        test = sax.getTest();

        //sort
        Sorter.sortQuestionsByAnswersNumber(test);

        //save
        DOM.saveToXML(test, Constants.OUTPUT_SAX_XML_FILE);

        /////////////////////////////////////// STAX
        STAX stax = new STAX(xmlFileName);
        stax.parse();
        test = stax.getTest();

        Sorter.sortAnswersByContent(test);

        DOM.saveToXML(test, Constants.OUTPUT_STAX_XML_FILE);

    }
}
