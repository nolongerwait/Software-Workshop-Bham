package application.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import application.model.User;
import application.utils.Toast;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Register Layout class
 * @author Zetian Qin zxq876
 */
public class RegisterPane extends VBox{
	Stage stage;
	public RegisterPane() {
		HBox top = new HBox();
		top.setPrefSize(430, 70);
		top.setStyle("-fx-background-color: lightblue;");
		
		Label logo = new Label("User Register");
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
		
		// Nickname Box
		HBox box1 = new HBox();
		box1.setPrefSize(370, 50);
		VBox.setMargin(box1, new Insets(30, 40, 0, 40));
		
		Label label1 = new Label("Nickname:");
		label1.setAlignment(Pos.CENTER_RIGHT);
		label1.setPrefSize(70, 20);
		label1.setFont(new Font(14));
		label1.requestFocus();
		HBox.setMargin(label1, new Insets(15, 0, 0, 0));
		
		TextField name = new TextField();
		name.setPrefSize(250, 35);
		HBox.setMargin(name, new Insets(8, 0, 0, 10));
		
		box1.getChildren().addAll(label1, name);
		
		// Password Box
		HBox box2 = new HBox();
		box2.setPrefSize(370, 50);
		VBox.setMargin(box2, new Insets(10, 40, 0, 40));
		
		Label label2 = new Label("Password:");
		label2.setAlignment(Pos.CENTER_RIGHT);
		label2.setPrefSize(70, 20);
		label2.setFont(new Font(14));
		HBox.setMargin(label2, new Insets(15, 0, 0, 0));
		
		PasswordField pwd = new PasswordField();
		pwd.setPrefSize(250, 35);
		HBox.setMargin(pwd, new Insets(8, 0, 0, 10));
		
		box2.getChildren().addAll(label2, pwd);
		
		// Confirm Password Box
		HBox box3 = new HBox();
		box3.setPrefSize(370, 50);
		VBox.setMargin(box3, new Insets(10, 40, 0, 40));
		
		Label label3 = new Label("Confirm Password:");
		label3.setAlignment(Pos.CENTER_RIGHT);
		label3.setPrefSize(70, 20);
		label3.setFont(new Font(14));
		HBox.setMargin(label3, new Insets(15, 0, 0, 0));
		
		PasswordField confirmPwd = new PasswordField();
		confirmPwd.setPrefSize(250,35);
		HBox.setMargin(confirmPwd, new Insets(8, 0, 0, 10));
		
		box3.getChildren().addAll(label3, confirmPwd);
		
		// Gander Choice
		HBox box4 = new HBox();
		box4.setPrefSize(370, 50);
		VBox.setMargin(box4, new Insets(10, 40, 0, 40));
		
		Label label4 = new Label("Gander:");
		label4.setAlignment(Pos.CENTER_RIGHT);
		label4.setPrefSize(70, 20);
		label4.setFont(new Font(14));
		HBox.setMargin(label4, new Insets(15, 0, 0, 0));
		
		ToggleGroup group = new ToggleGroup();
	    RadioButton button1 = new RadioButton("Male");
	    button1.setFont(new Font(14));
	    button1.setToggleGroup(group);
	    button1.setUserData("Male");
	    button1.setSelected(true);
	    HBox.setMargin(button1, new Insets(15, 0, 0, 30));
	    RadioButton button2 = new RadioButton("Female");
	    button2.setToggleGroup(group);
	    button2.setFont(new Font(14));
	    button2.setUserData("Female");
	    HBox.setMargin(button2, new Insets(15, 0, 0, 50));
		
		box4.getChildren().addAll(label4, button1, button2);
		
		// Birthday button
		HBox box5 = new HBox();
		box5.setPrefSize(370, 50);
		VBox.setMargin(box5, new Insets(10, 40, 0, 40));
		
		Label label5 = new Label("Birthday:");
		label5.setAlignment(Pos.CENTER_RIGHT);
		label5.setPrefSize(70, 20);
		label5.setFont(new Font(14));
		HBox.setMargin(label5, new Insets(15, 0, 0, 0));
		
		DatePicker birthday = new DatePicker();
		birthday.setPrefSize(250, 35);
		birthday.setValue(LocalDate.now());
		HBox.setMargin(birthday, new Insets(8, 0, 0, 10));
		
		box5.getChildren().addAll(label5, birthday);
		
		// Email Box
		HBox box6 = new HBox();
		box6.setPrefSize(370, 50);
		VBox.setMargin(box6, new Insets(10, 40, 0, 40));
		
		Label label6 = new Label("Email:");// Change to Local
		label6.setAlignment(Pos.CENTER_RIGHT);
		label6.setPrefSize(70, 20);
		label6.setFont(new Font(14));
		HBox.setMargin(label6, new Insets(15, 0, 0, 0));
		
		TextField eml = new TextField();
		eml.setPrefSize(250, 35);
		HBox.setMargin(eml, new Insets(8, 0, 0, 10));
		
		box6.getChildren().addAll(label6, eml);
		
		
		HBox box7 = new HBox();
		box7.setPrefSize(430, 50);
		VBox.setMargin(box7, new Insets(10, 0, 0, 0));

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
		
		
		// Register Button
		Button register = new Button("Register");
		register.setPrefSize(90, 40);
		register.setStyle("-fx-background-color: rgb(6,105,178);-fx-text-fill: white;");
		register.setFont(new Font(14));
		register.setCursor(Cursor.HAND);
		
		// Register Button Action
		register.setOnAction(event -> {
			Toast toastr = new Toast(register.getScene().getWindow());
			try {
				String nickname = name.getText();
				String password = pwd.getText();
				String confirmPassword = confirmPwd.getText();
				String gender = (String)group.getSelectedToggle().getUserData();
				String birth = birthday.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String email = eml.getText();
				if(nickname.equals("")) {
					Toast.Level level = Toast.Level.values()[1];
					toastr.show(level, 1000, "Nickname cannot be empty!");
					name.requestFocus();
				}else if(password.equals("")) {
					Toast.Level level = Toast.Level.values()[1];
					toastr.show(level, 1000, "Password cannot be empty!");
					pwd.requestFocus();
				}else if(confirmPassword.equals("")) {
					Toast.Level level = Toast.Level.values()[1];
					toastr.show(level, 1000, "Please Confirm the Password again!");
					confirmPwd.requestFocus();
				}else if(email.equals("")) {
					Toast.Level level = Toast.Level.values()[1];
					toastr.show(level, 1000, "Email cannot be empyt!");
					eml.requestFocus();
				}else if(!password.equals(confirmPassword)) {
					Toast.Level level = Toast.Level.values()[1];
					toastr.show(level, 1000, "Passwords twice are inconsistent!");
					pwd.requestFocus();
				}else {
					User user = new User();
					//user.setUserID();
					user.setNickname(nickname);
					user.setPwd(password);
					user.setGander(gender);
					user.setBirthday(birth);
					user.setEmail(email);
					System.out.println(nickname+"-"+gender);
					new HandleRegister((Stage)register.getScene().getWindow(),user);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		HBox.setMargin(register, new Insets(5, 0, 0, 170));
		
		box7.getChildren().addAll(cancel, register);
		
		main.getChildren().addAll(box1,box2,box3,box4,box5,box6,box7);
		
		this.getChildren().addAll(top,main);
	}
}