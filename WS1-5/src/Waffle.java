import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The Waffle class has 2 field variables, namely expenditures, maximum of types Expenditure[] and int.
 * @author Zetian Qin zxq876
 * @version 2019-11-25 15:50:11
 */
public class Waffle extends Application {
    /**
     * expenditures is the data of Expenditure object to be displayed. It is introduced as a global variable so that it can be used in the start method, but be defined in the main method.
     */
    private static Expenditure[] expenditures;

    /**
     * maximum is the maximum number of items which user would like to show in Waffle Chart. It is introduced as a global variable so that it can be used in the start method, but be defined in the main method.
     * maximum must be less than or same as the length of expenditures!
     */
    private static int maximum;

    /**
     * maximum is the width of squares which user would like to show in Waffle Chart. It is introduced as a global variable so that it can be used in the start method, but be defined in the main method.
     */
    private static double squareWidth;

    /**
     * gapOfEachSquare is the gap between squares showed in Waffle Chart. It is introduced as a global variable so that it can be used in the start method, and it would be defined by the value of squareWidth.
     */
    private static double gapOfEachSquare;

    // MAX_NUMBER_OF_SQUARE is the maximum number of squares in Waffle Chart.
    private static final int MAX_NUMBER_OF_SQUARE  = 100;

    // SCALING_RATIO_LEGEND is the scaling ratio of square between Waffle Chart and Legend.
    private static final double SCALING_RATIO_LEGEND = 0.6;

    // This is a default chart color scheme with twelve colors.
    private static final Color[] color = new Color[] {
        Color.web("#00a8e1"),
        Color.web("#99cc00"),
        Color.web("#e30039"),
        Color.web("#fcd300"),
        Color.web("#800080"),
        Color.web("#00994e"),
        Color.web("#ff6600"),
        Color.web("#808000"),
        Color.web("#db00c2"),
        Color.web("#008080"),
        Color.web("#0000ff"),
        Color.web("#c8cc00")
    };

    /**
     * Standard constructor of the abstract class Waffle.
     * @param expenditures // The data of expenditures which would be displayed.
     * @param maximum // The maximum of the kind of expenditures which would be displayed.
     */
    /*
    public Waffle(Expenditure[] expenditures, int maximum) {
        this.expenditures = Arrays.copyOf(expenditures, expenditures.length);
        this.maximum = maximum;
    }
    */

    // --- Compute the data which is needed in Waffle Chart - Below ---

    /**
     * This method caculate the x coordinate of all the 100 squares.
     * @param squareWidth The width of each square, as double.
     * @param gapOfSquare The gap length between squares, as double.
     * @return The x coordinate of 100 squares.
     */
    public double[] xCoordinateOfEachSquare(double squareWidth, double gapOfSquare) {
        double[] xCoordinateOfEachSquare = new double[MAX_NUMBER_OF_SQUARE];
        double xCoordinate = 0;

        for(int i = 0; i < 10; i++) {
            xCoordinate = (squareWidth + gapOfSquare) * i;
            for(int j = 0; j < 10; j++){
                xCoordinateOfEachSquare[j * 10 + i] = xCoordinate;
            }
        }
        return xCoordinateOfEachSquare;
    }

    /**
     * This method caculate the y coordinate of all the 100 squares.
     * @param squareWidth The width of each square, as double.
     * @param gapOfSquare The gap length between squares, as double.
     * @return The y coordinate of 100 squares
     */
    public double[] yCoordinateOfEachSquare(double squareWidth, double gapOfSquare) {
        double[] yCoordinateOfEachSquare = new double[MAX_NUMBER_OF_SQUARE];
        double yCoordinate = 0;

        for(int i = 0; i < 10; i++) {
            yCoordinate = (squareWidth + gapOfSquare) * i;
            for(int j = 0; j < 10; j++){
                yCoordinateOfEachSquare[i * 10 + j] = yCoordinate;
            }
        }
        return yCoordinateOfEachSquare;
    }

