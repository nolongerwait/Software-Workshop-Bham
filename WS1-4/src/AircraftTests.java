import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

/**
 * In Self-Testing, the code should be tested against three different types, normal cases, boundary cases, and illegal cases.
 * <p>
 * This class contains 6 tests to test all the functions in class Aircraft and Aeroplane.
 * @author Zetian Qin zxq876
 * @version 2019-11-14 14:24:28
 */
public class AircraftTests {
    public static final double TOLERANCE = 0.0000000001;

    private Aircraft aircraftTest1, aircraftTest2;
    private Aeroplane aeroplaneTest;

    @BeforeEach
    public void setup(TestInfo testInfo) {
        System.out.println("Start......" + testInfo.getDisplayName());

        //Test for class Aircraft
        aircraftTest1 = new Aircraft(200, 900);
        //Test for class Aerocraft
        aeroplaneTest = new Aeroplane(300, 800, 20);
        //Test for Polymorphism 
        aircraftTest2 = new Aeroplane(350, 850, 19.9);
    }

    @Test
    @DisplayName("Getter Tests in class Aircraft")
    public void test1() {
        int expectedNumOfPassengers = 200;
        int actualNumOfPassengers = aircraftTest1.getPassengerNumber();
        assertEquals(expectedNumOfPassengers, actualNumOfPassengers);

        int expectedMaxSpeed = 900;
        int actualMaxSpeed = aircraftTest1.getMaxSpeed();
        assertEquals(expectedMaxSpeed, actualMaxSpeed);
    }

    @Test
    @DisplayName("Setter Tests in class Aircraft")
    public void test2() {
        aircraftTest1.setPassengerNumber(322);
        int expectedNumOfPassengers = 322;
        int actualNumOfPassengers = aircraftTest1.getPassengerNumber();
        assertEquals(expectedNumOfPassengers, actualNumOfPassengers);

        aircraftTest1.setMaxSpeed(888);
        int expectedMaxSpeed = 888;
        int actualMaxSpeed = aircraftTest1.getMaxSpeed();
        assertEquals(expectedMaxSpeed, actualMaxSpeed);
    }

    @Test
    @DisplayName("Getter Tests in class Aeroplane")
    public void test3() {
        int expectedNumOfPassengers = 300;
        int actualNumOfPassengers = aeroplaneTest.getPassengerNumber();
        assertEquals(expectedNumOfPassengers, actualNumOfPassengers);

        int expectedMaxSpeed = 800;
        int actualMaxSpeed = aeroplaneTest.getMaxSpeed();
        assertEquals(expectedMaxSpeed, actualMaxSpeed);

        double expectedFuelCons = 20;
        double actualFuelCons = aeroplaneTest.getFuelConsumption();
        assertEquals(expectedFuelCons, actualFuelCons, TOLERANCE);
    }

    @Test
    @DisplayName("Setter Tests in class Aeroplane")
    public void test4() {
        aeroplaneTest.setPassengerNumber(333);
        int expectedNumOfPassengers = 333;
        int actualNumOfPassengers = aeroplaneTest.getPassengerNumber();
        assertEquals(expectedNumOfPassengers, actualNumOfPassengers);

        aeroplaneTest.setMaxSpeed(777);
        int expectedMaxSpeed = 777;
        int actualMaxSpeed = aeroplaneTest.getMaxSpeed();
        assertEquals(expectedMaxSpeed, actualMaxSpeed);


        aeroplaneTest.setFuelConsumption(11.11);
        double expectedFuelCons = 11.11;
        double actualFuelCons = aeroplaneTest.getFuelConsumption();
        assertEquals(expectedFuelCons, actualFuelCons, TOLERANCE);
    }

    @Test
    @DisplayName("Getter Tests for Polymorphism")
    public void test5() {

        int expectedNumOfPassengers = 350;
        int actualNumOfPassengers = aircraftTest2.getPassengerNumber();
        assertEquals(expectedNumOfPassengers, actualNumOfPassengers);

        int expectedMaxSpeed = 850;
        int actualMaxSpeed = aircraftTest2.getMaxSpeed();
        assertEquals(expectedMaxSpeed, actualMaxSpeed);
    }

    @Test
    @DisplayName("Setter Tests for Polymorphism")
    public void test6() {

        aircraftTest2.setPassengerNumber(222);
        int expectedNumOfPassengers = 222;
        int actualNumOfPassengers = aircraftTest2.getPassengerNumber();
        assertEquals(expectedNumOfPassengers, actualNumOfPassengers);

        aircraftTest2.setMaxSpeed(888);
        int expectedMaxSpeed = 888;
        int actualMaxSpeed = aircraftTest2.getMaxSpeed();
        assertEquals(expectedMaxSpeed, actualMaxSpeed);
    }

    @AfterEach
	public void tearDown(TestInfo testInfo) {
		System.out.println("Finished..." + testInfo.getDisplayName());
	}
}
