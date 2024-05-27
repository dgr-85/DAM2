package exerciciLibrosSAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerSAXGeneric extends DefaultHandler {
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String cadena = new String(ch,start,length);
		System.out.println("\t Caracters: "+ cadena);
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("Final Document XML");
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("Final Element: "+ qName);
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Inici Document XML");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		System.out.println("Inici Element: "+ qName);
		//Recorrem els atributs si n'hi ha
		for (int i=0; i<attributes.getLength();i++){
			System.out.println(("\t Atribut "+ i+" "+attributes.getQName(i)+" "+ attributes.getValue(i)));
		}
		
	}
}
