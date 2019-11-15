import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * In Self-Testing, the code should be tested against three different types, normal cases, boundary cases, and illegal cases.
 * <p>
 * This class contains    tests to test all the functions in class MusicTitle, AccountStandard and AccountAdministrator.
 * @author Zetian Qin zxq876
 * @version 2019-11-15 14:06:29
 */
public class AccountTests {

    private MusicTitle musicTitleTest1, musicTitleTest2, musicTitleTest3;
    private AccountStandard customerTest;
    private AccountAdministrator root;

    @BeforeEach
    public void setup(TestInfo testInfo) {
        System.out.println("Start......" + testInfo.getDisplayName());

        musicTitleTest1 = new MusicTitle("Hyde Park Live", "Rolling Stones", 18);
        musicTitleTest2 = new MusicTitle("Crush", "Bon Jovi", 19);
        musicTitleTest3 = new MusicTitle("Beethoven: Symphonies Nos. 5 and 7", "Ludwig van Beethoven", 23);

        ArrayList<MusicTitleInterface> testList = new ArrayList<>();
        testList.add(musicTitleTest1);
        testList.add(musicTitleTest2);
        testList.add(musicTitleTest3);

        customerTest = new AccountStandard("John", "Mr", "john@john.org", "corn");
        root = new AccountAdministrator("Mary", "Mrs", "root@admin.net", "rootpassword");
    }

    @Test
    @DisplayName("Getter Tests in class MusicTitle")
    public void test1() {
        //Test for MusicTitle.getTitle()
        String expectedTitle = "Hyde Park Live";
        String actualTitle = musicTitleTest1.getTitle();
        assertEquals(expectedTitle, actualTitle, "title should be: " + actualTitle);

        //Test for Music.getArtist()
        String expectedArtist = "Rolling Stones";
        String actualArtist = musicTitleTest1.getArtist();
        assertEquals(expectedArtist, actualArtist, "artist should be: " + actualArtist);

        //Test for Music.getPrice()
        int expectedPrice = 18;
        int actualPrice = musicTitleTest1.getPrice();
        assertEquals(expectedPrice, actualPrice, "artist should be: " + actualPrice);
    }

    // testing customerJohn's getName()
    @Test
    @DisplayName("Getter Tests in class AcccountStandard")
    public void test2() {
        //Test for AccountStandard.getName()
        String expectedName = "John";
        String actualName = customerTest.getName();
        assertEquals(expectedName, actualName, "name should be: " + actualName);

        //Test for AccountStandard.getSalutation()
        String expectedSalutation = "Mr";
        String actualSalutation = customerTest.getSalutation();
        assertEquals(expectedSalutation, actualSalutation, "salutation should be: " + actualSalutation);

        //Test for AccountStandard.getEmail()
        String expectedEmail = "john@john.org";
        String actualEmail = customerTest.getEmail();
        assertEquals(expectedEmail, actualEmail, "email should be: " + actualEmail);

        //Test for AccountStandard.getBalance()
        int expectedBalance = 0;
        int actualBalance = customerTest.getBalance();
        assertEquals(expectedBalance, actualBalance, "balance should be: " + actualBalance);

        //Test for AccountStandard.getTitlesBought()
        ArrayList<MusicTitle> expectedPurchaseList = new ArrayList<>();
        ArrayList<MusicTitle> actualPurchaseList = customerTest.getTitlesBought();
        for (int i = 0; i < actualPurchaseList.size(); i++) {
            assertEquals(expectedPurchaseList.get(i), actualPurchaseList.get(i));
        }

        //Test for AccountStandard.getFailedLoginAttempts()
        int expectedFailedLoginAttempts = 0;
        int actualFailedLoginAttempts = customerTest.getFailedLoginAttempts();
        assertEquals(expectedFailedLoginAttempts, actualFailedLoginAttempts, "failed login attempts should be: " + actualFailedLoginAttempts);
    }

    // testing customerJohn's getTitlesBought()
    @Test
    public void test8() {
        
    }

    // testing customerJohn's getFailedLoginAttempts()
    @Test
    public void test9() {
        
    }

    // testing MAXIMAL_LOGIN_ATTEMPTS
    @Test
    public void test10() {
        int expectedMaximalLoginAttempts = 3;
        int actualMaximalLoginAttempts = AccountStandard.MAXIMAL_LOGIN_ATTEMPTS;
        assertEquals(expectedMaximalLoginAttempts, actualMaximalLoginAttempts,
                "failed login attempts should be: " + actualMaximalLoginAttempts);
    }

