/**
 * This class provide a solution to Ex2 homework
 * <p>
 * The method <code>public static boolean rectangular(int[][] array)<\code> could determine if it is a rectangular array.
 * @author ZXQ876 Zetian Qin
 * @version 2019-10-21 17:28:07
 */

 public class Ex2 {
    public static void main(String[] args) {
        int[][] a = {{1,2,3},{1,3,2},{2,1,3},{2,3,1},{3,1,2},{3,2,1}};
        System.out.println(rectangular(a));
    }

    /**
     * Determin if the array is a rectangular array.
     * @param array the input two-dimensional array as int[][]
     * @return true if the array is rectangular, or false if not
     */
    public static boolean rectangular(int[][] array) {
        for(int i = 1; i < array.length; i++) {
            if(array[i-1].length != array[i].length) {
                return false;
            }
        }
        return true;
    }
 }
 