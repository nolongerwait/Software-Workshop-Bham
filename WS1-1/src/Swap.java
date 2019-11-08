/*
* This class exchange the values of two variable (Object type)
* @author ZXQ876 Zetian Qin
* @version 2019-10-03
* 
*/

public class Swap {
    public int i;
    public int j;
    /**
    * This method swap the value of two object variables.
    * Here I want to explain why I am making such these modification.
    * <p>
    * I have five-years experience of C ++ programming, but not in-depth understanding of Java, so some ideas are from C++, if I have errors, please bear with me.
    * <p>
    * In the original code, the intermediate temporary variable is not used in the swap method, which causes the original code to make the values of the two variables same (because the same value is assigned twice).
    * <p>
    * And the Primitive types in Java are Call by Value. So using the original swap method, the values of the two variables will not change, because the values stored by the two variables are used in Swap method instead of the two variables themselves.
    * <p>
    * Java is a strong OOP language, so I use Object types to replace the Primitive type, because the Object types are Call by Reference which could implement the function of exchanging two variable data after calling swap method.
    * <p>
    * You will notice that I still use int type in swap method. But when I am calling swap method, the value of i and j will pass to swap method by value. So, the value of j is assigned to the object i and the value of i is assigned to the object j.
    * @param i 1st variable. - int type
    * @param j 2nd variable. - int type
    */
    public void swap(int i, int j) {
        this.i = j;
        this.j = i;
    }
    
    public static void main(String[] args) {
        Swap Sw = new Swap();
        Sw.i = 2;
        Sw.j = 6;
        System.out.println("Original i: " + Sw.i + " k: " + Sw.j);
        Sw.swap(Sw.i, Sw.j);
        System.out.println("After swapping i: " + Sw.i + " k: " + Sw.j);
    }
}