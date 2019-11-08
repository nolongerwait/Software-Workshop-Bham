/**
* This class is Ex2 homework.
* @author ZXQ876 Zetian Qin
* @version 2019-10-03
* 
*/

public class Ex2 {
    public static void main(String[] args) {
        //declaration with initialization
        double ton = 0;
        double hundredweight = 0;
        double quarter = 0;
        double stone = 0;
        double pound = 0;
        double ounce = 0;
        double drachm = 0;
        double grain = 0;
        
        double result = 0;
        //the value of one person's weight
        stone = 11;
        pound = 6;
        //use the method imperialToKg
        result = imperialToKg(ton, hundredweight, quarter, stone, pound, ounce, drachm, grain);
        System.out.println("The weight in kg of this person is " + result);
    }

    /**
    * This method converts the imperial system weight unit to kilograms.
    * @param ton The value of different units of weight in imperial system. - double type
    * @param hundredweight The value of different units of weight in imperial system. - double type
    * @param quarter The value of different units of weight in imperial system. - double type
    * @param stone The value of different units of weight in imperial system. - double type
    * @param pound The value of different units of weight in imperial system. - double type
    * @param ounce The value of different units of weight in imperial system. - double type
    * @param drachm The value of different units of weight in imperial system. - double type
    * @param grain The value of different units of weight in imperial system. - double type
    * @return The results of the conversion of imperial system units into kilograms - double type
    */
    public static double imperialToKg(double ton, double hundredweight, double quarter, double stone, double pound, double ounce, double drachm, double grain) {
        double resultPound = ton * 2240 + hundredweight * 112 + quarter * 28 + pound + stone * 14 + ounce / 16 + drachm / 256 + grain / 7000;
        double resultKg = resultPound * 0.45359237;
        return resultKg;
    }
}