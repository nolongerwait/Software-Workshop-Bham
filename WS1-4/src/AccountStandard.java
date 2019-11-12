import java.util.ArrayList;

/**
 *  A standard account is an account for customers who can download music from the music web site. They are represented by 3 field variables: balance, titlesBought, and failedLoginAttempts of types int, ArrayList<MusicTitle>, and int, respectively. Only three login attempts are possible. If the password is entered 3 times incorrectly, the account is disabled and only an administrator can reinstate it.
 * 
 *  @author Zetian Qin zxq876
 *  @version 2019-11-11 16:08:58
 */
public class AccountStandard extends Account implements AccountStandardInterface {
    private int balance;
    private ArrayList<MusicTitle> titlesBought;
    private int failedLoginAttempts;
    
    public static final int MAXIMAL_LOGIN_ATTEMPTS = 3;

    /**
     * Standard constructor of the abstract class AccountStandard.
     * @param name The name of the user of the account.
     * @param salutation The salutation of the account holder (e.g., "Mr", "Ms", "Mrs", "Dr", "Prof")
     * @param email The email address of the account holder.
     * @param password The password of the account.
     */
    public AccountStandard(String name, String salutation, String email, String password) {
        super(name, salutation, email, password);
        this.balance = 0;
        this.titlesBought = new ArrayList<MusicTitle>();
        this.failedLoginAttempts = 0;
    }

    /**
     * Getter method to return the balance.
     * @return The balance of the account.
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     *  Method for a user to log in to their account by providing a password. It is first checked whether the account is still active (that is, not too many failed login attempts were made in the past) and secondly whether the password provided is correct. In case of a correct login the number of login attempts is reset to 0, else increased by 1.
     *  @param password The password provided for the login; this is to be compared to the password stored on the system.
     */
    public void login(String password) {
        if(this.failedLoginAttempts < MAXIMAL_LOGIN_ATTEMPTS) {
            if(this.checkPassword(password)) {
                this.setFailedLoginAttempts(0);
                this.setLoggedIn(true);
            }
            else {
                this.failedLoginAttempts++;
                this.setLoggedIn(false);
            }
        }
    }

    /**
     * Getter method to return the list of all titles bought by the user. 
     * @return The current list of titles bought by the user.
     */
    public ArrayList<MusicTitle> getTitlesBought() {
        return this.titlesBought;
    }

    /** 
     * Adds the amount - if positive - to the current balance. 
     * For non-positive amounts the action does nothing.
     * @param amount The amount being deposited.
     */
    public void deposit(int amount) {
        if(amount > 0) {
            this.balance = this.balance + amount;
        }
    }

    /**
     *  Setter for the balance.
     *  @param balance The new balance of the account.
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Getter method to return the number of failed login attempts.
     * @return The number of failed login attempts.
     */
    public int getFailedLoginAttempts() {
        return this.failedLoginAttempts;
    }

    /**
     *  Setter for the failed login attempts.
     *  @param failedLoginAttempts The new number of failed login attempts.
     */
    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    /** 
     *  If the user is logged in and has sufficient funds, a music title is bought by adding the title to the ArrayList of titlesBought of the customer. Furthermore the price of the title is subtracted from the funds of the customer. If the user is not logged in, a corresponding warning is to be printed. Likewise if the user has insufficient funds, they should be asked to pay money into their account.
     *  @param musicTitle The music title that the customer wants to
     *  buy.
     */
    public void buy(MusicTitle musicTitle) {
        if(getLoggedIn()) {
            if((this.getBalance() - musicTitle.getPrice()) >= 0) {
                this.setBalance(this.getBalance() - musicTitle.getPrice());
                this.titlesBought.add(musicTitle);
            }
            else {
                System.out.println("You should pay enough money into your account.");
            }
        }
        else {
            System.out.println("You are not logged in!\nPlease login first!");
        }
    }

    @Override
    /**
     * Standard toString method to represent the object in a human readable form. 
     * If the user is not logged in, only a warning should be printed, but no substantial information be given.
     * @return The object in a human readable form.
     */
    public String toString() {
        if(this.getLoggedIn()) {
            return "AccountStandard{" + this.getSalutation() + " " + this.getName() + ", \n You are logged in." + "}";
        }
        else {
            return "No user log in!";
        }
    }
}