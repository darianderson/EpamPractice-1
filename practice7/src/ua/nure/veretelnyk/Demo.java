package ua.nure.veretelnyk;

import org.xml.sax.SAXException;
import ua.nure.veretelnyk.constant.Constants;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException, XMLStreamException {
        Main.main(new String[] {Constants.INPUT_XML_FILE});
    }
}
