package ua.nure.veretelnyk;

import org.xml.sax.SAXException;
import ua.nure.veretelnyk.constant.Constants;
import ua.nure.veretelnyk.controller.DOM;
import ua.nure.veretelnyk.controller.SAX;
import ua.nure.veretelnyk.controller.STAX;
import ua.nure.veretelnyk.entity.Guns;
import ua.nure.veretelnyk.util.Sorter;

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

        // DOM
        DOM dom = new DOM(xmlFileName);
        dom.parse(true);
        Guns guns = dom.getGuns();
        Sorter.sortGunsByModel(guns);
        DOM.saveToXML(guns, Constants.OUTPUT_DOM_XML_FILE);

        // SAX
        SAX sax = new SAX(xmlFileName);
        sax.parse(true);
        guns = sax.getGuns();
        Sorter.sortGunsByOrigin(guns);
        DOM.saveToXML(guns, Constants.OUTPUT_SAX_XML_FILE);

        // STAX
        STAX stax = new STAX(xmlFileName);
        stax.parse();
        guns = stax.getGuns();
        Sorter.sortGunsBySightingRange(guns);
        DOM.saveToXML(guns, Constants.OUTPUT_STAX_XML_FILE);
    }
}