    /**
     * This method caculate the number of square of each item in expenditure.
     * @return the number of square of each item in expenditure, as ArrayList<Integer>.
     */
    public ArrayList<Integer> caculateNumberOfEachItem() {
        ArrayList<Integer> numberOfEachItem = new ArrayList<Integer>(); // Stores the number of square of each item in expenditure

        // Sort Array expenditures
        Arrays.sort(expenditures, (Expenditure exp1, Expenditure exp2) -> exp2.getValue() - exp1.getValue());

        // Computer number of square of each item.
        double sum = 0; // Store the sum of the data of all kinds of Expenditure.
        for(Expenditure itor:expenditures) {
            sum += itor.getValue();
        }

        int sumOfEachItem = 0; // Stores the sum of square of each item to define the square number of last item.

        for(int i = 0; i < maximum; i++) {
            int numberOfEach = 0;
            if(i == maximum - 1) {
                numberOfEachItem.add(MAX_NUMBER_OF_SQUARE - sumOfEachItem);
            }
            else {
                numberOfEach = (int)Math.round(expenditures[i].getValue() / sum * 100);
                numberOfEachItem.add(numberOfEach);
            }
            sumOfEachItem += numberOfEach;
        }
        return numberOfEachItem;
    }

    // --- Compute the data which is needed in Waffle Chart - Above ---

    // --- Draw the graphic of Waffle Chart ----------------- Below ---

    /**
     * This method draws the little square in Waffle Chart.
     * @param root The group to which the squares are to be added
     * @param xCoordinate The x coordinate of the square, as double.
     * @param yCoordinate The y coordinate of the square, as double.
     * @param squareWidth The width of square, as double.
     * @param color The color of the square, as Color.
     */
    public void drawSquare(Group root,double xCoordinate, double yCoordinate, double squareWidth, Color color) {
        Rectangle square = new Rectangle(xCoordinate, yCoordinate, squareWidth, squareWidth);
        square.setFill(color);
        root.getChildren().add(square);
    }

    /**
     * This method draws the Waffle Chart whose data from numberOfEachItem ArrayList.
     * @param root The group to which the Waffle Chart is to be added
     * @param squareWidth The width of square, as double.
     * @param gapOfEachSquare The gap length between squares, as double.
     * @param xCoordinateOfEachSquare The x coordinate of 100 squares
     * @param yCoordinateOfEachSquare The y coordinate of 100 squares
     * @param numberOfEachItem The number of square of each item in expenditure, as ArrayList<Integer>.
     */
    public void drawWaffleChart(Group root, double squareWidth, double gapOfEachSquare, double[] xCoordinateOfEachSquare, double[] yCoordinateOfEachSquare, ArrayList<Integer> numberOfEachItem) {
        int countSquare = 0; // Count the number of square which has been drawn.
        for(int i = 0; i < maximum; i++) {
            for(int indexOfNumberOfEachItem = 0; indexOfNumberOfEachItem < numberOfEachItem.get(i); indexOfNumberOfEachItem++) {
                drawSquare(root, xCoordinateOfEachSquare[countSquare], yCoordinateOfEachSquare[countSquare], squareWidth, color[i]);
                countSquare++;
            }
        }
    }

    // --- Draw the graphic of Waffle Chart ----------------- Above ---

    // --- Compute the data of Legend area ------------------ Below ---

    /**
     * This method computes the X coordinate of the Legned.
     * @param squareWidth The width of square, as double.
     * @param gapOfEachSquare The gap length between squares, as double.
     * @return The X coordinate of the Legend icon.
     */
    public double xLegendCoordinate(double squareWidth, double gapOfEachSquare) {
        return (squareWidth + gapOfEachSquare) * 9 + squareWidth + 40; // 40 is the gap between Waffle Chart and Legend.
    }

