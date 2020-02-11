package predictive;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

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

        String testCase2 = "Dione";
        String expectedCase2 = "34663";
        assertEquals(expectedCase2, PredictivePrototype.wordToSignature(testCase2));

        String testCase3 = "";
        String expectedCase3 = "";
        assertEquals(expectedCase3, PredictivePrototype.wordToSignature(testCase3));
    }

    @Test
    @DisplayName("Tests for the isValidWord method")
    public void test2() {
        String testCase1 = "hell0123jjj";
        assertFalse(PredictivePrototype.isValidWord(testCase1));

        String testCase2 = "";
        assertFalse(PredictivePrototype.isValidWord(testCase2));

        String testCase3 = "Hello";
        assertTrue(PredictivePrototype.isValidWord(testCase3));

        String testCase4 = "1002";
        assertFalse(PredictivePrototype.isValidWord(testCase4));
    }

    @Test
    @DisplayName("Tests for the signatureToWords method")
    public void test3() throws FileNotFoundException{
        String testCase1 = "4663";
        Set<String> expectedCase1 = new HashSet<String>();
        expectedCase1.add("hood");
        expectedCase1.add("ione");
        expectedCase1.add("ioof");
        expectedCase1.add("good");
        expectedCase1.add("hond");
        expectedCase1.add("inne");
        expectedCase1.add("gond");
        expectedCase1.add("hone");
        expectedCase1.add("hoof");
        expectedCase1.add("gone");
        expectedCase1.add("goof");
        expectedCase1.add("ioof");
        expectedCase1.add("home");
        expectedCase1.add("gome");
        assertEquals(expectedCase1, PredictivePrototype.signatureToWords(testCase1));
    }

    @AfterEach
	public void tearDown(TestInfo testInfo) {
		System.out.println("Finished..." + testInfo.getDisplayName());
	}
}