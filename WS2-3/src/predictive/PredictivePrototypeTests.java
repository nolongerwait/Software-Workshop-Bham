package predictive;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


/**
 * In Self-Testing, the code should be tested against three different types, normal cases, boundary cases, and illegal cases.
 * <p>
 * This class contains ? tests to test the basic functions in class PredictivePrototype.
 * @author Zetian Qin zxq876
 * @version 2020-02-06 12:51:26
 */

public class PredictivePrototypeTests {
    @BeforeEach
	public void setup(TestInfo testInfo) {
        System.out.println("Start......" + testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Tests for the wordToSignature method")
    public void test1() {
        String testCase1 = "hello";
        String expectedCase1 = "43556";
        assertEquals(expectedCase1, PredictivePrototype.wordToSignature(testCase1));
    }

    @Test
    @DisplayName("Tests for the isValidWord method")
    public void test2() {
        String testCase1 = "hell0123";
        assertFalse(PredictivePrototype.isValidWord(testCase1));

        String testCase2 = "";
        assertFalse(PredictivePrototype.isValidWord(testCase2));

        String testCase3 = "hello";
        assertTrue(PredictivePrototype.isValidWord(testCase3));

        String testCase4 = "1002";
        assertFalse(PredictivePrototype.isValidWord(testCase4));
    }

    @AfterEach
	public void tearDown(TestInfo testInfo) {
		System.out.println("Finished..." + testInfo.getDisplayName());
	}
}