    // testing MAXIMAL_LOGIN_ATTEMPTS
    @Test
    public void test11() {
        int expectedMaximalLoginAttempts = 3;
        int actualMaximalLoginAttempts = AccountStandard.MAXIMAL_LOGIN_ATTEMPTS;
        assertEquals(expectedMaximalLoginAttempts, actualMaximalLoginAttempts,
                "failed login attempts should be: " + actualMaximalLoginAttempts);
    }

    // customerJohn logs in successfully and tries to make a purchase, which
    // fails due to insufficient funds
    @Test
    public void test12() {
        customerJohn.login("corn");
        assertTrue(customerJohn.getLoggedIn());

        customerJohn.buy(musicTitle1);
        ArrayList<MusicTitle> actualPurchaseList = customerJohn.getTitlesBought();
        assertTrue(actualPurchaseList.isEmpty());
    }

    // customerJohn logs in unsuccessfully
    @Test
    public void test13() {
        customerJohn.login("cOrn");
        assertFalse(customerJohn.getLoggedIn());

        assertEquals(1, customerJohn.getFailedLoginAttempts());

        customerJohn.buy(musicTitle1);
        ArrayList<MusicTitle> actualPurchaseList = customerJohn.getTitlesBought();
        assertTrue(actualPurchaseList.isEmpty());
    }

    // customerJohn tries to log in twice unsuccessfully
    @Test
    public void test14() {
        customerJohn.login("cOrn");
        assertFalse(customerJohn.getLoggedIn());

        assertEquals(1, customerJohn.getFailedLoginAttempts());

        customerJohn.login("cOrn");
        assertFalse(customerJohn.getLoggedIn());

        assertEquals(2, customerJohn.getFailedLoginAttempts());

        customerJohn.buy(musicTitle1);
        ArrayList<MusicTitle> actualPurchaseList = customerJohn.getTitlesBought();
        assertTrue(actualPurchaseList.isEmpty());
    }

    // customerJohn completes a purchase successfully
    @Test
    public void test15() {
        customerJohn.login("cOrn");
        assertFalse(customerJohn.getLoggedIn());

        assertEquals(1, customerJohn.getFailedLoginAttempts());

        customerJohn.login("cOrn");
        assertFalse(customerJohn.getLoggedIn());

        assertEquals(2, customerJohn.getFailedLoginAttempts());

        customerJohn.login("corn");
        assertTrue(customerJohn.getLoggedIn());

        assertEquals(0, customerJohn.getFailedLoginAttempts());

        customerJohn.deposit(20);
        customerJohn.buy(musicTitle1);

        String expectedMusicTitle = "Hyde Park Live";
        String actualMusicTitle = customerJohn.getTitlesBought().get(0).getTitle();
        assertEquals(expectedMusicTitle, actualMusicTitle);

        String expectedArtist = "Rolling Stones";
        String actualArtist = customerJohn.getTitlesBought().get(0).getArtist();
        assertEquals(expectedArtist, actualArtist);

        int expectedPrice = 18;
        int actualPrice = customerJohn.getTitlesBought().get(0).getPrice();
        assertEquals(expectedPrice, actualPrice);

        int expectedBalance = 2;
        int actualBalance = customerJohn.getBalance();
        assertEquals(expectedBalance, actualBalance);

        customerJohn.logout();
        assertFalse(customerJohn.getLoggedIn());

        customerJohn.deposit(20);
        expectedBalance = 22;
        actualBalance = customerJohn.getBalance();
        assertEquals(expectedBalance, actualBalance);

        customerJohn.buy(musicTitle2);
        int expectedPurchaseListSize = 1; // still 1
        int actualPurchaseListSize = customerJohn.getTitlesBought().size();

        assertEquals(expectedPurchaseListSize, actualPurchaseListSize);
    }

