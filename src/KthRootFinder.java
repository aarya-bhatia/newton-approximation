package src;

/** This class accepts value for a number and root
 * It creates a function used to apporximate the value
 * of the k-th root of number
 */
public class KthRootFinder implements Function {

	int number = 100;
	int root = 2;

	/** constructor */
	public KthRootFinder(int number, int root) {
		if(root == 0) {
			throw new IllegalArgumentException("root cannot be zero");
		}
		this.number = number;
		this.root = root;
	}

	/** 
	 * @param x input value
	 * @return returns the value of the function at x
	 * using the equation:
	 * x = num^(1/root)
	 * f(x) = x^root - num
	 */
	@Override
       	public double eval(double x) {
		return Math.pow(x, root) - number;
	}

	/** @param x input value
	 * @return returns the value of the derivative at x
	 * using the following equation:
	 * df = (x^root - num)'
	 * (x^n)' = n*x^(n-1)
	 */
	@Override
	public  double derivative(double x) {
		return root * Math.pow(x, root-1);
	}
}
