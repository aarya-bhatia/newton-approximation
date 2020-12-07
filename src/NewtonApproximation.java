package src;

import java.util.Scanner;

/** Main class to launch program */
public class NewtonApproximation {

	private static Scanner scanner = new Scanner(System.in);

	/** prompts user and accepts integer input from user
	 * @param message message to display
	 * @return user input
	 */
	public static int prompt(String message) {
		System.out.print(message);
		int input = scanner.nextInt();
		input = Math.abs(input); // accept only positive value
		return input;
	}

	public static Solution polynomialRootSolution() {
		Function p = Polynomial.inputPolynomial();
		int places = prompt("Enter no. of decimal places:");
		Solution solution = new Solution(p, places);
		return solution;
	}

	public static Solution rootFinderSolution() {
		try {
			int number = prompt("Enter a number:");
			int root = prompt("Enter the root:");
			int places = prompt("Enter no. of decimal places:");
			try {
				Function krf = new KthRootFinder(number, root);
				Solution solution = new Solution(krf, places);
				return solution;
			} catch(IllegalArgumentException ex) {
				System.out.println("Error: " + ex.getMessage());
				return rootFinderSolution();
			}
		} catch(NumberFormatException ex) {
			System.out.println("Error: " + ex.getMessage());
			System.out.println("##############");
			return rootFinderSolution();
		}
	}
		
	/** main method */
	public static void main(String args[]){
		Solution solution = null;
		System.out.print("Do you want to find the k-th of an integer? (y/n)");
		char option = scanner.next().charAt(0);
		if(option == 'y' || option == 'Y') { 
			solution = rootFinderSolution();
		}
		if(solution != null) {
			System.out.println("###################");
			double result = solution.solve();
			System.out.println("result: " + result);
			System.out.println("###################");
		}
		solution = null;
		System.out.print("Do you want to approximate the root of a polynomial? (y/n)");
		option = scanner.next().charAt(0);
		if(option == 'y' || option == 'Y') { 
			solution = polynomialRootSolution();
		}
		if(solution != null) {
			System.out.println("###################");
			double result = solution.solve();
			System.out.println("result: " + result);
			System.out.println("###################");
		}
	}
}

