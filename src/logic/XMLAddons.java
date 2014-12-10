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
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQSequence;

import net.sf.saxon.xqj.SaxonXQDataSource;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLAddons {
	/**
	 * Ejecuta una expresión XPath dentro del contexto de un documento XML
	 * @param expression - Expresión XPath
	 * @param document - Documento XML
	 * @return NodeList con los nodos resultantes de ejecutar la expresión
	 */
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
	
	public static void executeXQueryExpresion(String expression) {
		XQDataSource ds = new SaxonXQDataSource();
		try {
			XQConnection conn = ds.getConnection();
			XQPreparedExpression expr = conn.prepareExpression(expression);
			XQSequence rs = expr.executeQuery();
			System.out.println(rs.getSequenceAsString(null));
			rs.close();
			conn.close();
			expr.close();
			conn.close();
		} catch (XQException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Transforma un documento XML en un archivo generado mediante una hoja de transformación XSLT
	 * @param xlstpath - Ruta de la hoja de transformación XSLT
	 * @param outpath - Ruta donde generar el fichero resultante (incluido el propio fichero)
	 * @param document - Documento XML
	 */
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
