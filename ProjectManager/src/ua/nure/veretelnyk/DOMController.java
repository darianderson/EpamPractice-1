package ua.nure.veretelnyk;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.veretelnyk.constants.Constants;
import ua.nure.veretelnyk.constants.XML;
import ua.nure.veretelnyk.entity.Customer;
import ua.nure.veretelnyk.entity.Project;
import ua.nure.veretelnyk.entity.Projects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DOMController {
    private String xmlFileName;
    private Projects projects;

    public DOMController(String xmlFileName) { this.xmlFileName = xmlFileName; }
    public Projects getProjects() { return projects; }

    public void parse(boolean validate) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        if(validate){
            dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON,true);
            dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }
        DocumentBuilder db = dbf.newDocumentBuilder();
        db.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(SAXParseException exception) throws SAXException {
                throw exception;
            }
        });

        Document document = db.parse(xmlFileName);
        Element root = document.getDocumentElement();
        projects = new Projects();
        NodeList projectsList = root.getElementsByTagName(XML.PROJECT.value());
        for(int i=0; i<projectsList.getLength(); ++i){
            Project project = getProject(projectsList.item(i));
            projects.getProjects().add(project);
        }
    }

    private Project getProject(Node qProject) {
        Project project = new Project();
        Element prElement = (Element) qProject;

        NodeList nameNode = prElement.getElementsByTagName(XML.PROJECT_NAME.value());
        project.setName(nameNode.item(0).getTextContent());

        NodeList price = prElement.getElementsByTagName(XML.PRICE.value());
        project.setPrice(Integer.parseInt(price.item(0).getTextContent()));

        NodeList description = prElement.getElementsByTagName(XML.DESCRIPTION.value());
        project.setDescription(description.item(0).getTextContent());

        NodeList customer = prElement.getElementsByTagName(XML.CUSTOMER.value());
        project.setCustomer(getCustomer(customer.item(0)));

        return project;
    }

    private Customer getCustomer(Node item) {
        Customer customer = new Customer();
        Element customerElement = (Element) item;

        NodeList id = customerElement.getElementsByTagName(XML.ID.value());
        customer.setId(id.item(0).getTextContent());

        NodeList name = customerElement.getElementsByTagName(XML.NAME.value());
        customer.setName(name.item(0).getTextContent());

        return customer;
    }
}
