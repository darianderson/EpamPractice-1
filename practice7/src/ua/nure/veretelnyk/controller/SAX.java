package ua.nure.veretelnyk.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.veretelnyk.constant.Constants;
import ua.nure.veretelnyk.constant.XML;
import ua.nure.veretelnyk.entity.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAX extends DefaultHandler {
    private String xmlFileName;
    private String currentElement;

    private Guns guns;
    private Gun gun;
    private TTC ttc;
    private Distance distance;

    public SAX(String xmlFileName) { this.xmlFileName = xmlFileName; }

    public void parse(boolean validate) throws SAXException, ParserConfigurationException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        factory.setNamespaceAware(true);
        if(validate){
            factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON,true);
            factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON,true);
        }

        factory.newSAXParser().parse(xmlFileName, this);

    }


    @Override
    public void error(SAXParseException e) throws SAXException {
        throw e;
    }
    public Guns getGuns() { return guns; }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = localName;
        if(XML.GUNS.equalsTo(currentElement)){
            guns = new Guns();
            return;
        }
        if(XML.GUN.equalsTo(currentElement)){
            gun = new Gun();
            return;
        }
        if(XML.TTC.equalsTo(currentElement)){
            ttc = new TTC();
            return;
        }
        if(XML.DISTANCE.equalsTo(currentElement)){
            distance = new Distance();
            return;
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String elementText = new String(ch,start,length).trim();
        if(elementText.isEmpty()) return;

        if(XML.MODEL.equalsTo(currentElement)) {
            gun.setModel(elementText);
            return;
        }
        if(XML.HANDY.equalsTo(currentElement)){
            gun.setHandy(Handy.fromValue(elementText));
            return;
        }
        if(XML.ORIGIN.equalsTo(currentElement)){
            gun.setOrigin(elementText);
            return;
        }
        if(XML.MATERIAL.equalsTo(currentElement)){
            gun.setMaterial(elementText);
            return;
        }
        if(XML.SHORT.equalsTo(currentElement)){
            distance.setType(XML.SHORT.value());
            distance.setDistance(Integer.parseInt(elementText));
            return;
        }
        if(XML.MIDDLE.equalsTo(currentElement)){
            distance.setType(XML.MIDDLE.value());
            distance.setDistance(Integer.parseInt(elementText));
            return;
        }
        if(XML.LONG.equalsTo(currentElement)){
            distance.setType(XML.LONG.value());
            distance.setDistance(Integer.parseInt(elementText));
            return;
        }
        if(XML.SIGHTING_RANGE.equalsTo(currentElement)){
            ttc.setSightingRange(Integer.parseInt(elementText));
            return;
        }
        if(XML.COLLAR.equalsTo(currentElement)){
            ttc.setCollar(Boolean.parseBoolean(elementText));
            return;
        }
        if(XML.OPTICS.equalsTo(currentElement)){
            ttc.setOptics(Boolean.parseBoolean(elementText));
            return;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(XML.GUN.equalsTo(localName)){
            guns.getGuns().add(gun);
            return;
        }
        if(XML.TTC.equalsTo(localName)){
            gun.setTTC(ttc);
            return;
        }
        if(XML.DISTANCE.equalsTo(localName)){
            ttc.setDistance(distance);
            return;
        }
    }
}
