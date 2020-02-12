package predictive;

public class Sigs2WordsList {
    public static void main(String[] args) {
        ListDictionary dic = new ListDictionary("/Users/welkin/eclipse-workspace/WS2-3/src/predictive/words");

        for(String itor:args) {
            System.out.println(itor + " : " + dic.signatureToWords(itor));
        }
    }
}