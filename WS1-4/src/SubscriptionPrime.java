/**
 * The class describes a prime journal subscription. In addition to a standard subscription, the customer receives a hardcopy of the journal. In order to store the corresponding address, where the hardcopy is to be sent to, a field variable address of type String is used.
 * @author Zetian Qin zxq876
 * @version 2019-11-09 17:05:16
 */
public class SubscriptionPrime extends Subscription implements SubscriptionPrimeInterface {
    private String deliveryAddress;

    /**
     * Standard constructor for the class SubscriptionPrime.
     * @param title The title of the journal.
     * @param email The email address to which the journal is to be sent.
     * @param cost The price in GBP the customer has to pay for one issue of journal.
     * @param deliveryAddress The address to which the journal is to be sent
     */
    public SubscriptionPrime(String title, String email, int cost, String deliveryAddress) {
        super(title, email, cost);
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * Standard getter for the delivery address.
     * @return The address to which the journal is to be sent.
     */
    public String getAddress(){
        return this.deliveryAddress;
    }

    @Override
    /**
     * Standard toString method to print the object.
     * @return The object in a human readable form.
     */
    public String toString(){
        return "SubscriptionPrime {" + "title = " + this.getTitle() + ", email = " + this.getEmail() + ", cost = " + this.getCost() + ", deliveryAddress" + this.deliveryAddress + "}";
    }
}