/**
 * @author zxq876 Zetian Qin
 * The solution of Worksheet2.
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
			return Math.max(depth(a.left, x) == -1 ? -1 : depth(a.left, x) + 1, depth(a.right, x) == -1 ? -1 : depth(a.right, x) + 1);
		}
	}

	// Exercise 4

	static <E> List<E> preorder(Tree<E> a) {
		if(a.isEmpty()) {
			return new List<E>();
		}
		else if(!a.getLeft().isEmpty() && a.getRight().isEmpty()) {
			return new List<E>(a.getValue(), preorder(a.getLeft()));
		}
		else if(a.getLeft().isEmpty() && !a.getRight().isEmpty()) {
			return new List<E>(a.getValue(), preorder(a.getRight()));
		}
		else if(a.getLeft().isEmpty() && a.getRight().isEmpty()) {
			return new List<E>(a.getValue(), new List<E>());
		}
		else {
			return append(new List<E>(a.getValue(), preorder(a.getLeft())), preorder(a.getRight()));
		}
	}

	/**
	 * helper method, for appending the List M to the tail of List L
	 * @param L List at the beginning, List
	 * @param M LIst at the end, List
	 * @param <E> generics parameter
	 * @return new List, List
	 */
	static <E> List<E> append(List<E> L, List<E> M) {
		if(L.isEmpty()) {
			return M;
		}
		else {
			return new List<E>(L.getHead(), append(L.getTail(), M));
		}
	}


	// Exercise 5
	/**
	 * Given a tree of integers a, the method returns a boolean value indicating whether a is a binary search tree.
	 * @param a tree for input, Tree
	 * @return whether a is a binary search tree, boolean
	 */
	static boolean isSearchTree(Tree<Integer> a) {
		if(a.isEmpty() || (a.getLeft().isEmpty() && a.getRight().isEmpty())) {
			return true;
		}
		else if(a.getLeft().isEmpty() && (a.getRight().getValue() > a.getValue())) {
			return true;
		}
		else if(a.getRight().isEmpty() && (a.getLeft().getValue() < a.getValue())) {
			return true;
		}
		else if(a.getLeft().getValue() >= a.getValue() || a.getValue() >= a.getRight().getValue()) {
			return false;
		}
		else {
			return (isSearchTree(a.getLeft()) && isSearchTree(a.getRight()));
		}
	}

	// Exercise 6

	/**
	 * Given a binary search tree of integers a, write a method that prints the values stored in it in descending order
	 * @param a binary search tree, Tree
	 */
	static void printDescending(Tree<Integer> a) {
		if (a.isEmpty()) {
			System.out.print("");
		}
		else {
			printDescending(a.getRight());
			System.out.print(a.getValue() + "  ");
			printDescending(a.getLeft());
		}
	}

	// Exercise 7

	/**
	 * method to find the maximum value stored in the binary search tree a
	 * @param a binary search tree, Tree
	 * @return the maximum value in the tree a, int
	 */
	static int max(Tree<Integer> a) {
		if(a.isEmpty()) {
			return 0;
		}
		else if(a.getRight().isEmpty()) {
			return a.getValue();
		}
		else {
			return max(a.getRight());
		}
	}

	// Exercise 8
	/**
	 * the method delete the value x from a and return the resulting tree.  The original tree a not be altered. Rather, it build a new tree that contains all the values of a except for one copy of x.  The resulting tree again be a binary search tree.
	 * @param a the binary search tree needed to be searched, Tree
	 * @param x the value x needed to be deleted, int
	 * @return the new tree that contains all the values of a except for one cpr one copy of x.
	 */
	static Tree<Integer> delete(Tree<Integer> a, int x) {
		if(a.isEmpty()) {
			return a;
		}
		//have found the value note needed to be deleted
		else if(a.getValue() == x) {
			//the node without subtree
			if(a.getRight().isEmpty() && a.getLeft().isEmpty()) {
				return new Tree<Integer>();
			}
			//the node only have one subtree
			else if(!a.getLeft().isEmpty() && a.getRight().isEmpty()) {
				return a.getLeft();
			}
			else if(a.getLeft().isEmpty() && !a.getRight().isEmpty()) {
				return a.getRight();
			}
			//the node have two subtrees
			else {
				return new Tree<Integer>(min(a.getRight()), a.getLeft(), delete(a.getRight(), min(a.getRight())));
			}
		}
		//have not found the value note needed to be deleted
		else {
			return new Tree<Integer>(a.getValue(), delete(a.getLeft(), x), delete(a.getRight(), x));
		}
	}

	/**
	 * helper method, used to find the minimum value in the search Tree
	 * @param a the search tree needed to be found, Tree
	 * @return the minimum value in the tree a.
	 */
	static int min(Tree<Integer> a) {
		if(a.isEmpty()) {
			return 0;
		}
		else if(a.getLeft().isEmpty()) {
			return a.getValue();
		}
		else {
			return min(a.getLeft());
		}
	}

	// Exercise 9
	/**
	 * Given a tree a (of an arbitrary element typeE), check to see if it is height-balanced, returning a boolean value.
	 * @param a Tree as input, Tree
	 * @param <E> generic parameter
	 * @return whether the Tree is height balanced(AVL Tree), boolean
	 */
	static <E> boolean isHeightBalanced(Tree<E> a) {
		if(a.isEmpty()) {
			return true;
		}
		// calculate the BF value of the node
		else if(Math.abs(a.getRight().getHeight() - a.getLeft().getHeight()) > 1) {
			return false;
		}
		else if(isHeightBalanced(a.getLeft()) && isHeightBalanced(a.getRight())) {
			return true;
		}
		else {
			return true;
		}
	}

	// Exercise 10

	/**
	 * the methods is to insert a node and maintain the height-balanced property of  tree
	 * @param a the Tree as input, Tree
	 * @param x the value need to insert into a, int
	 * @return the result from a, Tree
	 */
	static Tree<Integer> insertHB(Tree<Integer> a, int x) {
		assert isHeightBalanced(a);
		if(a.isEmpty()) {
			return new Tree<Integer>(x);
		}
		else if(a.getValue() == x) {
			return a;
		}
		//insert into left subtree
		else if(a.getValue() > x) {
			return keepBalance(new Tree<Integer>(a.getValue(), insertHB(a.getLeft(), x), a.getRight()));
		}
		//insert into right subtree
		else if(a.getValue() < x) {
			return keepBalance(new Tree<Integer>(a.getValue(), a.getLeft(), insertHB(a.getRight(),x)));
		}
		else {
			return new Tree<Integer>();
		}
	}

	/**
	 * the methods is to delete a node and maintain the height-balanced property of  tree
	 * @param a the Tree as input, Tree
	 * @param x the value need to delete a, int
	 * @return the result from a, Tree
	 */
	static Tree<Integer> deleteHB(Tree<Integer> a, int x) {
		assert isHeightBalanced(a);
		if(a.isEmpty()) {
			return a;
		}
		//have found the value note needed to be deleted
		else if(a.getValue() == x) {
			//1. the node without subtree
			if(a.getRight().isEmpty() && a.getLeft().isEmpty()) {
				return new Tree<Integer>();
			}
			//2. the node only have one subtree
			else if(!a.getLeft().isEmpty() && a.getRight().isEmpty()) {
				return a.getLeft();
			}
			else if(a.getLeft().isEmpty() && !a.getRight().isEmpty()) {
				return a.getRight();
			}
			//3. the node have two subtrees
			else {
				return keepBalance(new Tree<Integer>(min(a.getRight()), a.getLeft(), keepBalance(delete(a.getRight(), min(a.getRight())))));
			}
		}
		//have not found the value note needed to be deleted
		else {
			return keepBalance(new Tree<Integer>(a.getValue(), keepBalance(delete(a.getLeft(), x)), keepBalance(delete(a.getRight(), x))));
		}
	}

	/**
	 * unbalance height tree as input, then method will using correct rotation and return a balance-height tree
	 * @param a the tree as input, Tree
	 * @return the result after keep the balance of Tree a, Tree
	 */
	static Tree<Integer> keepBalance(Tree<Integer> a) {
		//while the left subtree is higher than right subtree
		if(a.getRight().getHeight() - a.getLeft().getHeight() == -2) {
			//the left subtree is LL-case
			if(a.getLeft().getRight().getHeight() - a.getLeft().getLeft().getHeight() <= 0) {
				//single right rotation
				return new Tree<Integer>(a.getLeft().getValue(), a.getLeft().getLeft(), new Tree<Integer>(a.getValue(), a.getLeft().getRight(), a.getRight()));
			}
			//the left subtree is LR-case
			else {
				//left rotation then right rotation
				return new Tree<Integer>(a.getLeft().getRight().getValue(), new Tree<Integer>(a.getLeft().getValue(), a.getLeft().getLeft(), a.getLeft().getRight().getLeft()), new Tree<Integer>(a.getValue(), new Tree<Integer>(), a.getRight()));
			}
		}
		//while the right subtree is higher than left subtree
		else if(a.getLeft().getHeight() - a.getRight().getHeight() == 2) {
			//the right subtree is RR-case
			if(a.getRight().getRight().getHeight() - a.getRight().getLeft().getHeight() >= 0) {
				//single left rotation
				return new Tree<Integer>(a.getRight().getValue(), new Tree<Integer>(a.getValue(), a.getLeft(), a.getRight().getLeft()), a.getRight().getRight());
			}
			//the right subtree is RL-case
			else {
				//right rotation then left rotation
				return new Tree<Integer>(a.getRight().getLeft().getValue(), new Tree<Integer>(a.getValue(), a.getLeft(), new Tree<Integer>()), new Tree<Integer>(a.getLeft().getValue(), a.getRight().getLeft().getRight(), a.getRight().getRight()));
			}
		}
		else {
			return a;
		}
	}

}

