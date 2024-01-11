package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SampleController {
	
	@FXML
	private AnchorPane apGenere;
	
	@FXML
	private Slider sAltura;
	
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
		
	}
}
