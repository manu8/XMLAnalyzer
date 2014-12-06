package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLAddons {
	public static NodeList executeXPathExpression(String expression, Document document) {
		NodeList nodes = null;
		try {
			nodes = (NodeList) XPathFactory.newInstance().newXPath().evaluate(expression, document, XPathConstants.NODESET);
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
	
	public static void transformXMLWithXSLT(String xlstpath, String outpath, Document document) {
		File stylesheet = null;
		if(xlstpath != ""){
			stylesheet = new File(xlstpath);
		}
		StreamSource stylesource = new StreamSource(stylesheet);
		Source source = new DOMSource(document);
		Result out = new StreamResult(new File(outpath));
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
			try {
				transformer.transform(source, out);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
