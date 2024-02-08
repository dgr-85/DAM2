package application;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller class for the first vista.
 */
public class Vista1Controller {

	@FXML
	private ImageView cardImg;

	Image img = new Image(getClass().getResourceAsStream("imgs/carta_12.jpg"));

	public void loadCard() {
		cardImg.setImage(img);
	}

}