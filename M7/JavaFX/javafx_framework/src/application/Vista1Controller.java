package application;

import java.util.function.UnaryOperator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/**
 * Controller class for the first vista.
 */
public class Vista1Controller {

	@FXML
	private ImageView cardImg;

	@FXML
	private Button btnPlay;

	@FXML
	private Label lblWord;

	@FXML
	private Label lblUnderlines;

	@FXML
	private TextField tfLletra;

	Image[] images = new Image[13];
	String[] possibleWords = { "AGNÒSTIQUES", "ESCURÇÓ", "FRUÏCIÓ", "GALVANOTÈCNIA", "PASTANAGA", "MASSÍS" };
	String hiddenWord;
	String revealedWord;

	boolean loaded = false;
	int count = 12;

	public void init() {
		if (!loaded) {
			instantiateImages();
			loaded = true;
		}
		loadCard();
		tfLletra.setTextFormatter(new TextFormatter<TextField>(modifyChange));
	}

	public void instantiateImages() {
		for (int i = 0; i < 13; i++) {
			if (i < 10) {
				images[i] = new Image(getClass().getResourceAsStream("resource/carta_0" + i + ".jpg"));
			} else {
				images[i] = new Image(getClass().getResourceAsStream("resource/carta_" + i + ".jpg"));
			}
		}
	}

	public void loadWord() {
		int randomWord = (int) (Math.random() * possibleWords.length);
		hiddenWord = possibleWords[randomWord];
		revealedWord = " ".repeat(hiddenWord.length());
		lblUnderlines.setText("_".repeat(hiddenWord.length()));
		tfLletra.setDisable(false);
		btnPlay.setText("Reiniciar partida");
	}

	public void loadCard() {
		cardImg.setImage(images[count--]);
		if (count < 0) {
			count = 12;
		}
	}

	UnaryOperator<TextFormatter.Change> modifyChange = change -> {
		if (change.isContentChange()) { // hi ha hagut un canvi
			int newLength = change.getControlNewText().length();
			if (newLength > 1) {
				return null; // descarta el canvi
			}
		}

		return change;
	};

	public void checkWord() {
		tfLletra.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				if (hiddenWord.contains(tfLletra.getText())) {
					Character lletra = tfLletra.getText().charAt(0);
					char[] revealedArray = revealedWord.toCharArray();
					for (int i = 0; i < revealedArray.length; i++) {
						if (hiddenWord.charAt(i) == lletra) {
							revealedArray[i] = lletra;
						}
					}
					revealedWord = String.valueOf(revealedArray);
					lblWord.setText(revealedWord);
				} else {
					loadCard();
				}

			}
		});
	}
}