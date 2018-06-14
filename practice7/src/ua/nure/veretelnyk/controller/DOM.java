package ua.nure.veretelnyk.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.veretelnyk.constant.Constants;
import ua.nure.veretelnyk.constant.XML;
import ua.nure.veretelnyk.entity.*;

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
    private Guns guns;

    public DOM(String xmlFileName) { this.xmlFileName = xmlFileName; }

    public Guns getGuns() { return guns; }

    public void parse(boolean validate) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setNamespaceAware(true);

        if(validate){
            dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        DocumentBuilder db = dbf.newDocumentBuilder();
        db.setErrorHandler(new DefaultHandler(){
            @Override
            public void error(SAXParseException e) throws SAXException {
                throw e;
            }
        });

        Document doc = db.parse(xmlFileName);
        Element root = doc.getDocumentElement();
        guns = new Guns();
        NodeList gunsNodes = root.getElementsByTagName(XML.GUN.value());
        for(int i=0; i<gunsNodes.getLength(); ++i){
            Gun gun = getGun(gunsNodes.item(i));
            guns.getGuns().add(gun);
        }

    }

    private Gun getGun(Node gunNode) {
        Gun gun = new Gun();
        Element gElement = (Element) gunNode;
        //setting up model
        Node modelNode = gElement.getElementsByTagName(XML.MODEL.value()).item(0);
        gun.setModel(modelNode.getTextContent());
        //Handy
        Node handyNode = gElement.getElementsByTagName(XML.HANDY.value()).item(0);
        gun.setHandy(Handy.fromValue(handyNode.getTextContent()));
        //Origin
        Node originNode = gElement.getElementsByTagName(XML.ORIGIN.value()).item(0);
        gun.setOrigin(originNode.getTextContent());
        //Material
        Node materialNode = gElement.getElementsByTagName(XML.MATERIAL.value()).item(0);
        gun.setMaterial(materialNode.getTextContent());
        // TTC
        Node ttcNode = gElement.getElementsByTagName(XML.TTC.value()).item(0);
        gun.setTTC(getTTC(ttcNode));

        return gun;
    }

    private TTC getTTC(Node ttcNode){
        TTC ttc = new TTC();
        Element tElement = (Element) ttcNode;

        Element distanceNode = (Element) tElement.getElementsByTagName(XML.DISTANCE.value()).item(0);
        NodeList distanceChildNodes = distanceNode.getChildNodes();
        for(int i=0; i<distanceChildNodes.getLength(); ++i){
            if(!distanceChildNodes.item(i).getTextContent().trim().isEmpty()) {
                ttc.setDistance( new Distance
                        (distanceChildNodes.item(i).getNodeName(), Integer.parseInt(distanceChildNodes.item(i).getTextContent())));
                break;
            }
        }

        //Sighting Range
        Node rangeNode = tElement.getElementsByTagName(XML.SIGHTING_RANGE.value()).item(0);
        ttc.setSightingRange(Integer.parseInt(rangeNode.getTextContent()));
        //Collar
        Node collarNode = tElement.getElementsByTagName(XML.COLLAR.value()).item(0);
        ttc.setCollar(Boolean.parseBoolean(collarNode.getTextContent()));
        //Optics
        Node opticsNode = tElement.getElementsByTagName(XML.OPTICS.value()).item(0);
        ttc.setOptics(Boolean.parseBoolean(opticsNode.getTextContent()));

        return ttc;
    }



    public static Document getDocument(Guns guns) throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        Element element = document.createElement(XML.GUNS.value());
        document.appendChild(element);

        for(Gun gun : guns.getGuns()){
            Element gElement = document.createElement(XML.GUN.value());
            element.appendChild(gElement);
            //Model
            Element modelElement = document.createElement(XML.MODEL.value());
            modelElement.setTextContent(gun.getModel());
            gElement.appendChild(modelElement);
            //Handy
            Element handyElement = document.createElement(XML.HANDY.value());
            handyElement.setTextContent(gun.getHandy().value());
            gElement.appendChild(handyElement);
            //Origin
            Element originElement = document.createElement(XML.ORIGIN.value());
            originElement.setTextContent(gun.getOrigin());
            gElement.appendChild(originElement);
            //Material
            Element materialElement = document.createElement(XML.MATERIAL.value());
            materialElement.setTextContent(gun.getMaterial());
            gElement.appendChild(materialElement);

            // TTC
            Element ttcElement = document.createElement(XML.TTC.value());
            gElement.appendChild(ttcElement);
            //Distance
            Element distanceElement = document.createElement(XML.DISTANCE.value());
            Element distanceChildElement = document.createElement(gun.getTTC().getDistance().getType());
            distanceChildElement.setTextContent(String.valueOf(gun.getTTC().getDistance().getDistance()));
            distanceElement.appendChild(distanceChildElement);
            ttcElement.appendChild(distanceElement);
            //Sighting Range
            Element rangeElement = document.createElement(XML.SIGHTING_RANGE.value());
            rangeElement.setTextContent(String.valueOf(gun.getTTC().getSightingRange()));
            ttcElement.appendChild(rangeElement);
            //Collar
            Element collarElement = document.createElement(XML.COLLAR.value());
            collarElement.setTextContent(String.valueOf(gun.getTTC().isCollar()));
            ttcElement.appendChild(collarElement);
            //Optics
            Element opticsElement = document.createElement(XML.OPTICS.value());
            opticsElement.setTextContent(String.valueOf(gun.getTTC().isOptics()));
            ttcElement.appendChild(opticsElement);
        }
        return document;
    }


    public static void saveToXML(Guns guns, String xmlFileName) throws ParserConfigurationException, TransformerException {
        saveToXML(getDocument(guns), xmlFileName);
    }

    public static void saveToXML(Document document, String xmlFileName) throws TransformerException {
        StreamResult result = new StreamResult(new File(xmlFileName));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        transformer.transform(new DOMSource(document), result);
    }










}

// Dawn Of Destiny - Angel Without wings
// Blameshift - War Within
// Loser - Down