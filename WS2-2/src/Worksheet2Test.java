import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


/**
 * In Self-Testing, the code should be tested against three different types, normal cases, boundary cases, and illegal cases.
 * <p>
 * This class contains 8 tests to test the basic functions in class Worksheet2.
 * @author Zetian Qin zxq876
 * @version 2020-01-23 15:08:38
 */

public class Worksheet2Test {

    @BeforeEach
	public void setup(TestInfo testInfo) {
        System.out.println("Start......" + testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Tests for the Exercise 1")
    public void test1() {
        // Tests for the normal case
        Tree<Integer> testCase1 = 
        new Tree<Integer>(9, 
            new Tree<Integer>(7, 
                new Tree<Integer>(6, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(8, new Tree<Integer>(), new Tree<Integer>())), 
            new Tree<Integer>(11, 
                new Tree<Integer>(10, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(12, new Tree<Integer>(), new Tree<Integer>())));

        Tree<Integer> exceptedCase1 = 
        new Tree<Integer>(-9, 
            new Tree<Integer>(-7, 
                new Tree<Integer>(-6, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(-8, new Tree<Integer>(), new Tree<Integer>())), 
            new Tree<Integer>(-11, 
                new Tree<Integer>(-10, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(-12, new Tree<Integer>(), new Tree<Integer>())));

        assertEquals(exceptedCase1, Worksheet2.negateAll(testCase1));
    }

    @Test
    @DisplayName("Tests for the Exercise 2")
    public void test2() {
        // Tests for the normal case - True
        Tree<Integer> testCase1 = 
        new Tree<Integer>(2, 
            new Tree<Integer>(4, 
                new Tree<Integer>(6, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(8, new Tree<Integer>(), new Tree<Integer>())), 
            new Tree<Integer>(10, 
                new Tree<Integer>(12, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(14, new Tree<Integer>(), new Tree<Integer>())));

        assertTrue(Worksheet2.allEven(testCase1));

        // Tests for the normal case - False
        Tree<Integer> testCase2 = 
        new Tree<Integer>(1, 
            new Tree<Integer>(2, 
                new Tree<Integer>(3, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(4, new Tree<Integer>(), new Tree<Integer>())), 
            new Tree<Integer>(5, 
                new Tree<Integer>(6, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(7, new Tree<Integer>(), new Tree<Integer>())));

        assertFalse(Worksheet2.allEven(testCase2));

        // Test for the empty tree
        assertTrue(Worksheet2.allEven(new Tree<Integer>()));
    }

    @Test
    @DisplayName("Tests for the Exercise 3")
    public void test3() {
        // Tests for the normal case
        Tree<Integer> testCase1 = 
        new Tree<Integer>(9, 
            new Tree<Integer>(7, 
                new Tree<Integer>(6, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(8, new Tree<Integer>(), new Tree<Integer>())), 
            new Tree<Integer>(11, 
                new Tree<Integer>(10, new Tree<Integer>(), new Tree<Integer>()), 
                new Tree<Integer>(12, new Tree<Integer>(), new Tree<Integer>())));

        assertEquals(0, Worksheet2.depth(testCase1, 9));
        assertEquals(1, Worksheet2.depth(testCase1, 7));
        assertEquals(2, Worksheet2.depth(testCase1, 8));
        assertEquals(2, Worksheet2.depth(testCase1, 6));
        assertEquals(1, Worksheet2.depth(testCase1, 11));
        assertEquals(2, Worksheet2.depth(testCase1, 10));
        assertEquals(2, Worksheet2.depth(testCase1, 12));
        assertEquals(-1, Worksheet2.depth(testCase1, 0));
        assertEquals(-1, Worksheet2.depth(new Tree<Integer>(), 12));
    }

    @AfterEach
	public void tearDown(TestInfo testInfo) {
		System.out.println("Finished..." + testInfo.getDisplayName());
	}
}