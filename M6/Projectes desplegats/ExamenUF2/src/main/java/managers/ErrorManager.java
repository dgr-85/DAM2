package managers;

public class ErrorManager {
	private static final String UNKNOWN_ERROR = "Unknown error.";
	private static final String DUPLICATE_KEY = " cannot be added (already exists).";
	private static final String NO_REFERENCED_TYPE = "'s type doesn't exist. Please create it first or select a valid type.";
	private static final String UNDELETABLE = " cannot be deleted;";
	private static final String NON_EXISTING_ELEMENT = " doesn't exist.";
	private static final String FOREIGN_REFERENCES = " one or more prizes are referencing it.";

	public static String getMessage(int code, String model) {
		switch (code) {
		case 0:
			return model + UNDELETABLE + NON_EXISTING_ELEMENT;
		case -1:
			return model + NON_EXISTING_ELEMENT;
		case -1062:
			return model + DUPLICATE_KEY;
		case -1451:
			return model + UNDELETABLE + FOREIGN_REFERENCES;
		case -1452:
			return model + NO_REFERENCED_TYPE;
		default:
			return UNKNOWN_ERROR;
		}
	}
}
