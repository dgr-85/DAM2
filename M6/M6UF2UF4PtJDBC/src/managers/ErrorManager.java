package managers;

public class ErrorManager {

	private static String message = "Unknown error";
	static final String DUP_KEY = " cannot be added (already exists).";
	static final String NO_REFERENCED_ROW = "'s type doesn't exist. Please create it first or select a valid type.";

	public static String getMessage(int code, String model) {
		switch (code) {
		case -1062:
			return model + DUP_KEY;
		case -1452:
			return model + NO_REFERENCED_ROW;
		default:
			return message;
		}
	}
}
