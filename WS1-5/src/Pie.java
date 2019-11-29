import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The Pie class has 2 field variables, namely expenditures, maximum of types Expenditure[] and int.
 * @author Zetian Qin zxq876
 * @version 2019-11-27 07:36:06
 */
public class Pie extends Application {
    /**
     * expenditures is the data of Expenditure object to be displayed. It is introduced as a global variable so that it can be used in the start method, but be defined in the main method.
     */
    private static Expenditure[] expenditures;

    /**
     * maximum is the maximum number of items which user would like to show in Pie Chart. It is introduced as a global variable so that it can be used in the start method, but be defined in the main method.
     * maximum must be less than or same as the length of expenditures!
     */
    private static int maximum;

    /**
     * radiusPie is the radius of circle which user would like to show in Pie Chart. It is introduced as a global variable so that it can be used in the start method, but be defined in the main method.
     */
    private static double radiusPie;

    /**
     * gapOfEachSquare is the gap between Texts and Circle showed in Pie Chart. It is introduced as a global variable so that it can be used in the start method, and it would be defined by the value of radiusPie.
     */
    private static double gapTextPie;

    // MAX_ANGLE is the maximum angle in Pie Chart.
    private static final double MAX_ANGLE = 360;

    // SCALING_RATIO_FONT is the scaling ratio between Text and radius of Pie.
    private static final double SCALING_RATIO_FONT = 0.6;

    /**
     * This method caculate the angle of each item in expenditure.
     * @return the angle of each item in expenditure, as ArrayList<Double>.
     */
    public ArrayList<Double> caculateAngleOfEachItem() {
        ArrayList<Double> angleOfEachItem = new ArrayList<Double>(); // Stores the angle of each item in expenditure

        // Sort Array expenditures
        Arrays.sort(expenditures, (Expenditure exp1, Expenditure exp2) -> exp2.getValue() - exp1.getValue());

        // Computer angle of each item.
        double sum = 0; // Store the sum of the data of all kinds of Expenditure.
        for(Expenditure itor:expenditures) {
            sum += itor.getValue();
        }

        double sumOfEachItem = 0; // Stores the sum of angle of each item to define the angle of last item.

        for(int i = 0; i < maximum; i++) {
            double angleOfEach = 0;
            if(i == maximum - 1) {
                angleOfEachItem.add(MAX_ANGLE - sumOfEachItem);
            }
            else {
                angleOfEach = expenditures[i].getValue() / sum * MAX_ANGLE;
                angleOfEachItem.add(angleOfEach);
            }
            sumOfEachItem += angleOfEach;
        }
        System.out.print("\nAngle of Each Item:" + angleOfEachItem.toString());
        return angleOfEachItem;
    }

    /**
     * This method computes the angle of Each Text in Pie Chart.
     * @param angleOfEachItem The angle of each item of expenditure, as ArrayList<Double>.
     * @return The angle of Each Text in Pie Chart, as ArrayList<Double>.
     */
    public ArrayList<Double> caculateAngleOfEachText(ArrayList<Double> angleOfEachItem) {
        ArrayList<Double> angleOfTexts = new ArrayList<Double>();
        double countLastAngle = 0;
        for(Double itor:angleOfEachItem) {
            double angleOfText = itor / 2 + countLastAngle;
            angleOfTexts.add(angleOfText);
            countLastAngle += itor;
        }
        System.out.print("\nAngle OF Texts:" +angleOfTexts.toString());
        return angleOfTexts;
    }

    /**
     * This method stores a Group of Text of Each Item in Pie Chart, and computes the Text Width of Each Item.
     * @param radiusPie The radius of Pie, as double.
     * @param textsGroup The Group of text of Each Item in Pie, as ArrayList<Double>.
     * @param textsWidth The Group of text width of Each Item in Pie as ArrayList<Double>.
     */
    public void createTextsGroup(double radiusPie, ArrayList<Text> textsGroup, ArrayList<Double> textsWidth) {
        for(int i = 0; i < maximum; i++) {
            Text title = new Text();
            if(i == (maximum - 1)) {
                title.setText("Other");
            }
            else {
                title.setText(expenditures[i].getDescription());
            }
            
            title.setFont(Font.font(radiusPie * 0.3 * SCALING_RATIO_FONT));
            title.applyCss();
            textsGroup.add(title);
            double textWidth = title.getLayoutBounds().getWidth();
            textsWidth.add(textWidth);
        }
    }

