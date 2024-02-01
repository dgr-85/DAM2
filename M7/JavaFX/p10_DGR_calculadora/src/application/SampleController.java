package application;

import java.time.LocalDate;
import java.time.Period;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SampleController {

	@FXML
	private ToggleGroup genere;

	@FXML
	private RadioButton rbDona;

	@FXML
	private RadioButton rbHome;

	@FXML
	private Slider sAltura;

	@FXML
	private Label valorAltura;

	@FXML
	private DatePicker dpNaixement;

	@FXML
	private TextField tfPes;

	@FXML
	private TextField tfTMB;

	@FXML
	private Button btnCalcular;

	@FXML
	private void initialize() {

		rbDona.setSelected(true);
		tfTMB.setText("0");
		dpNaixement.setValue(LocalDate.now().minus(Period.ofYears(20)));

		valorAltura.setText("165 cm");
		tfPes.setText("0");

		sAltura.setMin(150);
		sAltura.setMax(200);
		sAltura.setValue(165);
		sAltura.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				valorAltura.setText(String.format("%d cm", arg2.intValue()));

			}
		});

	}

	@FXML
	Integer calcularData(LocalDate dataEscollida) {
		Period periode = Period.between(dataEscollida, LocalDate.now());
		int edat = periode.getYears();
		if (edat < 20 || edat > 70) {
			mostrarAlerta("L'edat ha d'estar compresa entre 20 i 70 anys, ambós inclosos.");
			return null;
		}
		return edat;
	}

	@FXML
	Integer calcularPes(String pesEscollit) {
		int pes;
		try {
			pes = Integer.parseInt(pesEscollit);
		} catch (Exception e) {
			mostrarAlerta("El valor per al pes ha de ser un número enter.");
			return null;
		}
		if (pes < 25 || pes > 125) {
			mostrarAlerta("El pes ha de tenir un valor entre 25 i 125, ambdós inclosos.");
			return null;
		}
		return pes;
	}

	@FXML
	void calcularResultat() {
		Integer pes = calcularPes(tfPes.getText());
		Integer edat = calcularData(dpNaixement.getValue());
		int sexe;
		if (rbDona.isSelected()) {
			sexe = -161;
		} else {
			sexe = 5;
		}
		int altura = (int) sAltura.getValue();

		if (pes != null && edat != null) {
			int res = (int) Math.round(pes * 10 + 6.25 * altura - edat * 5 + sexe);
			tfTMB.setText(String.valueOf(res));
		}
	}

	@FXML
	void mostrarAlerta(String text) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Advertència");
		alert.setHeaderText("");
		alert.setContentText(text);
		alert.showAndWait();
	}
}
