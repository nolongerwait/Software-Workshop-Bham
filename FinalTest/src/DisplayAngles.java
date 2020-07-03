import java.util.function.Function;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DisplayAngles extends Application{
    // Define the minimum and maximum angle 0-90 degrees
    private final double MIN_ANGLE = 0.0;
    private final double MAX_ANGLE = 90.0;

    // Define the accuracy of circumcision(number of points on polyline)
    // The Polyline will be closer to Arc when this value is larger.
    private final int ACCURACY_CIRCUMCISION = 1000;

    // Define the magnification
    private final double MAGNIFICATION = 100;

    // Define the margin
    private final double MARGIN = 20;

    // Define the Size of Scene
    private final double X_SIZE = MARGIN * 2 + MAGNIFICATION * 3;
    private final double Y_SIZE = MARGIN * 3 + MAGNIFICATION;

    // Define the functionn of Arc(Circle)
    private final Function<Double, Double> f = x -> Math.sqrt(1 * 1 - x * x);

    @Override
    public void start(Stage stage) throws Exception {
        // Create a Group (scene graph) with elements.
        Group root = new Group();
        // Draw two angles
        drawAngles(root, 5, 75);
        // The scene consists of just one group.
        Scene scene = new Scene(root, X_SIZE,Y_SIZE);
        // Give the stage (window) a title and add the scene.
        stage.setTitle("Display Angle");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Create Arc using Polyline with the centre of arc.
     * @param x the X coordinate of centre of Arc
     * @param y the Y coordinate of centre of Arc
     * @return the Arc Polyline.
     */
    public Polyline createArc(double x, double y) {
        double[] pointsOnArc = new double[2 * (ACCURACY_CIRCUMCISION + 1)];
        for(int i = 0; i <= ACCURACY_CIRCUMCISION; ++i) {
            double arcX = 1.0 / ACCURACY_CIRCUMCISION * i;
            pointsOnArc[2 * i] = x - arcX * MAGNIFICATION;
            pointsOnArc[2 * i + 1] = y - f.apply(arcX) * MAGNIFICATION;
        }
        Polyline arc = new Polyline(pointsOnArc);
        return arc;
    }

    /**
     * Create the Angle
     * @param angle the degree of angle
     * @param x the X coordinate of centre of Arc
     * @param y the Y coordinate of centre of Arc
     * @return the Angle Line
     */
    public Line createAngleLine(double angle, double x, double y) {
        double endPointX = x - MAGNIFICATION * Math.cos(Math.PI * angle / 180);
        double endPointY = y - MAGNIFICATION * Math.sin(Math.PI * angle / 180);
        Line line = new Line(x, y, endPointX, endPointY);
        return line;
    }

    /**
     * Compute the coordinates of the centres of two arc.
     * @return the coordinate of two centres.
     */
    public double[] computeCentreOfArc() {
        double[] centreOfArc = new double[4];
        centreOfArc[0] = MARGIN + MAGNIFICATION;               // X - centre of left arc
        centreOfArc[1] = MARGIN + MAGNIFICATION;               // Y - centre of left arc
        centreOfArc[2] = centreOfArc[0] + MAGNIFICATION * 2;   // X - centre of right arc
        centreOfArc[3] = centreOfArc[1];                       // Y - centre of right arc
        return centreOfArc;
    }

    /**
     * Create the title of Angle.
     * @param angle the degree of angle
     * @param x the X coordinate of the centre of arc
     * @param y the Y coordinate of the centre of arc
     * @return the title as Text object.
     */
    public Text createTextOfAngle(double angle, double x, double y) {
        Text titleAngle = new Text();
        titleAngle.setText(String.format("%.2f° Angle", angle));
        titleAngle.setFont(Font.font(MARGIN * 0.8));
        titleAngle.applyCss();
        titleAngle.setX(x - titleAngle.getLayoutBounds().getWidth());
        titleAngle.setY(y + MARGIN * 1.1);
        return titleAngle;
    }

    /**
     * Draw the two angles
     * @param root The group to which element is to be added
     * @param angle1 the degree of left angle
     * @param angle2 the degree of right angle
     */
    public void drawAngles(Group root, double angle1, double angle2) throws IllegalArgumentException {
        if((angle1 < MIN_ANGLE || angle1 > MAX_ANGLE) || (angle2 < MIN_ANGLE || angle2 > MAX_ANGLE)) {
            throw new IllegalArgumentException("Angle out of range. Please check the angle between 0 and 90 degrees");
        }
        double[] centreOfArc = computeCentreOfArc();
        // Create left angle
        root.getChildren().add(createArc(centreOfArc[0], centreOfArc[1]));
        root.getChildren().add(createAngleLine(angle1, centreOfArc[0], centreOfArc[1]));
        root.getChildren().add(createTextOfAngle(angle1, centreOfArc[0], centreOfArc[1]));
        // Create right angle
        root.getChildren().add(createArc(centreOfArc[2], centreOfArc[3]));
        root.getChildren().add(createAngleLine(angle2, centreOfArc[2], centreOfArc[3]));
        root.getChildren().add(createTextOfAngle(angle2, centreOfArc[2], centreOfArc[3]));
    }
}