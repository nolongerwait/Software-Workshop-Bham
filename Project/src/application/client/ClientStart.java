package application.client;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ClientStart extends Application {
	double x1;
	double y1;
	double x_stage;
	double y_stage;
	@Override
	public void start(Stage primaryStage) {
		try {
			
			primaryStage.initStyle(StageStyle.UNDECORATED); // Set the background colour
	        primaryStage.setResizable(false); // Limit not to change the size of windows
	        
			Pane root = new LoginPane();
			
	        Scene scene = new Scene(root, 430, 330);
	        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
	      	// Allow move the Windows
			scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent m) {
					primaryStage.setX(x_stage + m.getScreenX() - x1);
					primaryStage.setY(y_stage + m.getScreenY() - y1);

				}
			});
			scene.setOnDragEntered(null);
			scene.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent m) {
					// Record the Mouse cursor coordinates when click mouse.
					x1 = m.getScreenX();
					y1 = m.getScreenY();
					x_stage = primaryStage.getX();
					y_stage = primaryStage.getY();
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}