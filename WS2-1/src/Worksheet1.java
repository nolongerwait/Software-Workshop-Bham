/**
 * This class contains all Exercise in WorkSheet 1 of teaching term 2
 * @author Zetian Qin zxq876
 * @version 2020-01-16 15:10:19
 */

public class Worksheet1 {

	// Exercise 1

	static int power(int m, int n) {
		if(m == 0 && n == 0) {
			throw new IllegalArgumentException();
		}
		else if(n == 0) {
			return 1;
		}
		else {
			return m * power(m, n - 1);
		}
	}

	static int fastPower(int m, int n) {
		if(m == 0 && n == 0) {
			throw new IllegalArgumentException();
		}
		else if(n == 0) {
			return 1;
		}
		else if(n % 2 == 0) {
			return fastPower(m, n/2) * fastPower(m, n/2);
		}
		else {
			return m * fastPower(m, n - 1);
		}
	}

	// Exercise 2

	static List<Integer> negateAll(List<Integer> a) {
		return new List<Integer>(); // replace this by your code
	}

	// Exercise 3

	static int find(int x, List<Integer> a) {
		return 0; // replace this by your code
	}

	// Exercise 4

	static boolean allEven(List<Integer> a) {
		return true; // replace this by your code
	}

	// Exercise 5

	static List<Integer> evenNumbers(List<Integer> a) {
		return new List<Integer>(); // replace this by your code
	}

	// Exercise 6

	static boolean sorted(List<Integer> a) {
		return true; // replace this by your code
	}

	// Exercise 7

	static List<Integer> merge(List<Integer> a, List<Integer> b) {
		return new List<Integer>(); // replace this by your code
	}

	// Exercise 8

	static List<Integer> removeDuplicates(List<Integer> a) {
		return new List<Integer>(); // replace this by your code
	}

}
