package application.client;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * User registration interface
 * @author Zetian Qin zxq876
 */
public class Register extends Application{
	double x1;
	double y1;
	double x_stage;
	double y_stage;
	Stage stage = new Stage();
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.initStyle(StageStyle.UNDECORATED); 
        primaryStage.setResizable(false); // Cannot change the size of windows
        
		VBox root = new RegisterPane();
		
        Scene scene = new Scene(root, 430, 500);
        scene.getStylesheets().add(getClass().getResource("register.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        
    // Allow to move the windows
	scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent m) {
			stage.setX(x_stage + m.getScreenX() - x1);
			stage.setY(y_stage + m.getScreenY() - y1);

		}
	});
	scene.setOnDragEntered(null);
	scene.setOnMousePressed(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent m) {
			x1 = m.getScreenX();
			y1 = m.getScreenY();
			x_stage = stage.getX();
			y_stage = stage.getY();
		}
	});
	}
	
	public void  showRegister() throws Exception {
        start(stage);
    }

}
