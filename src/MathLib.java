
public class MathLib {


	public static double idiv(double dividend, double divisor) {
		return 0;
	}

	public static double imul(double a, double b) {
		return 0;
	}

	/**
	 * Returns substraction of double arguments.
	 *
	 * @param a minuend (the number from that is subtracted)
	 * @param b subtrahend (the number being subtracted)
	 *
	 * @return difference (result of substraction)
	 *
	 * @author kudlav
	 */
	public  static double sub(double a, double b) {
		return a-b;
	}

	public  static double add(double a, double b) {
		return 0;
	}

	public  static double nRoot(int n, double a) {
		return 0;
	}


	/**
	 * Exponentiation - returns n-th power of a.
	 *
	 * @param n expoent
	 * @param a base
	 *
	 * @return result of exponentiation (n-th power of a)
	 *
	 * @author kudlav
	 */
	public  static double exp(int n, double a) {
		double result = 1.0D;
		int exp = abs(n);

		for(int i = 0; i < exp; i++) {
			result = result * a;
		}

		return (n < 0) ? 1/result : result;
	}

	public  static double fac(int a) {
		return 0;
	}

	public  static int mod(int a, int b) {
		return 0;
	}

	/**
	 * Absolute value of integer.
	 *
	 * @param a number
	 * @return number which is always positive
	 */
	private  static int abs(int a){
		return (a<=0) ? 0-a : a;
	}
}
