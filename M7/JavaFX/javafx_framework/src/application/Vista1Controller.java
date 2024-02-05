package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller class for the first vista.
 */
public class Vista1Controller {

	@FXML
	private TextField vistaText1;

	void setSalutacio(String nom) {
		if (nom != null) {
			if (!nom.isBlank()) {
				vistaText1.setText("Hola " + nom + "!!");
			}
		} else {
			vistaText1.setText("Escriu un nom a la Vista 2.");
		}

	}
}