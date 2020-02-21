package predictive;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * The MapDictionary class has 1 field variable, sigWordsDic, to store the word that has been read in and its signature.
 * And has 4 member methods which could help to deal with signature and words.
 * @author zxq876 Zetian Qin
 * @version 2020-02-12 02:16:04
 */
public class MapDictionary implements Dictionary {
    private Map<String, Set<String>> sigWordsDic;

    // The static variable keyboard to store the charactar of each key.
    static ArrayList<String> keyboard = new ArrayList<String>(Arrays.asList("1", "2abc", "3def", "4ghi", "5jkl", "6mno", "7pqrs", "8tuv", "9wxyz", "*", "#"));

    /**
     * the consturctor of MapDictionary
     * @param pathToFiles the path to the files
     */
    public MapDictionary(String pathToFiles) {
        sigWordsDic = new HashMap<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream(pathToFiles));
        } catch(FileNotFoundException e) {
            System.out.println("File words was no found");
            System.exit(0);
        }
        while(scanner.hasNextLine()) {
            StringBuffer words = new StringBuffer(scanner.nextLine().toLowerCase());
            if(isValidWord(words.toString())) {
                storeMap(this.sigWordsDic, words);
            }
        }
        scanner.close();
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
     * The method takes a word and returns a numeric signature.
     * Since using StringBuffer, it could be more efficient.
     * @param word The word we used daily
     * @return The signature of the word, as String type
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
     * This method is to help store the words in the same Set when they matches same signature.
     * @param sigWordsDic to store the word that has been read in and its signature.
     * @param words the words which will be stored.
     */
    public void storeMap(Map<String, Set<String>> sigWordsDic, StringBuffer words) {
        String signature = wordToSignature(words.toString());
        if(sigWordsDic.containsKey(signature)) {
            sigWordsDic.get(signature).add(words.toString());
        }
        else {
            Set<String> result = new HashSet<String>();
            result.add(words.toString());
            sigWordsDic.put(signature, result);
        }
    }

    /**
     * This method searches the dictionary using a binary search method to find a given signature.
     * @param sigature The given signature which the words will be matched
     * @return All the words which match the given signature.
     */
    @Override
    public Set<String> signatureToWords(String signature){
        if(this.sigWordsDic.containsKey(signature)) {
            return sigWordsDic.get(signature);
        }
        else {
            return new HashSet<String>();
        }
    }
}