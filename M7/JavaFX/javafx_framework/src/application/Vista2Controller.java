package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller class for the second vista.
 */
public class Vista2Controller {

	@FXML
	private TextField text2;

	String getNom() {
		return text2.getText();
	}
}