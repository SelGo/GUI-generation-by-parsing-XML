package com.selgo;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
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
    private static String jPanel;
    private static String jButton;
    private static String setTitle;
    private static String title;
    private static String setDefaultCloseOperation;
    private static String add;
    private static String setBackgroundColor;
    private static String setText;
    private static String press3;

    public static void main(String[] args) {

        try {
            FileInputStream ui = new FileInputStream("ui.xml");
            FileInputStream abstractContainer = new FileInputStream("AbstractContainer.widget.xml");
            FileInputStream abstractButton = new FileInputStream("AbstractButton.widget.xml");

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilderFactory builderFactory2 = DocumentBuilderFactory.newInstance();
            DocumentBuilderFactory builderFactory3 = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =  builderFactory.newDocumentBuilder();
            DocumentBuilder builder2 = builderFactory2.newDocumentBuilder();
            DocumentBuilder builder3 = builderFactory3.newDocumentBuilder();

            Document xmlDocument = builder.parse(abstractContainer);
            Document xmlDocument2 = builder2.parse(ui);
            Document xmlDocument3 = builder3.parse(abstractButton);
 
            XPath xPath =  XPathFactory.newInstance().newXPath();
            XPath xPath2 = XPathFactory.newInstance().newXPath();
            XPath xPath3 = XPathFactory.newInstance().newXPath();

            jFrame = "/widget/instances/instance/instance_api/class";
            Node node = (Node) xPath.compile(jFrame).evaluate(xmlDocument, XPathConstants.NODE);
            String query1 = node.getAttributes().getNamedItem("path").getNodeValue(); //javax.swing.JFrame

            setTitle = "/widget/instances/instance/instance_api/class/method";
            Node node2 = (Node) xPath.compile(setTitle).evaluate(xmlDocument, XPathConstants.NODE);
            String query2 = node2.getAttributes().getNamedItem("name").getNodeValue(); //setTitle

            title = "/uiModel/widgetResourceModel/widgetResource/instance/property";
            Node node4 = (Node) xPath2.compile(title).evaluate(xmlDocument2, XPathConstants.NODE);
            String query4 = node4.getAttributes().getNamedItem("value").getNodeValue(); // Frame Title

            setDefaultCloseOperation = "/widget/instances/instance/instance_api/class/method[@id='inst_1_cl_1_method_3']";
            Node node5 = (Node) xPath.compile(setDefaultCloseOperation).evaluate(xmlDocument, XPathConstants.NODE);
            String query5 = node5.getAttributes().getNamedItem("name").getNodeValue(); //setDefaultCloseOperation

            add = "widget/instances/instance/instance_api/class/method[@id='inst_1_cl_2_method_1']";
            Node node6 = (Node) xPath.compile(add).evaluate(xmlDocument, XPathConstants.NODE);
            String query6 = node6.getAttributes().getNamedItem("name").getNodeValue(); // add

            jPanel = "widget/instances/instance/instance_api/class[@id='inst_2_cl_1']";
            Node node7 = (Node) xPath.compile(jPanel).evaluate(xmlDocument, XPathConstants.NODE);
            String query7 = node7.getAttributes().getNamedItem("path").getNodeValue(); // JPanel

            setBackgroundColor = "widget/instances/instance/instance_api/class/method[@id='inst_2_cl_1_method_3']";
            Node node8 = (Node) xPath.compile(setBackgroundColor).evaluate(xmlDocument, XPathConstants.NODE);
            String query8 = node8.getAttributes().getNamedItem("name").getNodeValue(); // setBackground

            jButton = "widget/instances/instance/instance_api/class[@id='inst_1_cl_1']";
            Node node9 = (Node) xPath3.compile(jButton).evaluate(xmlDocument3, XPathConstants.NODE);
            String query9 = node9.getAttributes().getNamedItem("path").getNodeValue(); // javax.swing.JButton

            setText = "widget/instances/instance/instance_api/class/method[@id='inst_1_cl_1_method_1']";
            Node node10 = (Node) xPath3.compile(setText).evaluate(xmlDocument3, XPathConstants.NODE);
            String query10 = node10.getAttributes().getNamedItem("name").getNodeValue(); // setText


            press3 = "uiModel/widgetResourceModel/widgetResource/property";
            Node node11 = (Node) xPath2.compile(press3).evaluate(xmlDocument2, XPathConstants.NODE);
            String query11 = node11.getAttributes().getNamedItem("value").getNodeValue();
            System.out.println(query10);

            Class rootFrame = Class.forName(query1);
            Class parentJpanel = Class.forName(query7);
            Class childJpanel = Class.forName(query7);
            Class press1 = Class.forName(query9);

            Object rootFrameObject = rootFrame.newInstance();
            Object parentJpanelObject = parentJpanel.newInstance();
            Object childJpanelObject = childJpanel.newInstance();
            Object press1Object = press1.newInstance();

            Method setTitle = rootFrame.getMethod(query2, String.class);
            Method setVisible = rootFrame.getMethod("setVisible", Boolean.TYPE);
            Method setDefaultCloseOperation = rootFrame.getMethod(query5, Integer.TYPE);
            Method add = rootFrame.getMethod(query6, Component.class);
            Method setBackground = parentJpanel.getMethod(query8, Color.class);
            Method setText = press1.getMethod(query10, String.class);

            setTitle.invoke(rootFrameObject, query4);
            setDefaultCloseOperation.invoke(rootFrameObject, JFrame.DISPOSE_ON_CLOSE);
            setBackground.invoke(parentJpanelObject, Color.BLUE); // just to make sure the panel is added
            setBackground.invoke(childJpanelObject, Color.red);
            setText.invoke(press1Object, query11);
            add.invoke(childJpanelObject, press1Object);
            add.invoke(rootFrameObject, parentJpanelObject);
            add.invoke(parentJpanelObject, childJpanelObject);
            setVisible.invoke(rootFrameObject, true);

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}