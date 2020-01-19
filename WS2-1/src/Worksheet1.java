/**
 * This class contains all Exercise in WorkSheet 1 of teaching term 2
 * @author Zetian Qin zxq876
 * @version 2020-01-16 15:10:19
 */

public class Worksheet1 {

	// Exercise 1

	static int power(int m, int n) {
		if(m == 0 && n == 0) {
			throw new IllegalArgumentException("O to the power of 0 is undefined!");
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
			throw new IllegalArgumentException("O to the power of 0 is undefined!");
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
		if(a.isEmpty()) {
			return new List<Integer>();
		}
		else {
			return new List<Integer>(0 - a.getHead(), negateAll(a.getTail()));
		}
	}

	// Exercise 3

	static int find(int x, List<Integer> a) {
		if(a.isEmpty()) {
			throw new IllegalArgumentException(x + "does not appear in the list!");
		}
		else if(a.getHead() == x) {
			return 0;
		}
		else {
			return 1 + find(x, a.getTail());
		}
	}

	// Exercise 4

	static boolean allEven(List<Integer> a) {
		if(a.isEmpty()) {
			return true;
		}
		else if(a.getHead() % 2 == 0) {
			return true && allEven(a.getTail());
		}
		else {
			return false;
		}
	}

	// Exercise 5

	static List<Integer> evenNumbers(List<Integer> a) {
		if(a.isEmpty()) {
			return new List<Integer>();
		}
		else if(a.getHead() % 2 == 0) {
			return new List<Integer>(a.getHead(), evenNumbers(a.getTail()));
		}
		else {
			return evenNumbers(a.getTail());
		}
	}

	// Exercise 6

	static boolean sorted(List<Integer> a) {
		if(a.isEmpty() || a.getTail().isEmpty()) {
			return true;
		}
		else if(a.getHead() > a.getTail().getHead()) {
			return true && sorted(a.getTail());
		}
		else {
			return false;
		}
	}

	// Exercise 7

	static List<Integer> merge(List<Integer> a, List<Integer> b) {
		if(a.isEmpty()) {
			return b;
		}
		if(b.isEmpty()) {
			return a;
		}

		if(a.getHead() >= b.getHead()) {
			return new List<Integer>(a.getHead(), merge(a.getTail(), b));
		}
		else {
			return new List<Integer>(b.getHead(), merge(a, b.getTail()));
		}
	}

	// Exercise 8

	static List<Integer> removeDuplicates(List<Integer> a) {
		if(a.isEmpty()) {
			return new List<Integer>();
		}
		else if(a.getTail().isEmpty()) {
			return new List<Integer>(a.getHead(), new List<Integer>());
		}
		else if(a.getHead() == a.getTail().getHead()) {
			return removeDuplicates(a.getTail());
		}
		else {
			return new List<Integer>(a.getHead(), removeDuplicates(a.getTail()));
		}
	}
}