    /**
     * This method computes the centre coordinates of Circle because we donot know what width the Text on the Lift of Circle is.
     * @param radiusPie The radius of Pie, as double.
     * @param gapTextPie
     * @param angleOfTexts The angle of Each Text in Pie Chart, as ArrayList<Double>.
     * @param textsWidth The Group of text width of Each Item in Pie, as ArrayList<Double>.
     * @return The centre coordinates of Circle, as double[2] in which the First one is X coordinate and the Last one is Y coordinate.
     */
    public double[] computeCircleCentre(double radiusPie, double gapTextPie, ArrayList<Double> angleOfTexts, ArrayList<Double> textsWidth) {
        double[] circleCentre = new double[2];

        // Compute the X coordinate
        double maxWidth = 0;
        for(int i = 0; i < maximum; i++) {
            if(angleOfTexts.get(i) >= 75 && angleOfTexts.get(i) <= 105) {
                double totalWidth = textsWidth.get(i) / 2 + (-(radiusPie + gapTextPie) * Math.cos(angleOfTexts.get(i) * Math.PI / 180));
                if(totalWidth > maxWidth) {
                    maxWidth = totalWidth;
                }
            }
            else if(angleOfTexts.get(i) > 105 && angleOfTexts.get(i) < 255) {
                double totalWidth = textsWidth.get(i) + (-(radiusPie + gapTextPie) * Math.cos(angleOfTexts.get(i) * Math.PI / 180));
                if(totalWidth > maxWidth) {
                    maxWidth = totalWidth;
                }
            }
            else if(angleOfTexts.get(i) >= 255 && angleOfTexts.get(i) <= 285) {
                double totalWidth = textsWidth.get(i) / 2 + (-(radiusPie + gapTextPie) * Math.cos(angleOfTexts.get(i) * Math.PI / 180));
                if(totalWidth > maxWidth) {
                    maxWidth = totalWidth;
                }
            }
        }
        System.out.print(maxWidth);
        double xCircleCentre = (Math.round(maxWidth / 10) + 1) * 10;

        // Compute the Y coordinate
        double totalHeight = radiusPie * 1.2 + gapTextPie;
        double yCircleCentre = totalHeight;

        circleCentre[0] = xCircleCentre;
        circleCentre[1] = yCircleCentre;
        System.out.println("\nCirCle Center:" + Arrays.toString(circleCentre));
        return circleCentre;
    }

    /**
     * This method computes the X and Y coordinates of the End of Line of Each Item in Pie Chart.
     * @param circleCentre The centre coordinates of Circle, as double[2] in which the First one is X coordinate and the Last one is Y coordinate.
     * @param radiusPie The radius of Pie, as double.
     * @param angleOfEachItem The angle of each item of expenditure, as ArrayList<Double>.
     * @return The X and Y coordinates of the End of Line of Each Item in Pie Chart, as double[].
     */
    public double[] caculateLineEndCoordinate(double[] circleCentre, double radiusPie, ArrayList<Double> angleOfEachItem){
        double[] lineEndCoordinate = new double[2 * maximum];
        double countThisAngle = 0; // Record the angle of current Point of End of Line.
        for(int i = 0; i < maximum; i++) {
            lineEndCoordinate[2 * i] = circleCentre[0] + radiusPie * Math.cos(countThisAngle * Math.PI / 180);
            lineEndCoordinate[2 * i + 1] = circleCentre[1] - radiusPie * Math.sin(countThisAngle * Math.PI / 180);
            countThisAngle += angleOfEachItem.get(i);
        }
        return lineEndCoordinate;
    }

