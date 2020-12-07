package src;

/** interface to represent a function f(x)
 * a function must compute a y-val for some x
 * a function must compute a derivative for x
 */
public interface Function {
	/**
	 * @param x input for function
	 * @return value of function at given point
	 */
	public double eval(double x);

	/**
	 * @param x input for function
	 * @return derivate of function at x
	 */
	public double derivative(double x);
}

