package ua.nure.veretelnyk.practice7.controller;

import ua.nure.veretelnyk.practice7.constant.XML;
import ua.nure.veretelnyk.practice7.entity.*;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

public class STAX {
    private String xmlFileName;
    private Guns guns = new Guns();

    public Guns getGuns() { return guns; }

    public STAX(String xmlFileName) { this.xmlFileName = xmlFileName; }

    public void parse() throws XMLStreamException {
        Gun gun = null;
        TTC ttc = null;
        Distance distance = null;

        String currentElement = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
        XMLEventReader reader = factory.createXMLEventReader(new StreamSource(xmlFileName));

        while (reader.hasNext()){
            XMLEvent event = reader.nextEvent();
            if(event.isCharacters() && event.asCharacters().isWhiteSpace())
                continue;

            if(event.isStartElement()){
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();

                if(XML.GUN.equalsTo(currentElement)){
                    gun = new Gun();
                    continue;
                }
                if(XML.TTC.equalsTo(currentElement)){
                    ttc = new TTC();
                    continue;
                }
                if(XML.DISTANCE.equalsTo(currentElement)){
                    distance = new Distance();
                    continue;
                }
            }

            if(event.isCharacters()){
                Characters characters = event.asCharacters();

                if(XML.MODEL.equalsTo(currentElement)){
                    gun.setModel(characters.getData());
                    continue;
                }
                if(XML.HANDY.equalsTo(currentElement)){
                    gun.setHandy(Handy.fromValue(characters.getData()));
                    continue;
                }
                if(XML.ORIGIN.equalsTo(currentElement)){
                    gun.setOrigin(characters.getData());
                    continue;
                }
                if(XML.MATERIAL.equalsTo(currentElement)){
                    gun.setMaterial(characters.getData());
                    continue;
                }
                if(XML.SHORT.equalsTo(currentElement) ||
                        XML.MIDDLE.equalsTo(currentElement) ||
                        XML.LONG.equalsTo(currentElement)){
                    distance.setType(currentElement);
                    distance.setDistance(Integer.parseInt(characters.getData()));
                    continue;
                }
                if (XML.SIGHTING_RANGE.equalsTo(currentElement)){
                    ttc.setSightingRange(Integer.parseInt(characters.getData()));
                    continue;
                }
                if(XML.COLLAR.equalsTo(currentElement)){
                    ttc.setCollar(Boolean.parseBoolean(characters.getData()));
                    continue;
                }
                if(XML.OPTICS.equalsTo(currentElement)){
                    ttc.setOptics(Boolean.parseBoolean(characters.getData()));
                    continue;
                }
            }

            if(event.isEndElement()){
                EndElement endElement = event.asEndElement();
                String localName = endElement.getName().getLocalPart();

                if(XML.GUN.equalsTo(localName)){
                    guns.getGuns().add(gun);
                    continue;
                }
                if(XML.TTC.equalsTo(localName)){
                    gun.setTTC(ttc);
                    continue;
                }
                if(XML.DISTANCE.equalsTo(localName)){
                    ttc.setDistance(distance);
                    continue;
                }
            }
        }
        reader.close();
    }
}
