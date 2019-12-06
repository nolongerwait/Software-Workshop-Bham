import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PartBquestion2 {

    public void subStatements(String str, Map<Character,Integer> storeVariables) {
        str.replace(" ", "");
        

    }

    public int computeTheResult(String str) {
        int result = 0;
        Map<Character, Integer> storeVariables = new HashMap<Character, Integer>();
        String[] subStatement = str.split("\n");
        for(int i = 0; i < subStatement.length; i++) {
            if(subStatement[i].length() == 1) {
                result = storeVariables.get(subStatement[i].charAt(0));
            }
            else {

            }
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "A\nB\nC\n";
        String[] substr = str.split("\n");
        System.out.println(Arrays.toString(substr));
    }
}