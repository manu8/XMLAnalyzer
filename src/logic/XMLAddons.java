package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
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
import javax.xml.xquery.XQConstants;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQSequence;

import net.sf.saxon.xqj.SaxonXQDataSource;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Añade las funcionalidades de:
 * - Ejecutar expresiones XPath
 * - Transformar el documento XML usando una hoja de transformación XSLT
 * @author Manuel Berrio Martín
 */
public class XMLAddons {
	/**
	 * Ejecuta una expresión XPath dentro del contexto de un documento XML
	 * @param expression - Expresión XPath
	 * @param document - Documento XML
	 * @return NodeList con los nodos resultantes de ejecutar la expresión
	 */
	public static NodeList executeXPathExpression(String expression, Document document) {
		NodeList nodes = null; //Lista de nodos resultante de ejecutar la expresión XPath
		try {
			//Realiza la búsqueda de nodos dada la expresión XPath
			nodes = (NodeList) XPathFactory.newInstance().newXPath().evaluate(expression, document, XPathConstants.NODESET); 
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nodes;
	}
	
	public static void executeXQueryExpresion(String expression, Document document) {
		XQDataSource ds = new SaxonXQDataSource();
		try {
			XQConnection conn = ds.getConnection();
			XQPreparedExpression expr = conn.prepareExpression(expression);
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader streamReader = factory.createXMLStreamReader(new FileReader((File) document));
			expr.bindDocument(XQConstants.CONTEXT_ITEM, streamReader, conn.createDocumentType());
			XQSequence rs = expr.executeQuery();
			System.out.println(rs.getSequenceAsString(null));
			rs.close();
			conn.close();
			expr.close();
			conn.close();
		} catch (XQException | FileNotFoundException | XMLStreamException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Transforma un documento XML en un archivo generado mediante una hoja de transformación XSLT
	 * @param xlstpath - Ruta de la hoja de transformación XSLT
	 * @param outpath - Ruta donde generar el fichero resultante (incluido el propio fichero)
	 * @param document - Documento XML
	 * @return Objeto File del documento generado
	 */
	public static File transformXMLWithXSLT(String xlstpath, String outpath, Document document) {
		File stylesheet = null; //Hoja de transformación XSLT
		Result out = null; //Resultado de la transformación
		if(xlstpath != ""){
			stylesheet = new File(xlstpath);
		}
		StreamSource stylesource = new StreamSource(stylesheet); //Stream del XSLT
		Source source = new DOMSource(document); //Fichero origen a transformar
		out = new StreamResult(new File(outpath)); //Stream del fichero resultante
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource); //Transformador de XSLT
			try {
				transformer.transform(source, out); //Transformación del documento XML
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
		return new File(outpath);
	}
}
