package parserGeneric;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static org.w3c.dom.Node.*;

public class ParserDOMGeneric {


	Document doc;
	File fichero;

	public ParserDOMGeneric(File fichero) {
		super();
		this.fichero = fichero;
	}

	public boolean generarDOM(){

		DocumentBuilder builder = null;

		//Se crea un objeto DocumentBuiderFactory.
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();

		//Indica que el modelo DOM no debe contemplar los comentarios que tenga el 
		//XML.
		factory.setIgnoringComments(true);

		//Ignora los espacios en blanco.
		//Si no se hace esto entonces los espacios en blanco aparecen como nodos.
		factory.setIgnoringElementContentWhitespace(true);

		try {

			//Se crea un objeto DocumentBuilder 
			//para cargarla estructura de árbol DOM a partir del XML 
			//seleccionado.
			builder = factory.newDocumentBuilder();
			//Interpreta (parsea) el documento XML (file) y genera el DOM 
			//equivalente.
			//doc apunta al árbol DOM listo para ser recorrido.
			this.doc=builder.parse(fichero);
			return true;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		} 
	}

	public void recorreryMostrarDOMGeneric(){
		//Obtiene el primero nodo del DOM
		Node raiz=doc.getFirstChild();
		listNodes(raiz);

	}

	protected void listNodes(Node node) {
		String nodeName = node.getNodeName();
		System.out.println(" Node: "+nodeName);
		short type =node.getNodeType();
		System.out.println(" Node Type: " + nodeType(type));
		if(type == TEXT_NODE){
			System.out.println(" Content is: "+node.getTextContent());
		} else if(node.hasAttributes()) {
			System.out.println(" Element Attributes are:");
			NamedNodeMap attrs = node.getAttributes();  
			for(int i = 0 ; i<attrs.getLength() ; i++) {
				Attr attribute = (Attr)attrs.item(i);     
				System.out.println(" " + attribute.getName()+" = "+attribute.getValue());
			}
		}

		NodeList list = node.getChildNodes();       
		if(list.getLength() > 0) {                  
			System.out.println("Child Nodes of "+nodeName+" are:");
			for(int i = 0 ; i<list.getLength() ; i++) {
				listNodes(list.item(i));      
			}
		}         
	}

	static String nodeType(short type) {
		switch(type) {
		case ELEMENT_NODE:                return "Element";
		case DOCUMENT_TYPE_NODE:          return "Document type";
		case ENTITY_NODE:                 return "Entity";
		case ENTITY_REFERENCE_NODE:       return "Entity reference";
		case NOTATION_NODE:               return "Notation";
		case TEXT_NODE:                   return "Text";
		case COMMENT_NODE:                return "Comment";
		case CDATA_SECTION_NODE:          return "CDATA Section";
		case ATTRIBUTE_NODE:              return "Attribute";
		case PROCESSING_INSTRUCTION_NODE: return "Attribute";
		}
		return "Unidentified";
	}

}
