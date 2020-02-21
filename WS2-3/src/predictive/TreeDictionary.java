package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * The TreeDictionary class has 1 field variable, sigWordsDic, to store the word that has been read in and its signature.
 * And has 4 member methods which could help to deal with signature and words.
 * @author zxq876 Zetian Qin
 * @version 2020-02-12 02:16:04
 */
public class TreeDictionary implements Dictionary {
    private Node root;
    
    // The static variable keyboard to store the charactar of each key.
    static ArrayList<String> keyboard = new ArrayList<String>(Arrays.asList("1", "2abc", "3def", "4ghi", "5jkl", "6mno", "7pqrs", "8tuv", "9wxyz", "*", "#"));

	/**
	 * This is the inner class help us to create the node of a tree
	 */
	private class Node {
		public TreeSet<String> words;
		public TreeMap<Character, Node> next;

		/**
		 * This is a constructor of Node. It contains the words and points next node.
		 * 
		 * @param words the words we store in a node
		 */
		public Node(TreeSet<String> words) {
			this.words = words;
			next = new TreeMap<>();
		}

		/**
		 * This is a Constructor of node that we override. It contains the empty set and
		 * points next node.
		 */
		public Node() {
			words = new TreeSet<>();
			next = new TreeMap<>();
		}

		/**
		 * This is a method update the words of node
		 * 
		 * @param word the word we want to update.
		 * @return the new set of word
		 */
		public TreeSet<String> setWords(String word) {
			this.words.add(word);
			return words;
		}

		/**
		 * This is a method to get the words of node
		 * 
		 * @return the set of words of node
		 */
		public TreeSet<String> getWords() {
			return this.words;
		}
	}

	/**
	 * This is a constructor of TreeDictionary This method take the path of
	 * dictionary and read all word and corresponding signature and stores them
	 * 
	 * @param path the path of the dictionary.
	 */
	public TreeDictionary(String path) {
		root = new Node();
		String temp;
		try {
			Scanner sc = new Scanner(new File(path));
			while (sc.hasNextLine()) {
				temp = sc.nextLine().toLowerCase();
				if (temp.matches("[a-zA-Z]+")) {
					add(wordToSignature(temp), temp);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is a method that add a node to the TreeDictionary. This is the help
	 * method of construct TreeDictionary
	 * 
	 * @param signature the signature of word
	 * @param word      the word we want to add in node.
	 */
	public void add(String signature, String word) {
		Node cur = root;
		for (int i = 0; i < signature.length(); i++) {
			char c = signature.charAt(i);
			cur.next.putIfAbsent(c, new Node());
			cur = cur.next.get(c);
		}
		cur.setWords(word);
	}

	/**
	 * This is a method that can turn the word to the correspond signature
	 * 
	 * @param word the word we takes
	 * @return a numeric signature
	 */
	public String wordToSignature(String word) {
        StringBuffer result = new StringBuffer();
        StringBuffer wordBuffer = new StringBuffer(word.toLowerCase());

        for(int i = 0; i < wordBuffer.length(); i++) {
            for(String itor:keyboard) {
                if(itor.contains("" + wordBuffer.charAt(i))) {
                    result.append(itor.charAt(0));
                    break;
                }
            }
        }

        return result.toString();
	}

	/**
	 * This method help us find a set of possible matching words from dictionary
	 * This is the help method of signatureToWords
	 * 
	 * @param prefix the numeric signature that we take
	 * @param cur    the node we search
	 * @param index  the index of signature
	 * @return
	 */
	public Set<String> findWords(String prefix, Node cur, int index) {
		if (index == prefix.length())
			return cur.getWords();
		char c = prefix.charAt(index);
		while (!cur.next.containsKey(c)) {
			if (cur.next.isEmpty()) {
				return new TreeSet<String>();
			}
			cur = cur.next.get(c);
		}
		index++;
		return findWords(prefix, cur.next.get(c), index);
	}

	/**
	 * This is a method turn the signature to the words
	 * 
	 * @param prefix the numeric signature that we take
	 * @return a set of possible matching words from dictionary.
	 */
	@Override
	public Set<String> signatureToWords(String prefix) {
		Node cur = root;
		int index = 0;
		if (prefix.matches("[2-9]*")) {
			return findWords(prefix, cur, index);
		} else
			return new TreeSet<>();
	}
}
