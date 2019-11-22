import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 * This class draw a upload icon and display it.
 * @author Zetian Qin zxq876
 * @version 2019-11-21 15:19:27
 **/

public class Upload extends Application {

    @Override
    /**
     * The main structure of the javafx.
     * @param stage the main display component of the javaFX.
     */
    public void start(Stage stage) throws Exception {
        Group root = new Group();

        // New a scene object and init the size and title.
        Scene scene = new Scene(root, 600, 300);
        stage.setTitle("Upload Icon");
        stage.setScene(scene);

        // the group is used to contain the polygon.
        Group g = new Group();

        // New a upper icon using the polygon.
        Polygon upIcon = new Polygon();
        // The offset is the position
        double offsetXUpIcon = scene.getWidth() / 2;
        double offsetYUpIcon = 40.0;

        double upIconSmallX = 10;
        double upIconBigX = 30;
        double upIconSmallY = 25;
        double upIconBigY = 100;
        upIcon.getPoints().addAll(new Double[] {0.0, 0.0, -upIconBigX, upIconSmallY, -upIconSmallX, upIconSmallY, -upIconSmallX, upIconBigY,upIconSmallX, upIconBigY, upIconSmallX, upIconSmallY, upIconBigX, upIconSmallY});

        for(int i = 0; i < upIcon.getPoints().size(); i++) {
            if (i % 2 == 0) {
                upIcon.getPoints().set(i, upIcon.getPoints().get(i) + offsetXUpIcon);
            }
            if (i % 2 == 1) {
                upIcon.getPoints().set(i, upIcon.getPoints().get(i) + offsetYUpIcon);
            }
        }

        Polygon downIcon = new Polygon();
        double offsetXDownIcon = scene.getWidth() / 2;
        double offsetYDownIcon = 160;

        double downIconSmallX = 60;
        double downIconBigX = 80;
        double downIconBigY = 80;
        double downIconSmallY = 20;

        downIcon.getPoints().addAll(new Double[] {0.0, 0.0, -downIconSmallX, 0.0, -downIconSmallX, -downIconBigY, -downIconBigX, -downIconBigY, -downIconBigX, downIconSmallY, downIconBigX, downIconSmallY, downIconBigX, -downIconBigY, downIconSmallX, -downIconBigY, downIconSmallX, 0.0});

        for(int i = 0; i < downIcon.getPoints().size(); i++) {
            if (i % 2 == 0) {
                downIcon.getPoints().set(i, downIcon.getPoints().get(i) + offsetXDownIcon);
            }
            if (i % 2 == 1) {
                downIcon.getPoints().set(i, downIcon.getPoints().get(i) + offsetYDownIcon);
            }
        }

        g.getChildren().add(upIcon);
        g.getChildren().add(downIcon);
        scene.setRoot(g);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}