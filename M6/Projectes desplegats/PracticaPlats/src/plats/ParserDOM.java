package plats;

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
	File fitxer;

	public ParserDOM(File fichero) {
		super();
		this.fitxer = fichero;
	}

	public boolean generarDOM() {
		DocumentBuilder builder = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);
		try {
			builder = factory.newDocumentBuilder();
			this.doc = builder.parse(fitxer);
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

	public void recorrerIMostrarDOM() {
		Node node;
		Node arrel = doc.getFirstChild();
		NodeList nodelist = arrel.getChildNodes();
		for (int i = 0; i < nodelist.getLength(); i++) {
			node = nodelist.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println(processarPlat(node).toString());
			}
		}
	}

	public void recercaByTag(String tag, String valor) {
		Node node;
		NodeList llistaTag = doc.getElementsByTagName(tag);
		for (int i = 0; i < llistaTag.getLength(); i++) {
			node = llistaTag.item(i);
			if ((node.getTextContent()).equals(valor)) {
				System.out.println(processarPlat(node.getParentNode()).toString());
			}
		}
	}

	public void modificarTitol(String titol, String nouTitol) {
		Node node;
		NodeList llistaTag = doc.getElementsByTagName("Titulo");
		for (int i = 0; i < llistaTag.getLength(); i++) {
			node = llistaTag.item(i);
			if ((node.getTextContent()).equals(titol)) {
				node.setTextContent(nouTitol);
				/*
				 * Alternativa: baixem al node text
				 * node.getChildNodes().item(0).setNodeValue(nouTitol);
				 */

				System.out.println(processarPlat(node.getParentNode()).toString());
			}
		}
	}

	public void afegirNode(Plat nouPlat) {
		Element plat = doc.createElement("Plat");
		plat.setAttribute("codi", nouPlat.getCodi());
		plat.setAttribute("preu", String.valueOf(nouPlat.getPreu()));
		plat.setTextContent(nouPlat.getDescripcio());
		Element tipus = doc.createElement("Tipus");
		tipus.setTextContent(nouPlat.getTipus());
		Element grup = doc.createElement("Grup");
		grup.setAttribute("id", nouPlat.getIdGrup());
		Element descripcioGrup = doc.createElement("Descripcio");
		descripcioGrup.setTextContent(nouPlat.getDescripcioGrup());
		Element tipusGrup = doc.createElement("Tipus");
		tipusGrup.setTextContent(nouPlat.getTipusGrup());
		grup.appendChild(descripcioGrup);
		grup.appendChild(tipusGrup);
		plat.appendChild(tipus);
		plat.appendChild(grup);
		doc.getFirstChild().appendChild(plat);

	}

	protected Plat processarPlat(Node n) {
		Node ntemp = null;
		Plat plat = new Plat();

		// De la lista de atributos que tiene el nodo selecciona el primero
		// (en nuestro ejemplo no hay atributos)

		// Obtiene los hijos del Libro (titulo y autor)
		NodeList nodos = n.getChildNodes();

		for (int i = 0; i < nodos.getLength(); i++) {
			ntemp = nodos.item(i);
			// Se debe comprobar el tipo de nodo que se quiere tratar porque es
			// posible que haya nodos tipo TEXT que contengan retornos de carro al cambiar
			// de l�nea
			// en el XML.
			// En este ejemplo, cuando detecta un nodo que no es tipo ELEMENT_NODE
			// es porque es tipo TEXT y contiene los retornos de carro "\n" que se incluyen
			// en el fichero
			// de texto al crear el XML.
			if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
				// IMPORTANTE: para obtener el texto con el título accede al nodo
				// TEXT hijo de ntemp y saca su valor.
				switch (ntemp.getNodeName()) {
				case "Descripcio":
					plat.setDescripcio(ntemp.getChildNodes().item(0).getNodeValue());
					plat.setCodi(ntemp.getAttributes().item(0).getNodeValue());
					break;
				case "Tipus":
					plat.setTipus(ntemp.getChildNodes().item(0).getNodeValue());
					break;
				case "Grup":
					plat.setGrup(ntemp.getChildNodes().item(0).getNodeValue());
					plat.setIdGrup(ntemp.getAttributes().item(0).getNodeValue());
					break;
				default:
					System.out.println("Algo no cuadra...");
				}
			}
		}
		return plat;
	}

	public int guardarDOMaFileTransformer(File destino) {
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "10");
			Source source = new DOMSource(doc);
			StreamResult result = new StreamResult(destino);
			transformer.transform(source, result);
			return 0;
		} catch (Exception e) {
			return -1;
		}
	}

	public void recorrerByTag() {
		NodeList llistaPlats = doc.getElementsByTagName("Plat");
		for (int i = 0; i < llistaPlats.getLength(); i++) {
			Node n = llistaPlats.item(i);
			Plat plat = new Plat();
			String descripcio = ((Element) n).getElementsByTagName("Descripcio").item(0).getTextContent();
			String codi = n.getAttributes().item(0).getNodeValue();
			Integer preu = Integer.parseInt(n.getAttributes().item(1).getNodeValue());
			String tipus = ((Element) n).getElementsByTagName("Tipus").item(0).getTextContent();
			String grup = ((Element) n).getElementsByTagName("Grup").item(0).getTextContent();
			String grupId = ((Element) n).getElementsByTagName("Grup").item(0).getAttributes().item(0).getNodeValue();
			plat.setDescripcio(descripcio);
			plat.setCodi(codi);
			plat.setPreu(preu);
			plat.setTipus(tipus);
			plat.setGrup(grup);
			plat.setIdGrup(grupId);
			System.out.println(plat.toString());
		}
	}

}
