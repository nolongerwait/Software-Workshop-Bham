package predictive;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * The PredictivePrototype has two method wordToSignature() and signatureToWords(), which could help to deal with signature and words.
 * @author zxq876 Zetian Qin
 * @version 2020-02-06 12:51:43
 */

public class PredictivePrototype {
    // The static variable keyboard to store the charactar of each key.
    static ArrayList<String> keyboard = new ArrayList<String>(Arrays.asList("1", "2abc", "3def", "4ghi", "5jkl", "6mno", "7pqrs", "8tuv", "9wxyz", "*", "#"));

    /**
     * The method takes a word and returns a numeric signature.
     * Since using StringBuffer, it could be more........
     * @param word The word we used daily
     * @return The signature of the word, as String type
     */
    public static String wordToSignature(String word) {
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
     * This method could help check if a given word is valid.
     * Word with non-alphabetic characters is not valid.
     * @param word The given word
     * @return True,the word is valid. And False, the word is not valid.
     */
    static boolean isValidWord(String word) {
        String regex = "^[a-zA-Z]+$";
        return word.matches(regex);
    }

    /**
     * This method takes the given numeric signature and returns a set of possible matching words from the dictionary. 
     * The returned list must not have duplicates and each word should be in lower-case.
     * @param signature The given signature which the words will be matched
     * @return All the words which match the given signature.
     */
    public static Set<String> signatureToWords(String signature) {
        Set<String> result = new HashSet<String>();
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new
            FileInputStream("/Users/welkin/eclipse-workspace/WS2-3/src/predictive/word"));
        }catch(FileNotFoundException e)
        {
            System.out.println("File word was no found");
            System.exit(0);
        }
        while(scanner.hasNextLine()) {
            StringBuffer word = new StringBuffer(scanner.nextLine());
            if(isValidWord(word.toString())) {
                StringBuffer tempSignature = new StringBuffer(wordToSignature(word.toString()));
                if(tempSignature.toString().equals(signature)) {
                    result.add(word.toString());
                }
            }
        }
        scanner.close();
        return result;
    }
}