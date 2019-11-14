import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * In Self-Testing, the code should be tested against three different types, normal cases, boundary cases, and illegal cases.
 * <p>
 * This class contains 3 tests to test all the functions in class Subscription and SubscriptionPrime.
 * @author Zetian Qin zxq876
 * @version 2019-11-14 14:24:34
 */
public class SubscriptionTests {
    private SubscriptionInterface subInterfaceTest;
    private SubscriptionPrime subPrimeTest;
    private Subscription subTest;

    @BeforeEach
    public void setup(TestInfo testInfo) {
        System.out.println("Start......" + testInfo.getDisplayName());

        //Test for Subscription
        subInterfaceTest = new Subscription("Journal Test1", "subInterfaceTest@test.com", 1);
        //Test for class SubscriptionPrime
        subPrimeTest = new SubscriptionPrime("Journal Test2", "subPrimeTest@test.com", 3, "Address Test2");
        //Test for Polymorphism 
        subTest = new SubscriptionPrime("Journal Test3", "subTest@test.com", 2, "Address Test3");
    }

    @Test
    @DisplayName("Getter Tests for Subscription")
    public void test1() {
        String expectedTitle = "Journal Test1";
        String actualTitle = subInterfaceTest.getTitle();
        assertEquals(expectedTitle, actualTitle, "The correct Title should be: " + expectedTitle);

        String expectedEmail = "subInterfaceTest@test.com";
        String actualEmail = subInterfaceTest.getEmail();
        assertEquals(expectedEmail, actualEmail, "The correct E-mail address should be: " + expectedEmail);

        int expectedCost = 1;
        int actualCost = subInterfaceTest.getCost();
        assertEquals(expectedCost, actualCost, "The correct Cost of Journal should be: " + expectedCost);
    }

    @Test
    @DisplayName("Getter Tests for SubscriptionPrime")
    public void test2() {
        String expectedTitle = "Journal Test2";
        String actualTitle = subPrimeTest.getTitle();
        assertEquals(expectedTitle, actualTitle, "The correct Title should be: " + expectedTitle);

        String expectedEmail = "subPrimeTest@test.com";
        String actualEmail = subPrimeTest.getEmail();
        assertEquals(expectedEmail, actualEmail, "The correct E-mail address should be: " + expectedEmail);

        int expectedCost = 3;
        int actualCost = subPrimeTest.getCost();
        assertEquals(expectedCost, actualCost, "The correct Cost of Journal should be: " + expectedCost);

        String expectedDeliveryAddress = "Address Test2";
        String actualDeliveryAddress = subPrimeTest.getAddress();
        assertEquals(expectedDeliveryAddress, actualDeliveryAddress, "The correct Delivery Address should be: " + expectedDeliveryAddress);
    }

    @Test
    @DisplayName("Getter Tests for Polymorphism")
    public void test3() {
        String expectedTitle = "Journal Test3";
        String actualTitle = subTest.getTitle();
        assertEquals(expectedTitle, actualTitle, "The correct Title should be: " + expectedTitle);

        String expectedEmail = "subTest@test.com";
        String actualEmail = subTest.getEmail();
        assertEquals(expectedEmail, actualEmail, "The correct E-mail address should be: " + expectedEmail);

        int expectedCost = 2;
        int actualCost = subTest.getCost();
        assertEquals(expectedCost, actualCost, "The correct Cost of Journal should be: " + actualCost);
    }

    @AfterEach
	public void tearDown(TestInfo testInfo) {
		System.out.println("Finished..." + testInfo.getDisplayName());
	}
}
