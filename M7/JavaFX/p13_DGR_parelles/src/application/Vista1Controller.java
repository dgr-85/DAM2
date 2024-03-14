package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * Controller class for the first vista.
 */
public class Vista1Controller implements Initializable {

	@FXML
	private ImageView ivReset;

	@FXML
	private TextField tfPoints;

	@FXML
	private GridPane gpMain;

	GameManager manager = GameManager.getManager();

	Image[] images = new Image[6];

	boolean loaded = false;

	public void init() {
		if (!loaded) {
			instantiateImages();
			loaded = true;
		}
		for (Node node : gpMain.getChildren()) {
			if (node instanceof ImageView) {
				Integer r = GridPane.getRowIndex(node);
				Integer c = GridPane.getColumnIndex(node);
				int row = r == null ? 0 : r;
				int col = c == null ? 0 : c;
				((ImageView) node).setImage(new Image(getClass().getResourceAsStream("resource/img_off.jpg")));
			}
		}
		tfPoints.setText(String.valueOf(manager.getPoints() * 4));
		ivReset.setImage(new Image(getClass().getResourceAsStream("resource/img_new.png")));
	}

	public void instantiateImages() {
		for (int i = 0; i < images.length; i++) {
			images[i] = new Image(getClass().getResourceAsStream("resource/img0" + (i + 1) + ".jpg"));
		}
	}

	public void gameStart() {
		for (Node node : gpMain.getChildren()) {
			node.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}
			});
		}
	}

	public void gameEnd() {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		init();
	}
}