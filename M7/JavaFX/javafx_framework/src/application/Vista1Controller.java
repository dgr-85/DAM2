package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller class for the first vista.
 */
public class Vista1Controller {

	@FXML
	private ImageView cardImg;

	@FXML
	private Button btnPlay;

	Image[] images = new Image[13];
	String[] hiddenWords = { "AGNÒSTIQUES", "ESCURÇÓ", "FRUÏCIÓ", "GALVANOTÈCNIA", "PASTANAGA", "MASSÍS" };

	boolean loaded = false;
	int count = 12;

	public void instantiateImages() {
		for (int i = 0; i < 13; i++) {
			if (i < 10) {
				images[i] = new Image(getClass().getResourceAsStream("resource/carta_0" + i + ".jpg"));
			} else {
				images[i] = new Image(getClass().getResourceAsStream("resource/carta_" + i + ".jpg"));
			}
		}
	}

	public void loadCard() {
		if (!loaded) {
			instantiateImages();
			loaded = true;
		}
		cardImg.setImage(images[count--]);
		if (count < 0) {
			count = 12;
		}

	}

}