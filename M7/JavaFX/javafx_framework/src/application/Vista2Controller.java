package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller class for the second vista.
 */
public class Vista2Controller {

	@FXML
	private TextField vistaText2;

	String getNom() {
		return vistaText2.getText();
	}
}