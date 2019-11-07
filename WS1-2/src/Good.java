/**
 * Good is a class for the representation of the name and net price of goods
 * This class distinguish two field object variables.
 * <pre>
 *  name, a String to the name of goods
 *  netPrice, a double to the net price of goods
 * </pre>
 * @author Zetian Qin zxq876
 * @version 2019-10-11 21:54:57
 */
public class Good{
    private String name;
    private double netPrice;
    final static double VAT_RATE = 20; //fixed VAT rate of 20 percent
    private double grossPrice; //store the final price with VAT and discount. it should not be the field variable.

    /**
     * This constructor creates a goods information from two parts:
     * name and net price, which are a String and a double, respectively.
     * @param name The name of the goods
     * @param netPrice The net price of the goods
     */
    public Good(String name, double netPrice){
        this.name = name;
        this.netPrice = netPrice;
        grossPrice = this.grossPrice();
    }

    /**
     * get the name from a Good
     * @return the name of the goods
     */
    public String getName()
    {
        return name;
    }

    /**
     * set the name of a Good
     * @param name The changed name of the goods
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * get the net price from a Good
     * @return the net price of the goods
     */
    public double getNetPrice(){
        return netPrice;
    }

    /**
     * set the net price of a Good
     * @param netPrice The changed net price of the goods
     */
    public void setNetPrice(double netPrice){
        this.netPrice = netPrice;
        grossPrice = this.grossPrice();
    }

    /**
     * get the gross price from a Good
     * @return the gross price of the goods
     */
    public double getGrossPrice(){
        return grossPrice;
    }

    /**
     * toString defines how to print the Good
     * @return the print type of the goods
     */
    public String toString(){
        return "The " + name + " has a gross price of \u00A3" + String.format("%.2f", grossPrice) + ".";
    }

    /**
     * Calculate the gross price with VAT
     * @return the gross price of the goods with VAT
     */
    public double grossPrice(){
        return this.netPrice * (1 + VAT_RATE / 100.0);
    }

    /**
     * Calculate and set the final price with VAT and discount
     * @param rate The discount rate of the goods
     */
    public void discount(double rate){
        grossPrice = this.grossPrice() * (1 - rate / 100.0);
    }

    public static void main(String[] args){
        Good milk = new Good("Milk", 0.50);
        System.out.println(milk);
        milk.discount(20);
        System.out.println(milk);
        milk.discount(0);
        System.out.println(milk);
    }
}