    /**
     * This method computes the X and Y coordinates of the Start Point of Text of Each Item in Pie Chart.
     * @param circleCentre The centre coordinates of Circle, as double[2] in which the First one is X coordinate and the Last one is Y coordinate.
     * @param radiusPie The radius of Pie, as double.
     * @param gapTextPie The gap distence between Text and Pie, as double.
     * @param angleOfTexts The angle of Each Text in Pie Chart, as ArrayList<Double>.
     * @param textsWidth The Group of text width of Each Item in Pie, as ArrayList<Double>.
     * @return The X and Y coordinates of the Start Point of Text of Each Item in Pie Chart, as double[].
     */
    public double[] caculateTextsCoordinate(double[] circleCentre, double radiusPie, double gapTextPie, ArrayList<Text> textsGroup, ArrayList<Double> angleOfTexts, ArrayList<Double> textsWidth) {
        double[] textsCoordinate = new double[2 * maximum];
        for(int i = 0; i < maximum; i++) {
            if(angleOfTexts.get(i) < 75) {
                textsCoordinate[2 * i] = circleCentre[0] + (radiusPie + gapTextPie) * Math.cos(angleOfTexts.get(i) * Math.PI / 180);
                textsCoordinate[2 * i + 1] = circleCentre[1] - (radiusPie + gapTextPie) * Math.sin(angleOfTexts.get(i) * Math.PI / 180); 
            }
            else if(angleOfTexts.get(i) >= 75 && angleOfTexts.get(i) <= 105) {
                textsCoordinate[2 * i] = circleCentre[0] + (radiusPie + gapTextPie) * Math.cos(angleOfTexts.get(i) * Math.PI / 180) - (textsWidth.get(i) / 2);
                textsCoordinate[2 * i + 1] = circleCentre[1] - (radiusPie + gapTextPie) * Math.sin(angleOfTexts.get(i) * Math.PI / 180); 
            }
            else if(angleOfTexts.get(i) > 105 && angleOfTexts.get(i) <= 180) {
                textsCoordinate[2 * i] = circleCentre[0] + (radiusPie + gapTextPie) * Math.cos(angleOfTexts.get(i) * Math.PI / 180) - textsWidth.get(i);
                textsCoordinate[2 * i + 1] = circleCentre[1] - (radiusPie + gapTextPie) * Math.sin(angleOfTexts.get(i) * Math.PI / 180); 
            }
            else if(angleOfTexts.get(i) > 180 && angleOfTexts.get(i) < 255) {
                textsCoordinate[2 * i] = circleCentre[0] + (radiusPie + gapTextPie) * Math.cos(angleOfTexts.get(i) * Math.PI / 180) - textsWidth.get(i);
                textsCoordinate[2 * i + 1] = circleCentre[1] - (radiusPie + gapTextPie) * Math.sin(angleOfTexts.get(i) * Math.PI / 180) + + textsGroup.get(i).getLayoutBounds().getHeight() / 2;
            }
            else if(angleOfTexts.get(i) > 285) {
                textsCoordinate[2 * i] = circleCentre[0] + (radiusPie + gapTextPie) * Math.cos(angleOfTexts.get(i) * Math.PI / 180);
                textsCoordinate[2 * i + 1] = circleCentre[1] - (radiusPie + gapTextPie) * Math.sin(angleOfTexts.get(i) * Math.PI / 180) + textsGroup.get(i).getLayoutBounds().getHeight() / 2; 
            }
            else {
                textsCoordinate[2 * i] = circleCentre[0] + (radiusPie + gapTextPie) * Math.cos(angleOfTexts.get(i) * Math.PI / 180) - (textsWidth.get(i) / 2);
                textsCoordinate[2 * i + 1] = circleCentre[1] - (radiusPie + gapTextPie) * Math.sin(angleOfTexts.get(i) * Math.PI / 180) + textsGroup.get(i).getLayoutBounds().getHeight() / 2; 
            }
        }
        System.out.print("\n Coordinates of Text:" + Arrays.toString(textsCoordinate));
        return textsCoordinate;
    }

