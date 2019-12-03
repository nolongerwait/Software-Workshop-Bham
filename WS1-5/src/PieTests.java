import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
/**
 * In Self-Testing, the code should be tested against three different types, normal cases, boundary cases, and illegal cases.
 * <p>
 * This class contains 2 tests to test the basic functions in class Pie.
 * @author Zetian Qin zxq876
 * @version 2019-12-03 09:46:25
 */

public class PieTests {
    public static final double TOLERANCE = 0.0000000001;
    private Expenditure expenditure;

    @BeforeEach
	public void setup(TestInfo testInfo) {
		System.out.println("Start......" + testInfo.getDisplayName());
	}

	@Test
	@DisplayName("Tests for the Expenditure.")
	public void test1() {
		expenditure = new Expenditure("description", 10);

		// Getter Tests
		String expectedDescription = "description";
		String actualDescription = expenditure.getDescription();
		assertEquals(expectedDescription, actualDescription);

		int expectedValue = 10;
		int actualValue = expenditure.getValue();
		assertEquals(expectedValue, actualValue);
	}

	@Test
	@DisplayName("Test for the Exception in Pie.")
	public void test2() {
        Expenditure[] expendituresArgs = new Expenditure[] {
            new Expenditure("Salaries", 11000),
            new Expenditure("Paper", 2000),
            new Expenditure("Rent", 5000),
            new Expenditure("Most popular books on Java etc.",10000),
            new Expenditure("Heating", 3000),
            new Expenditure("Coffee/Tea", 7000),
            new Expenditure("Biscuits", 8000),
            new Expenditure("Travel", 18000),
            new Expenditure("Electricity", 1000),
            new Expenditure("Pencils", 3000)
        };
        int maximumArgs = 15;
		
		// Tests for the Exception
        Assertions.assertThrows(IllegalArgumentException.class, () -> Pie.setValues(expendituresArgs, maximumArgs), "Expected setValues() to throw, but it didn't");

		Assertions.assertThrows(IllegalArgumentException.class, () -> Pie.setValues(expendituresArgs, maximumArgs, 200), "Expected setValues() to throw, but it didn't");
	}

	@AfterEach
	public void tearDown(TestInfo testInfo) {
		System.out.println("Finished..." + testInfo.getDisplayName());
	}
}