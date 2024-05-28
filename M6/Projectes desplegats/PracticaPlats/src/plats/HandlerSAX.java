package plats;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerSAX extends DefaultHandler {

//	1- Plat
//	2- Descripcio
//	3- Tipus
//	4- Grup
//	5- Descripcio (grup)
//	6- Tipus (grup)

	int elementActual = 0;
	Plat platActual;
	List<Plat> plats = new ArrayList<>();

	public List<Plat> recollirPlats() {
		return plats;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String cadena = new String(ch, start, length);
		if (!cadena.isEmpty()) {
			switch (elementActual) {
			case 2 -> {
				platActual.setDescripcio(cadena);
				elementActual = 1;
			}
			case 3 -> {
				platActual.setTipus(cadena);
				elementActual = 1;
			}
			case 5 -> {
				platActual.setDescripcioGrup(cadena);
				elementActual = 4;
			}
			case 6 -> {
				platActual.setTipusGrup(cadena);
				elementActual = 4;
			}
			default -> {
			}
			}
		}

	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("Hem acabat de llegir el Document");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.contains("Plat")) {
			elementActual = 0;
			plats.add(platActual);
		}
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Comencem a llegir el Document");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {
		case "Plat" -> {
			platActual = new Plat();
			elementActual = 1;
		}
		case "Descripcio" -> {
			if (elementActual == 1) {
				platActual.setCodi(attributes.getValue("codi"));
				platActual.setPreu(Integer.parseInt(attributes.getValue("preu")));
				elementActual = 2;
			} else {
				elementActual = 5;
			}
		}
		case "Tipus" -> elementActual = elementActual == 1 ? 3 : 6;
		case "Grup" -> {
			platActual.setIdGrup(attributes.getValue("id"));
			elementActual = 4;
		}
		default -> {
		}
		}
	}
}
