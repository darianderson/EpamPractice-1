package ua.nure.veretelnyk.QuestionPractice;

import org.xml.sax.SAXException;
import ua.nure.veretelnyk.QuestionPractice.constants.Constants;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        Main.main(new String[] {Constants.INPUT_XML_FILE});
    }
}
