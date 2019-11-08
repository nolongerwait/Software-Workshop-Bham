/**
* The following code is buggy. Why?
* The perimeter of a rectangle should not be an input variable, it is only determined by the width and height of the rectangle. When the width and height change, the perimeter of the rectangle should also change.
* <p>
* We define a rectangle by the three field variables width, height and perimeter,
* each of type double. Furthermore, we write getters and
* setters for the two fields as well as a toString method. We test
* it in a main method.
* <\p>
* @version 2019-10-12 10:06:17
* @author Zetian Qin zxq876 & Manfred Kerber 
*/
public class Rectangle {
    private double width;
    private double height;
    private double perimeter;

    /**
    * <pre>
    *                 width
    * +--------------------------------------+
    * |                                      |
    * |                                      |
    * |                                      | height
    * |                                      |
    * |                                      |
    * +--------------------------------------+
    * </pre>
    * @param width The width of the rectangle.
    * @param height The height of the rectangle.
    */
    public Rectangle(double width, double height){
        this.width = width;
        this.height = height;
        this.perimeter = 2 * (this.width + this.height);
    }

    /**
    * Getter for the width.
    * @return The width of the rectangle is returned.
    */
    public double getWidth() {
        return width;
    }

    /**
    * Getter for the height.
    * @return The height of the rectangle is returned. 
    */
    public double getHeight() {
        return height;
    } 

    /**
    * Getter for the perimeter.
    * @return The perimeter of the rectangle is returned. 
    */
    public double getPerimeter() {
        return perimeter;
    } 

    /**
    * Setter for the width. The width of the rectangle is updated. 
    * @param width The new width of the updated rectangle.
    */
    public void setWidth(double width) {
        this.width = width; 
        setPerimeter();
    }

    /**
    * Setter for the height. The height of the rectangle is updated.
    * @param height The new height of the updated rectangle. 
    */
    public void setHeight(double height) {
        this.height = height;
        setPerimeter();
    } 

    /**
     * Setter for the perimeter. The perimeter of the rectangle is auto updated,
     * when the width and height is updated. 
     */
    public void setPerimeter(){
        this.perimeter = 2 * (this.height + this.width);
    }
    /**
    * @return A human readable description of the rectangle in form
    * of the three field variables specifying it. 
    */
    public String toString() {
        return "The rectangle has a width of " + width + 
        ", a height of " + height +
        ", and a perimeter of " + perimeter + ".";
    } 

    /**
    * main method with a test of the setHeight setter and the * toString method.
    */
    public static void main(String[] args) { 
        Rectangle r = new Rectangle(2, 4); 
        System.out.println(r);
        r.setHeight(5);
        System.out.println(r);
    }
}