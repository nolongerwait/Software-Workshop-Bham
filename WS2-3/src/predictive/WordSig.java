package predictive;

public class WordSig implements Comparable<WordSig> {
    private String words;
    private String signature;

    /**
     * The constructor of the class WordSig.
     * @param words the given words
     * @param signature the signature of the words.
     */
    public WordSig(String words, String signature) {
        this.words = words;
        this.signature = signature;
    }

    /**
     * The getter of the field variable words
     * @return the field variable words, as String.
     */
    public String getWords() {
        return this.words;
    }

    /**
     * The getter of the field variable signature
     * @return the field variable signature, as String.
     */
    public String getSignature() {
        return this.signature;
    }

    /**
     * Compares this object with the specified object for order. Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     * @param ws the object to be compared
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(WordSig ws) {
        if(this.getSignature().equals(ws.getSignature())) {
            return this.getWords().compareTo(ws.getWords());
        }
        else {
            return this.getSignature().compareTo(ws.getSignature());
        }
    }
}