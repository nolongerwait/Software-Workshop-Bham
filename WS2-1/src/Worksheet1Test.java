import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


/**
 * In Self-Testing, the code should be tested against three different types, normal cases, boundary cases, and illegal cases.
 * <p>
 * This class contains 8 tests to test the basic functions in class Worksheet1.
 * @author Zetian Qin zxq876
 * @version 2020-01-16 15:17:21
 */

public class Worksheet1Test {

    @BeforeEach
	public void setup(TestInfo testInfo) {
        System.out.println("Start......" + testInfo.getDisplayName());
    }
    
    @Test
    @DisplayName("Tests for the Exercise 1")
    public void test1() {
        // Tests for power() method
        assertEquals(9, Worksheet1.power(3,2));
        assertEquals(1024, Worksheet1.power(2,10));
        assertEquals(1, Worksheet1.power(4,0));
        assertEquals(5, Worksheet1.power(5,1));
        assertThrows(IllegalArgumentException.class, () -> Worksheet1.power(0, 0), "Expected power() to throw, but it didn't");

        // Test for fastPower() method
        assertEquals(9, Worksheet1.fastPower(3,2));
        assertEquals(1024, Worksheet1.fastPower(2,10));
        assertEquals(1, Worksheet1.fastPower(4,0));
        assertEquals(5, Worksheet1.fastPower(5,1));
        assertThrows(IllegalArgumentException.class, () -> Worksheet1.fastPower(0, 0), "Expected fastPower() to throw, but it didn't");
    }

    @Test
    @DisplayName("Tests for the Exercise 2")
    public void test2() {
        // Test for the List in normal case 
        List<Integer> testCase1 = new List<Integer>(2,new List<Integer>(-5, new List<Integer>(8, new List<Integer>(0,new List<Integer>()))));

        List<Integer> expectedCase1 = new List<Integer>(-2, new List<Integer>(5, new List<Integer>(-8, new List<Integer>(0, new List<Integer>()))));

        assertEquals(expectedCase1, Worksheet1.negateAll(testCase1));

        // Test for the empty List
        List<Integer> testCase2 = new List<Integer>();

        List<Integer> expectedCase2 = new List<Integer>();

        assertEquals(expectedCase2, Worksheet1.negateAll(testCase2));
    }

    @Test
    @DisplayName("Tests for the Exercise 3")
    public void test3() {
        List<Integer> testCase = new List<Integer>(7,new List<Integer>(5, new List<Integer>(3, new List<Integer>(8,new List<Integer>()))));

        assertEquals(2, Worksheet1.find(3,testCase));
        assertEquals(0, Worksheet1.find(7,testCase));
        assertEquals(1, Worksheet1.find(5,testCase));
        assertEquals(3, Worksheet1.find(8,testCase));
        assertThrows(IllegalArgumentException.class, () -> Worksheet1.find(0, testCase), "Expected find() to throw, but it didn't");
    }

    @Test
    @DisplayName("Tests for the Exercise 4")
    public void test4() {
        // Test for the normal case - False
        List<Integer> testCase1 = new List<Integer>(2,new List<Integer>(3,new List<Integer>(-5, new List<Integer>(8, new List<Integer>(-2, new List<Integer>())))));

        assertFalse(Worksheet1.allEven(testCase1));

        // Test for the normal case - True
        List<Integer> testCase2 = new List<Integer>(2,new List<Integer>(4,new List<Integer>(-6, new List<Integer>(8, new List<Integer>(-2, new List<Integer>())))));

        assertTrue(Worksheet1.allEven(testCase2));
    }

    @Test
    @DisplayName("Tests for the Exercise 5")
    public void test5() {
        // Test for the normal case
        List<Integer> testCase1 = new List<Integer>(2,new List<Integer>(3,new List<Integer>(-5, new List<Integer>(8, new List<Integer>(-2, new List<Integer>())))));

        List<Integer> expectedCase1 = new List<Integer>(2, new List<Integer>(8, new List<Integer>(-2, new List<Integer>())));

        assertEquals(expectedCase1, Worksheet1.evenNumbers(testCase1));

        // Test for the List does not contain even numbers
        List<Integer> testCase2 = new List<Integer>(1,new List<Integer>(3,new List<Integer>(-5, new List<Integer>(9, new List<Integer>(-1, new List<Integer>())))));

        List<Integer> expectedCase2 = new List<Integer>();

        assertEquals(expectedCase2, Worksheet1.evenNumbers(testCase2));

        // Test for empty List
        List<Integer> testCase3 = new List<Integer>();

        List<Integer> expectedCase3 = new List<Integer>();

        assertEquals(expectedCase3, Worksheet1.evenNumbers(testCase3));
    }

