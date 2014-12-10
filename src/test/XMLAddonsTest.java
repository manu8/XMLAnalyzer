package test;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import logic.XMLValidator;
import logic.XMLAddons;

public class XMLAddonsTest {

	public static void main(String[] args) {
		Document doc = new XMLValidator().XMLBienFormado("assets/samples/document.xml");
		NodeList nodes = XMLAddons.executeXPathExpression("//firstname", doc);
		for(int i = 0; i < nodes.getLength(); i++){
			System.out.println(nodes.item(i).getTextContent());
		}
		
		XMLAddons.transformXMLWithXSLT("assets/samples/Stylesheet.xsl", "src/test/Generated Files/generated.html", doc);
	}

}
