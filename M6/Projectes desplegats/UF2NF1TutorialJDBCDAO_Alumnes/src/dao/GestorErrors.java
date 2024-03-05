package dao;

public final class GestorErrors {
	
	private static String missatge = "Error desconegut";
	static final String FATAL_ERROR = "Error desconegut";
	static final String CLAU_DUP = " duplicat/a"; 

	public static String getMissatge(int codi, String model) {
		switch (codi) {
			case -1062: 
				return model + CLAU_DUP;
			default: 
				return missatge;
		}
	}



}
