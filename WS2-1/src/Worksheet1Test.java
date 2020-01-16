import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


/**
 * In Self-Testing, the code should be tested against three different types, normal cases, boundary cases, and illegal cases.
 * <p>
 * This class contains  tests to test the basic functions in class Waffle.
 * @author Zetian Qin zxq876
 * @version 2020-01-16 15:17:21
 */

public class Worksheet1Test {
    private Worksheet1 worksheet1test;

    @BeforeEach
	public void setup(TestInfo testInfo) {
        System.out.println("Start......" + testInfo.getDisplayName());
        worksheet1test = new Worksheet1();
    }
    
    @Test
    @DisplayName("Tests for the Exercise 1")
    public void test1() {
        assertEquals(worksheet1test.power(3,2), 9);
        assertEquals(worksheet1test.power(2,10), 1024);
        assertEquals(worksheet1test.power(4,0), 1);
        assertEquals(worksheet1test.power(5,1), 5);

        assertEquals(worksheet1test.fastPower(2,10), 1024);
    }

    @Test
    @DisplayName("Tests for the Exercise 2")
    public void test2() {
        
    }

    @AfterEach
	public void tearDown(TestInfo testInfo) {
		System.out.println("Finished..." + testInfo.getDisplayName());
	}
}


