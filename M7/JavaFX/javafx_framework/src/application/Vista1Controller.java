package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller class for the first vista.
 */
public class Vista1Controller {

	@FXML
	private TextField text1;

	void setSalutacio(String nom) {
		text1.setText("Hola " + nom + "!!");
	}
}