import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * In Self-Testing, the code should be tested against three different types, normal cases, boundary cases, and illegal cases.
 * <p>
 * This class contains 12 tests to test all the functions in class GenerateClass.
 * @author Zetian Qin zxq876
 * @version 2019-11-03 19:16:55
 */
public class GenerateClassTests {

	private GenerateClass testClass1;
	private GenerateClass testClass2;
	private GenerateClass testClass3;

	@BeforeEach
	public void setup(TestInfo testInfo) {
		System.out.println("Start......" + testInfo.getDisplayName());

		//Normal Test(the example in WS1-3)
		String classname1 = "Person";
		String[] variableNames1 = { "name", "dob" };
		String[] variableTypes1 = { "String", "Date" };
		testClass1 = new GenerateClass(classname1, variableNames1, variableTypes1);

		//More Different Test
		String classname2 = "Car";
		String[] variableNames2 = { "make", "maxSpeed", "weight", "colour" };
		String[] variableTypes2 = { "String", "double", "double", "String" };
		testClass2 = new GenerateClass(classname2, variableNames2, variableTypes2);

		//Empty Test
		String classname3 = "Empty";
		String[] variableNames3 = {};
		String[] variableTypes3 = {};

		testClass3 = new GenerateClass(classname3, variableNames3, variableTypes3);

	}

	@Test
	@DisplayName("makeFields Test in testClass1 Normal Test(the example in WS1-3)")
	public void test1() {
		String expectedFields = "  private String name;\n" + "  private Date dob;\n\n";
		String actualFields = testClass1.makeFields();
		assertEquals(expectedFields, actualFields);
	}

	@Test
	@DisplayName("makeConstructor Test in testClass1 Normal Test(the example in WS1-3)")
	public void test2() {
		String expectedConstructor = "  public Person(String name, Date dob){\n" + "    this.name = name;\n"
				+ "    this.dob = dob;\n" + "  }\n";
		String actualConstructor = testClass1.makeConstructor();
		assertEquals(expectedConstructor, actualConstructor);
	}

	@Test
	@DisplayName("makeGetters Test in testClass1 Normal Test(the example in WS1-3)")
	public void test3() {
		String expectedGetters = "  public String getName(){\n" + "    return name;\n" + "  }\n"
				+ "  public Date getDob(){\n" + "    return dob;\n" + "  }\n";
		String actualGetters = testClass1.makeGetters();
		assertEquals(expectedGetters, actualGetters);
	}

	@Test
	@DisplayName("makeSetters Test in testClass1 Normal Test(the example in WS1-3)")
	public void test4() {
		String expectedSetters = "  public void setName(String name){\n" + "    this.name = name;\n" + "  }\n"
				+ "  public void setDob(Date dob){\n" + "    this.dob = dob;\n" + "  }\n";
		String actualSetters = testClass1.makeSetters();
		assertEquals(expectedSetters, actualSetters);
	}

	@Test
	@DisplayName("makeFields Test in testClass2 More Different Test")
	public void test5() {
		String expectedFields = "  private String make;\n" + "  private double maxSpeed;\n" + "  private double weight;\n" + "  private String colour;\n\n";
		String actualFields = testClass2.makeFields();
		assertEquals(expectedFields, actualFields);
	}

	@Test
	@DisplayName("makeConstructor Test in testClass2 More Different Test")
	public void test6() {
		String expectedConstructor = "  public Car(String make, double maxSpeed, double weight, String colour){\n"
				+ "    this.make = make;\n" + "    this.maxSpeed = maxSpeed;\n" + "    this.weight = weight;\n" + "    this.colour = colour;\n" + "  }\n";
		String actualConstructor = testClass2.makeConstructor();
		assertEquals(expectedConstructor, actualConstructor);
	}

	@Test
	@DisplayName("makeGetters Test in testClass2 More Different Test")
	public void test7() {
		String expectedGetters = "  public String getMake(){\n" + "    return make;\n" + "  }\n"
				+ "  public double getMaxSpeed(){\n" + "    return maxSpeed;\n" + "  }\n" + "  public double getWeight(){\n"
				+ "    return weight;\n" + "  }\n" + "  public String getColour(){\n"
				+ "    return colour;\n" + "  }\n";
		String actualGetters = testClass2.makeGetters();
		assertEquals(expectedGetters, actualGetters);
	}

	@Test
	@DisplayName("makeSetters Test in testClass2 More Different Test")
	public void test8() {
		String expectedSetters = "  public void setMake(String make){\n" + "    this.make = make;\n" + "  }\n"
				+ "  public void setMaxSpeed(double maxSpeed){\n" + "    this.maxSpeed = maxSpeed;\n" + "  }\n"
				+ "  public void setWeight(double weight){\n" + "    this.weight = weight;\n" + "  }\n" + "  public void setColour(String colour){\n" + "    this.colour = colour;\n" + "  }\n";
		String actualSetters = testClass2.makeSetters();
		assertEquals(expectedSetters, actualSetters);
	}

	@Test
	@DisplayName("makeFields Test in testClass3 Empty Test")
	public void test9() {
		String expectedFields = "\n";
		String actualFields = testClass3.makeFields();
		assertEquals(expectedFields, actualFields);
	}

	@Test
	@DisplayName("makeConstructor Test in testClass3 Empty Test")
	public void test10() {
		String expectedConstructor = "  public Empty(){}\n";
		String actualConstructor = testClass3.makeConstructor();
		assertEquals(expectedConstructor, actualConstructor);
	}

	@Test
	@DisplayName("makeGetters Test in testClass3 Empty Test")
	public void test11() {
		String expectedGetters = "";
		String actualGetters = testClass3.makeGetters();
		assertEquals(expectedGetters, actualGetters);
	}

	@Test
	@DisplayName("makeSetters Test in testClass3 Empty Test")
	public void test12() {
		String expectedSetters = "";
		String actualSetters = testClass3.makeSetters();
		assertEquals(expectedSetters, actualSetters);
	}

	@AfterEach
	public void tearDown(TestInfo testInfo) {
		System.out.println("Finished..." + testInfo.getDisplayName());
	}
}
