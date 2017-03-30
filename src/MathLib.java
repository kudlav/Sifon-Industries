
public class MathLib {


	/**
	 * Returns division of double arguments.
	 * <p>
	 * Besides of zero division, special cases are returning values as specified in <a href="http://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.17.2"> Java SE Specifications</a> (IEEE 754).
	 * 
	 * @param a the dividend
	 * @param b the divisor
	 * @throws ArithmeticException if there is division by zero
	 * @return the value dividend / divisor
	 * 
	 * @author Rengyr
	 */
	public static double idiv(double a, double b) {
		if (b == 0){
			throw new ArithmeticException("Division by zero");
		}
		return a / b;
	}

	public static double imul(double a, double b) {
		return 0;
	}

	public  static double sub(double a, double b) {
		return 0;
	}

	public  static double add(double a, double b) {
		return 0;
	}

	public  static double nRoot(int n, double a) {
		return 0;
	}

	public  static double exp(int n, double a) {
		return 0;
	}

	public  static double fac(int a) {
		return 0;
	}

	/**
	 * Returns remainder of integer arguments.
	 * 
	 * @param a the dividend
	 * @param b the divisor
	 * @return remainder of dividend and divisor
	 * 
	 * @author Rengyr
	 */
	public  static int mod(int a, int b) {
		return a % b;
	}

}
