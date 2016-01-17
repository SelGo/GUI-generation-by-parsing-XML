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
    private static String setTitle;
    private static String title;
    private static String setDefaultCloseOperation;
    private static String add;

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
            System.out.println(query6);

            Class rootFrame = Class.forName(query1);
            Object rootFrameObject = rootFrame.newInstance();
            Method setTitle = rootFrame.getMethod(query2, String.class);
            Method setVisible = rootFrame.getMethod("setVisible", Boolean.TYPE);
            Method setDefaultCloseOperation = rootFrame.getMethod(query5, Integer.TYPE);
            Method add = rootFrame.getMethod(query6, Component.class);

            setTitle.invoke(rootFrameObject, query4);
            setDefaultCloseOperation.invoke(rootFrameObject, JFrame.DISPOSE_ON_CLOSE);
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