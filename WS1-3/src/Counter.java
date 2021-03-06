import java.util.*;
import java.util.regex.Pattern;

/**
 * <pre>
 * NOTE: I once asked whether the name of Ex4 is Counter or Counting, but no one told me the answer. So I don't want to lose my mark because the file name is incorrect.
 * The Problems in This Code:
 * <p>
 * 1. Each case in switch is not independent. After executing a certain case, the remaining cases will be executed sequentially.
 * <p>
 * 2. When enter a illegal value(not 1 2 3 4 5), s.next(p12345) throw an InputMismatchException. So, it must be caught. 
 * And then doing Exception handling, must clean the buffer in Scanner.next by using s.next(). 
 * And due to this Exception, we cannot enter the default case forever, I delete the default case.
 * <p>
 * 3. When the loop is end, must close the Scanner.
 * <p>
 * 4. In those methods(add, subtract, set) with parameter Scanner s, when catch the NumberFormatException should use the method itself again(iterative call) to re-enter the right value instead of new Scanner again.
 * <p>
 * 5. I found the warning(The value of the local variable parcels is not used) in main method, but I cannot solve this warning because there is no delete operation in Java.
 * </pre>
 * The class is used in order to interactively count a number of
 * persons/items in a room, storeroom, etc. A user is able to select
 * between five options:
 * <pre>
 * 1. add to the counter,
 * 2. subtract from the counter,
 * 3. show the counter,
 * 4. set the counter to a new value, and
 * 5. exit the program.
 * </pre>
 * @author Zetian Qin and Manfred Kerber and Alexandros Evangelidis
 * @version 2019-11-04 16:35:07
 * 
 */
public class Counter {
    /**
     * Pattern for the choice of input: either 1, 2, 3, 4, or 5
     */
    public static final Pattern p12345 = Pattern.compile("[12345]");

    /**
     * Field variable to store the value of the counter. 
     */
    private int counter;

    /**
     * The constructor initializes the counter as 0. Starts a scanner 
     * to read from the command line, offering 5 choices for (add,
     * subtract, show counter, set counter, and exit). It stays in a
     * loop until the program is explicitly exited by entering 5.
     * It has no parameters. 
     */
    public Counter(){
        this.counter = 0;
        //System.in is like System.out, however, for input and not for output. 
        Scanner s = new Scanner(System.in);
        /* can take value 1 (add)
         *                2 (subtract)
         *                3 (show counter)
         *                4 (set counter)
         *                5 (exit)
         */
        byte topChoice = 1; // Can be anything but 5 to enter the loop
        while (topChoice != 5) {
            System.out.println("Please enter:\n"+
            "1 to add to the total\n" + 
            "2 to subtract from the total\n"+ 
            "3 to show the total\n"+
            "4 to set the total\n"+
            "5 to exit the program");
            //Due to the Exception(InputMismatchException) thrown by s.next(p12345), must to catch it. - Zetian Qin zxq876
            try {
                topChoice = (byte) Integer.parseInt(s.next(p12345));
                //Add break statement in switch to make each case independently and DELETE the useless default case. - Zetian Qin zxq876
                switch(topChoice) {
                    case 1: 
                    	add(s);
                    	break;
                    case 2: 
                    	subtract(s);
                    	break;
                    case 3: 
	                    show();
	                    break;
                    case 4: 
	                    set(s);
	                    break;
                    case 5: 
	                    System.out.println("Finally there are "
	                    + counter + " items available.");
	                    break;
                }
            }
            catch(InputMismatchException ex) {
	            System.out.println("You need to enter an integer between 1 and 5.");
	            s.next(); // clean the buffer in Scanner. - Zetian Qin zxq876
            }
        }
        s.close(); //when the loop is end, Scanner must be closed. - Zetian Qin zxq876
    }
    
    /**
     * The method reads in a number from the input and adds it to the counter.
     * @param s The scanner from which the input is read. 
     */
    public void add(Scanner s) {
        System.out.println("How much to add?");
        try {
            counter += Integer.parseInt(s.next());
        }
        catch (NumberFormatException e) {
            System.out.println("You need to enter an integer.");
            //s = new Scanner(System.in);
            //Add a iterative call to re-enter legal data. - Zetian Qin zxq876
            add(s);
        }
    }
    /**
     * The method reads in a number from the input and subtracts it
     * from the counter.
     * @param s The scanner from which the input is read.
     */
    public void subtract(Scanner s) {
        System.out.println("How much to subtract?");
        try {
            counter -= Integer.parseInt(s.next());
        }
        catch (NumberFormatException e) {
            System.out.println("You need to enter an integer.");
            //s = new Scanner(System.in);
            //Add a iterative call to re-enter legal data. - Zetian Qin zxq876
            subtract(s);
        }
    }

    /**
     * The method prints the current value of the counter. 
     */
    public void show() {
        System.out.println("Currently there are " + counter +
        " items available.");
    }


    /**
     * The method reads in a number from the input and sets the
     * counter to this value
     * @param s The scanner from which the input is read.
     */
    public void set(Scanner s) {
        System.out.println("To which value do you want to set the counter?");
        try {
            counter = Integer.parseInt(s.next());
        }
        catch (NumberFormatException e) {
            System.out.println("You need to enter an integer.");
            //s = new Scanner(System.in);
            //Add a iterative call to re-enter legal data. - Zetian Qin zxq876
            set(s);
            
        }
    }
    
	public static void main(String[] args) {
        Counter parcels = new Counter();
    }
}