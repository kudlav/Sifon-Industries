
public class MathLib {


	public static double idiv(double dividend, double divisor) {
		return 0;
	}

	public static double imul(double a, double b) {
		return 0;
	}

	public  static double sub(double a, double b) {
		return 0;
	}
        
        /**
         * Addition
         * 
         * @param a First number
         * @param b The number which is added to the forst one
         * @return a + b
         * 
         * @author mmusil
         */
	public  static double add(double a, double b) {
		return a+b;
	}

	public  static double nRoot(int n, double a) {
		return 0;
	}

	public  static double exp(int n, double a) {
		return 0;
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

	public  static int mod(int a, int b) {
		return 0;
	}

}
