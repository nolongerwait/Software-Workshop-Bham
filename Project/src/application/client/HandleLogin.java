package application.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import application.dao.UserDAO;
import application.model.User;
import application.utils.Toast;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Handle Login class is defined to deal with Login
 * @author Zetian Qin zxq876
 */
public class HandleLogin {
	private String ipAddr;
	private int port;
	private static ClientThread clientThread;
	private static TextArea showMsg;
	private static String msg;
	// Data Source
	private static ObservableList<String> data;
	// Create ListView
	private static ListView<String> list;
	// Count the number of online users
	private static Label userNum;
	public static final String username = null;
	public static String privateChatUser;
	// Default Group Chat
	private static boolean isPublic = true;
	
    public HandleLogin(Stage stage,Button btn, TextField userName,PasswordField pwd) throws Exception {
    	Toast toast = new Toast(stage);
    	
    	if(userName.getUserData() == null) {
    		Toast.Level level = Toast.Level.values()[2];
			toast.show(level, 1000, "Please confirm the login settings!!");
    	}else {
    		this.ipAddr = (String)userName.getUserData();
        	this.port = Integer.parseInt((String)pwd.getUserData());
        	System.out.println(this.ipAddr + ":" + this.port);
    		UserDAO userDAO = new UserDAO();
			User user = userDAO.findBy(userName.getText(), pwd.getText());
        	if(user != null) {
    			Thread.sleep(2000);
    			stage.close();
    			ChatRoom room = new ChatRoom(user.getNickname());
                room.showChat();
    			clientThread = new ClientThread(user.getNickname());
    			clientThread.start();
    			
				
        	}else {
        		Toast.Level level = Toast.Level.values()[2];
    			toast.show(level, 1000, "UseID or Password is not correct!!");
        	}
    	}
	}
	/**
	 * ChatRoom class defined the ChatRoom Layout and Actions
	 * @author Zetian Qin zxq876
	 */
	static class ChatRoom extends Application {
    	double x1;
    	double y1;
    	double x_stage;
    	double y_stage;
    	Label title;
    	Stage stage = new Stage();
    	String userName;
    	public static void main(String[] args) {
    		launch(args);
    	}
    	public ChatRoom(String userName) {
			this.userName = userName;
			//System.out.println(this.userName + "|");
    	}

