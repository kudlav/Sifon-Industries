
/**
 * Class with static mathematical methods.
 */
public class MathLib {

	/**
	 * Returns the division of double arguments.
	 * <p>
	 * Besides of zero division, special cases are returning values as specified in <a href="http://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.17.2"> Java SE Specifications</a> (IEEE 754).
	 * 
	 * @author Rengyr
	 * @param a dividend
	 * @param b divisor
	 * @return the value dividend / divisor
	 * @throws ArithmeticException if there is division by zero
	 */
	public static double idiv(double a, double b) {
		if (b == 0){
			throw new ArithmeticException("Division by zero");
		}
		return a / b;
	}

	/**
	 * Returns the multiplication of double arguments.
	 * 
	 * @author AdamKuba
	 * @param a multiplicand
	 * @param b multiplier
	 * @return result of multiplication
	 */
	public static double imul(double a, double b) {
		return a*b;
	}

	/**
	 * Returns the subtraction of double arguments.
	 *
	 * @author kudlav
	 * @param a minuend (the number from that is subtracted)
	 * @param b subtrahend (the number being subtracted)
	 * @return difference (result of subtraction)
	 */
	public  static double sub(double a, double b) {
		return a-b;
	}
	
	/**
	 * Returns the addition of double arguments.
	 * 
	 * @author mmusil
	 * @param a first summand
	 * @param b second summand
	 * @return a + b
	 */
	public  static double add(double a, double b) {
		return a+b;
	}

	/**
	 * Returns the Nth root implemented by the formula from <a href="https://en.wikipedia.org/wiki/Nth_root"> wikipedia</a>.
	 * 
	 * @author AdamKuba
	 * @param n degree
	 * @param a radicand
	 * @return nth root of the radicand
	 */
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
	 * Returns the n-th power of a.
	 *
	 * @author kudlav
	 * @param n exponent
	 * @param a base
	 * @return result of exponentiation (n-th power of a)
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
	 * Returns the factorial of argument. If arguments is negative, the result is -1.
	 * 
	 * @author mmusil
	 * @param a number from which is factorial counted
	 * @return factorial from a, -1 for negative argument
	 */
	public  static double fac(int a) {
		if (a>0){
			int f = a;
			while (a>1){
				f = f * (a-1);
				a--;
			}
			return f;
		}
		if(a==0){
			return 1;
		}
		else{
			return -1;
		}
	}

	/**
	 * Returns the remainder of integer arguments.
	 * 
	 * @author Rengyr
	 * @param a the dividend
	 * @param b the divisor
	 * @return remainder of dividend and divisor
	 */
	public  static int mod(int a, int b) {
		return a % b;
	}

	/**
	 * Returns the absolute value of double.
	 * 
	 * @param a number
	 * @return absolute value of argument
	 */
	private static double abs(double a){
		return (a<=0.0D) ? 0.0D - a :a;
	}
	/**
	 * Returns the Absolute value of integer.
	 *
	 * @param a number
	 * @return number which is always positive
	 */
	private  static int abs(int a){
		return (a<=0) ? 0-a : a;
	}
}
/*** End of MathLib.java file ***/