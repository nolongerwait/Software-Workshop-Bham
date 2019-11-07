import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.ArrayList;

/**
 * In Self-Testing, the code should be tested against three different types, normal cases, boundary cases, and illegal cases.
 * <p>
 * This class contains 8 tests to test all the functions in class Salaries.
 * @author Zetian Qin zxq876
 * @version 2019-11-03 00:16:48
 */

public class SalariesTests {
	public static final double TOLERANCE = 0.0000000001;
	private Salaries salaries;

	@BeforeEach
	public void setup(TestInfo testInfo) {
		System.out.println("Start......" + testInfo.getDisplayName());

		salaries = new Salaries();

		double[] employee1Salary = { 1200, 1000, 2000, 1400, 1300, 0, 0, 0, 0, 0, 0, 0 };
		double[] employee2Salary = { 1000, 1000, 1900, 2000, 1320, 2110, 1350, 1400, 1600, 2200, 2300, 1450 };
		double[] employee3Salary = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1400 };
		salaries.add(employee1Salary);
		salaries.add(employee2Salary);
		salaries.add(employee3Salary);
	}

	@Test
	@DisplayName("Method double average(double) Normal Test NO ZERO SALARY")
	public void test1() {
		double[] employeeTempSalary = { 2390, 2300, 2000, 2000, 1420, 2110, 1110, 1400, 2300, 2200, 2000, 3000 };
		double expectedAverage = 2019.1666666666;
		double actualAverage = Salaries.average(employeeTempSalary);
		assertEquals(expectedAverage, actualAverage, TOLERANCE);
	}

	@Test
	@DisplayName("Method double average(double) Normal Test NOT ALL ZERO SALARY")
	public void test2() {
		double[] employeeTempSalary = { 2000, 2000, 1000, 2000, 1000, 0, 0, 0, 0, 0, 0, 0 };
		double expectedAverage = 1600;
		double actualAverage = Salaries.average(employeeTempSalary);
		assertEquals(expectedAverage, actualAverage, TOLERANCE);
	}

	@Test
	@DisplayName("Method double average(double) Boundary Test ONE MONTH SALARY")
	public void test3() {
		double[] employeeTempSalary = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3000 };
		double expectedAverage = 3000;
		double actualAverage = Salaries.average(employeeTempSalary);
		assertEquals(expectedAverage, actualAverage, TOLERANCE);
	}

	@Test
	@DisplayName("Method double average(double) Boundary Test ZERO SALARY")
	public void test4() {
		double[] employeeTempSalary = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		assertThrows(IllegalArgumentException.class, () -> {
			Salaries.average(employeeTempSalary);
		});
	}

	@Test
	@DisplayName("Method ArrayList<Double> averageSalaries() Narmal Test")
	public void test5() {
		ArrayList<Double> expectedAverageSalaries = new ArrayList<>();

		expectedAverageSalaries.add(1380.0);
		expectedAverageSalaries.add(1635.8333333333);
		expectedAverageSalaries.add(1400.0);

		ArrayList<Double> actualAverageSalaries = salaries.averageSalaries();

		for (int i = 0; i < actualAverageSalaries.size(); i++) {
			assertEquals(expectedAverageSalaries.get(i), actualAverageSalaries.get(i), TOLERANCE);
		}
	}

	@Test
	@DisplayName("Method ArrayList<Double> averageSalaries() Zero Test")
	public void test6() {
		double[] employeeTempSalary = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		ArrayList<Double> expectedAverageSalaries = new ArrayList<>();

		expectedAverageSalaries.add(1380.0);
		expectedAverageSalaries.add(1635.8333333333);
		expectedAverageSalaries.add(1400.0);
		expectedAverageSalaries.add(0.);

		salaries.add(employeeTempSalary);
		ArrayList<Double> actualAverageSalaries = salaries.averageSalaries();

		for (int i = 0; i < actualAverageSalaries.size(); i++) {
			assertEquals(expectedAverageSalaries.get(i), actualAverageSalaries.get(i), TOLERANCE);

		}
	}

	@Test
	@DisplayName("Method boolean not3TimesHigher() Test")
	public void test7() {
		double[] employeeTemp1Salary = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		double[] employeeTemp2Salary = { 1235, 1420, 2100, 1950, 1365, 1256, 0, 1425, 1235, 1455, 0, 0 };
		salaries.add(employeeTemp1Salary);
		salaries.add(employeeTemp2Salary);
		assertTrue(salaries.not3TimesHigher());
	}

	@Test
	@DisplayName("Method boolean not3TimesHigher() False Test")
	public void test8() {
		double[] employeeTempSalary = { 9999999, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		salaries.add(employeeTempSalary);
		assertFalse(salaries.not3TimesHigher());
	}


	@AfterEach
	public void tearDown(TestInfo testInfo) {
		System.out.println("Finished..." + testInfo.getDisplayName());
	}
}
