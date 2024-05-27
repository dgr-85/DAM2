package plats;

import java.io.File;

public class ConsultarMenus {

	public static void main(String[] args) {

		File fitxer = new File("MenuXML.xml");

		if (fitxer.exists()) {
			ParserDOM parser = new ParserDOM(fitxer);

			if (parser.generarDOM()) {
				parser.recorrerIMostrarDOM();
			}
		} else {
			System.out.println("El fitxer no existeix");
		}
	}

}
