package test;

import logic.XMLValidator;

public class XMLValidatorTest {
	public static void main(String[] args) {
		XMLValidator xml = new XMLValidator();
		xml.XMLBienFormado("assets/samples/document.xml");
		xml.XmlDtd("assets/samples/document.xml");
		xml.XmlSchema("assets/samples/document.xml");
	}
}
