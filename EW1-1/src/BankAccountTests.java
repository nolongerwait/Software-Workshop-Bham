import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/***
 * This file contains 2 JUnit tests for testing the toString method* in the class BankAccount.java
 * @author Manfred Kerber
 * @version 2019-10-15
 */
public class BankAccountTests {
    private BankAccount manfred;

    @BeforeEach
    public void initObjects() {
        manfred = new BankAccount("Manfred");
        manfred.payIn(28.23);
    }

    @Test
    public void assertEqualsTest1() {
        assertEquals("Account name: Manfred Balance: 28.23", manfred.toString(),
                "failure in method toString: " + " expected string not equal computed string");
    }

    @Test
    public void assertEqualsTest2() {
        manfred.withdraw(0.99);
        assertEquals("Account name: Manfred Balance: 27.24", manfred.toString(), "failure in method toString: " + " expected string not equal computed string");
    }
}