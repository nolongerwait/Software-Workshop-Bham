package predictive;


/**
 * This class Sigs2WordsProto just provide a command-line programs for calling the signatureToWords() method in PredictivePrototype class.
 * @author zxq876 Zetian Qin
 * @version 2020-02-07 22:57:14
 */
public class Sigs2WordsProto {
    public static void main(String[] args) {
        for(String itor:args) {
            System.out.println(itor + " : " + PredictivePrototype.signatureToWords(itor));
        }
    }
}