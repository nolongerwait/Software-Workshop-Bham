package predictive;

public class Sigs2WordsMap {
    public static void main(String[] args) {
        MapDictionary dic = new MapDictionary("/usr/share/dict/words");

        for(String itor:args) {
            System.out.println(itor + " : " + dic.signatureToWords(itor));
        }
    }
}