package application.client;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.regex.Pattern;
import application.dialog.StyleDialog;
import application.utils.Toast;

/**
 * LoginPane class is define the Layout of Login Dlg
 * @author Zetian Qin zxq876
 */
public class LoginPane extends Pane{
	Stage stage;
	TextField userName;
	PasswordField password;
	public LoginPane() {
		VBox vb = new VBox();
		this.getChildren().add(vb);
		vb.setPrefSize(430, 330);
		
		StackPane stack = new StackPane();
		stack.setPrefSize(430, 127);
		
		
		//Top Backgroud
		ImageView topBg = new ImageView(new Image("/application/res/background.jpg"));
		topBg.setFitHeight(127);
		topBg.setFitWidth(430);
		//Top Icon
		ImageView loginIcon = new ImageView(new Image("/application/res/qq.png"));
		loginIcon.setFitHeight(30);
		loginIcon.setFitWidth(30);
		StackPane.setMargin(loginIcon, new Insets(-75, 375, 0, 0));
		
		//Top Logo
		Label logo = new Label("SABARMATI");
		logo.getStyleClass().add("logo");
		StackPane.setMargin(logo, new Insets(-75, 240, 0, 0));
		
		//Login Setting Button Icon
		Button setting  = new Button();
		setting.setPrefSize(30, 30);
		setting.setId("setting");
		setting.setCursor(Cursor.HAND);
		StackPane.setMargin(setting, new Insets(-85, 0, 0, 270));
		
		// Login Button Action
		setting.setOnAction((e) -> {
			this.settingDialog(setting);
		});
		
		// Minize Windows Button
		Button minimizeWindow = new Button();
		minimizeWindow.setPrefSize(30, 30);
		minimizeWindow.setId("minimizeWindow");
		minimizeWindow.setCursor(Cursor.HAND);
		// Minize Window Button Action
		minimizeWindow.setOnMouseClicked((e)->{
			stage = (Stage) minimizeWindow.getScene().getWindow();
			stage.setIconified(true);
		});
		StackPane.setMargin(minimizeWindow, new Insets(-85, 0, 0, 330));
		
		// Close Window Button
		Button closeWindow = new Button();
		closeWindow.setPrefSize(30, 30);
		closeWindow.setId("closeWindow");
		closeWindow.setCursor(Cursor.HAND);
		// Close Window Button Action
		closeWindow.setOnMouseClicked((e)->{
			stage = (Stage) closeWindow.getScene().getWindow();
			stage.close();
		});
		StackPane.setMargin(closeWindow, new Insets(-85, 0, 0, 390));
		
		stack.getChildren().addAll(topBg,loginIcon,logo,setting,minimizeWindow,closeWindow);
		
		// UserID Input Box
		HBox nameBox = new HBox();
		nameBox.setPrefSize(430, 40);
		
		Label nameLabel = new Label("Email:");
		nameLabel.setPrefSize(80, 40);
		userName = new TextField();
		userName.setPrefSize(280, 40);
		userName.setPromptText("Email:");
		userName.requestFocus();
		userName.setTooltip(new Tooltip("Please enter your Email!"));
		
		nameBox.getChildren().addAll(nameLabel,userName);
		VBox.setMargin(nameBox, new Insets(20, 50, 0, 50));
		
		// Password Input Box
		HBox pwdBox = new HBox();
		pwdBox.setPrefSize(430, 40);
		
		Label pwdLabel = new Label("Password:");
		pwdLabel.setPrefSize(80, 40);
		password = new PasswordField();
		password.setPrefSize(280, 40);
		password.setPromptText("Password:");
		password.setTooltip(new Tooltip("Please entre your Password!"));
		pwdBox.getChildren().addAll(pwdLabel,password);
		VBox.setMargin(pwdBox, new Insets(15, 50, 0, 50));
		
		
		// Login Box
		HBox loginBox = new HBox();
		loginBox.setPrefSize(200, 30);
		
		Button loginBtn = new Button("Login");
		loginBtn.setPrefSize(230, 30);
		loginBtn.getStyleClass().add("login-button");
		
		loginBox.getChildren().add(loginBtn);
		// Login Button Action
		loginBtn.setOnAction(event -> {
			Toast toastr = new Toast(loginBtn.getScene().getWindow());
			try {
				if(userName.getText().equals("")) {
					Toast.Level level = Toast.Level.values()[1];
					toastr.show(level, 1000, "Email cannot be empyt!");
					userName.requestFocus();
				}else if(password.getText().equals("")) {
					Toast.Level level = Toast.Level.values()[1];
					toastr.show(level, 1000, "Password cannot be empyt!");
					password.requestFocus();
				}else {
					new HandleLogin((Stage)loginBtn.getScene().getWindow(),loginBtn,userName,password);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		HBox.setMargin(loginBtn, new Insets(0, 0, 0, 50));
		VBox.setMargin(loginBox, new Insets(20, 50, 0, 50));
		
		// Register Box
		HBox regBox = new HBox();
		regBox.setPrefSize(200, 30);
		
		Button regBtn = new Button("Register");
		regBtn.getStyleClass().add("reg");
		regBtn.setPrefSize(100, 30);
		regBtn.setCursor(Cursor.HAND);
		// Register Action
		regBtn.setOnAction((e) -> {
			try {
				new Register().showRegister();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		HBox.setMargin(regBtn, new Insets(0, 0, 0, 6));
		
		Button resetBtn = new Button("Reset Password");
		resetBtn.getStyleClass().add("reg");
		resetBtn.setPrefSize(140, 30);
		resetBtn.setCursor(Cursor.HAND);
		// Reset Action
		resetBtn.setOnAction((e) -> {
			try {
				new Reset().showReset();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		HBox.setMargin(resetBtn, new Insets(0, 0, 0, 250));
		regBox.getChildren().addAll(regBtn,resetBtn);
		
		vb.getChildren().addAll(stack,nameBox,pwdBox,loginBox,regBox);
	}
	// Login Dlg
	public void settingDialog(Button btn)
	{
		SettingDialog dlg = new SettingDialog(btn);
		
		// dlg.exec() return true or false
		if(dlg.exec())
		{
			userName.setUserData(dlg.ipAddr.getText());
			password.setUserData(dlg.port.getText());
		}
	}
	// Setting Dlg
	class SettingDialog extends StyleDialog
	{
		Label label1 = new Label("IP address：");
		TextField ipAddr = new TextField();
		
		Label label2 = new Label("Port：");
		TextField port = new TextField();
		Button buttonCancel  = new Button("Cancel");
		Button buttonOK  = new Button("OK");
		
		public SettingDialog(Node anchor)
		{
			super(anchor);
			
			initLayout();
			
			// Text Box Listener to limit only number could be entred.
			ipAddr.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("[\\d*|\\.]")) { 
						ipAddr.setText(newValue.replaceAll("[^\\d|\\.]", "")); 
					} 
					
				}
			});
			
			port.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) { 
						port.setText(newValue.replaceAll("[^\\d]", "")); 
					} 
					
				}
			});
			
			// OK button to close Dlg
			buttonOK.setOnAction((e)->{
				Toast toastr = new Toast(buttonOK.getScene().getWindow());
				String ipRegex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"; // IP adderss
				String portRegex = "^([0-9]|[1-9]\\d|[1-9]\\d{2}|[1-9]\\d{3}|[1-5]\\d{4}|6[0-4]\\d{3}|65[0-4]\\d{2}|655[0-2]\\d|6553[0-5])$";
				String ip = ipAddr.getText();
				String portID = port.getText();
				if(ip.equals("")) {
					Toast.Level level = Toast.Level.values()[1];
					toastr.show(level, 1000, "IP address cannot be empty!");
					ipAddr.requestFocus();
				}else if(portID.equals("")) {
					Toast.Level level = Toast.Level.values()[1];
					toastr.show(level, 1000, "Port ID cannot be empty!");
					port.requestFocus();
				}else if(!Pattern.compile(ipRegex).matcher(ip).matches()) {
					Toast.Level level = Toast.Level.values()[1];
					toastr.show(level, 1000, "IP address is invalid!!");
					ipAddr.requestFocus();
				}else if(!Pattern.compile(portRegex).matcher(portID).matches()) {
					Toast.Level level = Toast.Level.values()[1];
					toastr.show(level, 1000, "Port ID is invalid!!");
					port.requestFocus();
				}else {
					accept();
				}	
			});
			
			// Cancel button to close Dlg
			buttonCancel.setOnAction((e)->{
				
				cancel();
				
			});
		}
		// Layout Setting
		private void initLayout()
		{	
			this.setDialogTitle("Login Setting");
			
			VBox root = new VBox();
			this.setContentView(root); // Root Container
			root.setPrefWidth(400); // The sieze of Windows depend on root container
			root.setPrefHeight(180);
			
			HBox box1 = new HBox();
			box1.setPrefSize(370, 50);
			VBox.setMargin(box1, new Insets(0, 40, 0, 25));
			
			label1.setAlignment(Pos.CENTER_RIGHT);
			label1.setPrefSize(70, 20);
			label1.setFont(new Font(14));
			HBox.setMargin(label1, new Insets(15, 0, 0, 0));
			
			ipAddr.setPrefSize(250, 35);
			
			if(userName.getUserData() != null) {
				ipAddr.setText((String)userName.getUserData());
			}else {
				ipAddr.setText("127.0.0.1"); // Default IP address
			}
			HBox.setMargin(ipAddr, new Insets(8, 0, 0, 10));
			box1.getChildren().addAll(label1, ipAddr);
			
			HBox box2 = new HBox();
			box2.setPrefSize(370, 50);
			VBox.setMargin(box2, new Insets(10, 40, 0, 25));
			
			label2.setAlignment(Pos.CENTER_RIGHT);
			label2.setPrefSize(70, 20);
			label2.setFont(new Font(14));
			HBox.setMargin(label2, new Insets(15, 0, 0, 0));
			
			port.setPrefSize(250, 35);
			if(password.getUserData() != null) {
				port.setText((String)password.getUserData());
			}else {
				port.setText("9999"); // Default PortID
			}
			HBox.setMargin(port, new Insets(8, 0, 0, 10));
			box2.getChildren().addAll(label2, port);
			
			HBox box3 = new HBox();
			box3.setPrefSize(400, 50);
			VBox.setMargin(box3, new Insets(10, 0, 0, 0));
			buttonCancel.setPrefSize(90, 40);
			buttonCancel.setStyle("-fx-background-color: rgb(6,105,178);-fx-text-fill: white;");
			buttonCancel.setFont(new Font(14));
			buttonCancel.setCursor(Cursor.HAND);
			HBox.setMargin(buttonCancel, new Insets(5, 0, 0, 40));
			
			
			buttonOK.setPrefSize(90, 40);
			buttonOK.setStyle("-fx-background-color: rgb(6,105,178);-fx-text-fill: white;");
			buttonOK.setFont(new Font(14));
			buttonOK.setCursor(Cursor.HAND);
			HBox.setMargin(buttonOK, new Insets(5, 0, 0, 150));
			box3.getChildren().addAll(buttonCancel,buttonOK);
			
			root.getChildren().addAll( box1,box2,box3 );
		}
	}
}
