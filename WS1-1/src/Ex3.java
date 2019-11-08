/**
* This class is Ex3 homework.
* @author ZXQ876 Zetian Qin
* @version 2019-10-03
* 
*/

public class Ex3 {
    public static void main(String[] args) {
        //declaration with initialization
        int hours = 0;
        int minutes = 0;
        double seconds = 0;
        
        int angle = 0;
        //Ex3-(a) 
        //9:00
        hours = 9;
        minutes = 0;
        angle = timeToAngle(hours, minutes, seconds);
        
        System.out.println("The angle between the hour hand and the minute hand at " + hours + ":" + minutes + " is " + angle + "°." );
        //3:00
        hours = 3;
        minutes = 0;
        angle = timeToAngle(hours, minutes, seconds);
        
        System.out.println("The angle between the hour hand and the minute hand at " + hours + ":" + minutes + " is " + angle + "°." );
        //18:00
        hours = 18;
        minutes = 0;
        angle = timeToAngle(hours, minutes, seconds);
        
        System.out.println("The angle between the hour hand and the minute hand at " + hours + ":" + minutes + " is " + angle + "°." );
        //1:00, 
        hours = 1;
        minutes = 0;
        angle = timeToAngle(hours, minutes, seconds);
        
        System.out.println("The angle between the hour hand and the minute hand at " + hours + ":" + minutes + " is " + angle + "°." );
        //2:30
        hours = 2;
        minutes = 30;
        angle = timeToAngle(hours, minutes, seconds);
        
        System.out.println("The angle between the hour hand and the minute hand at " + hours + ":" + minutes + " is " + angle + "°." );
        //4:41
        hours = 4;
        minutes = 41;
        angle = timeToAngle(hours, minutes, seconds);
        
        System.out.println("The angle between the hour hand and the minute hand at " + hours + ":" + minutes + " is " + angle + "°." );
        
        //Ex3-(b)
        //0:00:22
        hours = 0;
        minutes = 0;
        seconds = 20;
        angle = timeToAngle(hours, minutes, seconds);
        
        System.out.println("The angle between the hour hand and the minute hand at " + hours + ":" + minutes + ":" + seconds + " is " + angle + "°." );
        //13:05:27.272727272727
        hours = 13;
        minutes = 5;
        seconds = 27.272727272727;
        angle = timeToAngle(hours, minutes, seconds);
        
        System.out.println("The angle between the hour hand and the minute hand at " + hours + ":" + minutes + ":" + seconds + " is " + angle + "°." );
    }  

    /**
    * 1) The hour hand moves 30° per hour, moves 0.5° per minute and moves 1/120° per second. 2) The minute hand moves 6° per minute and moves 0.1° per second.
    * <p>
    * So the angle between the hour and minute hands should be (30 * hours + 0.5 * minutes + 1.0 / 120 * seconds) - (6 * minutes + 0.1 * seconds). 
    * <p>
    * However, the Ex3 requires the angle is a CWW(counterclockwise) angle, so in fact, the angle should be (30 * hours + 0.5 * minutes + 1.0 / 120 * seconds) - (6 * minutes + 0.1 * seconds) + 360. Finally, the Ex3 requires the angle should be between 0 and 359, so the result requires a modulo operation( % 360).
    * <p>
    *In addition, due to Ex3 requirements which result should be rounded off. I used the Math.round method in Java. But because the result of Math.round is a long type,  I do a forced type conversion to get the int type.
    * @param hours The value of hours. - int type
    * @param minutes The value of minutes. - int type
    * @param seconds The value of seconds. - double type
    * @return The angle(CWW) between the hour hand and the minute hand. - int type
    */
    public static int timeToAngle(int hours, int minutes, double seconds) {
        int angle = (int)(Math.round(((30 * hours + 0.5 * minutes + 1.0 / 120 * seconds) - (6 * minutes + 0.1 * seconds) + 360))) % 360;
        return angle;
    }
}