    /**
     * This method computes the X coordinate of the Legend Text.
     * @param xLegendCoordinate The X coordinate of the Legned, as double.
     * @param squareWidth The width of square, as double.
     * @return The X coordinate of the Legend Text.
     */
    public double xLegendTextCoordinate(double xLegendCoordinate, double squareWidth) {
        return xLegendCoordinate + squareWidth * SCALING_RATIO_LEGEND  + 10; // 10 is the gap between the Legend icon and text.
    }

    /**
     * This method computes the Y coordinates of each item in Legend.
     * @param squareWidth The width of square, as double.
     * @param gapOfEachSquare The gap length between squares, as double.
     * @return The Y coordinates of each item in Legend, as double[].
     */
    public double[] yLegendCorrdinates(double squareWidth, double gapOfEachSquare) {
        double[] yLegendCorrdinates = new double[maximum]; // Stores the coordinate of the legend of each item.

        // Compute the coordinate
        // By default, the bottom of the Legend is consistent with the Waffle Chart.
        double bottom = (squareWidth + gapOfEachSquare) * 9 + squareWidth; // the Y coordinate of the bottom of the Waffle Chart.
        double squareWidthOfLegend = squareWidth * SCALING_RATIO_LEGEND;  // the width of squares in Legend.
        for(int i = 0; i < maximum; i++) {
            double yLegendCoordinate = bottom - squareWidthOfLegend - (squareWidthOfLegend + gapOfEachSquare) * (maximum - 1 - i);
            yLegendCorrdinates[i] = yLegendCoordinate;
        }
        return yLegendCorrdinates;
    }

    // --- Compute the data of Legend area ------------------ Above ---

    // --- Draw the Legend area ----------------------------- Below ---

    /**
     * This method draws the Legend icon and Legend text.
     * @param root The group to which the Legend Line(icon and text) is to be added
     * @param xLegendCoordinate The X coordinate of the Legend icon, as double.
     * @param yLegendCoordinate The Y coordinate of the Legend icon, as double.
     * @param xLegendTextCoordinate The X coordinate of the Legend Text, as double.
     * @param squareWidth The width of square, as double.
     * @param title The title of Each Item in Legend.
     * @param color The color of the square, as Color.
     */
    public void drawLegendLine(Group root, double xLegendCoordinate, double yLegendCoordinate, double xLegendTextCoordinate, double squareWidth, String title, Color color) {
        // Draws the square of Each Item in Legend icon.
        Rectangle square = new Rectangle(xLegendCoordinate, yLegendCoordinate, squareWidth * SCALING_RATIO_LEGEND, squareWidth * SCALING_RATIO_LEGEND);
        square.setFill(color);
        root.getChildren().add(square);

        // Draws the Text of Each Item behind the Legend icon. (Note the difference between the reference points of the graphics and text)
        Text titleOfEachItem = new Text(xLegendTextCoordinate, yLegendCoordinate + squareWidth * SCALING_RATIO_LEGEND, title);
        titleOfEachItem.setFont(Font.font(squareWidth * SCALING_RATIO_LEGEND));
        root.getChildren().add(titleOfEachItem);
    }

    /**
     * This method draws the Legend area by using drawLegendLine() method.
     * @param root The group to which the Legend is to be added
     * @param xLegendCoordinate  The X coordinate of the Legend icon, as double.
     * @param yLegendCorrdinates The Y coordinates of the Legend icon, as double[].
     * @param squareWidth The width of square, as double.
     */
    public void drawLegend(Group root, double xLegendCoordinate, double[] yLegendCorrdinates, double squareWidth) {
        for(int i = 0; i < maximum; i++) {
            String title = "";
            if(i == (maximum - 1)) {
                title = "Other";
            }
            else {
                title = expenditures[i].getDescription();
            }
            double xLegendTextCoordinate = xLegendTextCoordinate(xLegendCoordinate, squareWidth);
            drawLegendLine(root, xLegendCoordinate, yLegendCorrdinates[i], xLegendTextCoordinate, squareWidth, title, color[i]);
        }
    }

    // --- Draw the Legend area ----------------------------- Above ---

