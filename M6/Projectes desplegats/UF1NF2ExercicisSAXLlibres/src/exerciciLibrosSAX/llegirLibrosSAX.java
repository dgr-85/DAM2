package exerciciLibrosSAX;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class llegirLibrosSAX {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		File fitxer= new File ("LibrosXML.xml");
		
		//Instanciem un nou parser SAX
		SAXParserFactory factory =SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		//Instanciem un nou handler de la classe que hem fet extend per tractar el nostre document
		HandlerSAXLibros hl= new HandlerSAXLibros();
		//Indiquem que tracti el document: hem de dir-li quin document (fitxer) i amb quin handler (hl)
		parser.parse(fitxer, hl);
		//Podem tenir diver-sos handlers per tractar el document de formes diferents.
		//Aquest seria un handler genèric que imprimiria tot el que va rebent als diferents events
		/*HandlerSAXGeneric hg= new HandlerSAXGeneric();
		parser.parse(fitxer, hg);*/	

	}

}
