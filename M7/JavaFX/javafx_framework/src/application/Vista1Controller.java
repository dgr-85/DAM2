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
	private TextField tfLetter;

	@FXML
	private Label lblFailedLetters;

	Image[] images = new Image[13];
	String[] possibleWords = { "AGNÒSTIQUES", "ESCURÇÓ", "FRUÏCIÓ", "GALVANOTÈCNIA", "PASTANAGA", "MASSÍS" };
	String hiddenWord;
	String revealedWord;
	String validLetters = "AÀBCÇDEÈÉFGHIÍÏJKLMNOÒÓPQRSTUÚÜVWXYZ";
	String formattedValidLetters = "AABCÇDEEEFGHIIIJKLMNOOOPQRSTUUUVWXYZ";
	String startGame = "Iniciar partida";
	String restartGame = "Reiniciar partida";

	boolean loaded = false;
	int count = 12;

	public void init() {
		if (!loaded) {
			instantiateImages();
			loaded = true;
		}
		lblFailedLetters.setVisible(false);
		loadCard();
		tfLetter.setTextFormatter(new TextFormatter<TextField>(modifyChange));
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

	public void gameStart() {
		int randomWord = (int) (Math.random() * possibleWords.length);
		hiddenWord = possibleWords[randomWord];
		revealedWord = " ".repeat(hiddenWord.length());
		lblWord.setText("");
		lblUnderlines.setText("_".repeat(hiddenWord.length()));
		tfLetter.setDisable(false);
		cardImg.setImage(images[12]);
		btnPlay.setText(restartGame);
	}

	public void loadCard() {
		cardImg.setImage(images[count--]);
		if (count <= 0) {
			gameEnd();
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
		tfLetter.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER && !tfLetter.getText().isBlank()) {
				char fixedLetter = formatWord(tfLetter.getText()).charAt(0);
				if (formatWord(hiddenWord).indexOf(fixedLetter) != -1) {
					char[] revealedArray = revealedWord.toCharArray();
					char[] hiddenArray = formatWord(hiddenWord).toCharArray();
					char[] defaultHiddenArray = hiddenWord.toCharArray();
					for (int i = 0; i < revealedArray.length; i++) {
						if (hiddenArray[i] == fixedLetter) {
							revealedArray[i] = defaultHiddenArray[i];
						}
					}
					revealedWord = String.valueOf(revealedArray);
					lblWord.setText(revealedWord);
					if (revealedWord.equals(hiddenWord)) {
						gameEnd();
					}
				} else {
					loadCard();
				}

			}
		});
	}

	public String formatWord(String word) {
		return word.toUpperCase().replace('À', 'A').replace('È', 'E').replace('É', 'E').replace('Í', 'I')
				.replace('Ï', 'I').replace('Ò', 'O').replace('Ó', 'O').replace('Ú', 'U').replace('Ü', 'U');
	}

	public void gameEnd() {
		tfLetter.setDisable(true);
		btnPlay.setText(startGame);
	}
}