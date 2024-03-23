package application;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
	Image[] stockImages = new Image[6];
	Image[] imagePairs;
	Boolean[] areImgsFaceUp;
	Integer firstCard = null;
	Integer secondCard = null;
	Integer totalColumns;

	public void init() {
		totalColumns = gpMain.getColumnCount();
		instantiateImagesFromStock();
		clearImageViews();
		tfPoints.clear();
		ivReset.setImage(new Image(getClass().getResourceAsStream("resource/img_new.png")));
	}

	public void clearImageViews() {
		for (Node node : gpMain.getChildren()) {
			((ImageView) node).setImage(new Image(getClass().getResourceAsStream("resource/img_off.jpg")));
		}
	}

	public void instantiateImagesFromStock() {
		for (int i = 0; i < stockImages.length; i++) {
			stockImages[i] = new Image(getClass().getResourceAsStream("resource/img0" + (i + 1) + ".jpg"));
		}
	}

	public void instantiateImagePairs() {
		int j = 0;
		for (int i = 0; i < imagePairs.length; i += 2) {
			imagePairs[i] = stockImages[j];
			imagePairs[i + 1] = stockImages[j];
			j++;
		}
		Collections.shuffle(Arrays.asList(imagePairs));
	}

	public void instantiateImageBooleans() {
		for (int i = 0; i < areImgsFaceUp.length; i++) {
			areImgsFaceUp[i] = false;
		}
	}

	public void gameStart() {
		imagePairs = new Image[manager.getPoints() * 2];
		instantiateImagePairs();
		areImgsFaceUp = new Boolean[imagePairs.length];
		instantiateImageBooleans();
		clearImageViews();
		tfPoints.setText(String.valueOf(manager.getPoints() * 4));
		for (Node node : gpMain.getChildren()) {
			node.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					if (node instanceof ImageView) {
						turnCardFaceUp(node);
					}
				}
			});
		}
	}

	public void trackScore() {
		Integer currentPoints = Integer.parseInt(tfPoints.getText());
		currentPoints = currentPoints > 0 ? currentPoints - 1 : 0;
		tfPoints.setText(String.valueOf(currentPoints));
	}

	public void turnCardFaceUp(Node node) {
		Integer row = GridPane.getRowIndex(node);
		Integer column = GridPane.getColumnIndex(node);
		Integer gridPosToArrayPos = row * totalColumns + column;
		if (gridPosToArrayPos < imagePairs.length && !areImgsFaceUp[gridPosToArrayPos]) {
			trackScore();
			((ImageView) node).setImage(imagePairs[gridPosToArrayPos]);
			areImgsFaceUp[gridPosToArrayPos] = true;
			if (!Arrays.asList(areImgsFaceUp).contains(false)) {
				gameEnd();
			}
			checkPlay(gridPosToArrayPos);
		}
	}

	public void checkPlay(Integer position) {
		if (firstCard == null) {
			firstCard = position;
		} else if (secondCard == null) {
			secondCard = position;
		} else {
			if (imagePairs[firstCard].equals(imagePairs[secondCard])) {
				firstCard = position;
				secondCard = null;
			} else {
				resetWrongCards(position);
			}
		}
	}

	public void resetWrongCards(Integer position) {
		areImgsFaceUp[firstCard] = false;
		areImgsFaceUp[secondCard] = false;
		Integer firstRow = firstCard / totalColumns;
		Integer firstColumn = firstCard % totalColumns;
		Integer secondRow = secondCard / totalColumns;
		Integer secondColumn = secondCard % totalColumns;
		for (Node node : gpMain.getChildren()) {
			if ((GridPane.getRowIndex(node).equals(firstRow) && GridPane.getColumnIndex(node).equals(firstColumn))
					|| (GridPane.getRowIndex(node).equals(secondRow)
							&& GridPane.getColumnIndex(node).equals(secondColumn))) {
				((ImageView) node).setImage(new Image(getClass().getResourceAsStream("resource/img_off.jpg")));
			}
		}
		firstCard = position;
		secondCard = null;
	}

	public void gameEnd() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Has acabat la partida");
		alert.setHeaderText("");
		alert.setContentText("La teva puntuació final és: " + tfPoints.getText());
		alert.showAndWait();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		init();
	}
}