package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * Controller class for the second vista.
 */
public class Vista2Controller implements Initializable {

	@FXML
	ComboBox<Integer> cbRows;

	@FXML
	ComboBox<Integer> cbColumns;

	@FXML
	RadioButton rbUppers;

	@FXML
	RadioButton rbLowers;

	@FXML
	Button btnRestart;

	ToggleGroup tg;
	GameManager manager = GameManager.getManager();
	ObservableList<Integer> olRows = FXCollections.observableArrayList(3, 4);
	ObservableList<Integer> olColumns = FXCollections.observableArrayList(3, 4, 5);

	public void init() {
		tg = new ToggleGroup();
		rbUppers.setToggleGroup(tg);
		rbLowers.setToggleGroup(tg);
		cbRows.setValue(4);
		cbRows.setItems(olRows);
		cbColumns.setValue(5);
		cbColumns.setItems(olColumns);
	}

	public void setRules() {
		manager.setNumRows(cbRows.getValue());
		manager.setNumColumns(cbColumns.getValue());
		manager.setUseUppers(rbUppers.isSelected());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		init();
		btnRestart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				setRules();
				VistaNavigator.loadVista(VistaNavigator.VISTA_1);
			}
		});
	}

}