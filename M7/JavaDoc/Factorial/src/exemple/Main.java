package exemple;

public class Main {

	public static void main(String[] args) {

		Factorial factorial = new Factorial();

		System.out.println("Factorial de 10 amb getResursiveFactorial: " + factorial.getRecursiveFactorial(10));
		System.out.println("Factorial de 10 amb getResursiveFact: " + factorial.getRecursiveFact(10));
		System.out.println("Factorial de 10 amb getIterativeFactorial: " + factorial.getIterativeFactorial(10));

	}

}
