package exerciciLibros;

import java.io.File;

public class LlegirLibros {

	public static void main(String[] args) {
		
		File fitxer = new File("LibrosXML.xml");
		
		if (fitxer.exists()){
			ParserDOM parser = new ParserDOM(fitxer);
			
			if(parser.generarDOM()){
				parser.recorreryMostrarDOM();
				System.out.println("Alternativa amb getElementsByTagName");
				parser.recorrerByTag();
				System.out.println("Exercici 2: Recerca");
				parser.recercaByTag("Titulo", "El Nombre de la Rosa");
				parser.recercaByTag("Autor", "Gonzalo Giner");
				System.out.println("Exercici 3: Modificar títol");
				parser.modificarTitol("El Nombre de la Rosa", "El Nombre de la Cosa");
				parser.guardarDOMaFileTransformer(new File("LibrosXMLModificat.XML"));
				System.out.println("Exercici 4: Afegir node");
				Libro l = new Libro();
				l.setAnyPublicacio(1951);
				l.setAutor("Isaac Asimov");
				l.setTitol("La Fundación");
				parser.afegirNode(l);
				parser.guardarDOMaFileTransformer(new File("LibrosXMLModificat.XML"));
			}
		}else{	
			System.out.println("El fitxer no existeix");
		}
	}
}
