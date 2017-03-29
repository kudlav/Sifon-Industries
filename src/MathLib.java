
public class MathLib {


	public static double idiv(double dividend, double divisor) {
		return 0;
	}

	/**
	 * Multiplication.
	 * @param a first number to multiplication
	 * @param b second number to multiplication
	 * @return result of multiplication
	 * */
	public static double imul(double a, double b) {
		return a*b;
	}

	public  static double sub(double a, double b) {
		return 0;
	}

	public  static double add(double a, double b) {
		return 0;
	}

	/**
	 * Nth root implemented by the formula from wikipedia.
	 * @param n number of root
	 * @param a root base
	 * @return result of nth root
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

	public  static double exp(int n, double a) {
		return 0;
	}

	public  static double fac(int a) {
		return 0;
	}

	public  static int mod(int a, int b) {
		return 0;
	}
	
	/**
	 * Absolute value.
	 * @param a number
	 * @return number which is always positive
	 * */
	private static double abs(double a){
		return (a<=0.0D) ? 0.0D - a :a;
	}
}
