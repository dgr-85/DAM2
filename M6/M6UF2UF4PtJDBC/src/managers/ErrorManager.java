package managers;

public class ErrorManager {

	private static String message = "Unknown error";
	static final String FATAL_ERROR = "Unknown error";
	static final String DUP_KEY = " duplicated";

	public static String getMessage(int code, String model) {
		switch (code) {
		case -1062:
			return model + DUP_KEY;
		default:
			return message;
		}
	}
}
