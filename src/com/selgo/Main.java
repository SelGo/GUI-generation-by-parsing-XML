package com.selgo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
 
public class Main {
    private static String jFrame;
    private static String setTitle;
    private static String mappingPropery;
    private static String title;

    public static void main(String[] args) {

        try {
            FileInputStream ui = new FileInputStream("ui.xml");
            FileInputStream abstractContainer = new FileInputStream("AbstractContainer.widget.xml");

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilderFactory builderFactory2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder =  builderFactory.newDocumentBuilder();
            DocumentBuilder builder2 = builderFactory2.newDocumentBuilder();
            Document xmlDocument = builder.parse(abstractContainer);
            Document xmlDocument2 = builder2.parse(ui);
 
            XPath xPath =  XPathFactory.newInstance().newXPath();
            XPath xPath2 = XPathFactory.newInstance().newXPath();

            jFrame = "/widget/instances/instance/instance_api/class";
            Node node = (Node) xPath.compile(jFrame).evaluate(xmlDocument, XPathConstants.NODE);
            String query1 = node.getAttributes().getNamedItem("path").getNodeValue(); //javax.swing.JFrame
            System.out.println(query1);

            setTitle = "/widget/instances/instance/instance_api/class/method";
            Node node2 = (Node) xPath.compile(setTitle).evaluate(xmlDocument, XPathConstants.NODE);
            String query2 = node2.getAttributes().getNamedItem("name").getNodeValue(); //setTitle
            System.out.println(query2);

            mappingPropery = "/widget/instances/instance/mappings/mapping/mappingProperty[@id='inst_1_prop_1']";
            Node node3 = (Node) xPath.compile(mappingPropery).evaluate(xmlDocument, XPathConstants.NODE);
            String query3 = node3.getAttributes().getNamedItem("id").getNodeValue(); //inst_1_prop_1
            System.out.println(query3);

            title = "/uiModel/widgetResourceModel/widgetResource/instance/property@id=''";
            Node node4 = (Node) xPath2.compile(title).evaluate(xmlDocument2, XPathConstants.NODE);
            Element n = (Element) node4;
            String query4 = n.getAttribute("name");
            System.out.println(query4);

            Class rootFrame = Class.forName(query1);
            Object rootFrameObject = rootFrame.newInstance();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException /*| NoSuchMethodException*/ | SecurityException | IllegalArgumentException /*| InvocationTargetException*/ ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}