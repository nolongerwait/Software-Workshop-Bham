/**
 * Aeroplane is a sub-class of the class Aircraft for simple information of aeroplane.
 * This class distinguish three field variables.
 * <pre>
 * passengerNumber, a int to the number of passenger which could sit in the aeroplane, inherite from aircraft.
 * maxSpedd, a int to the max speed of the aeroplane, inherite from aircraft.
 * fuelConsumption, a double to the consumption of the aeroplane.
 * </pre>
 * @author Zetian Qin zxq876
 * @version 2019-11-05 16:42:15
 */

public class Aeroplane extends Aircraft {

    private double fuelConsumption;

    /**
     * The constructor of the class Aeroplane with three field variables.
     * @param passengerNumber the number of passenger which could sit in the aeroplane as int.
     * @param maxSpeed the max speed of the aeroplane as int.
     * @param fuelConsumption the consumption of fuel of the aeroplane as double.
     */
    public Aeroplane(int passengerNumber, int maxSpeed, double fuelConsumption) {
        super(passengerNumber, maxSpeed);
        this.fuelConsumption = fuelConsumption;
    }

    /**
     * This getter could get the fuelConsumption from the class Aeroplane.
     * @return the fuelConsumption of the Aeroplane
     */
    public double getFuelConsumption() {
        return this.fuelConsumption;
    }

    /**
     * This setter could set new fuelConsumption in the class Aeroplane.
     * @param fuelConsumption The changed fuelConsumption of the Aeroplane as double
     */
    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    /**
     * This toString defines how to print a Aeroplane.
     * @return the print type of the Aeroplane as String.
     */
    public String toString() {
        return "Aerplane{" + "passengerNumber = " + this.getPassengerNumber() + ", maxSpeed = " + this.getMaxSpeed() + ", fuelConsumption = " + this.fuelConsumption + "}";
    }

}