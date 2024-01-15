package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

		sAltura.setMin(150);
		sAltura.setMax(200);
		sAltura.setValue(165);

//		sAltura.valueProperty().addListener(new ChangeListener<Number>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
//				valorAltura.setText(String.format("%d cm", arg2));
//
//			}
//		});

	}

	@FXML
	void calcularResultat(ActionEvent event) {
		int sexe;
		if (rbDona.isSelected()) {
			sexe = -161;
		} else {
			sexe = 5;
		}
		int pes = Integer.parseInt(tfPes.getText());
		int altura = (int) sAltura.getValue();

		int res = (int) (pes * 10 + 6.25 * altura - 100 + sexe);
		tfTMB.setText(String.valueOf(res));
	}
}
