package predictive;

public class Sigs2WordsTree {
    public static void main(String[] args) {
        TreeDictionary dic = new TreeDictionary("/usr/share/dict/words");

        for(String itor:args) {
            System.out.println(itor + " : " + dic.signatureToWords(itor));
        }
    }
}