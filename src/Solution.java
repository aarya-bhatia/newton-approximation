package src;

/** Main calculations take place here */
public class Solution {

	Function function;
	int places = 8;

	public Solution(Function function, int places) {
		this.function = function;
		this.places = places;
	}

	/**
	 * @param places number of decimal places to round off to
	 * @param number number to round off
	 */
	private double toFixed(double number) {
		double factor = Math.pow(10, places);
		return (double) Math.round(number*factor)/factor;
	}

	/**
	 * Formula
	 * x(n+1) = x(n) - f(x(n)) / f'(x(n))
	 *
	 * @param x previous value of x
	 * @param f function to obtain f(x) and f'(x) 
	 * @return next value of x using formula
	 */
	private double nextIter(double x, Function f) {
		double der = f.derivative(x);
		if(der == 0) { throw new ArithmeticException("division by 0"); }
		double temp = f.eval(x)/der;
		return x - temp;
	}

	/** @return applies newton method to approximate real root of function
	 */
	public double solve() {
		double x = 1; // initial value
		int iterCount = 0;
		while(true) {
			if(iterCount > 100) {
				System.out.println("Solution not found");
				return -999;
			}

			System.out.println("current iteration : " + x);
			double xnew;
			try {
				xnew = nextIter(x, function);
				xnew = toFixed(xnew);
			} catch(ArithmeticException ex) {
				System.out.println("Error: " + ex.getMessage());
				return -999;
			}

			if(xnew == x) {
				System.out.println("Solution found");
				return x;
			}
			x = xnew;
		}
	}
}
