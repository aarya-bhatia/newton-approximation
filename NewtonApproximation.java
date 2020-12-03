import java.util.Scanner;

class NewtonApproximation {

	private static interface Function {

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

	/* class to compute the root of polynomial functions */
	private static class Polynomial extends Solution {
		
		int degree;
		int[] coefficients;
		Function f;

		public Polynomial() {

		}

		public static Polynomial create(Function func) {
			Polynomial p = new Polynomial(func);
			return p;
		}

		public static void input() {
			Scanner scan = new Scanner(System.in);
			System.out.print("Enter degree of polynomial:");
			int degree = scan.nextInt();
			degree = degree > 0 ? degree : degree*-1; // modulus
			int[] coff = new int[degree+1];
			for(int i = 0; i <= degree; i++) {
				System.out.print("Enter coefficient of x^"+i+":");
				coff[i] = scan.nextInt();	
			}
		}

		/* TODO */

		/** accept polyomial as input and return the corresponding function
		 * @param s string in the form "a1 a2 a3 ...an" where a1,a2,an are the cofficients
		 * of x with degrees from 0 to n
		 * @return polynomial function corresponding to input
		 */
		public static Function parseStringToFunction(String s) {
			/*
			 */
		}

	}

	/* Main calculations take place here */
	private static class Solution {
		int number = 100;
		int root = 2;
		int places = 8;
		
		Function f = Solution.createFunction(number,root);

		private Solution(int n, int r, int p) {
			number = n;
			root = r;
			places = p;
		}

		private static Function createFunction(int number, int root) {
			return new Function() {

				/* x = num^(1/root)
				 * f(x) = x^root - num
				 */

				@Override
				public double eval(double x) {
					return Math.pow(x, root) - number;
				}

				/* df = (x^root - num)'
				 * (x^n)' = n*x^(n-1)
				 */
				
				@Override
				public  double derivative(double x) {
					return root * Math.pow(x, root-1);
				}
			};
		}

		/**
		 * @param places number of decimal places to round off to
		 * @param number number to round off
		 */
		private static double toFixed(double number, int places) {
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
		private static double nextIter(double x, Function f) {
			double temp = f.eval(x)/f.derivative(x);
			return x - temp;
		}

		/** @return returns the k-th root of number using newton method
		 */
		public double findRoot() {
			double x = 1; // initial value
			int iterCount = 0;
			while(true) {
				if(iterCount > 100) {
					System.out.println("Solution not found");
					return -999;
				}

				System.out.println("current iteration : " + x);
				double xnew = nextIter(x, f);
				xnew = toFixed(xnew, places);
				
				if(xnew == x) {
					System.out.println("Solution found");
					return x;
				}
				x = xnew;
			}
		}

		public void setFunction(Function f) {
			this.f = f;
		}

		public static Solution create(int number, int root, int places) {
			if(root == 0) {
				throw new IllegalArgumentException("root cannot be zero");
			}
			Solution s = new Solution(number, root, places);
			Function f = createFunction(number, root);
			s.setFunction(f);
			return s;
		}
	}

	static Scanner scanner = new Scanner(System.in);

	public static int prompt(String message) {
		System.out.print(message);
		int input = scanner.nextInt();
		input = Math.abs(input); // accept only positive value
		return input;
	}

	public static Solution input() {
		try {
			int number = prompt("Enter a number:");
			int root = prompt("Enter the root:");
			int places = prompt("Enter no. of decimal places:");
			return Solution.create(number, root, places);
		} catch(NumberFormatException e) {
			System.out.println("Invalid input!");
			System.out.println("##############");
			return input();
		}
	}
		
	/* main method */
	public static void main(String args[]){
		Solution solution = null;

		try {
			solution = input();
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		if(solution != null) {
			int number = solution.number;
			int root = solution.root;
			int places = solution.places;
			System.out.println("input:" + number);
			System.out.println("root:" + root);
			System.out.println("decimal places:" + places);
			System.out.println("Finding the root of " + number);
			System.out.println("###################");
			double result = solution.findRoot();
			System.out.println("result: " + result);
		}
	}

	public static void test() {
		Function f = new Function() {
			public double eval(double x) {
				return 3*Math.pow(x,4) - 8*Math.pow(x,3) + 2;
			}

			public double derivate(double x) {
				return 12*Math.pow(x,3) - 24*Math.pow(x,2);
			}
		};

	//	Solution s = Solution.create(f);
		
	}
}

