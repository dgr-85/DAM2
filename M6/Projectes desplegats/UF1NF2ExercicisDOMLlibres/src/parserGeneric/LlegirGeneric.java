package parserGeneric;

import java.io.File;

public class LlegirGeneric {

	public static void main(String[] args) {
		
		File fitxer = new File("LibrosXML.xml");
		
		if (fitxer.exists()){
			ParserDOMGeneric parser = new ParserDOMGeneric(fitxer);
			
			if(parser.generarDOM()){
				parser.recorreryMostrarDOMGeneric();
			}
		}else{	
			System.out.println("El fitxer no existeix");
		}
	}
}
