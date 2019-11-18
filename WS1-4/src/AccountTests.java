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
 * This class contains 7 tests to test all the functions in class MusicTitle, AccountStandard and AccountAdministrator.
 * @author Zetian Qin zxq876
 * @version 2019-11-17 01:59:20
 */
public class AccountTests {

    private MusicTitle musicTitleTest1, musicTitleTest2, musicTitleTest3;
    private AccountStandard customerTest;
    private AccountAdministrator root;

    @BeforeEach
    public void setup(TestInfo testInfo) {
        System.out.println("Start......" + testInfo.getDisplayName());

        musicTitleTest1 = new MusicTitle("Music Title Test1", "Artist Test1", 15);
        musicTitleTest2 = new MusicTitle("Music Title Test2", "Artist Test2", 30);
        musicTitleTest3 = new MusicTitle("Music Title Test3", "Artist Test3", 20);

        ArrayList<MusicTitleInterface> testList = new ArrayList<>();
        testList.add(musicTitleTest1);
        testList.add(musicTitleTest2);
        testList.add(musicTitleTest3);

        customerTest = new AccountStandard("TestName", "Mr", "TestName@Test.com", "standaradpassword");
        root = new AccountAdministrator("TestAdminName", "Mrs", "TestAdminName@admin.net", "rootpassword");
    }

    @Test
    @DisplayName("Getter Tests in class MusicTitle")
    public void test1() {
        //Test for MusicTitle.getTitle()
        String expectedTitle = "Music Title Test1";
        String actualTitle = musicTitleTest1.getTitle();
        assertEquals(expectedTitle, actualTitle);

        //Test for Music.getArtist()
        String expectedArtist = "Artist Test1";
        String actualArtist = musicTitleTest1.getArtist();
        assertEquals(expectedArtist, actualArtist);

        //Test for Music.getPrice()
        int expectedPrice = 15;
        int actualPrice = musicTitleTest1.getPrice();
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    @DisplayName("Getter Tests in class AcccountStandard")
    public void test2() {
        //Test for AccountStandard.getName()
        String expectedName = "TestName";
        String actualName = customerTest.getName();
        assertEquals(expectedName, actualName);

        //Test for AccountStandard.getSalutation()
        String expectedSalutation = "Mr";
        String actualSalutation = customerTest.getSalutation();
        assertEquals(expectedSalutation, actualSalutation);

        //Test for AccountStandard.getEmail()
        String expectedEmail = "TestName@Test.com";
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
        customerTest.login("standaradpassword");
        assertTrue(customerTest.getLoggedIn());

        //Test for logout
        customerTest.logout();
        assertFalse(customerTest.getLoggedIn());

        //Test for login unsuccessfully
        customerTest.login("standaradpAssword");
        assertFalse(customerTest.getLoggedIn());
        assertEquals(1, customerTest.getFailedLoginAttempts());

        //Test for login unsuccessfully twice
        customerTest.login("standaradpa$sword");
        assertFalse(customerTest.getLoggedIn());
        assertEquals(2, customerTest.getFailedLoginAttempts());

        //Test for login unsuccessfully three times
        customerTest.login("standaradpassw0rd");
        assertFalse(customerTest.getLoggedIn());
        assertEquals(3, customerTest.getFailedLoginAttempts());
    }

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
        customerTest.login("standaradpassword");
        assertTrue(customerTest.getLoggedIn());

        customerTest.buy(musicTitleTest1);
        actualPurchaseList = customerTest.getTitlesBought();
        assertTrue(actualPurchaseList.isEmpty());

        //Successfully complete a purchase
        customerTest.deposit(20);
        customerTest.buy(musicTitleTest1);

        String expectedMusicTitle = "Music Title Test1";
        String actualMusicTitle = customerTest.getTitlesBought().get(0).getTitle();
        assertEquals(expectedMusicTitle, actualMusicTitle);

        String expectedArtist = "Artist Test1";
        String actualArtist = customerTest.getTitlesBought().get(0).getArtist();
        assertEquals(expectedArtist, actualArtist);

        int expectedPrice = 15;
        int actualPrice = customerTest.getTitlesBought().get(0).getPrice();
        assertEquals(expectedPrice, actualPrice);

        int expectedBalance = 5;
        int actualBalance = customerTest.getBalance();
        assertEquals(expectedBalance, actualBalance);

        System.out.print(customerTest);

        //Logout, add money to balance, then try to buy music but failed.
        customerTest.logout();
        assertFalse(customerTest.getLoggedIn());

        customerTest.deposit(20);
        expectedBalance = 25;
        actualBalance = customerTest.getBalance();
        assertEquals(expectedBalance, actualBalance);

        customerTest.buy(musicTitleTest2);//No login, failed.
        int expectedPurchaseListSize = 1;
        int actualPurchaseListSize = customerTest.getTitlesBought().size();
        assertEquals(expectedPurchaseListSize, actualPurchaseListSize);

        System.out.print(customerTest);
    }

