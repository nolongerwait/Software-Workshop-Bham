/**
 * BankAccount is a class for a very simple bank account created from* the name of the account holder. We distinguish two field* variables: accountName and balance.
 * @author Manfred Kerber
 * @version 2018-10-11
 */
public class BankAccount {
    private String accountName;
    private double balance;

    /**
     * BankAccount is a constructor for a very simple bank account created* @param
     * accountName the account name as String
     */
    public BankAccount(String accountName) {
        this.accountName = accountName;
        this.balance = 0;
    }

    /*** @return the accountName as a String */
    public String getAccountName() {
        return accountName;
    }

    /*** @return the balance of a BankAccount */
    public double getBalance() {
        return balance;
    }

    /***
     * sets the balance of a BankAccount
     * @param balance the new balance on the
     * account
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * the amount will be taken from the balance
     * @param amount The amount to be withdrawn.
     */
    public void withdraw(double amount) {
        setBalance(getBalance() - amount);
    }

    /**
     * the amount will be added the balance
     * @param amount The amount to be paid in.
     */
    public void payIn(double amount) {
        setBalance(getBalance() + amount);
    }

    /**
     * toString defines how to print a BankAccount
     * @return the print type of an account
     */
    public String toString() {
        return "Account name: " + accountName + " Balance: " + String.format("%.2f", balance);
    }
}
