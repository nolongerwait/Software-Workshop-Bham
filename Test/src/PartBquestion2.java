import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This program designed for the Question 2 of Part B in Assignment 3 Algorithm Design & Analysis (and Revision).
 * <pre>
 * </pre>
 * @author Zetian Qin zxq876
 * @version 2019-12-11 16:58:09
 */
public class PartBQuestion2 {

    /**
     * HasDigit method could check whether the String contains digit or not.
     * @param content The string which would be checked, as String.
     * @return Whether the String contains digit or not, [true - if the string contains digits, false - if the string does not contain digits], as boolean.
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
            String addend1 = strCopy.substring(strCopy.indexOf("=") + 1, strCopy.indexOf("+"));
            String addend2 = strCopy.substring(strCopy.indexOf("+") + 1, strCopy.length());
            // Both two addends have digits.
            if(HasDigit(addend1)&&HasDigit(addend2)) {
                storeVariables.put(strCopy.charAt(0), Integer.parseInt(addend1) + Integer.parseInt(addend2));
            }
            // Only addend1 has digits.
            else if(HasDigit(addend1)) {
                // Check if there is a negative sign before the variable(addend2).
                if(addend2.contains("-")) {
                    storeVariables.put(strCopy.charAt(0), Integer.parseInt(addend1) - storeVariables.get(addend2.charAt(1)));
                }
                else {
                    storeVariables.put(strCopy.charAt(0), Integer.parseInt(addend1) + storeVariables.get(addend2.charAt(0)));
                }
            }
            // Only addend2 has digits.
            else if(HasDigit(addend2)) {
                // Check if there is a negative sign before the variable(addend1).
                if(addend1.contains("-")) {
                    storeVariables.put(strCopy.charAt(0), Integer.parseInt(addend2) - storeVariables.get(addend1.charAt(1)));
                }
                else {
                    storeVariables.put(strCopy.charAt(0), Integer.parseInt(addend2) + storeVariables.get(addend1.charAt(0)));
                }
            }
            else {
                // Check if there are negative signs before both two addends.
                if(addend1.contains("-") && addend2.contains("-")) {
                    storeVariables.put(strCopy.charAt(0), -(storeVariables.get(addend1.charAt(1))) - storeVariables.get(addend2.charAt(1)));
                }
                // Only one negative sign before addend1.
                else if(addend1.contains("-")) {
                    storeVariables.put(strCopy.charAt(0), -(storeVariables.get(addend1.charAt(1))) + storeVariables.get(addend2.charAt(0)));
                }
                // Only one negative sign before addend2.
                else if(addend2.contains("-")) {
                    storeVariables.put(strCopy.charAt(0), storeVariables.get(addend1.charAt(0)) - storeVariables.get(addend2.charAt(1)));
                }
                // No negative sign before both addends.
                else {
                    storeVariables.put(strCopy.charAt(0), storeVariables.get(addend1.charAt(0)) + storeVariables.get(addend2.charAt(0)));
                }
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
            if(subStatement[i].contains("=")) {
                subStatements(subStatement[i], storeVariables);
            }
            else {
                if(subStatement[i].contains("-")) {
                    result = -(storeVariables.get(subStatement[i].charAt(1)));
                }
                else {
                    result = storeVariables.get(subStatement[i].charAt(0));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "A = 2\nB = 22\nK = 91\nK = -A + -1000000\nZ = -K + A\nZ\n";
        int result = computeTheResult(str);
        System.out.println(result);
    }
}