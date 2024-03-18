package application;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
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
	Image[] imagesDoubled;

	public void init() {
		instantiateImages();
		clearImageViews();
		tfPoints.clear();
		ivReset.setImage(new Image(getClass().getResourceAsStream("resource/img_new.png")));
	}

	public void clearImageViews() {
		for (Node node : gpMain.getChildren()) {
			if (node instanceof ImageView) {
				((ImageView) node).setImage(new Image(getClass().getResourceAsStream("resource/img_off.jpg")));
			}
		}
	}

	public void instantiateImages() {
		for (int i = 0; i < images.length; i++) {
			images[i] = new Image(getClass().getResourceAsStream("resource/img0" + (i + 1) + ".jpg"));
		}
		for (int i = 0; i < imagesDoubled.length; i += 2) {
			int j = 0;
			imagesDoubled[i] = images[j];
			imagesDoubled[i + 1] = images[j];
			j++;
		}
		Collections.shuffle(Arrays.asList(imagesDoubled));
	}

	public void gameStart() {
		imagesDoubled = new Image[manager.getPoints() * 2];
		clearImageViews();
		tfPoints.setText(String.valueOf(manager.getPoints() * 4));
		for (Node node : gpMain.getChildren()) {
			node.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					if (node instanceof ImageView) {
						int num = (int) (Math.random() * images.length);
						((ImageView) node).setImage(images[num]);
					}
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

	/*
	 * De GridPane a array: pos array = columna + (fila * num columnes) p.e. fila 1
	 * columna 3, total columnes 4 pos array = 3 + (1 * 4) = 7
	 * 
	 * D'array a GridPane: pos array // num columnes = fila pos array % num columnes
	 * = columna p.e. pos array 7 7 // 4 = 1 7 % 4 = 3
	 */

}