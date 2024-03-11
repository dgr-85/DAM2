package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * Controller class for the second vista.
 */
public class Vista2Controller implements Initializable {

	@FXML
	RadioButton rb4;
	@FXML
	RadioButton rb5;
	@FXML
	RadioButton rb6;
	@FXML
	Button btnReturn;

	ToggleGroup tg;
	GameManager manager = GameManager.getManager();

	public void init() {
		tg = new ToggleGroup();
		rb4.setToggleGroup(tg);
		rb5.setToggleGroup(tg);
		rb6.setToggleGroup(tg);
	}

	public void setRules() {
		RadioButton rbSelected = (RadioButton) tg.getSelectedToggle();
		manager.setPoints(Integer.valueOf(rbSelected.getText()));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		init();
		btnReturn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				setRules();
				init();
				VistaNavigator.loadVista(VistaNavigator.VISTA_1);
			}
		});
	}

}