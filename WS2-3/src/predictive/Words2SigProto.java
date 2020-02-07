package predictive;

/**
 * This class Words2SigProto just provide a command-line programs for calling the wordToSignature() method in PredictivePrototype class.
 * @author zxq876 Zetian Qin
 * @version 2020-02-07 22:57:14
 */
public class Words2SigProto {
    public static void main(String[] args) {
        for(String itor:args) {
            System.out.println(itor + " : " + PredictivePrototype.wordToSignature(itor));
        }
    }
}