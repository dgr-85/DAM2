package plats;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class ConsultarMenus {

	public static void main(String[] args) {

		File fitxer = new File("MenuXML.xml");

		if (fitxer.exists()) {
			ParserDOM parser = new ParserDOM(fitxer);
			if (parser.generarDOM()) {
//				System.out.println("Recórrer i mostrar DOM:");
//				parser.recorrerIMostrarDOM();
//				System.out.println("Recerca per tag:");
//				parser.recercaByTag("Descripcio", "Macarrons");
				System.out.println("Recollint informació dels plats del fitxer XML...");
				List<Plat> plats = parser.crearLlistaPlats();
				StringBuilder sb = new StringBuilder();
				for (Plat plat : plats) {
					sb.append(plat);
				}

				System.out.println("Escrivint el contingut dels plats al fitxer ResumPlats.txt...");
				try (FileWriter fileWriter = new FileWriter("ResumPlats.txt");
						BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
					bufferedWriter.write(sb.toString());
				} catch (Exception e) {
					System.out.println("Error en escriure al fitxer ResumPlats.txt.");
				}

				System.out.println("Llegint el contingut del fitxer ResumPlats.txt...");
				try (BufferedReader bufferedReader = new BufferedReader(new FileReader("ResumPlats.txt"))) {
					String str;
					while ((str = bufferedReader.readLine()) != null) {
						System.out.println(str);
					}
				} catch (Exception e) {
					System.out.println("Error en llegir el fitxer ResumPlats.txt");
				}

				System.out.println("Afegint nou plat...");
				Plat plat = new Plat();
				plat.setDescripcio("Bacallà a la llauna");
				plat.setCodi("A44");
				plat.setPreu(14);
				plat.setTipus("Plat Principal");
				plat.setIdGrup("02");
				plat.setDescripcioGrup("Origen Animal");
				plat.setTipusGrup("Peix");
				parser.afegirNode(plat);
				File xml2 = new File("PlatsXML2.xml");
				parser.guardarDOMaFileTransformer(xml2);
			}
		} else {
			System.out.println("No s'ha trobat un fitxer XML per a recórrer.");
		}
	}

}
