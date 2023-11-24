package managers;

public class ErrorManager {

	private static final String GENERIC_ERROR = "Unknown error";
	private static final String DUP_KEY = " cannot be added (already exists).";
	private static final String NO_REFERENCED_TYPE = "'s type doesn't exist. Please create it first or select a valid type.";
	private static final String NON_EXISTING_ELEMENT = " doesn't exist.";

	public static String getMessage(int code, String model) {
		switch (code) {
		case -1:
			return model + NON_EXISTING_ELEMENT;
		case -1062:
			return model + DUP_KEY;
		case -1452:
			return model + NO_REFERENCED_TYPE;
		default:
			return GENERIC_ERROR;
		}
	}
}
