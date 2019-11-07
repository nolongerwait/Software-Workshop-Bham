/**
 * Temperature is a class for the data of temperatures over a year.
 * This class has no field object variables.
 * @author Zetian Qin zxq876
 * @version 2019-10-28 14:12:32
 */
public class Temperature {

    /**
     * This coldest method is written to find the first day of the year with the lowest temperature.
     * @param temperatures The data of temperatures over a year, as a double array.
     * @return The first day of the year with the lowest temperature.
     */
    public static int coldest(double[] temperatures) {
        int index = 1;
        double minimal = temperatures[0];
        for(int i = 1; i < temperatures.length; i++) {
            if(temperatures[i] < minimal) {
                minimal = temperatures[i];
                index = i + 1;
            }
        }
        return index;
    }
}