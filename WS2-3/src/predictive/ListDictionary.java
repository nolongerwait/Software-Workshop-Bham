package predictive;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * The ListDictionary class has 1 field variable, wordSigsDic, to store the word that has been read in and its signature.
 * And has 
 */
public class ListDictionary implements Dictionary {

    private ArrayList<WordSig> wordSigsDic;

    // The static variable keyboard to store the charactar of each key.
    static ArrayList<String> keyboard = new ArrayList<String>(Arrays.asList("1", "2abc", "3def", "4ghi", "5jkl", "6mno", "7pqrs", "8tuv", "9wxyz", "*", "#"));
    
    /**
     * The consturctor of ListDictionary takes a String path to the dictionary, reads stores it in an ArrayList. Each entry of the ArrayList must be a pair, consisting of the word that has been read in and its signature.
     * @param pathToFiles the path to the Words file.
     */
    public ListDictionary(String pathToFiles) {
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new
            FileInputStream(pathToFiles));
        }catch(FileNotFoundException e)
        {
            System.out.println("File words was no found");
            System.exit(0);
        }
        while(scanner.hasNextLine()) {
            StringBuffer words = new StringBuffer(scanner.nextLine().toLowerCase());
            if(isValidWord(words.toString())) {
                String signature = wordToSignature(words.toString());
                WordSig wordSig = new WordSig(words.toString(), signature);
                wordSigsDic.add(wordSig);
            }
        }
        scanner.close();
        Collections.sort(wordSigsDic);
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
     * Since using StringBuffer, it could be more........
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


    public Set<String> signatureToWords(String signature) {
        Set<String> result = new HashSet<String>();
        int index = Collections.binarySearch(wordSigsDic, signature);
    }
}