    	@Override
    	public void start(Stage primaryStage) throws Exception {
    		//Parent root = FXMLLoader.load(getClass().getResource("ChatRoom.fxml"));
    		primaryStage.initStyle(StageStyle.UNDECORATED);

    		VBox root = new VBox();
    		
    		// Top of Windows
    		AnchorPane box1 = new AnchorPane();
    		box1.setPrefSize(1300, 60);
    		box1.setStyle("-fx-background-color: rgb(51,122,183); -fx-border-radius: 0.8;");
    		
    		title = new Label();
    		title.setText("Chat Room[User："+userName+"]");
    		title.setStyle("-fx-text-fill:#fff");
    		title.setFont(Font.font("微软雅黑", FontWeight.BOLD, 18));
    		AnchorPane.setTopAnchor(title, 15.0);
    		AnchorPane.setLeftAnchor(title, 20.0);
    		//HBox.setMargin(title, new Insets(15, 0, 0, 20));
    		
    		Button closeBtn = new Button("Exit");
    		closeBtn.setStyle("-fx-background-color: rgb(91,192,222);-fx-text-fill:white");
    		closeBtn.setFont(new Font(16));
    		closeBtn.setCursor(Cursor.HAND);
    		AnchorPane.setTopAnchor(closeBtn, 12.0);
    		AnchorPane.setRightAnchor(closeBtn, 20.0);
			//HBox.setMargin(closeBtn, new Insets(12, 0, 0, 1060));
			
    		// Exit the Chat Room
    		closeBtn.setOnAction((e) -> {
    			Stage stage = (Stage)closeBtn.getScene().getWindow();
    			stage.close();
    			clientThread.logout(userName);;
    		});
    		
    		box1.getChildren().addAll(title,closeBtn);
    		
			// Main Body of Chat Room
    		HBox box2 = new HBox();
    		box2.setPrefSize(1300, 500);
    		
    		StackPane sp = new StackPane();
    		sp.setPrefSize(900, 500);
    		
    		showMsg = new TextArea();
    		showMsg.setPrefSize(900, 500);
    		showMsg.setStyle("-fx-border-color: lightblue;");
    		showMsg.setFont(new Font(14));
    		showMsg.setEditable(false);
    		StackPane.setMargin(showMsg, new Insets(10, 0, 10, 10));
    		
    		Button btn = new Button("View the History Messages");
    		btn.setStyle("-fx-background-color: rgba(0,0,0,0);-fx-text-fill:#1a29d0");
    		btn.setCursor(Cursor.HAND);
    		btn.setFont(new Font(14));
    		StackPane.setAlignment(btn, Pos.TOP_RIGHT);
    		StackPane.setMargin(btn, new Insets(20, 10, 0, 0));
    		
    		sp.getChildren().addAll(showMsg,btn);
    		
    		VBox vb = new VBox();
    		vb.setPrefSize(360, 200);
    		vb.setStyle("-fx-border-color: lightblue;");
    		HBox.setMargin(vb, new Insets(10, 0, 10, 20));
    		
    		HBox hb = new HBox();
    		hb.setPrefSize(360, 50);
    		
    		Label lb1 = new Label("Online Users:");
    		lb1.setFont(new Font(14));
    		HBox.setMargin(lb1, new Insets(15, 0, 0, 10));
    		
    		userNum = new Label();
    		userNum.setStyle("-fx-text-fill:red");
    		userNum.setFont(new Font(14));
    		HBox.setMargin(userNum, new Insets(15, 0, 0, 10));
    		
    		hb.getChildren().addAll(lb1,userNum);
    		
    		data = FXCollections.observableArrayList();
    		list = new ListView<String>(data);
    		list.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent click) {
					if(click.getClickCount() == 2) {
						privateChatUser = list.getSelectionModel().getSelectedItem();
						if(!privateChatUser.equals(userName)) {
							title.setText("Chat Room[User："+userName+"] and "+privateChatUser+"are in Private chat.");
							isPublic = false;
						}else {
							title.setText("Chat Room[User："+userName+"]");
							isPublic = true;
						}
					}
					
				}
			});
    		list.setPrefSize(358, 429);
    		list.setStyle("-fx-border-color: rgba(0,0,0,0);");
    		
    		vb.getChildren().addAll(hb,list);
    		
    		box2.getChildren().addAll(sp,vb);
    		
    		// Send Messages Button
    		StackPane box3 = new StackPane();
    		box3.setPrefSize(1300, 240);
    		
    		HTMLEditor message = new HTMLEditor();
    		message.setPrefSize(1300, 240);
    		
    		Button sendBtn = new Button("Send");
    		sendBtn.setPrefWidth(80);
    		sendBtn.setStyle("-fx-background-color: rgb(91,192,222);-fx-text-fill:white");
    		sendBtn.setFont(Font.font(16));
    		sendBtn.setCursor(Cursor.HAND);
    		StackPane.setAlignment(sendBtn, Pos.BOTTOM_RIGHT);
    		StackPane.setMargin(sendBtn, new Insets(0, 20, 10, 0));
    		// Send Messages Action
    		sendBtn.setOnAction((e) -> {
    			Toast toast = new Toast(closeBtn.getScene().getWindow());
    			msg = htmlRemoveTag(message.getHtmlText());
    			if(msg.equals("")) {
    				Toast.Level level = Toast.Level.values()[1];
    				toast.show(level, 1000, "You cannot send EMPTY messages!");
    				message.requestFocus();
    			}else {
    				sendBtn.setFocusTraversable(false);
    				if(null!=clientThread){
    					clientThread.sendChatMsg();// Using sendChatMsg in ClientThread to send messages.
    				}
    					
    				message.setHtmlText("");
    				message.requestFocus();
    			}
    		});
    		
    		box3.getChildren().addAll(message,sendBtn);
    		root.getChildren().addAll(box1,box2,box3);
    		Scene scene = new Scene(root, 1300, 800);
    		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    		primaryStage.setScene(scene);
    		primaryStage.show();

    		// Allow move the Windows.
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
    				// Record the Mouse cursor coordinates when click mouse.
    				x1 = m.getScreenX();
    				y1 = m.getScreenY();
    				x_stage = stage.getX();
    				y_stage = stage.getY();
    			}
    		});

    	}    	

		/**
		 * Filtering HTML tags
		 * @param inputString the String input which need remove the html tags
		 * @return the String which did not include html tags
		 */
    	public String htmlRemoveTag(String inputString) {
    		if (inputString == null)
    			return null;
    		String htmlStr = inputString; // The String including HTML tags
    		String textStr = "";
    		java.util.regex.Pattern p_script;
    		java.util.regex.Matcher m_script;
    		java.util.regex.Pattern p_style;
    		java.util.regex.Matcher m_style;
    		java.util.regex.Pattern p_html;
    		java.util.regex.Matcher m_html;
    		try {
    			//Define the regex for script tags <script[^>]*?>[\\s\\S]*?<\\/script>
    			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; 
    			//Define the regex for style tags <style[^>]*?>[\\s\\S]*?<\\/style>
    			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; 
    			String regEx_html = "<[^>]+>"; // Define the regex for HTML tags
    			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
    			m_script = p_script.matcher(htmlStr);
    			htmlStr = m_script.replaceAll(""); // Filtering script tags
    			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
    			m_style = p_style.matcher(htmlStr);
    			htmlStr = m_style.replaceAll(""); // Filtering style tags
    			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
    			m_html = p_html.matcher(htmlStr);
    			htmlStr = m_html.replaceAll(""); // Filtering HTML tags
    			textStr = htmlStr;
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		return textStr;// return Text Strings
    	}

    	public void showChat() throws Exception {
    		start(stage);
    	}
    	
    }
	
	public class ClientThread extends Thread {
		
		// Communication socket
		public Socket socket;
		// Data input stream
		private DataInputStream dis;
		// Data output stream
		private DataOutputStream dos;
		// Whether user is logged or not
		private boolean isLogged;
		// The user ID
		private String userName;
		public ClientThread(String userName) {
			this.userName = userName;
		}

		/**
		 * Connect to Server and Login
		 * @throws IOException
		 */
		private void login() throws IOException {

			// Connect to Server and Get I/O socket stream
			socket = new Socket(ipAddr, port);
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			// Get Username, then construct and send login report
			String username = userName;
			String msgLogin = "LOGIN#" + username;
			dos.writeUTF(msgLogin);
			dos.flush();
			// Receive the report Server respond and determine if user is logged.
			String response = dis.readUTF();
			// Fail to Login
			if (response.equals("FAIL")) {
				System.out.println("Login to server failed!");
				// Close connection and socket
				socket.close();
				return;
			}
			// Success to Login
			else {
				addMsg("Welcome to Chat Room!");
				this.sendSysMsg("Entre the Chat Room!");
				isLogged = true;
				// Update the list of users
				String[] self = { username };
				updateUserList(list, self, "ADD");
				// GGet the number of online users
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						userNum.setText(String.valueOf(data.size()));		
					}
				});
			}
		}
		
		/**
		 * Exit the Chat Room
		 * @param username the user who logout
		 */
		public void logout(String username) {
			try {
				this.sendSysMsg("Exit the Chat Room!");
				Utils.sendMsg(socket, "LOGOUT#" + username);
				isLogged=false;
				String[] self = { username };
				updateUserList(list, self, "ADD");
				// Get the number of online users
				userNum.setText(String.valueOf(data.size()));
				//lblRoomInfo.setText("目前在线人数" + listUsers.getModel().getSize() + "人");
				socket.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		

		/**
		 * Messages users send
		 */
		public void sendChatMsg() {
			String msgChat = null;
			String dstUser = "All Users";
			if (isPublic) {
				msgChat = "TALKTO_ALL#" + msg + "#false";
			}
			else {
				dstUser = privateChatUser;
				msgChat = "TALKTO#" + dstUser + "#" + msg;
			}
			// Send the chat report to Server
			Utils.sendMsg(socket, msgChat);
			// Add the message to record box
			addMsg(Utils.getTimeStr() + " [I] said to [" + dstUser + "]：\n" + msg);
		}
		
		/**
		 * Messages system send
		 * @param msg the messages that system send
		 */
		public void sendSysMsg(String msg) {
			String msgChat = null;
			msgChat = "TALKTO_ALL#" + msg + "#true";
			//S end the chat report to Server
			Utils.sendMsg(socket, msgChat);

		}

		// Update the list of users
		@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
		public void updateUserList(ListView list, String[] items, String op) {
			switch (op) {
			case "ADD": // Add the new user
				for (int i = 0; i < items.length; i++) {
					data.add(items[i]);
					break;
				}
			case "DEL":// Delete users
				for (int i = 0; i < items.length; i++) {
					data.remove(items[i]);
					break;

				}
			default:
				break;
			}
			list.setItems(data);// Update

		}
		

		// Thread Main Body
		@Override
		public void run() {
			// Connect to Server and Login
			try {
				login();
			} catch (Exception e) {
				System.out.println("An exception occurred while connecting to the login server!");
				e.printStackTrace();
				return;
			}
			while (isLogged) {
				try {
					String msg = dis.readUTF();
					// String msg=Utils.recvMsg(socket);
					String[] parts = msg.split("#");
					switch (parts[0]) {
					// Progress the report about list of users that Server respond
					case "USERLIST":
						String[] self = { username };
						updateUserList(list, self, "ADD");
						// Get the number of online users
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								userNum.setText(String.valueOf(data.size()));
							}
						});
						
						for (int i = 1; i < parts.length; i++) {
							data.add(parts[i]);
						}
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								userNum.setText(String.valueOf(data.size()));	
							}
						});
						System.out.println("USERLIST");
						System.out.println(data.size());
						break;
					// Progress the report about new login user that Server respond
					case "LOGIN":
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								data.add(parts[1]);	
								userNum.setText(String.valueOf(data.size()));	
							}
						});		
						break;
					case "LOGOUT":
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								data.remove(parts[1]);
							}
						});				
						String[] logoutUser={parts[1]};
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								updateUserList(list,logoutUser,"DEL");
								userNum.setText(String.valueOf(data.size()));
							}
						});
						break;
					case "TALKTO_ALL":
						if(parts[3].equals("false")) {
							addMsg(Utils.getTimeStr() + " ["+parts[1] + "] said to ALL Users：\n" + parts[2]);
						}else if(parts[3].equals("true")){
							addMsg("[System Messages] " + parts[1] + parts[2]);
						}
						break;
					case "TALKTO":

						addMsg(Utils.getTimeStr()+ " [" + parts[1] + "] said to me：" + parts[2]);
						break;
					default:
						break;
					}
				} catch (IOException e) {
					isLogged = false;
					e.printStackTrace();
				}
			}
		}

		/**
		 * Add messages to Text Box
		 * @param msg The message add to Text Box
		 */
		public void addMsg(String msg) {
			// Add message in Text Box
			showMsg.appendText(msg + "\n");
			// To last line of Text Box
			showMsg.positionCaret(showMsg.getText().length());
		}
	}

	/**
	 * Utils Class included the Report Sender and Receiver method 
	 * @author Zetian Qin zxq876
	 */
	public static class Utils {
		/**
		 * Send messages as String by socket
		 * @param s the socket
		 * @param msg the messages
		 */
		public static void sendMsg(Socket s, String msg) {
			try {
				// Character Stream
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Read String from socket
		 * @param s the socket
		 * @return the String in socket
		 * @throws IOException
		 */
		@SuppressWarnings("resource")
		public static String recvMsg(Socket s) throws IOException {
			String msg = null;
			DataInputStream dis = (DataInputStream) new java.io.DataInputStream(s.getInputStream());
			msg = ((java.io.DataInputStream) dis).readUTF();
			return msg;
	
		}

		/**
		 * Get current time as format String
		 * @return the current time
		 */
		public static String getTimeStr() {
			SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	
			return fm.format(new Date());
	
		}
	}
}