    @Test
    @DisplayName("Tests for the Exercise 6")
    public void test6() {
        // Test for the normal case - True
        List<Integer> testCase1 = new List<Integer>(9,new List<Integer>(7,new List<Integer>(1, new List<Integer>(0, new List<Integer>(-2, new List<Integer>())))));

        assertTrue(Worksheet1.sorted(testCase1));

        // Test for the normal case - False
        List<Integer> testCase2 = new List<Integer>(2,new List<Integer>(4,new List<Integer>(-6, new List<Integer>(8, new List<Integer>(-2, new List<Integer>())))));

        assertFalse(Worksheet1.sorted(testCase2));

        // Test for the empty List
        List<Integer> testCase3 = new List<Integer>();
        
        assertTrue(Worksheet1.sorted(testCase3));
    }

    @Test
    @DisplayName("Tests for the Exercise 7")
    public void test7() {
        // Test for the number of Node in List a is less than List b
        List<Integer> testCase1a = new List<Integer>(8,new List<Integer>(2,new List<Integer>()));
        List<Integer> testCase1b = new List<Integer>(9,new List<Integer>(8, new List<Integer>(7, new List<Integer>(5,new List<Integer>()))));

        List<Integer> expectedCase1 = new List<Integer>(9, new List<Integer>(8, new List<Integer>(8, new List<Integer>(7, new List<Integer>(5, new List<Integer>(2, new List<Integer>()))))));

        assertEquals(expectedCase1, Worksheet1.merge(testCase1a,testCase1b));

        // Test for the number of Node in List a is more than List b
        List<Integer> testCase2a = new List<Integer>(8,new List<Integer>(5,new List<Integer>(5, new List<Integer>(2, new List<Integer>()))));
        List<Integer> testCase2b = new List<Integer>(9,new List<Integer>());

        List<Integer> expectedCase2 = new List<Integer>(9, new List<Integer>(8, new List<Integer>(5, new List<Integer>(5, new List<Integer>(2, new List<Integer>())))));

        assertEquals(expectedCase2, Worksheet1.merge(testCase2a,testCase2b));

        // Test for the number of Node in List a is equal to List b
        List<Integer> testCase3a = new List<Integer>(8,new List<Integer>(5,new List<Integer>(5, new List<Integer>(2, new List<Integer>()))));
        List<Integer> testCase3b = new List<Integer>(9,new List<Integer>(8, new List<Integer>(7, new List<Integer>(5, new List<Integer>()))));
        List<Integer> expectedCase3 = new List<Integer>(9, new List<Integer>(8, new List<Integer>(8, new List<Integer>(7, new List<Integer>(5, new List<Integer>(5, new List<Integer>(5, new List<Integer>(2, new List<Integer>()))))))));

        assertEquals(expectedCase3, Worksheet1.merge(testCase3a,testCase3b));

        // Test for the List a is empty
        List<Integer> testCase4a = new List<Integer>();
        List<Integer> testCase4b = new List<Integer>(9,new List<Integer>(8, new List<Integer>(7, new List<Integer>(5, new List<Integer>()))));

        List<Integer> expectedCase4 = new List<Integer>(9,new List<Integer>(8, new List<Integer>(7, new List<Integer>(5, new List<Integer>()))));

        assertEquals(expectedCase4, Worksheet1.merge(testCase4a,testCase4b));

        // Test for the List b is empty
        List<Integer> testCase5a = new List<Integer>(8,new List<Integer>(5,new List<Integer>(5, new List<Integer>(2, new List<Integer>()))));
        List<Integer> testCase5b = new List<Integer>();

        List<Integer> expectedCase5 = new List<Integer>(8,new List<Integer>(5,new List<Integer>(5, new List<Integer>(2, new List<Integer>()))));

        assertEquals(expectedCase5, Worksheet1.merge(testCase5a,testCase5b));
    }

    @Test
    @DisplayName("Tests for the Exercise 8")
    public void test8() {
        // Test for the empty List
        List<Integer> testCase1 = new List<Integer>(); 
        List<Integer> expectedCase1 = new List<Integer>();
        
        assertEquals(expectedCase1, testCase1);

        // Test for the List which all elements are same
        List<Integer> testCase2 = new List<Integer>(9,new List<Integer>(9,new List<Integer>(9, new List<Integer>(9, new List<Integer>(9, new List<Integer>())))));

        List<Integer> expectedCase2 = new List<Integer>(9,new List<Integer>());

        assertEquals(expectedCase2, Worksheet1.removeDuplicates(testCase2));

        // Test for the List in normal case
        List<Integer> testCase3 = new List<Integer>(999,new List<Integer>(999,new List<Integer>(8, new List<Integer>(8, new List<Integer>(7, new List<Integer>())))));

        List<Integer> expectedCase3 = new List<Integer>(999,new List<Integer>(8, new List<>(7, new List<Integer>())));

        assertEquals(expectedCase3, Worksheet1.removeDuplicates(testCase3));

        // Test for the List which has only one element
        List<Integer> testCase4 = new List<Integer>(999,new List<Integer>());

        List<Integer> expectedCase4 = new List<Integer>(999,new List<Integer>());

        assertEquals(expectedCase4, Worksheet1.removeDuplicates(testCase4));
    }

    @AfterEach
	public void tearDown(TestInfo testInfo) {
		System.out.println("Finished..." + testInfo.getDisplayName());
	}
}