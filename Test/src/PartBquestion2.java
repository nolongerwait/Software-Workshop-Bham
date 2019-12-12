import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This program designed for the Question 2 of Part B in Assignment 3 Algorithm Design and Analysis (and Revision).
 * <p>
 * Write a Java program that is a mini-interpreter for a toy programming language that allows the following
 * <ol>
 * <li>The use of variables that consist of a single letter (e.g. A, a, ...).
 * <li>The use of whole numbers: ( e.g. -1, -20, 0, 1, 200), NOTE: All the number are integer.
 * <li>Assignment (=): ( e.g. A = B, A = 10, )
 * <li>Addition of exactly two elements (variables or constants) (+) ( e.g. C = A + B, D = 1 + A, ... )
 * <ul>
 * <li>NOTE: It supports negative sign operations in addition, but does not support assignment to a negative variable ( e.g. -C = A + B is legal, C = -A + B is also legal).
 * </ul>
 * <li>The ability to "return" a value when a single variable or constant is on a line by itself ( e.g. A, B, 10)
 * </ol>
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
     * Addition method could process the addition in each sub-statement.
     * NOTE: The sub-statement does not start with '-'! And the left value of statement is variable wihtout minus!
     * @param strCopy A string copy from the sub-statement, as String.
     * @param storeVariables The Map container that holds variables and values, as Map.
     * @return The result of this addition, as int.
     */
    public static int addition(String strCopy, Map<Character,Integer> storeVariables) {
        int resultOfAddition = 0;
        String addend1 = strCopy.substring(strCopy.indexOf("=") + 1, strCopy.indexOf("+"));
        String addend2 = strCopy.substring(strCopy.indexOf("+") + 1, strCopy.length());
        // Both two addends have digits.
        if(HasDigit(addend1)&&HasDigit(addend2)) {
            resultOfAddition = Integer.parseInt(addend1) + Integer.parseInt(addend2);
        }
        // Only addend1 has digits.
        else if(HasDigit(addend1)) {
            // Check if there is a negative sign before the variable(addend2).
            if(addend2.contains("-")) {
                resultOfAddition = Integer.parseInt(addend1) - storeVariables.get(addend2.charAt(1));
                //storeVariables.put(strCopy.charAt(0), Integer.parseInt(addend1) - storeVariables.get(addend2.charAt(1)));
            }
            else {
                resultOfAddition = Integer.parseInt(addend1) + storeVariables.get(addend2.charAt(0));
                //storeVariables.put(strCopy.charAt(0), Integer.parseInt(addend1) + storeVariables.get(addend2.charAt(0)));
            }
        }
        // Only addend2 has digits.
        else if(HasDigit(addend2)) {
            // Check if there is a negative sign before the variable(addend1).
            if(addend1.contains("-")) {
                resultOfAddition = Integer.parseInt(addend2) - storeVariables.get(addend1.charAt(1));
            }
            else {
                resultOfAddition = Integer.parseInt(addend2) + storeVariables.get(addend1.charAt(0));
            }
        }
        // Both two addends are variables.
        else {
            // Check if there are negative signs before both two addends.
            if(addend1.contains("-") && addend2.contains("-")) {
                resultOfAddition = -(storeVariables.get(addend1.charAt(1))) - storeVariables.get(addend2.charAt(1));
            }
            // Only one negative sign before addend1.
            else if(addend1.contains("-")) {
                resultOfAddition = -(storeVariables.get(addend1.charAt(1))) + storeVariables.get(addend2.charAt(0));
            }
            // Only one negative sign before addend2.
            else if(addend2.contains("-")) {
                resultOfAddition = storeVariables.get(addend1.charAt(0)) - storeVariables.get(addend2.charAt(1));
            }
            // No negative sign before both addends.
            else {
                resultOfAddition = storeVariables.get(addend1.charAt(0)) + storeVariables.get(addend2.charAt(0));
            }
        }
        return resultOfAddition;
    }

    /**
     * Assignment method could process the right value of assignment statements.
     * NOTE: The sub-statement does not start with '-'! And the left value of statement is variable wihtout minus!
     * @param strCopy A string copy from the sub-statement, as String.
     * @param storeVariables The Map container that holds variables and values, as Map.
     * @return The right value of assignment statements, as int.
     */
    public static int assignment(String strCopy, Map<Character,Integer> storeVariables) {
        int rightValueOfAssignment = 0;
        // Assign number to variable.
        if(HasDigit(strCopy)) {
            String number = strCopy.substring(2, strCopy.length());
            rightValueOfAssignment = Integer.parseInt(number);
            //storeVariables.put(strCopy.charAt(0),Integer.parseInt(number));
        }
        // Assign the value of a minus variable to other variable.
        else if(strCopy.contains("-")) {
            rightValueOfAssignment = -(storeVariables.get(strCopy.charAt(3)));
            //storeVariables.put(strCopy.charAt(0),-(storeVariables.get(strCopy.charAt(3))));
        }
        // Assign the value of a variable to other variable.
        else {
            rightValueOfAssignment = storeVariables.get(strCopy.charAt(2));
            //storeVariables.put(strCopy.charAt(0),storeVariables.get(strCopy.charAt(2)));
        }
        return rightValueOfAssignment;
    }

    /**
     * AssignmentStatements method could process assigment in each sub-statement.
     * @param str The sub-statement separated, as String.
     * @param storeVariables The Map container that holds variables and values, as Map.
     */
    public static void assignmentStatements(String str, Map<Character,Integer> storeVariables) {
        String strCopy = str.replaceAll(" ", "");
        // Assignment in sub-statement with Addition.
        if(strCopy.contains("+")) {
            // If the first character is '-', the minus result of this addition is assigned to the variable.
            if(strCopy.charAt(0) == '-') {
                strCopy = strCopy.substring(1);
                storeVariables.put(strCopy.charAt(0), -addition(strCopy, storeVariables));
            }
            // Normal assignment with addition, the result of this addition is assigned to the variable.
            else {
                storeVariables.put(strCopy.charAt(0), addition(strCopy, storeVariables));
            }
        }
        // Assignment in sub-statement.
        else {
            // If the first character is '-', the minus result of this assignment is assigned to the variable.
            if(strCopy.charAt(0) == '-') {
                strCopy = strCopy.substring(1);
                storeVariables.put(strCopy.charAt(0), -assignment(strCopy, storeVariables));
            }
            // Normal assignment with addition, the result of this assignment is assigned to the variable.
            else {
                storeVariables.put(strCopy.charAt(0), assignment(strCopy, storeVariables));
            }
        }
    }
    
    /**
     * ReturnStatements method could process return in return sub-statement
     * @param str The sub-statement which need to return value, as String.
     * @param storeVariables The Map container that holds variables and values, as Map.
     * @return The value which will be returned or printed in Screan, as int.
     */
    public static int returnStatements(String str, Map<Character,Integer> storeVariables) {
        int valueToReturn = 0;
        // If the sub-statement is number, it just returns the value.
        if(HasDigit(str)){
            valueToReturn = Integer.parseInt(str);
        }
        // The sub-statement is variable and the variable has minus sign, it returns the value of the minus variable.
        else if(str.contains("-")) {
            valueToReturn = -(storeVariables.get(str.charAt(1)));
        }
        // Normal return, just return the value of the variable.
        else {
            valueToReturn = storeVariables.get(str.charAt(0));
        }
        return valueToReturn;
    }

    /**
     * ToyProgram method could achieve the function which is required in Part B question 2.
     * This method could achieve mini-interpreter for a toy programming language and return the result.
     * @param str The input of the toy programming language, as String.
     * @return The result of the toy programming language, as int.
     */
    public static int toyProgram(String str) {
        int result = 0;
        Map<Character, Integer> storeVariables = new HashMap<Character, Integer>();
        String[] subStatement = str.split("\n");
        for(int i = 0; i < subStatement.length; i++) {
            // If the sub-statement contains "=", it goes to process assignment and addition.
            if(subStatement[i].contains("=")) {
                assignmentStatements(subStatement[i], storeVariables);
            }
            // If the sub-statement does not contain "=", it goes to process return.
            else {
                result = returnStatements(subStatement[i], storeVariables);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String strTestCase1 = "-A = 2\nB = 22\nZ = 91\n-K = -A + B\nZ = K + A\n-Z";
        System.out.println(toyProgram(strTestCase1));
        String strTestCase2 = "8";
        System.out.println(toyProgram(strTestCase2));
    }
}