package exemple;

/**
 * <p>
 * Aquesta classe inclou tres mètodes diferents per a calcular factorials. El
 * concepte matemàtic de factorial s'aplica a números enters positius i
 * consisteix a multiplicar-los per tots els enters positius inferiors a
 * aquests.
 * </p>
 * <p>
 * Per a saber més respecte el càlcul factorial, pots visitar el següent enllaç:
 * <a href="https://ca.wikipedia.org/wiki/Factorial">Definició de factorial a la
 * Viquipèdia</a>
 * </p>
 * 
 * @author David Gómez
 * @version 1.0
 * 
 */

public class Factorial {
	/**
	 * <p>
	 * Aquest mètode rep un número enter positiu i en calcula el factorial creant
	 * una instància nova de la classe Factorial i cridant-ne el mètode amb el
	 * mateix nom que aquest, passant-li el número restant-li 1. Aquesta crida a
	 * instàncies noves és successiva fins arribar a 1.
	 * </p>
	 * 
	 * @param n Número del qual se'n calcularà el factorial.
	 * @return double El resultat final del càlcul factorial.
	 * @exception IllegalArgumentException Si n és negatiu.
	 */
	public double getRecursiveFactorial(int n) throws IllegalArgumentException {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		if (n == 1 || n == 0) {
			return 1;
		}
		Factorial fmenos1 = new Factorial();
		return n * fmenos1.getRecursiveFactorial(n - 1);
	}

	/**
	 * @deprecated Aquest mètode està obsolet i cal utilitzar getRecursiveFactorial
	 *             enlloc seu.
	 * @param n Número del qual se'n calcularà el factorial.
	 * @return double El resultat final del càlcul factorial.
	 */
	@Deprecated
	public double getRecursiveFact(int n) {
		if (n == 1 || n == 0) {
			return 1;
		}
		Factorial fmenos1 = new Factorial();
		return n * fmenos1.getRecursiveFact(n - 1);
	}

	/**
	 * <p>
	 * Aquest mètode rep un número enter positiu i en calcula el factorial creant
	 * una variable amb valor 1 i executant-hi una successió de multiplicacions pel
	 * mateix valor més 1, fins arribar al número proporcionat. Per exemple, si el
	 * número que s'ha passat és 4, la multiplicació total seria 1 * 2 * 3 * 4.
	 * </p>
	 * 
	 * @param n Número del qual se'n calcularà el factorial.
	 * @return double El resultat final del càlcul factorial.
	 * @exception IllegalArgumentException Si n és negatiu.
	 */
	public double getIterativeFactorial(int n) throws IllegalArgumentException {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		if (n == 1 || n == 0) {
			return 1;
		}
		int aux = 1;
		for (int i = 2; i <= n; i++) {
			aux *= i;
		}
		return aux;
	}
}
