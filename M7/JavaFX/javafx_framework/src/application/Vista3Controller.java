package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller class for the third vista.
 */
public class Vista3Controller {

	/**
	 * Event handler fired when the user requests a previous vista.
	 *
	 * @param event the event that triggered the handler.
	 */
	@FXML
	void firstPane(ActionEvent event) {
		VistaNavigator.loadVista(VistaNavigator.VISTA_1);
	}

}