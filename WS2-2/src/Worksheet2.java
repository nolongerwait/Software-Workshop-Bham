/**
 * @author <PUT YOUR NAME HERE>
 * This class contains the solution for Worksheet2
 */

public class Worksheet2 implements Worksheet2Interface {

	// Exercise 1

	static Tree<Integer> negateAll(Tree<Integer> t) {
		if(t.isEmpty()) {
			return new Tree<Integer>();
		}
		else {
			return new Tree<Integer>(0 - t.getValue(), negateAll(t.getLeft()), negateAll(t.getRight()));
		}
	}

	// Exercise 2

	static boolean allEven(Tree<Integer> a) {
		if(a.isEmpty()) {
			return true;
		}
		else if(a.getValue() % 2 == 0) {
			return true && allEven(a.getLeft()) && allEven(a.getRight());
		}
		else {
			return false;
		}
	}

	// Exercise 3

	static int depth(Tree<Integer> a, int x) {
		if(a.isEmpty()) {
			return -1;
		}
		else if(a.getValue() == x) {
			return 0;
		}
		else {
			return 1 + Math.max(depth(a.getLeft(), x), depth(a.getRight(), x));
		}
	}

	// Exercise 4

	static <E> List<E> preorder(Tree<E> a) {
		return new List<E>();
	}

	// Exercise 5

	static boolean isSearchTree(Tree<Integer> a) {
		return true;
	}

	// Exercise 6

	static void printDescending(Tree<Integer> a) {
	}

	// Exercise 7

	static int max(Tree<Integer> a) {
		return 0;
	}

	// Exercise 8

	static Tree<Integer> delete(Tree<Integer> a, int x) {
		return new Tree<Integer>();
	}

	// Exercise 9
	static <E> boolean isHeightBalanced(Tree<E> a) {
		return true;
	}

	// Exercise 10

	static Tree<Integer> insertHB(Tree<Integer> a, int x) {
		return new Tree<Integer>();
	}

	static Tree<Integer> deleteHB(Tree<Integer> a, int x) {
		return new Tree<Integer>();
	}

}

