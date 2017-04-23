import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

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
	public static BigDecimal idiv(BigDecimal a, BigDecimal b) {
		if (b.doubleValue() == 0){
			throw new ArithmeticException("Division by zero");
		}
		return a.divide(b,15, RoundingMode.HALF_UP);
	}

	/**
	 * Returns the multiplication of double arguments.
	 * 
	 * @author AdamKuba
	 * @param a multiplicand
	 * @param b multiplier
	 * @return result of multiplication
	 */
	public static BigDecimal imul(BigDecimal a, BigDecimal b) {
		return a.multiply(b);
	}

	/**
	 * Returns the subtraction of double arguments.
	 *
	 * @author kudlav
	 * @param a minuend (the number from that is subtracted)
	 * @param b subtrahend (the number being subtracted)
	 * @return difference (result of subtraction)
	 */
	public static BigDecimal sub(BigDecimal a, BigDecimal b) {
		return a.subtract(b);
	}
	
	/**
	 * Returns the addition of double arguments.
	 * 
	 * @author mmusil
	 * @param a first summand
	 * @param b second summand
	 * @return a + b
	 */
	public static BigDecimal add(BigDecimal a, BigDecimal b) {
		return a.add(b);
	}

	/**
	 * Returns the Nth root implemented by the formula from <a href="https://en.wikipedia.org/wiki/Nth_root"> wikipedia</a>.
	 * 
	 * @author AdamKuba
	 * @param n degree
	 * @param a radicand
	 * @return nth root of the radicand
	 */
	public static BigDecimal nRoot(BigInteger n, BigDecimal a) {
		boolean signed = false;
		if (n.doubleValue()<0){
			n = n.negate();
			signed = true;
		}
		if(signed && a.doubleValue()<0){
			return new BigDecimal("-1");
		}
		double p = 0.00000000001;
		BigDecimal prev=a;
		BigDecimal next=a.divide(new BigDecimal(n),15, RoundingMode.HALF_UP);
		
		while(abs(prev.subtract(next)).doubleValue()>p){
			prev = next;
			next = (new BigDecimal(n.subtract(new BigInteger("1"))).multiply(prev).add(a.divide(exp(n.subtract(new BigInteger("1")),prev),15, RoundingMode.HALF_UP))).divide(new BigDecimal(n),15, RoundingMode.HALF_UP);
		}
		if (signed) next = new BigDecimal("1").divide(next,15, RoundingMode.HALF_UP);
		next = next.round(new MathContext(10));
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
	public static BigDecimal exp(BigInteger n, BigDecimal a) {
		BigDecimal result = new BigDecimal("1");
		BigInteger exp = abs(n);

		for(int i = 0; i < exp.doubleValue(); i++) {
			result = result.multiply(a);
		}

		return (n.doubleValue() < 0) ? new BigDecimal("1").divide(result) : result;
	}

	/**
	 * Returns the factorial of argument. If arguments is negative, the result is -1.
	 * 
	 * @author mmusil
	 * @param a number from which is factorial counted
	 * @return factorial from a, -1 for negative argument
	 */
	public static BigDecimal fac(int a) {
		if (a>0){
			double f = a;
			while (a>1){
				f = f * (a-1f);
				a--;
			}
			return new BigDecimal(f);
		}
		if(a==0){
			return new BigDecimal("1");
		}
		else{
			return new BigDecimal("-1");
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
	public static BigDecimal mod(BigInteger a, BigInteger b) {
		return new BigDecimal(a.mod(b));
	}

	/**
	 * Returns the absolute value of double.
	 * 
	 * @param a number
	 * @return absolute value of argument
	 */
	private static BigDecimal abs(BigDecimal a){
		return (a.doubleValue()<=0.0D) ? new BigDecimal("0").subtract(a) :a;
	}
	/**
	 * Returns the Absolute value of integer.
	 *
	 * @param a number
	 * @return number which is always positive
	 */
	private static BigInteger abs(BigInteger a){
		return (a.doubleValue()<=0) ? new BigInteger("0").subtract(a) : a;
	}
}
/*** End of MathLib.java file ***/