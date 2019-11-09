public class Subscription implements SubscriptionInterface {
    private String title;
    private String email;
    private int cost;
    
    /**
     * Standard constructor for the class Subscription
     * @param title The title of the journal.
     * @param email The email address to which the journal is to be sent.
     * @param cost The price in GBP the customer has to pay for one issue of journal.
     */
    public Subscription(String title, String email, int cost) {
        this.title = title;
        this.email = email;
        this.cost = cost;
    }
    
    /**
     * Standart getter for the title
     * @return The title of the journal
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Standard getter for the email
     * @return The email address to which the journal is to be sent
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Standard getter for the cost
     * @return The price in GBP the customer has to pay for one issue of journal
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Standard toString method to print the object.
     * @return The object in a human readable form/
     */
    public String toString() {
        return "Subcription {" + "title = " + this.title + ", email = " + this.email + ", cost = " + this.cost + "}";
    }
}