/**
* This class provide a solution to  Ex1 homework
* @author ZXQ876 Zetian Qin 
* @version 2019-10-03
* 
*/

public class Ex1 {
    public static void main(String[] args) {
        //declaration with initialization
        double radius = 0;
        double area = 0;
        //the value of radius is 0
        radius = 0;
        area = areaCircle(radius);
        System.out.println("The area of circle with radius of " + radius + " is " + area);
        //the value of radius is 5
        radius = 5;
        area = areaCircle(radius);
        System.out.println("The area of circle with radius of " + radius + " is " + area);
        //the value of radius is 10
        radius = 10;
        area = areaCircle(radius);// area = Math.PI * radius * radius
        // area = areaRR
        System.out.println("The area of circle with radius of " + radius + " is " + area);
    }
    
    /**
    * This method compute the area of circles.
    * @param radius The value of radius of circle - double type
    * @return The area of circle with radius value - double type
    *
    */
    public static double areaCircle(double radius) {
        double area = Math.PI * radius * radius;
        return area;
    }
}