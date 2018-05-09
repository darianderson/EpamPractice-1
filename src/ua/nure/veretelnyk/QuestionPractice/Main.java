package ua.nure.veretelnyk.QuestionPractice;

import org.xml.sax.SAXException;
import ua.nure.veretelnyk.QuestionPractice.constants.Constants;
import ua.nure.veretelnyk.QuestionPractice.controller.DOM;
import ua.nure.veretelnyk.QuestionPractice.entity.Test;
import ua.nure.veretelnyk.QuestionPractice.util.Sorter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        if (args.length != 1)
            System.out.println("Enter file with questions.");

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
    }
}
