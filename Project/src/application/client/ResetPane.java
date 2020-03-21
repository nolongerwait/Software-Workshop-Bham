package application.client;

import application.utils.Toast;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Register Layout class
 * @author Zetian Qin zxq876
 */
public class ResetPane extends VBox{
	Stage stage;
	public ResetPane() {
		HBox top = new HBox();
		top.setPrefSize(430, 70);
		top.setStyle("-fx-background-color: lightblue;");
		
		Label logo = new Label("Reset Password");
		logo.setFont(new Font(17));
		
		HBox.setMargin(logo, new Insets(10, 0, 0, 10));
		
		// Close Button
		Button closeWindow = new Button();
		closeWindow.setPrefSize(30, 30);
		closeWindow.setId("closeWindow");
		closeWindow.setCursor(Cursor.HAND);
		HBox.setMargin(closeWindow, new Insets(10, 0, 0, 280));
		// Close Button Action
		closeWindow.setOnMouseClicked((e)->{
			stage = (Stage) closeWindow.getScene().getWindow();
			stage.close();
		});
		
		top.getChildren().addAll(logo,closeWindow);
		
		VBox main = new VBox();
		main.setPrefSize(430, 450);
		main.setStyle("-fx-background-color: rgba(0,0,255,0.2);");
		
		// Email Box
		HBox box1 = new HBox();
		box1.setPrefSize(370, 50);
		VBox.setMargin(box1, new Insets(30, 40, 0, 40));
		
		Label label1 = new Label("Email:");
		label1.setAlignment(Pos.CENTER_RIGHT);
		label1.setPrefSize(70, 80);
		label1.setFont(new Font(14));
		label1.requestFocus();
		HBox.setMargin(label1, new Insets(15, 0, 0, 0));
		
		TextField email = new TextField();
		email.setPrefSize(250, 35);
		HBox.setMargin(email, new Insets(8, 0, 0, 10));
		
		box1.getChildren().addAll(label1, email);
		
		// Old Password Box
		HBox box2 = new HBox();
		box2.setPrefSize(370, 50);
		VBox.setMargin(box2, new Insets(10, 40, 0, 40));
		
		Label label2 = new Label("Old Password:");
		label2.setAlignment(Pos.CENTER_RIGHT);
		label2.setPrefSize(70, 80);
		label2.setFont(new Font(14));
		HBox.setMargin(label2, new Insets(15, 0, 0, 0));
		
		PasswordField oldPwd = new PasswordField();
		oldPwd.setPrefSize(250, 35);
		HBox.setMargin(oldPwd, new Insets(8, 0, 0, 10));
		
		box2.getChildren().addAll(label2, oldPwd);
		
		// New Password Box
		HBox box3 = new HBox();
		box3.setPrefSize(370, 50);
		VBox.setMargin(box3, new Insets(10, 40, 0, 40));
		
		Label label3 = new Label("New Password:");
		label3.setAlignment(Pos.CENTER_RIGHT);
		label3.setPrefSize(70, 20);
		label3.setFont(new Font(14));
		HBox.setMargin(label3, new Insets(15, 0, 0, 0));
		
		PasswordField newPwd = new PasswordField();
		newPwd.setPrefSize(250,35);
		HBox.setMargin(newPwd, new Insets(8, 0, 0, 10));
		
        box3.getChildren().addAll(label3, newPwd);
        
		HBox box4 = new HBox();
		box4.setPrefSize(430, 50);
        VBox.setMargin(box4, new Insets(10, 0, 0, 0));
        
		// Cancel Button
		Button cancel = new Button("Cnacel");
		cancel.setPrefSize(90, 40);
		cancel.setStyle("-fx-background-color: rgb(6,105,178);-fx-text-fill: white;");
		cancel.setFont(new Font(14));
		cancel.setCursor(Cursor.HAND);
		// Cancel Button Action
		cancel.setOnAction((e) -> {
			Stage stage = (Stage)cancel.getScene().getWindow();
			stage.close();
		});
		HBox.setMargin(cancel, new Insets(5, 0, 0, 40));
		
		
		// Reset Button
		Button reset = new Button("Reset");
		reset.setPrefSize(90, 40);
		reset.setStyle("-fx-background-color: rgb(6,105,178);-fx-text-fill: white;");
		reset.setFont(new Font(14));
		reset.setCursor(Cursor.HAND);
		
		// Register Button Action
		reset.setOnAction(event -> {
			Toast toastr = new Toast(reset.getScene().getWindow());
			try {
				String Email = email.getText();
				String OldPassword = oldPwd.getText();
				String NewPassword = newPwd.getText();
				if(Email.equals("")) {
					Toast.Level level = Toast.Level.values()[1];
					toastr.show(level, 1000, "Nickname cannot be empty!");
					email.requestFocus();
				}else if(OldPassword.equals("")) {
					Toast.Level level = Toast.Level.values()[1];
					toastr.show(level, 1000, "Password cannot be empty!");
					oldPwd.requestFocus();
				}else if(NewPassword.equals("")) {
					Toast.Level level = Toast.Level.values()[1];
					toastr.show(level, 1000, "Please Confirm the Password again!");
					newPwd.requestFocus();
				}else if(OldPassword.equals(NewPassword)) {
					Toast.Level level = Toast.Level.values()[1];
					toastr.show(level, 1000, "New Password is equal to the Old.");
					newPwd.requestFocus();
				}else {
					new HandleReset((Stage)reset.getScene().getWindow(),Email,OldPassword,NewPassword);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		HBox.setMargin(reset, new Insets(5, 0, 0, 170));
		
		box4.getChildren().addAll(cancel, reset);
		
		main.getChildren().addAll(box1,box2,box3,box4);
		
		this.getChildren().addAll(top,main);
	}
}