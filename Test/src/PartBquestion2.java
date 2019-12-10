import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartBquestion2 {

    /**
     * HasDigit method could check whether the String contains digit or not.
     * @param content The string which would be checked, as String.
     * @return Whether the String contains digit or not, true - if the string contains digits, false - if the string does not contain digits, as boolean.
     */
    public static boolean HasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * SubStatements method could process each separated sub-statement.
     * @param str The sub-statement separated, as String.
     * @param storeVariables The Map container that holds variables and values, as Map.
     */
    public static void subStatements(String str, Map<Character,Integer> storeVariables) {
        String strCopy = str.replaceAll(" ", "");
        if(strCopy.contains("+")) {
            if(HasDigit(strCopy)) {
                String number1 = strCopy.substring(strCopy.indexOf("=") + 1, strCopy.indexOf("+") - 1);
                String number2 = strCopy.substring(strCopy.indexOf("+") + 1, strCopy.length());
                storeVariables.put(strCopy.charAt(0), Integer.parseInt(number1) + Integer.parseInt(number2));
            }
            else {
                storeVariables.put(strCopy.charAt(0),storeVariables.get(strCopy.charAt(2)) + storeVariables.get(strCopy.charAt(4)));
            }
        }
        else {
            String number = strCopy.substring(2, strCopy.length());
            storeVariables.put(strCopy.charAt(0),Integer.parseInt(number));
        }


    }

    /**
     * ComputeTheResult method could achieve the function which is required in Part B question 2.
     * This method could achieve mini-interpreter for a toy programming language and return the result.
     * @param str The input of the toy programming language, as String.
     * @return The result of the toy programming language, as int.
     */
    public static int computeTheResult(String str) {
        int result = 0;
        Map<Character, Integer> storeVariables = new HashMap<Character, Integer>();
        String[] subStatement = str.split("\n");
        for(int i = 0; i < subStatement.length; i++) {
            if(subStatement[i].length() == 1) {
                result = storeVariables.get(subStatement[i].charAt(0));
            }
            else {
                subStatements(subStatement[i], storeVariables);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "A = 2\nB = 8\nC = A + B\nC\n";
        int result = computeTheResult(str);
        System.out.println(result);
    }
}