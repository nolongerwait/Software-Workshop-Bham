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
        assertEquals(expectedTitle, actualTitle);

        //Test for Music.getArtist()
        String expectedArtist = "Rolling Stones";
        String actualArtist = musicTitleTest1.getArtist();
        assertEquals(expectedArtist, actualArtist);

        //Test for Music.getPrice()
        int expectedPrice = 18;
        int actualPrice = musicTitleTest1.getPrice();
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    @DisplayName("Getter Tests in class AcccountStandard")
    public void test2() {
        //Test for AccountStandard.getName()
        String expectedName = "John";
        String actualName = customerTest.getName();
        assertEquals(expectedName, actualName);

        //Test for AccountStandard.getSalutation()
        String expectedSalutation = "Mr";
        String actualSalutation = customerTest.getSalutation();
        assertEquals(expectedSalutation, actualSalutation);

        //Test for AccountStandard.getEmail()
        String expectedEmail = "john@john.org";
        String actualEmail = customerTest.getEmail();
        assertEquals(expectedEmail, actualEmail);

        //Test for AccountStandard.getBalance()
        int expectedBalance = 0;
        int actualBalance = customerTest.getBalance();
        assertEquals(expectedBalance, actualBalance);

        //Test for AccountStandard.getTitlesBought()
        ArrayList<MusicTitle> expectedPurchaseList = new ArrayList<>();
        ArrayList<MusicTitle> actualPurchaseList = customerTest.getTitlesBought();
        for (int i = 0; i < actualPurchaseList.size(); i++) {
            assertEquals(expectedPurchaseList.get(i), actualPurchaseList.get(i));
        }

        //Test for AccountStandard.getFailedLoginAttempts()
        int expectedFailedLoginAttempts = 0;
        int actualFailedLoginAttempts = customerTest.getFailedLoginAttempts();
        assertEquals(expectedFailedLoginAttempts, actualFailedLoginAttempts);
    }

    @Test
    @DisplayName("Test to check the MAXIMAL_LOGIN_ATTEMPTS")
    public void test3() {
        int expectedMaximalLoginAttempts = 3;
        int actualMaximalLoginAttempts = AccountStandard.MAXIMAL_LOGIN_ATTEMPTS;
        assertEquals(expectedMaximalLoginAttempts, actualMaximalLoginAttempts);
    }

    @Test
    @DisplayName("Tests for login and logout")
    public void test4() {
        //Test for login successfully
        customerTest.login("corn");
        assertTrue(customerTest.getLoggedIn());

        //Test for logout
        customerTest.logout();
        assertFalse(customerTest.getLoggedIn());

        //Test for login unsuccessfully
        customerTest.login("cOrn");
        assertFalse(customerTest.getLoggedIn());
        assertEquals(1, customerTest.getFailedLoginAttempts());

        //Test for login unsuccessfully twice
        customerTest.login("cOrn");
        assertFalse(customerTest.getLoggedIn());
        assertEquals(2, customerTest.getFailedLoginAttempts());

        //Test for login unsuccessfully three times
        customerTest.login("cOrn");
        assertFalse(customerTest.getLoggedIn());
        assertEquals(3, customerTest.getFailedLoginAttempts());
    }

    @Test
    public void test12() {
        customerTest.login("corn");
        assertTrue(customerTest.getLoggedIn());

        customerTest.buy(musicTitleTest1);
        ArrayList<MusicTitle> actualPurchaseList = customerTest.getTitlesBought();
        assertTrue(actualPurchaseList.isEmpty());
    }

    // customerTest logs in unsuccessfully
    @Test
    @DisplayName("Test for buy music.")
    public void test5() {
        ArrayList<MusicTitle> actualPurchaseList;

        //Fail to buy without login
        customerTest.buy(musicTitleTest1);
        actualPurchaseList = customerTest.getTitlesBought();
        assertTrue(actualPurchaseList.isEmpty());
        assertFalse(customerTest.getLoggedIn());

        //Fail to buy due to insufficient balance
        customerTest.login("corn");
        assertTrue(customerTest.getLoggedIn());

        customerTest.buy(musicTitleTest1);
        actualPurchaseList = customerTest.getTitlesBought();
        assertTrue(actualPurchaseList.isEmpty());

        //
        customerTest.deposit(20);
        customerTest.buy(musicTitleTest1);

        String expectedMusicTitle = "Hyde Park Live";
        String actualMusicTitle = customerTest.getTitlesBought().get(0).getTitle();
        assertEquals(expectedMusicTitle, actualMusicTitle);

        String expectedArtist = "Rolling Stones";
        String actualArtist = customerTest.getTitlesBought().get(0).getArtist();
        assertEquals(expectedArtist, actualArtist);

        int expectedPrice = 18;
        int actualPrice = customerTest.getTitlesBought().get(0).getPrice();
        assertEquals(expectedPrice, actualPrice);

        int expectedBalance = 2;
        int actualBalance = customerTest.getBalance();
        assertEquals(expectedBalance, actualBalance);
    }

    // customerTest tries to log in twice unsuccessfully
    @Test
    public void test14() {
        customerTest.login("cOrn");
        assertFalse(customerTest.getLoggedIn());

        assertEquals(1, customerTest.getFailedLoginAttempts());

        customerTest.login("cOrn");
        assertFalse(customerTest.getLoggedIn());

        assertEquals(2, customerTest.getFailedLoginAttempts());

        customerTest.buy(musicTitleTest1);
        ArrayList<MusicTitle> actualPurchaseList = customerTest.getTitlesBought();
        assertTrue(actualPurchaseList.isEmpty());
    }

    // customerTest completes a purchase successfully
    @Test
    public void test15() {
        customerTest.login("cOrn");
        assertFalse(customerTest.getLoggedIn());

        assertEquals(1, customerTest.getFailedLoginAttempts());

        customerTest.login("cOrn");
        assertFalse(customerTest.getLoggedIn());

        assertEquals(2, customerTest.getFailedLoginAttempts());

        customerTest.login("corn");
        assertTrue(customerTest.getLoggedIn());

        assertEquals(0, customerTest.getFailedLoginAttempts());

        customerTest.deposit(20);
        customerTest.buy(musicTitleTest1);

        String expectedMusicTitle = "Hyde Park Live";
        String actualMusicTitle = customerTest.getTitlesBought().get(0).getTitle();
        assertEquals(expectedMusicTitle, actualMusicTitle);

        String expectedArtist = "Rolling Stones";
        String actualArtist = customerTest.getTitlesBought().get(0).getArtist();
        assertEquals(expectedArtist, actualArtist);

        int expectedPrice = 18;
        int actualPrice = customerTest.getTitlesBought().get(0).getPrice();
        assertEquals(expectedPrice, actualPrice);

        int expectedBalance = 2;
        int actualBalance = customerTest.getBalance();
        assertEquals(expectedBalance, actualBalance);

        customerTest.logout();
        assertFalse(customerTest.getLoggedIn());

        customerTest.deposit(20);
        expectedBalance = 22;
        actualBalance = customerTest.getBalance();
        assertEquals(expectedBalance, actualBalance);

        customerTest.buy(musicTitleTest2);
        int expectedPurchaseListSize = 1; // still 1
        int actualPurchaseListSize = customerTest.getTitlesBought().size();

        assertEquals(expectedPurchaseListSize, actualPurchaseListSize);
    }

    // customerTest enters wrong password three times, admin tries to reset the
    // password without logging in
    @Test
    public void test16() {
        customerTest.login("cOrn");
        assertFalse(customerTest.getLoggedIn());

        assertEquals(1, customerTest.getFailedLoginAttempts());

        customerTest.login("cOrrn");
        assertFalse(customerTest.getLoggedIn());

        assertEquals(2, customerTest.getFailedLoginAttempts());

        customerTest.login("c0rn");
        assertFalse(customerTest.getLoggedIn());

        assertEquals(3, customerTest.getFailedLoginAttempts());

        root.resetAccount(customerTest, "seed");
        String expectedPassword = "corn";
        assertTrue(customerTest.checkPassword(expectedPassword));

        int expectedFailedLoginAttempts = 3;
        int actualFailLoginAttempts = customerTest.getFailedLoginAttempts();
        assertEquals(expectedFailedLoginAttempts, actualFailLoginAttempts);
    }

    // customerTest enters wrong password three times, admin manages to log in successfully, and
    // resets customer John' account successfully.
    @Test
    public void test17() {
        customerTest.login("cOrn");
        assertFalse(customerTest.getLoggedIn());

        assertEquals(1, customerTest.getFailedLoginAttempts());

        customerTest.login("cOrrn");
        assertFalse(customerTest.getLoggedIn());

        assertEquals(2, customerTest.getFailedLoginAttempts());

        customerTest.login("c0rn");
        assertFalse(customerTest.getLoggedIn());

        assertEquals(3, customerTest.getFailedLoginAttempts());

        root.login("rootpasssword");
        assertFalse(root.getLoggedIn());

        root.login("rootpassword");
        assertTrue(root.getLoggedIn());

        root.addAccount(customerTest);
        String expectedAccountName = "John";
        String actualAccountName = root.getAccounts().get(0).getName();
        assertEquals(expectedAccountName, actualAccountName);
        assertTrue(!root.getAccounts().isEmpty());

        root.resetAccount(customerTest, "seed");
        System.out.println(root);
        String expectedPassword = "seed";
        assertTrue(customerTest.checkPassword(expectedPassword));

        int expectedFailedLoginAttempts = 0;
        int actualFailLoginAttempts = customerTest.getFailedLoginAttempts();
        assertEquals(expectedFailedLoginAttempts, actualFailLoginAttempts);

        customerTest.login("seed");
        //assertTrue(customerTest.getLoggedIn());

        customerTest.deposit(100);
        customerTest.buy(musicTitleTest1);
        customerTest.buy(musicTitleTest2);
        customerTest.buy(musicTitleTest3);

        int expectedPurchaseListSize = 3;
        int actualPurchaseListSize = customerTest.getTitlesBought().size();
        assertEquals(expectedPurchaseListSize, actualPurchaseListSize);

        String expectedTitle = "Beethoven: Symphonies Nos. 5 and 7";
        String expectedArtist = "Ludwig van Beethoven";
        int expectedPrice = 23;

        String actualTitle = customerTest.getTitlesBought().get(2).getTitle();
        String actualArtist = customerTest.getTitlesBought().get(2).getArtist();
        int actualPrice = customerTest.getTitlesBought().get(2).getPrice();
        assertEquals(expectedTitle, actualTitle);
        assertEquals(expectedArtist, actualArtist);
        assertEquals(expectedPrice, actualPrice);

        int expectedBalance = 40;
        int actualBalance = customerTest.getBalance();
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