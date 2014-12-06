package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLAddons {
	public static NodeList executeXPathExpression(String expression, InputSource source) {
		NodeList nodes = null;
		try {
			nodes = (NodeList) XPathFactory.newInstance().newXPath().evaluate(expression, source, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nodes;
	}
	
	public static void executeXQueryExpresion(String expression, InputSource source) {
		FileInputStream query = null;
		try {
			query = new FileInputStream(expression);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Transformer transformXMLWithXSLT(String xlstpath, InputSource document) {
		File stylesheet = null;
		if(xlstpath != ""){
			stylesheet = new File(xlstpath);
		}
		Transformer transformer = null;
		StreamSource stylesource = new StreamSource(stylesheet);
		try {
			transformer = TransformerFactory.newInstance().newTransformer(stylesource);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transformer;
	}
}
