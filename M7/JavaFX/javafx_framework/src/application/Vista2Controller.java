package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;

/**
 * Controller class for the second vista.
 */
public class Vista2Controller {

	@FXML
	Spinner<Integer> spLives;

	@FXML
	CheckBox cbFailedLetters;

	@FXML
	CheckBox cbRepeatError;

	@FXML
	Button btnReturn;

	GameManager manager = GameManager.getManager();

	public void init() {
		spLives.getValueFactory().setValue(manager.getLives());
		cbFailedLetters.setSelected(manager.getShowFailedLetters());
		cbRepeatError.setSelected(manager.getCountRepetitionsAsErrors());
	}

	public void setRules() {
		spLives.valueProperty().addListener((arg0, arg1, arg2) -> {
			manager.setLives(arg2);
		});
		manager.setShowFailedLetters(cbFailedLetters.isSelected());
		manager.setCountRepetitionsAsErrors(cbRepeatError.isSelected());
		init();
	}

}