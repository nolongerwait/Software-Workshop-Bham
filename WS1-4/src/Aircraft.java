/**
 * Aircraft is a class for a very simple infromation of aircraft.
 * This class distinguish two field variables.
 * <pre>
 * passengerNumber, a int to the number of passenger which could sit in the aircraft.
 * maxSpedd, a int to the max speed of the aircraft.
 * </pre>
 * @author Zetian Qin zxq876
 * @version 2019-11-05 15:46:38
 */

public class Aircraft {

    private int passengerNumber;
    private int maxSpeed;

    /**
     * The constructor of the class Aricraft with two parameters.
     * @param passengerNumber the number of passenger which could sit in the aricraft as int.
     * @param maxSpeed the max speed of the aricraft as int.
     */
    public Aircraft(int passengerNumber, int maxSpeed) {
        this.passengerNumber = passengerNumber;
        this.maxSpeed = maxSpeed;
    }

    /**
     * This getter could get the passengerNumber from the class Aircraft.
     * @return the passengerNumber of the Aircraft
     */
    public int getPassengerNumber() {
        return this.passengerNumber;
    }

    /**
     * This getter could get the maxSpeed from the class Aircraft.
     * @return the maxSpeed of the Aircraft
     */
    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    /**
     * This setter could set new passengerNumber in the class Aircraft.
     * @param passengerNumber The changed passengerNumber of the Aircraft as int.
     */
    public void setPassengerNumber(int passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    /**
     * This setter could set new maxSpeed in the class Aircraft.
     * @param maxSpeed The changed maxSpeed of the Aricraft as int.
     */
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * This toString defines how to print a Aircraft.
     * @return the print type of the Aircraft as String.
     */
    public String toString() {
        return "Aircraft{" + "passengerNumber = " + this.passengerNumber + ", maxSpeed = " + this.maxSpeed + "}";
    }

}