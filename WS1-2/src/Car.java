/**
 * Car is a class for a very simple infromation of car.
 * This class distinguish four field object varibales.
 * <pre>
 *  make, a String to the material of the car
 *  price, an int to the price of the car
 *  maxSpeed, an int to the max speed of the car
 *  color, the color of the car
 * </pre>
 * @author Zetian Qin zxq876
 * @version 2019-10-11 13:05:35
 */

public class Car{
    private String make;
    private int    price;
    private int    maxSpeed;
    private String color;

    /**
     * Car method is a constructor for a very simple infromation of a car
     * @param make is the material of the car as String
     * @param price is the price of the car as int
     * @param maxSpeed is the max speed of the car as int
     * @param color is the color of the car as String
     */
    public Car(String make, int price, int maxSpeed, String color){
        this.make = make;
        this.price = price;
        this.maxSpeed = maxSpeed;
        this.color = color;
    }

    /**
     * get the make from a Car
     * @return the make of the car as String
     */
    public String getMake(){
        return make;
    }

    /**
     * set the make of a Car
     * @param make for the changed make as String
     */
    public void setMake(String make){
        this.make = make;
    }

    /**
     * get the price from a Car
     * @return the price of the car as int
     */
    public int getPrice(){
        return price;
    }

    /**
     * set the price of a Car
     * @param price the changed price of the car as int
     */
    public void setPrice(int price){
        this.price = price;
    }

    /**
     * get the max speed from a Car
     * @return the max speed of the car as int
     */
    public int getMaxSpeed(){
        return maxSpeed;
    }

    /**
     * set the max speed of a Car
     * @param maxSpeed the changed max speed of the car as int
     */
    public void setMaxSpeed(int maxSpeed){
        this.maxSpeed = maxSpeed;
    }

    /**
     * get the color from a Car
     * @return the color of the car as String
     */
    public String getColour(){
        return color;
    }

    /**
     * set the color of a Car
     * @param color the changed color of the car as String
     */
    public void setColour(String color){
        this.color = color;
    }

    /**
     * toString defines how to print a Car
     * @return the print type of a car
     */
    public String toString(){
        return "Make: " + make + " \r\nPrice: " + price + " \r\nMax speed: " + maxSpeed + " \r\nColor: " + color;
    }
}