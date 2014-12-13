package logic;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Validador del documento XML, provee acceso a dicho documento
 * @author Jose María Pérez Maroto
 *
 */
public class XMLValidator {
	/**
	 * Método que comprueba que el xml está bien formado
	 * @param documento Ruta del xml que queremos comprobar
	 * @return Document
	 */
	public Document XMLBienFormado(String documento) {
		Document doc = null;
		try {
			DocumentBuilderFactory factoria = DocumentBuilderFactory
					.newInstance();
			factoria.setValidating(false);
			factoria.setNamespaceAware(true);

			DocumentBuilder builder = factoria.newDocumentBuilder();
			builder.setErrorHandler(new SimpleErrorHandler());

			doc = builder.parse(new InputSource(documento));
			System.out.println("El xml está bien formado");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
	/**
	 * Método que comprueba que el xml valida contra el DTD
	 * @param documento Ruta del xml que queremos comprobar
	 */
	public void XmlDtd(String documento) {
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		factoria.setValidating(true);
		factoria.setNamespaceAware(true);

		DocumentBuilder builder = null;
		try {
			builder = factoria.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		builder.setErrorHandler(new SimpleErrorHandler());

		try {
			Document document = builder.parse(new InputSource(documento));
			System.out.println("El archivo a validado correctamente frente al DTD");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("El archivo no a validado frente al DTD");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("No se encuentra el archivo");
		}
	}

	/**
	 * Método que comprueba que el xml valida contra el XSD
	 * @param documento Ruta del xml que queremos comprobar
	 */
	public void XmlSchema(String documento){
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		factoria.setValidating(true);
		factoria.setNamespaceAware(true);
		factoria.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", 
		      "http://www.w3.org/2001/XMLSchema");

		DocumentBuilder builder = null;
		try {
			builder = factoria.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		builder.setErrorHandler(new SimpleErrorHandler());

		try {
			Document document = builder.parse(new InputSource(documento));
			System.out.println("El documento ha validado correctamente frente al Schema");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("El documento no ha validado correctamente frente al Schema");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Documento no encontrado");
		}
	}
}
