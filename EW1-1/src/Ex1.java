/**
* This class provide a solution to the Ex1 in Extra Work 1.
* <p>
* The method <code> public static int max(int[] a)<\code> could find the max value in an array.
* <\p>
* @version 2019-10-21 17:12:38
* @author Zetian Qin zxq876
*/

public class Ex1 {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        System.out.println("The maximal value in the int type array is " + max(a));
        int[] b = {1, 5, 3};
        System.out.println("The maximal value in the int type array is " + max(b));
        int[] c = {7, 4 ,3};
        System.out.println("The maximal value in the int type array is " + max(c));
        int[] d = {-1, -2, -3};
        System.out.println("The maximal value in the int type array is " + max(d));
        int[] e = {-5};
        System.out.println("The maximal value in the int type array is " + max(e));
    }

    /**
     * Compute the maximal value of the int type array  
     * @param array the input array as int[]
     * @return the maximal value of this array
     */
    public static int max(int[] array) {
        int result = array[0];
        for(int i = 0; i < array.length; i++) {
            if(result < array[i]) {
                result =  array[i];
            }
        }
        return result;
    }
}