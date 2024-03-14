package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
public class Vista1Controller implements Initializable {

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

	@FXML
	private TextField tfFailedLetters;

	GameManager manager = GameManager.getManager();

	Image[] images = new Image[13];
	String[] possibleWords = { "AGNÒSTIQUES", "ESCURÇÓ", "FRUÏCIÓ", "GALVANOTÈCNIA", "PASTANAGA", "MASSÍS" };
	String hiddenWord;
	String revealedWord;
	String validLetters = "AÀBCÇDEÈÉFGHIÍÏJKLMNOÒÓPQRSTUÚÜVWXYZ";
	String formattedValidLetters = "AABCÇDEEEFGHIIIJKLMNOOOPQRSTUUUVWXYZ";
	String startGame = "Iniciar partida";
	String restartGame = "Reiniciar partida";

	boolean showFailedLetters;
	boolean countRepetitionsAsErrors;
	boolean loaded = false;
	int count = 12;

	public void init() {
		if (!loaded) {
			instantiateImages();
			loaded = true;
		}
		lblFailedLetters.setVisible(manager.getShowFailedLetters());
		tfFailedLetters.setVisible(manager.getShowFailedLetters());
		cardImg.setImage(images[manager.getLives()]);
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
		count = manager.getLives();
		int randomWord = (int) (Math.random() * possibleWords.length);
		hiddenWord = possibleWords[randomWord];
		revealedWord = " ".repeat(hiddenWord.length());
		lblWord.setText("");
		lblUnderlines.setText("_".repeat(hiddenWord.length()));
		tfLetter.setDisable(false);
		tfLetter.setText("");
		lblFailedLetters.setVisible(manager.getShowFailedLetters());
		tfFailedLetters.setVisible(manager.getShowFailedLetters());
		tfFailedLetters.clear();
		showFailedLetters = manager.getShowFailedLetters();
		countRepetitionsAsErrors = manager.getCountRepetitionsAsErrors();
		cardImg.setImage(images[count]);
		btnPlay.setText(restartGame);
	}

	public void loadCard() {
		cardImg.setImage(images[--count]);
		if (count <= 0) {
			displayAlert("You lost", "The word was: " + hiddenWord);
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
				char formattedLetter = formatWord(tfLetter.getText()).charAt(0);
				if (formatWord(hiddenWord).indexOf(formattedLetter) != -1) {
					char[] revealedWordToArray = revealedWord.toCharArray();
					char[] formattedHiddenWordToArray = formatWord(hiddenWord).toCharArray();
					char[] defaultHiddenWordToArray = hiddenWord.toCharArray();
					if (countRepetitionsAsErrors
							&& String.valueOf(revealedWordToArray).contains(String.valueOf(formattedLetter))) {
						loadCard();
					}
					for (int i = 0; i < revealedWordToArray.length; i++) {
						if (formattedHiddenWordToArray[i] == formattedLetter) {
							revealedWordToArray[i] = defaultHiddenWordToArray[i];
						}
					}
					revealedWord = String.valueOf(revealedWordToArray);
					lblWord.setText(revealedWord);
					if (revealedWord.equals(hiddenWord)) {
						displayAlert("You won!", "Congratulations!");
						gameEnd();
					}
				} else {
					if (showFailedLetters) {
						tfFailedLetters.appendText(formattedLetter + " ");
					}
					loadCard();
				}

			}
		});
	}

	public String formatWord(String word) {
		char[] wordToChars = word.toUpperCase().toCharArray();
		int pos;
		for (int i = 0; i < wordToChars.length; i++) {
			pos = validLetters.indexOf(wordToChars[i]);
			if (pos != -1 && wordToChars[i] != formattedValidLetters.charAt(pos)) {
				wordToChars[i] = formattedValidLetters.charAt(pos);
			}
		}
		return String.valueOf(wordToChars);
	}

	public void gameEnd() {
		tfLetter.clear();
		tfLetter.setDisable(true);
		tfFailedLetters.clear();
		btnPlay.setText(startGame);
	}

	public void displayAlert(String title, String text) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION); // TODO confirmar si vols reiniciar
		alert.setTitle(title);
		alert.setHeaderText("");
		alert.setContentText(text);
		alert.showAndWait();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		init();
	}
}