    @Test
    @DisplayName("Test for Administrator to reset Standard Acccount password")
    public void test6() {
        //Standard Account failed to login three times.
        customerTest.login("standaradpAssword");
        assertFalse(customerTest.getLoggedIn());
        assertEquals(1, customerTest.getFailedLoginAttempts());

        customerTest.login("standaradpa$sword");
        assertFalse(customerTest.getLoggedIn());
        assertEquals(2, customerTest.getFailedLoginAttempts());

        customerTest.login("standaradpassw0rd");
        assertFalse(customerTest.getLoggedIn());
        assertEquals(3, customerTest.getFailedLoginAttempts());

        //Administrator try to reset password without login
        root.resetAccount(customerTest, "newStandardpassword");
        String expectedPassword = "standaradpassword";
        assertTrue(customerTest.checkPassword(expectedPassword));

        //Administrator login and reset password
        root.login("rootpassword");
        assertTrue(root.getLoggedIn());

        root.addAccount(customerTest);
        String expectedAccountName = "TestName";
        String actualAccountName = root.getAccounts().get(0).getName();
        assertEquals(expectedAccountName, actualAccountName);
        assertTrue(!root.getAccounts().isEmpty());

        root.resetAccount(customerTest, "newStandardpassword");
        System.out.println(root);
        expectedPassword = "newStandardpassword";
        assertTrue(customerTest.checkPassword(expectedPassword));

        int expectedFailedLoginAttempts = 0;
        int actualFailLoginAttempts = customerTest.getFailedLoginAttempts();
        assertEquals(expectedFailedLoginAttempts, actualFailLoginAttempts);

        customerTest.login("newStandardpassword");
        assertTrue(customerTest.getLoggedIn());
    }

    @Test
    @DisplayName("Test for Administrator to reset Administrator Acccount password")
    public void test7() {
        Account AdminTest = new AccountAdministrator("AdminTest", "Mr", "AdminTest@Test.com", "abc123zxc");

        //Administrator Account failed to login three times.
        AdminTest.login("Abc123zxc");
        assertFalse(AdminTest.getLoggedIn());

        AdminTest.login("abc456zxc");
        assertFalse(AdminTest.getLoggedIn());

        AdminTest.login("abc123ZXY");
        assertFalse(AdminTest.getLoggedIn());

        //Administrator login and reset password
        root.login("rootpassword");
        assertTrue(root.getLoggedIn());

        root.addAccount(AdminTest);
        root.resetAccount(AdminTest, "simplepassword");

        String expectedPassword = "simplepassword";
        String actualPassword = root.getAccounts().get(0).getPassword();
        assertEquals(expectedPassword, actualPassword);

        //Test for new password
        assertFalse(AdminTest.getLoggedIn());
        AdminTest.login("simplepassword");
        assertFalse(!AdminTest.getLoggedIn());

        AdminTest.changePassword("simplepassword", "self-setpassword");
        AdminTest.logout();
        assertFalse(AdminTest.getLoggedIn());

        AdminTest.login("self-setpassword");
        assertTrue(AdminTest.getLoggedIn());
    }

    @AfterEach
	public void tearDown(TestInfo testInfo) {
		System.out.println("Finished..." + testInfo.getDisplayName());
	}
}