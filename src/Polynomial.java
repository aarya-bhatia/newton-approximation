package src;

import java.util.Scanner;

/** class to compute the root of polynomial functions */
public class Polynomial implements Function {

	int degree;
	float[] coeff;
	Function f;

	/** constructor 
	 * @param degree degree tells the highest power of x 
	 * in polynomial functon f(x)
	 * @param coeff this stores the coefficient of each
	 * term in the polynomial expansion
	 */
	public Polynomial(int degree, float coeff[]) {
		this.degree = degree;
		this.coeff = coeff;
	}

	/** takes input for degree and coefficients of polynomial function
	 * @return a polynomial function inistialized with corresponding data
	 */
	public static Function inputPolynomial() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter degree of polynomial:");
		int degree = scan.nextInt();
		degree = degree > 0 ? degree : degree*-1; // modulus
		float[] coff = new float[degree+1];
		for(int i = 0; i <= degree; i++) {
			System.out.print("Enter coefficient of x^"+i+":");
			coff[i] = scan.nextFloat();	
		}

		Function p = new Polynomial(degree, coff);
		System.out.println("The polynomial function is " + p.toString());
		return p;
	}

	/** accept polyomial as input and return the corresponding function
	 * @param s string in the form "a1 a2 a3 ...an" where a1,a2,an are the cofficients
	 * of x with degrees from 0 to n
	 * @return polynomial function corresponding to input
	 */
	public static Function parseStringToFunction(String s) {
		return null;
	}

	/** @return string representation of polynomial function */
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder("f(x) = ");
		for(int i = 0; i < coeff.length; i++){
			String currentTerm = String.valueOf(coeff[i]) + "x^" + String.valueOf(i);
			out.append(currentTerm);
			if(i < coeff.length - 1) {
				out.append(" + ");
			}
		}
		return out.toString();
	}

	/** @return value of function at x */
	@Override
	public double eval(double x) {
		double tot = 0.0;
		int index = 0;
		for(float cf: coeff) {
			tot += cf * Math.pow(x, index++);
		}
		return tot;
	}

	/** @return derivative of function at x */
	@Override
	public double derivative(double x) {
		double tot = 0.0;
		for(int i = 0; i < coeff.length; i++) {
			tot += coeff[i] * i * Math.pow(x, i-1);
		}
		return tot;
	}
}