    /**
     * This method draws the Total Pie Chart with the most suitable size of Scene.
     * @param root The group to which the Every Node is to be added, as Group.
     * @param radiusPie The radius of Pie, as double.
     * @param gapTextPie The gap distence between Text and Pie, as double.
     * @param angleOfEachItem The angle of each item of expenditure, as ArrayList<Double>.
     * @param angleOfTexts The angle of Each Text in Pie Chart, as ArrayList<Double>.
     */
    public void drawPie(Group root, double radiusPie, double gapTextPie, ArrayList<Double> angleOfEachItem, ArrayList<Double> angleOfTexts){
        ArrayList<Text> textsGroup = new ArrayList<Text>();
        ArrayList<Double> textsWidth = new ArrayList<Double>();
        createTextsGroup(radiusPie, textsGroup, textsWidth);
        double[] circleCentre = computeCircleCentre(radiusPie, gapTextPie, angleOfTexts, textsWidth);
        
        double[] lineEndCoordinate = caculateLineEndCoordinate(circleCentre, radiusPie, angleOfEachItem);
        double[] textsCoordinate = caculateTextsCoordinate(circleCentre, radiusPie, gapTextPie, textsGroup, angleOfTexts, textsWidth);

        // Draw the Circle
        Circle circle = new Circle(circleCentre[0], circleCentre[1], radiusPie, Color.WHITE);
        circle.setStroke(Color.BLACK);

        root.getChildren().addAll(circle);

        // Draw the Line
        for(int i = 0; i < maximum; i++) {
            Line line = new Line(circleCentre[0], circleCentre[1], lineEndCoordinate[2 * i], lineEndCoordinate[2 * i + 1]);
            root.getChildren().addAll(line);
        }

        // Set the Text Coordinate.
        for(int i = 0; i < maximum; i++) {
            textsGroup.get(i).setX(textsCoordinate[2 * i]);
            textsGroup.get(i).setY(textsCoordinate[2 * i + 1]);
            root.getChildren().addAll(textsGroup.get(i));
        }
    }

    @Override
    /**
     * @param stage The window to be displayed.
     */
    public void start(Stage stage) throws Exception {
        double radiusPie = 100;
        double gapTextPie = 10;
        
        ArrayList<Double> angleOfEachItem = caculateAngleOfEachItem();
        ArrayList<Double> angleOfTexts = caculateAngleOfEachText(angleOfEachItem);

        Group root = new Group();

        drawPie(root, radiusPie, gapTextPie, angleOfEachItem, angleOfTexts);

        Scene scene = new Scene(root);

        stage.setTitle("Pie Chart");
        stage.setScene(scene);
        stage.show();
        
    }

    // --- Set Initial Data area ----------------------------- Below ---

    /**
     * This method likes the constructor in normal class, and it sets the value of the global variables expenditures and maximum from parameter and set the global variables radiusPie with a default value.
     * @param expendituresArgs The data of Expenditure object which would be shown as Pie Chart.
     * @param maximumArgs The maximum items which user would like to show in Pie Chart.
     * @throws IllegalArgumentException The length of Array expendituresArgs is less than the maximumArgs.
     */
    public static void setValues(Expenditure[] expendituresArgs, int maximumArgs) throws IllegalArgumentException {
        if(expendituresArgs.length < maximumArgs) {
            throw new IllegalArgumentException("The length of Array expendituresArgs must be greater than or equal to the maximumArgs");
        }
        else {
            expenditures = expendituresArgs;
            maximum = maximumArgs;
            radiusPie = 100; // 100 is the Default value.
            gapTextPie = radiusPie * 0.1;
        }
    }

    /**
     * This method likes the constructor in normal class, and it sets the value of the global variables expenditures and maximum and radiusPie from parameter.
     * @param expendituresArgs The data of Expenditure object which would be shown as Pie Chart.
     * @param maximumArgs The maximum items which user would like to show in Pie Chart.
     * @param radiusPie The radius of circle in Pie Chart.
     * @throws IllegalArgumentException The length of Array expendituresArgs is less than the maximumArgs.
     */
    public static void setValues(Expenditure[] expendituresArgs, int maximumArgs, double radiusPieArgs) throws IllegalArgumentException {
        if(expendituresArgs.length < maximumArgs) {
            throw new IllegalArgumentException("The length of Array expendituresArgs must be greater than or equal to the maximumArgs");
        }
        else {
            expenditures = expendituresArgs;
            maximum = maximumArgs;
            radiusPie = radiusPieArgs;
            gapTextPie = radiusPie * 0.1;
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
        launch(args);
    }
}