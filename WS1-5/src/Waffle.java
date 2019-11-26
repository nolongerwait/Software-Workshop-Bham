import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * The Waffle class should have 2 field variables, namely expenditures, maximum of types Expenditure[] and int.
 * @author Zetian Qin zxq876
 * @version 2019-11-25 15:50:11
 */
public class Waffle extends Application {
    private Expenditure[] expenditures;
    private int maximum;
    private static final int MAX_NUMBER_OF_SQUARE  = 100;

    /**
     * Standard constructor of the abstract class Waffle.
     * @param expenditures // The data of expenditures which would be displayed.
     * @param maximum // The maximum of the kind of expenditures which would be displayed.
     */
    public Waffle(Expenditure[] expenditures, int maximum) {
        this.expenditures = Arrays.copyOf(expenditures, expenditures.length);
        this.maximum = maximum;
    }

    /**
     * This method caculate the x coordinate of all the 100 squares.
     * @param squareWidth The width of each square, as double.
     * @param gapOfSquare The gap length between squares, as double.
     * @return The x coordinate of 100 squares.
     */
    public double[] xCoordinateOfEachSquare(double squareWidth, double gapOfSquare) {
        double[] xCoordinateOfEachSquare = new double[100];
        double xCoordinate = 0;
        for(int i = 0; i < 10; i++) {
            xCoordinate = xCoordinate + (squareWidth + gapOfSquare) * i;
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
        double[] yCoordinateOfEachSquare = new double[100];
        double yCoordinate = 0;
        for(int i = 0; i < 10; i++) {
            yCoordinate = yCoordinate + (squareWidth + gapOfSquare) * i;
            for(int j = 0; j < 10; j++){
                yCoordinateOfEachSquare[j * 10 + i] = yCoordinate;
            }
        }
        return yCoordinateOfEachSquare;
    }

    /**
     * This method caculate the number of square of each item in expenditure.
     * @return the number of square of each item in expenditure, as ArrayList<Integer>.
     */
    public ArrayList<Integer> caculateNumberOfEachItem() {
        // Sort Array expenditures
        Arrays.sort(this.expenditures, (Expenditure exp1, Expenditure exp2) -> exp2.getValue() - exp1.getValue());

        // Computer number of square of each item.
        double sum = 0; // Store the sum of the data of all kinds of Expenditure.
        for(Expenditure itor:this.expenditures) {
            sum += itor.getValue();
        }

        ArrayList<Integer> numberOfEachItem = new ArrayList<Integer>();
        int sumOfEachItem = 0; // Store the sum of square of each item to define the square number of last item.

        for(Expenditure itor:this.expenditures) {
            int numberOfEach = (int)Math.round(itor.getValue() / sum);
            sumOfEachItem += numberOfEach;
            if(sumOfEachItem <= MAX_NUMBER_OF_SQUARE) {
                numberOfEachItem.add(numberOfEach);
            }
            else {
                numberOfEach = MAX_NUMBER_OF_SQUARE - sumOfEachItem;
            }
        }

        return numberOfEachItem;
    }

    /**
     * This method draws the little square in Waffle Chart.
     * @param xCoordinate The x coordinate of the square, as double.
     * @param yCoordinate The y coordinate of the square, as double.
     * @param squareWidth The width of square, as double.
     * @param color The color of the square, as Paint.
     */
    public void drawSquare(double xCoordinate, double yCoordinate, double squareWidth, Paint color) {
        Rectangle square = new Rectangle(xCoordinate, yCoordinate, squareWidth, squareWidth);
        square.setFill(color);
    }

    /**
     * This method draws the Waffle Chart whose data from numberOfEachItem ArrayList.
     * @param root
     * @param squareWidth The width of square, as double.
     * @param gapOfEachSquare The gap length between squares, as double.
     * @param xCoordinateOfEachSquare The x coordinate of 100 squares
     * @param yCoordinateOfEachSquare The y coordinate of 100 squares
     * @param numberOfEachItem
     */
    public void drawWaffleChart(Group root,double squareWidth, double gapOfEachSquare, double[] xCoordinateOfEachSquare, double[] yCoordinateOfEachSquare, ArrayList<Integer> numberOfEachItem) {
        int countSquare = 0;
        for(int i = 0; i < this.maximum; i++) {
            for(int indexOfNumberOfEachItem = 0; indexOfNumberOfEachItem < numberOfEachItem.get(i); indexOfNumberOfEachItem++) {
                
            }
        }
    }

    @Override
    /**
     * @param arg0 The window to be displayed.
     */
    public void start(Stage arg0) throws Exception {
        double squareWidth = 10;
        double gapOfEachSquare = 2;
        double[] xCoordinateOfEachSquare = xCoordinateOfEachSquare(squareWidth, gapOfEachSquare);
        double[] yCoordinateOfEachSquare = yCoordinateOfEachSquare(squareWidth, gapOfEachSquare);
        ArrayList<Integer> numberOfEachItem = caculateNumberOfEachItem();


    }

    /**
     * main method to launch the application.
     */
    public static void main(String[] args) {
        //Data of Expenditure.
        Expenditure[] expenditures = new Expenditure[] {
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
        int maximum = 8;
        Waffle display = new Waffle(expenditures, maximum);
        launch(args);
    }
}