    @Override
    /**
     * @param stage The window to be displayed.
     */
    public void start(Stage stage) throws Exception {

        double[] xCoordinateOfEachSquare = xCoordinateOfEachSquare(squareWidth, gapOfEachSquare);
        double[] yCoordinateOfEachSquare = yCoordinateOfEachSquare(squareWidth, gapOfEachSquare);
        ArrayList<Integer> numberOfEachItem = caculateNumberOfEachItem();

        Group root = new Group();

        drawWaffleChart(root, squareWidth, gapOfEachSquare, xCoordinateOfEachSquare, yCoordinateOfEachSquare, numberOfEachItem);
        
        double xLegendCoordinate = xLegendCoordinate(squareWidth, gapOfEachSquare);
        double[] yLegendCorrdinates = yLegendCorrdinates(squareWidth, gapOfEachSquare);

        drawLegend(root, xLegendCoordinate, yLegendCorrdinates, squareWidth);

        // The scene consists of just one group.
        Scene scene = new Scene(root);

        // Give the stage (window) a title and add the scene.
        stage.setTitle("Waffle Chart");
        stage.setScene(scene);
        stage.show();
    }

    // --- Set Initial Data area ----------------------------- Below ---

    /**
     * This method likes the constructor in normal class, and it sets the value of the global variables expenditures and maximum from parameter and set the global variables squareWidth with a default value.
     * @param expendituresArgs The data of Expenditure object which would be shown as Waffle Chart.
     * @param maximumArgs The maximum items which user would like to show in Waffle Chart.
     * @throws IllegalArgumentException The length of Array expendituresArgs is less than the maximumArgs.
     */
    public static void setValues(Expenditure[] expendituresArgs, int maximumArgs) throws IllegalArgumentException {
        if(expendituresArgs.length < maximumArgs) {
            throw new IllegalArgumentException("The length of Array expendituresArgs must be greater than or equal to the maximumArgs");
        }
        else {
            expenditures = expendituresArgs;
            maximum = maximumArgs;
            squareWidth = 30; // 30 is the Default value.
            gapOfEachSquare = squareWidth * 0.2;
        }
    }

    /**
     * This method likes the constructor in normal class, and it sets the value of the global variables expenditures and maximum and squareWidth from parameter.
     * @param expendituresArgs The data of Expenditure object which would be shown as Waffle Chart.
     * @param maximumArgs The maximum items which user would like to show in Waffle Chart.
     * @param squareWidthArgs The width of squares in Waffle Chart.
     * @throws IllegalArgumentException The length of Array expendituresArgs is less than the maximumArgs.
     */
    public static void setValues(Expenditure[] expendituresArgs, int maximumArgs, double squareWidthArgs) throws IllegalArgumentException {
        if(expendituresArgs.length < maximumArgs) {
            throw new IllegalArgumentException("The length of Array expendituresArgs must be greater than or equal to the maximumArgs");
        }
        else {
            expenditures = expendituresArgs;
            maximum = maximumArgs;
            squareWidth = squareWidthArgs;
            gapOfEachSquare = squareWidth * 0.2;
        }
    }

    // --- Set Initial Data area ----------------------------- Above ---

    /**
     * main method to launch the application.
     */
    public static void main(String[] args) {
        // Data
        Expenditure[] expendituresArgs = new Expenditure[] {
            new Expenditure("Salaries", 11000),
            new Expenditure("Paper", 2000),
            new Expenditure("Rent", 5000),
            new Expenditure("Most popular books on Java etc.",10000),
            new Expenditure("Heating", 3000),
            new Expenditure("Coffee/Tea", 7000),
            new Expenditure("Biscuits", 8000),
            new Expenditure("Travel", 18000),
            new Expenditure("Electricity", 1000),
            new Expenditure("Pencils", 3000)
        };
        int maximumArgs = 8;
        // Set Vaules
        setValues(expendituresArgs, maximumArgs);
        // Launch
        launch();
    }
}