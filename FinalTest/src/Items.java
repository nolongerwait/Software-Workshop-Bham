/**
 * Both DVD and Book belong to Item, and they all have common field variables such as name, price, and discount. 
 * So I created these common basic attributes as an Item abstract class as the topmost basic class.
 */
public abstract class Items {
    private String name;
    private double price;
    // Field variable to represent the actual price of the item.
    private double actualPrice;

    private double discountPercentage;

    // Constructor with default dicount percentage
    Items(String name, double price) {
        this.name = name;
        this.price = price;
        this.discountPercentage = 0;
        this.actualPrice = this.price * (1 - this.discountPercentage / 100.0);
    }

    Items(String name, double price, double discountPercentage) {
        if(discountPercentage >= 100 || discountPercentage < 0) {
            throw new IllegalArgumentException("discountPercentage must be in [0, 100). ");
        }
        this.name = name;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.actualPrice = this.price * (1 - this.discountPercentage / 100.0);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.actualPrice = this.price * (1 - this.discountPercentage / 100.0);
    }

    public double getDiscountPercentage() {
        return this.discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        if(discountPercentage >= 100 || discountPercentage < 0) {
            throw new IllegalArgumentException("discountPercentage must be in [0, 100). ");
        }
        this.discountPercentage = discountPercentage;
        this.actualPrice = this.price * (1 - this.discountPercentage / 100.0);
    }

    // Getter for actual price.
    // No setter fot it because the actual price changes directly with the calculation of prices and discounts, it should not be changed at will.
    public double getActualPrice() {
        return this.actualPrice;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\n" + String.format("Price: %.2f\nDiscount: %.2f%%\nActual Price: %.2f", this.price, this.discountPercentage, this.actualPrice) + "\n";
    }
}