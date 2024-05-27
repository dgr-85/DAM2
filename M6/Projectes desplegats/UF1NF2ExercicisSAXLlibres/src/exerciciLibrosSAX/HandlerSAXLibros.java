package exerciciLibrosSAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlerSAXLibros extends DefaultHandler {
	/*
	 * Per controlar per on passem creem la variable elementActual de la que considerarem els
	 * següents valors
	 * Node Libro --> elementActual = 1
	 * Node Titulo --> elementActual = 2
	 * Node Autor --> elementActual = 3
	 */
	int elementActual = 0;
	Libro llibreActual;
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
	/* Quan detectem un event characters només ens interessarà si arriba després d'haver
	 * passat per un node Titulo o Autor. Aquesta informació la tenim amb la variable elementActual
	 */
		String cadena = new String(ch,start,length);
		if(elementActual== 2){
			llibreActual.setTitol(cadena);
		}else if(elementActual==3){
			llibreActual.setAutor(cadena);
		}
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("Hem acabat de llegir el Document");
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		/* En aquest exemple, quan detectem el final d'un element llibre 
		 * imprimirem la informació del llibre que s'ha anat construïnt
		 * A més sempre tornem la variable de control al valor inicial
		 */
		if(qName.contains("Libro")){
			System.out.println(llibreActual.toString());
		}
		elementActual=0;
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Comencem a llegir el Document");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		/* Si comencem nodede tipus Libro hem d'agafar l'any */
		if(qName.equals("Libro")){
			llibreActual = new Libro();
			String anyP = attributes.getValue(attributes.getQName(0));	
			llibreActual.setAnyPublicacio(Integer.parseInt(anyP));
			//Actualitzem la variable de control per saber per on anem passant.
			elementActual = 1;
		}
		else if(qName.equals("Titulo")){
			//Si estem en el element titulo no hem de fer res però controlem que passem
			elementActual = 2;
		}
		else if(qName.equals("Autor")){
			//Si estem en el element autor no hem de fer res però controlem que passem
			elementActual = 3;
		}
	}

	
}
