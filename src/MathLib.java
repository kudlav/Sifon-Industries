
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

	/**
	 * Multiplication.
	 * @param a first number to multiplication
	 * @param b second number to multiplication
	 * @return result of multiplication
	 * @author AdamKuba
	 * */
	public static double imul(double a, double b) {
		return a*b;
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
		/**
		* Addition
		* 
		* @param a First number
		* @param b The number which is added to the first one
		* @return a + b
		* 
		* @author mmusil
		*/
	public  static double add(double a, double b) {
		return a+b;
	}

	/**
	 * Nth root implemented by the formula from wikipedia.
	 * @param n number of root
	 * @param a root base
	 * @return result of nth root
	 * @author AdamKuba
	 * */
	public  static double nRoot(int n, double a) {
		if(a<0){
			return -1;
		}
		double p = 0.00000000001;
		double prev=a;
		double next=a/n;
		
		while(abs(prev-next)>p){
			prev = next;
			next = ((n-1.0)*prev+(a/exp(n-1,prev)))/n;
			
		}
		return next;
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

		/**
		* Factorial
		* @param a Number from its factorial is counted
		* @return factorial from a
		* 
		* @author mmusil
		*/
	public  static double fac(int a) {
			int f = a;
			while (a>1){
				f = f * (a-1);
				a--;
			}
			return f;
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

	/**
	 * Absolute value.
	 * @param a number
	 * @return number which is always positive
	 */
	private static double abs(double a){
		return (a<=0.0D) ? 0.0D - a :a;
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
