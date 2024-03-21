package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Controller class for the first vista.
 */
public class Vista1Controller implements Initializable {

	@FXML
	private GridPane gpMain;

	@FXML
	private Label lblPath;

	@FXML
	private Button btnStart;

	static GameManager manager = GameManager.getManager();
	String alphabet = "ABCDEFGHIJKLMNOPQRST";
	Button lastButtonPressed;
	Integer btnLastRow = null;
	Integer btnLastColumn = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		init();
	}

	public void init() {
		int i = 0;
		int rows = manager.getNumRows();
		int columns = manager.getNumColumns();
		for (Node node : gpMain.getChildren()) {
			if (GridPane.getRowIndex(node) < rows && GridPane.getColumnIndex(node) < columns) {
				((Button) node).setDisable(false);
				((Button) node).setText(String.valueOf(alphabet.charAt(i)));
				i++;
			} else {
				((Button) node).setText("");
				((Button) node).setDisable(true);
			}
		}
	}

	public void gameStart() {
		alphabet = manager.getUseUppers() ? alphabet.toUpperCase() : alphabet.toLowerCase();
		init();
		lblPath.setText("");
		for (Node node : gpMain.getChildren()) {
			((Button) node).setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					checkPlay((Button) node);
				}
			});
		}
	}

	public void checkPlay(Button btn) {
		Integer btnRow = GridPane.getRowIndex(btn);
		Integer btnColumn = GridPane.getColumnIndex(btn);
		if (lastButtonPressed != null) {
			btnLastRow = GridPane.getRowIndex(lastButtonPressed);
			btnLastColumn = GridPane.getColumnIndex(lastButtonPressed);
		}
		if (btnLastRow != null && btnLastColumn != null
				&& (Math.abs(btnRow - btnLastRow) > 1 || Math.abs(btnColumn - btnLastColumn) > 1)) {
			return;
		}
		btn.setDisable(true);
		lblPath.setText(lblPath.getText() + btn.getText());
		lastButtonPressed = btn;
	}

	public void gameEnd() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Has acabat la partida");
		alert.setHeaderText("");
		alert.setContentText("Felicitats!");
		alert.showAndWait();
	}

}