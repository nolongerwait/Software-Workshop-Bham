package predictive;

public class Sigs2WordsList {
    public static void main(String[] args) {
        ListDictionary dic = new ListDictionary("/usr/share/dict/words");

        for(String itor:args) {
            System.out.println(itor + " : " + dic.signatureToWords(itor));
        }
    }
}