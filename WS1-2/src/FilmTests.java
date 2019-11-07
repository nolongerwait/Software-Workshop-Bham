import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * In Self-Testing, the code should be tested against three different types, normal cases, boundary cases, and illegal cases.
 * <p>
 * However, since the current program does not perform the legality detection of the parameters and the description of the abnormalities, this test only performs simple routine tests.
 * <p>
 * This class contains 5 tests to test the toString, the setter and getter of the class Film.
 * @author Zetian Qin zxq876
 * @version 2019-10-19 11:43:07
 */
public class FilmTests {

	private Date date;
	private Film detectiveConan;

	@BeforeEach
	public void init(TestInfo testInfo) {
		System.out.println("Start..." + testInfo.getDisplayName());
		date = new Date(18, "October", 2019);
		detectiveConan = new Film("Detective Conan", date, 123);
	}

	@Test
	@DisplayName("Getter of Date Test")
	public void test1() {
		int expectedDay = 18;
		int actualDay = detectiveConan.getReleaseDate().getDay();
		assertEquals(expectedDay, actualDay, "failure in test1: " + " expected day does not equal the actual day");
		String expectedMonth = "October";
		String actualMonth = detectiveConan.getReleaseDate().getMonth();
		assertEquals(expectedMonth, actualMonth, "failure in test1: " + " expected month does not equal the actual month");
		int expectedYear = 2019;
		int actualYear = detectiveConan.getReleaseDate().getYear();
		assertEquals(expectedYear, actualYear, "failure in test1: " + " expected year does not equal the actual year");
	}

	@Test
	@DisplayName("Getter of Title Test")
	public void test2() {
		String expectedTitle = "Detective Conan";
		String actualTitle = detectiveConan.getTitle();
		assertEquals(expectedTitle, actualTitle, "failure in test2: " + " expected title does not equal the actual title");
	}

	@Test
	@DisplayName("Getter of Length Test")
	public void test3() {
		int expectedLength = 123;
		int actualLength = detectiveConan.getLength();
		assertEquals(expectedLength, actualLength,
				"failure in test3: " + " expected length does not equal the actual length");
	}

	@Test
	@DisplayName("Setter of Date Test")
	public void test4() {
		Date updatedReleaseDate = new Date(03, "October", 2020);
		detectiveConan.setReleaseDate(updatedReleaseDate);

		String expectedDate = "3 October 2020";
		String actualDate = detectiveConan.getReleaseDate().toString();

		assertEquals(expectedDate, actualDate, "failure in test4: " + " expected date does not equal the actual date");
	}

	@Test
	@DisplayName("Comprehensive Test")
	public void test5() {
		Date updatedReleaseDate = new Date(01, "January", 2022);
		Film joker = new Film("Joker", updatedReleaseDate, 122);
		joker.setReleaseDate(new Date(04, "October", 2019));

		boolean expected = false;
		boolean actual = detectiveConan.getReleaseDate() == updatedReleaseDate;

		assertEquals(expected, actual, "failure in test5: " + " expected value date does not equal the actual value");

		assertFalse(joker.getTitle().equals(detectiveConan.getTitle()), "failure in test5: " + " titles of the two films should not be the same");

		assertTrue(!joker.getTitle().equals(detectiveConan.getTitle()), "failure in test5: " + " titles of the two films should not be the same");

		assertTrue(joker.getReleaseDate().getDay() != detectiveConan.getReleaseDate().getDay(), "failure in test5: " + " Days of the two films should not be equal to each other");

		assertTrue(joker.getReleaseDate().getYear() == detectiveConan.getReleaseDate().getYear(), "failure in test5: " + " Years of the two films should be equal to each other");
	}

	@Test
	@AfterEach
	public void tearDown(TestInfo testInfo) {
    	System.out.println("Finished..." + testInfo.getDisplayName());
    }	

}
