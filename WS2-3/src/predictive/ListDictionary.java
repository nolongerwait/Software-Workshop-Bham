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
 * And has 2 member methods which could help to deal with signature and words.
 * @author zxq876 Zetian Qin
 * @version 2020-02-12 02:16:04
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
        wordSigsDic = new ArrayList<WordSig>();
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new FileInputStream(pathToFiles));
        }catch(FileNotFoundException e)
        {
            System.out.println("File words was no found");
            System.exit(0);
        }
        while(scanner.hasNextLine()) {
            StringBuffer words = new StringBuffer(scanner.nextLine().toLowerCase());
            if(isValidWord(words.toString())) {
                String signature = wordToSignature(words.toString());
                WordSig wordSigs = new WordSig(words.toString(), signature);
                this.wordSigsDic.add(wordSigs);
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
     * This method searches the dictionary using a binary search method to find a given signature.
     * This method will be more efficient than the Part1 since it do not need read the file every time and the binary search will save time in average.
     * @param signature The given signature which the words will be matched
     * @return All the words which match the given signature.
     */
    @Override
    public Set<String> signatureToWords(String signature) {
        Set<String> result = new HashSet<String>();
        WordSig searchSig = new WordSig("", signature);
        if(signature.length() == 0) {
            return result;
        }
        // using Binary Search to find all elements which match the signature.
        int index = Collections.binarySearch(this.wordSigsDic, searchSig);
        int first = index - 1;
        int last = index + 1;
        if(index >= 0) {
            result.add(this.wordSigsDic.get(index).getWords());

            // find others words which match the signature.
            while(first >= 0 && this.wordSigsDic.get(first).getSignature().equals(signature)) {
                result.add(this.wordSigsDic.get(first).getWords());
                first--;
            }
            while(last < this.wordSigsDic.size() && this.wordSigsDic.get(last).getSignature().equals(signature)) {
                result.add(this.wordSigsDic.get(last).getWords());
                last++;
            }
        }

        return result;
    }
}