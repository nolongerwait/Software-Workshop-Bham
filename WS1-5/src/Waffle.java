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
 * The Waffle class should have 2 field variables, namely expenditures, maximum of types Expenditure[] and int.
 * @author Zetian Qin zxq876
 * @version 2019-11-25 15:50:11
 */
public class Waffle extends Application {
    private Expenditure[] expenditures = new Expenditure[] {
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
    private int maximum = 8; // !maximum must less than or same as the length of expenditures!
    
    private static final int MAX_NUMBER_OF_SQUARE  = 100;
    private static final double SCALING_RATIO_LEGEND = 0.6;

    private final Color[] color = new Color[] {
        Color.web("#1C1C1C"),
        Color.web("#DC9FB4"),
        Color.web("#947A6D"),
        Color.web("#F7C242"),
        Color.web("#00896C"),
        Color.web("#86C166"),
        Color.web("#A8497A"),
        Color.web("#77428D"),
        Color.web("#0D5661"),
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
        Arrays.sort(this.expenditures, (Expenditure exp1, Expenditure exp2) -> exp2.getValue() - exp1.getValue());

        // Computer number of square of each item.
        double sum = 0; // Store the sum of the data of all kinds of Expenditure.
        for(Expenditure itor:this.expenditures) {
            sum += itor.getValue();
        }

        int sumOfEachItem = 0; // Stores the sum of square of each item to define the square number of last item.

        for(int i = 0; i < this.maximum; i++) {
            int numberOfEach = 0;
            if(i == this.maximum - 1) {
                numberOfEachItem.add(MAX_NUMBER_OF_SQUARE - sumOfEachItem);
            }
            else {
                numberOfEach = (int)Math.round((this.expenditures)[i].getValue() / sum * 100);
                numberOfEachItem.add(numberOfEach);
            }
            sumOfEachItem += numberOfEach;
        }
        return numberOfEachItem;
    }

    /**
     * This method draws the little square in Waffle Chart.
     * @param root 
     * @param xCoordinate The x coordinate of the square, as double.
     * @param yCoordinate The y coordinate of the square, as double.
     * @param squareWidth The width of square, as double.
     * @param color The color of the square, as Paint.
     */
    public void drawSquare(Group root,double xCoordinate, double yCoordinate, double squareWidth, Color color) {
        Rectangle square = new Rectangle(xCoordinate, yCoordinate, squareWidth, squareWidth);
        square.setFill(color);
        root.getChildren().add(square);
    }

    /**
     * This method draws the Waffle Chart whose data from numberOfEachItem ArrayList.
     * @param root
     * @param squareWidth The width of square, as double.
     * @param gapOfEachSquare The gap length between squares, as double.
     * @param xCoordinateOfEachSquare The x coordinate of 100 squares
     * @param yCoordinateOfEachSquare The y coordinate of 100 squares
     * @param numberOfEachItem The number of square of each item in expenditure, as ArrayList<Integer>.
     */
    public void drawWaffleChart(Group root, double squareWidth, double gapOfEachSquare, double[] xCoordinateOfEachSquare, double[] yCoordinateOfEachSquare, ArrayList<Integer> numberOfEachItem) {
        int countSquare = 0; // Count the number of square which has been drawn.
        for(int i = 0; i < this.maximum; i++) {
            for(int indexOfNumberOfEachItem = 0; indexOfNumberOfEachItem < numberOfEachItem.get(i); indexOfNumberOfEachItem++) {
                drawSquare(root, xCoordinateOfEachSquare[countSquare], yCoordinateOfEachSquare[countSquare], squareWidth, (this.color)[i]);
                countSquare++;
            }
        }
    }

    /**
     * This method computes the X coordinate of the Legned.
     * @param squareWidth The width of square, as double.
     * @param gapOfEachSquare The gap length between squares, as double.
     * @return
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
        double[] yLegendCorrdinates = new double[this.maximum]; // Stores the coordinate of the legend of each item.

        // Compute the coordinate
        // By default, the bottom of the Legend is consistent with the Waffle Chart.
        double bottom = (squareWidth + gapOfEachSquare) * 9 + squareWidth; // the Y coordinate of the bottom of the Waffle Chart.
        double squareWidthOfLegend = squareWidth * SCALING_RATIO_LEGEND;  // the width of squares in Legend.
        for(int i = 0; i < this.maximum; i++) {
            double yLegendCoordinate = bottom - squareWidthOfLegend - (squareWidthOfLegend + gapOfEachSquare) * (this.maximum - 1 - i);
            yLegendCorrdinates[i] = yLegendCoordinate;
        }
        return yLegendCorrdinates;
    }

    /**
     * 
     * @param root
     * @param xLegendCoordinate
     * @param yLegendCoordinate
     * @param xLegendTextCoordinate
     * @param squareWidth
     * @param title
     * @param color
     */
    public void drawLegendLine(Group root, double xLegendCoordinate, double yLegendCoordinate, double xLegendTextCoordinate, double squareWidth, String title, Color color) {
        Rectangle square = new Rectangle(xLegendCoordinate, yLegendCoordinate, squareWidth * SCALING_RATIO_LEGEND, squareWidth * SCALING_RATIO_LEGEND);
        square.setFill(color);
        root.getChildren().add(square);

        Text titleOfEachItem = new Text(xLegendTextCoordinate, yLegendCoordinate + squareWidth * SCALING_RATIO_LEGEND, title);
        System.out.println(title);
        System.out.println(xLegendTextCoordinate);
        System.out.println(yLegendCoordinate);
        titleOfEachItem.setFont(Font.font(squareWidth * SCALING_RATIO_LEGEND));
        root.getChildren().add(titleOfEachItem);
    }

    /**
     * 
     * @param root
     * @param xLegendCoordinate
     * @param yLegendCorrdinates
     * @param squareWidth
     */
    public void drawLegend(Group root, double xLegendCoordinate, double[] yLegendCorrdinates, double squareWidth) {
        for(int i = 0; i < this.maximum; i++) {
            String title = "";
            if(i == this.maximum - 1) {
                title = "Others";
            }
            else {
                title = this.expenditures[i].getDescription();
            }
            double xLegendTextCoordinate = xLegendTextCoordinate(xLegendCoordinate, squareWidth);
            drawLegendLine(root, xLegendCoordinate, yLegendCorrdinates[i], xLegendTextCoordinate, squareWidth, title, this.color[i]);
        }
    }

    @Override
    /**
     * @param stage The window to be displayed.
     */
    public void start(Stage stage) throws Exception {
        double squareWidth = 30;
        double gapOfEachSquare = 5;
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

    /**
     * main method to launch the application.
     */
    public static void main(String[] args) {
        /*
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
        */
        launch();
    }
}