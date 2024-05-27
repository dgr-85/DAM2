package exerciciLibros;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParserDOM {
	

	Document doc;
	File fichero;

	public ParserDOM(File fichero) {
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

	public void recorreryMostrarDOM(){
		Node node;

		//Obtiene el primero nodo del DOM
		Node raiz=doc.getFirstChild();

		//Obtiene una lista de nodos con todos los nodos hijo.
		NodeList nodelist=raiz.getChildNodes();

		//Proceso los nodos hijo
		for (int i=0; i<nodelist.getLength(); i++) //Proceso los nodos hijo
		{ 
			node = nodelist.item(i);
			//Al ejecutar paso a paso este código, se observa como los nodos que
			// encuentra son
			// de tipo 1 (ELEMENT_NODE) y tipo 3 (TEXT_NODE). Esto es porque en DOM 
			//todo elemento tiene un nodo
			//hijo TEXT aunque esté en blanco.

			//Es un nodo libro que hay que procesar si es de tipo Elemento
			if(node.getNodeType()==Node.ELEMENT_NODE){ 			
				System.out.println(procesarLibro(node).toString());
			}
		}
	}
	
	public void recercaByTag(String tag, String valor){
		Node node;

		//Obté la llista de nodes corresponents a l'etiqueta que volem cercar
		NodeList llistaTag=doc.getElementsByTagName(tag);

		//Cerquem entre els nodes aquell que el valor coincideix amb el que cerquem
		for (int i=0; i<llistaTag.getLength(); i++) //Proceso los nodos hijo
		{ 
			node = llistaTag.item(i);
			if((node.getTextContent()).equals(valor)){
				//Com que la funció processarLibro necessita un node tipus Libro
				//quan hem trobat el node Titol o Autor que volem l'hem de cridar
				//amb el pare (pujem un nivell)
				System.out.println(procesarLibro(node.getParentNode()).toString());
			}
		}
	}
	
	public void modificarTitol(String titol, String nouTitol){
		Node node;

		//Obté la llista de nodes corresponents a l'etiqueta que volem cercar
		NodeList llistaTag=doc.getElementsByTagName("Titulo");

		//Cerquem entre els nodes aquell que el valor coincideix amb el que cerquem
		for (int i=0; i<llistaTag.getLength(); i++) 
		{ 
			node = llistaTag.item(i);
			if((node.getTextContent()).equals(titol)){
				node.setTextContent(nouTitol);
				/*
				 * Alternativa: baixem al node text
				 * node.getChildNodes().item(0).setNodeValue(nouTitol);
				 */
				
				System.out.println(procesarLibro(node.getParentNode()).toString());
			}
		}
	}
	
	public void afegirNode(Libro nouLlibre){

		//Creem node Libro
		Element l1 = doc.createElement("Libro");
		//Posem l'atribut al node Libro
		l1.setAttribute("publicado_en", String.valueOf(nouLlibre.getAnyPublicacio()));
		//Creem node Titulo
		Element t1 = doc.createElement("Titulo");
		//Posem el contingut al node Titulo
		t1.setTextContent(nouLlibre.getTitol());
		//Creem node Autor
		Element a1 = doc.createElement("Autor");
		//Posem el contingut al node Autor
		a1.setTextContent(nouLlibre.getAutor());
		//Ara els hem d'enganxar: el node Titulo i el node Autor s'enganxen al node Libro
		l1.appendChild(t1);
		l1.appendChild(a1);
		//System.out.println(procesarLibro(l1).toString());
		//El node Libro s'ha d'enganxar al node Libros
		doc.getFirstChild().appendChild(l1);

	}
	
	protected Libro procesarLibro(Node n){
		Node ntemp=null;
		Libro llibre = new Libro();

		//De la lista de atributos que tiene el nodo selecciona el primero 
		//(en nuestro ejemplo solo hay un atributo)
		llibre.setAnyPublicacio(Integer.valueOf(n.getAttributes().item(0).getNodeValue()));

		//Obtiene los hijos del Libro (titulo y autor)
		NodeList nodos=n.getChildNodes();

		for (int i=0; i<nodos.getLength(); i++)
		{
			ntemp = nodos.item(i);
			//Se debe comprobar el tipo de nodo que se quiere tratar porque es
			// posible que haya nodos tipo TEXT que contengan retornos de carro al cambiar de l�nea 
			//en el XML.
			//En este ejemplo, cuando detecta un nodo que no es tipo ELEMENT_NODE 
			//es porque es tipo TEXT y contiene los retornos de carro "\n" que se incluyen en el fichero 
			//de texto al crear el XML.
			if(ntemp.getNodeType()==Node.ELEMENT_NODE){
				//IMPORTANTE: para obtener el texto con el título accede al nodo 
				//TEXT hijo de ntemp y saca su valor.
				if(ntemp.getNodeName()=="Titulo"){
					llibre.setTitol(ntemp.getChildNodes().item(0).getNodeValue());
				}else{
					llibre.setAutor(ntemp.getChildNodes().item(0).getNodeValue());
				}
			}            
		}
		return llibre;
	}
	
	public int guardarDOMaFileTransformer(File destino)
    {
        try{
                
        //Obtenemos un transformer               
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "10");
        
        //Indicamos la fuente a transformar
        Source source = new DOMSource(doc);
        
        //Indicamos el destino
        StreamResult result = new StreamResult(destino);
        
              
        transformer.transform(source,result);

        return 0;
         }catch(Exception e) {
           
           return -1;
        }
    }

	public void recorrerByTag(){

		//Obtiene una lista de nodos con todos los nodos hijo.
		NodeList llistaLibro=doc.getElementsByTagName("Libro");

		//Proceso los nodos hijo
		for (int i=0; i<llistaLibro.getLength(); i++) 
		{ 
				Node n = llistaLibro.item(i);
				Libro l = new Libro();
				Integer anyPublicacio = Integer.parseInt(n.getAttributes().item(0).getNodeValue());
				String titol = ((Element) n).getElementsByTagName("Titulo").item(0).getTextContent();
				String autor = ((Element) n).getElementsByTagName("Autor").item(0).getTextContent();
				l.setAnyPublicacio(anyPublicacio);
				l.setAutor(autor);
				l.setTitol(titol);
				System.out.println(l.toString());
			
		}
	}

}