    // customerJohn enters wrong password three times, admin tries to reset the
    // password without logging in
    @Test
    public void test16() {
        customerJohn.login("cOrn");
        assertFalse(customerJohn.getLoggedIn());

        assertEquals(1, customerJohn.getFailedLoginAttempts());

        customerJohn.login("cOrrn");
        assertFalse(customerJohn.getLoggedIn());

        assertEquals(2, customerJohn.getFailedLoginAttempts());

        customerJohn.login("c0rn");
        assertFalse(customerJohn.getLoggedIn());

        assertEquals(3, customerJohn.getFailedLoginAttempts());

        root.resetAccount(customerJohn, "seed");
        String expectedPassword = "corn";
        assertTrue(customerJohn.checkPassword(expectedPassword));

        int expectedFailedLoginAttempts = 3;
        int actualFailLoginAttempts = customerJohn.getFailedLoginAttempts();
        assertEquals(expectedFailedLoginAttempts, actualFailLoginAttempts);
    }

    // customerJohn enters wrong password three times, admin manages to log in successfully, and
    // resets customer John' account successfully.
    @Test
    public void test17() {
        customerJohn.login("cOrn");
        assertFalse(customerJohn.getLoggedIn());

        assertEquals(1, customerJohn.getFailedLoginAttempts());

        customerJohn.login("cOrrn");
        assertFalse(customerJohn.getLoggedIn());

        assertEquals(2, customerJohn.getFailedLoginAttempts());

        customerJohn.login("c0rn");
        assertFalse(customerJohn.getLoggedIn());

        assertEquals(3, customerJohn.getFailedLoginAttempts());

        root.login("rootpasssword");
        assertFalse(root.getLoggedIn());

        root.login("rootpassword");
        assertTrue(root.getLoggedIn());

        root.addAccount(customerJohn);
        String expectedAccountName = "John";
        String actualAccountName = root.getAccounts().get(0).getName();
        assertEquals(expectedAccountName, actualAccountName);
        assertTrue(!root.getAccounts().isEmpty());

        root.resetAccount(customerJohn, "seed");
        System.out.println(root);
        String expectedPassword = "seed";
        assertTrue(customerJohn.checkPassword(expectedPassword));

        int expectedFailedLoginAttempts = 0;
        int actualFailLoginAttempts = customerJohn.getFailedLoginAttempts();
        assertEquals(expectedFailedLoginAttempts, actualFailLoginAttempts);

        customerJohn.login("seed");
        //assertTrue(customerJohn.getLoggedIn());

        customerJohn.deposit(100);
        customerJohn.buy(musicTitle1);
        customerJohn.buy(musicTitle2);
        customerJohn.buy(musicTitle3);

        int expectedPurchaseListSize = 3;
        int actualPurchaseListSize = customerJohn.getTitlesBought().size();
        assertEquals(expectedPurchaseListSize, actualPurchaseListSize);

        String expectedTitle = "Beethoven: Symphonies Nos. 5 and 7";
        String expectedArtist = "Ludwig van Beethoven";
        int expectedPrice = 23;

        String actualTitle = customerJohn.getTitlesBought().get(2).getTitle();
        String actualArtist = customerJohn.getTitlesBought().get(2).getArtist();
        int actualPrice = customerJohn.getTitlesBought().get(2).getPrice();
        assertEquals(expectedTitle, actualTitle);
        assertEquals(expectedArtist, actualArtist);
        assertEquals(expectedPrice, actualPrice);

        int expectedBalance = 40;
        int actualBalance = customerJohn.getBalance();
        assertEquals(expectedBalance, actualBalance);
    }

    // admin resets deputy's account successfully
    @Test
    public void test18() {
        Account deputy = new AccountAdministrator("Tim", "Mr", "tim@sysadmin.org", "dep123");
        deputy.login("ddep123");
        assertFalse(deputy.getLoggedIn());

        deputy.login("deep123");
        assertFalse(deputy.getLoggedIn());

        deputy.login("dep1123");
        assertFalse(deputy.getLoggedIn());

        root.login("rootpassword");
        assertTrue(root.getLoggedIn());

        root.addAccount(deputy);
        root.resetAccount(deputy, "dep1235");

        String expectedPassword = "dep1235";
        String actualPassword = root.getAccounts().get(0).getPassword();
        assertEquals(expectedPassword, actualPassword);

        assertFalse(deputy.getLoggedIn());
        deputy.login("dep1235");
        assertFalse(!deputy.getLoggedIn());

        deputy.changePassword("dep1235", "dep1242");
        deputy.logout();
        assertFalse(deputy.getLoggedIn());

        deputy.login("dep1242");
        assertTrue(deputy.getLoggedIn());
    }

    @AfterEach
	public void tearDown(TestInfo testInfo) {
		System.out.println("Finished..." + testInfo.getDisplayName());
	}
}