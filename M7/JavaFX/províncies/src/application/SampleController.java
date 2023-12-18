package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

public class SampleController {
	ObservableList<String> llistaComunitats = FXCollections.observableArrayList("Catalunya", "València");
	ObservableList<String> llistaProvinciesValencia = FXCollections.observableArrayList("Alacant", "Castelló",
			"València");
	ObservableList<String> llistaProvinciesCatalunya = FXCollections.observableArrayList("Barcelona", "Girona",
			"Lleida", "Tarragona");
	@FXML
	private Button bSortir;

	@FXML
	private ComboBox<String> selectComunitat;

	@FXML
	private ChoiceBox<String> selectProvincia;

	@FXML
	void canviaProvincies(ActionEvent event) {
		String provinciaSeleccionada = selectComunitat.getValue();
		if (provinciaSeleccionada.equals("Catalunya")) {
			selectProvincia.setItems(llistaProvinciesCatalunya);
			selectProvincia.setValue("Barcelona");
		} else {
			selectProvincia.setItems(llistaProvinciesValencia);
			selectProvincia.setValue("Alacant");
		}
	}

	@FXML
	void sortirAplicacio(ActionEvent event) {
		System.exit(1);
	}

	@FXML
	private void initialize() {
		selectComunitat.setValue("Catalunya");
		selectComunitat.setItems(llistaComunitats);
		selectProvincia.setValue("Barcelona");
		selectProvincia.setItems(llistaProvinciesCatalunya);
	}

}
