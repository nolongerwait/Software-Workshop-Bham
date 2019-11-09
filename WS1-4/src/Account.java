/**
 * The Account class should have 5 field variables, namely name, salutation, email, password, and loggedIn of types String, String, String, String, and boolean.
 * @author Zetian Qin zxq876
 * @version 2019-11-09 23:29:32
 */
public abstract class Account implements AccountInterface {
    private String name;
    private String salutation;
    private String email;
    private String password;
    private boolean loggedIn;

    /**
     *  We require in each sub-class the existence of a login method.
     *  @param password The password provided that will be compared to the password stored on the system, i.e., the value of the field variable. If the password provided is correct, the field variable loggedIn is changes to true, else a warning is to be printed.
     */
    public abstract void login(String password);

    /**
     *  Getter to check whether a user is logged in.
     *
     *  If the user is not loggedIn the toString method should be disabled and only a warning should be printed that first a login is necessary. Further methods may be disabled in sub-classes.
     * 
     *  @return true if the user is looged in, false else.
     */
    public boolean getLoggedIn() {
        return this.loggedIn;
    }

    /**
     *  setter for loggedIn
     *  @param loggedIn New value for the variable loggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     *  The user is no longer logged in, that is, the
     *  loggedIn variable set to false.
     */
    public void logout() {
        this.loggedIn = false;
    }

    /**
     * Getter method to return the name of the user.
     * @return The name of the user of the account.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Getter method to return the salutation of the account holder.
     * @return The salutation of the account holder (e.g., "Mr", "Ms", "Mrs", "Dr", "Prof")
     */
    public String getSalutation() {
        return this.salutation;
    }

    /**
     * Getter method to return the email address of the account holder.
     * @return The email address of the account holder.  
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Getter method to return the password. 
     * @return The password of the account.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     *  Setter for the password.
     *  @param password The new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method to compare a provided password with the stored password.
     * @param password The provided password to which the password of the this object is compared.
     * @return true if the password of the account agrees with the argument, false else.
     */
    public abstract boolean checkPassword(String password);

    /**
     * Standard toString method to represent the object in a human readable form.
     * @return The object in a human readable form.
     */
    public abstract String toString();
 
    /** 
     * Changes the password from old to new if the old password is correct, else an error message is printed.
     * @param oldPassword The current password.
     * @param newPassword The future password.
     */
    public abstract void changePassword(String oldPassword, String newPassword);
}