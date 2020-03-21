package application.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import application.client.HandleLogin.Utils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class ServerStart extends Application
{

	// Whether the Server is running or not
	private boolean isRunning;
	// Using HashMap<String, ClientHandler> to store each user and client
	private HashMap<String, ClientHandler> clientHandlerMap = new HashMap<String, ClientHandler>();
	private ServerSocket server;
	TextField ipAddr;
	TextField port;
	Button startBtn;
	Button stopBtn;
	TextArea log;
	ListView<String> list;
	ObservableList<String> data;
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Parent root = FXMLLoader.load(getClass().getResource("ServerFrame.fxml"));
		VBox root = new VBox();
		root.setStyle("-fx-background-color: #0ff;");
		
		HBox box1 = new HBox();
		box1.setPrefSize(900, 100);
		
		Label lb3 = new Label("Server IP Address:");
		lb3.setFont(new Font(18));
		HBox.setMargin(lb3, new Insets(40, 0, 0, 20));
		
		ipAddr = new TextField();
		ipAddr.setText("127.0.0.1");
		ipAddr.setPrefSize(200, 40);
		ipAddr.setFont(new Font(16));
		HBox.setMargin(ipAddr, new Insets(30, 0, 0, 20));
		
		Label lb4 = new Label("PortID:");
		lb4.setFont(new Font(18));
		HBox.setMargin(lb4, new Insets(40, 0, 0, 40));
		
		port = new TextField();
		port.setText("9999");
		port.setPrefSize(100, 40);
		port.setFont(new Font(16));
		HBox.setMargin(port, new Insets(30, 0, 0, 10));
		
		startBtn = new Button("Start");
		startBtn.setStyle("-fx-background-color: skyblue;");
		startBtn.setPrefSize(100, 40);
		startBtn.setFont(new Font(18));
		startBtn.setCursor(Cursor.HAND);
		HBox.setMargin(startBtn, new Insets(30, 0, 0, 80));
		// Start the Server
		startBtn.setOnAction((e) -> {
			startServer();
		});
		
		
		stopBtn = new Button("Stop");
		stopBtn.setStyle("-fx-background-color: red;");
		stopBtn.setDisable(true);
		stopBtn.setPrefSize(100, 40);
		stopBtn.setFont(new Font(18));
		stopBtn.setCursor(Cursor.HAND);
		HBox.setMargin(stopBtn, new Insets(30, 20, 0, 10));
		// Stop the Server
		stopBtn.setOnAction((e) -> {
			stopServer();
		});
		
		box1.getChildren().addAll(lb3,ipAddr,lb4,port,startBtn,stopBtn);
		
		Label lb1 = new Label("Messages Record");
		lb1.setFont(new Font(18));
		VBox.setMargin(lb1, new Insets(0, 0, 0, 20));
		
		log = new TextArea();
		log.setPrefSize(860, 200);
		log.setEditable(false);
		log.setFont(new Font(14));
		VBox.setMargin(log, new Insets(10, 20, 0, 20));
		
		Label lb2 = new Label("List of online users");
		lb2.setFont(new Font(18));
		VBox.setMargin(lb2, new Insets(20, 0, 0, 20));
		
		data = FXCollections.observableArrayList();
		list = new ListView<String>(data);
		list.setItems(data);
		list.setPrefSize(200, 200);
		VBox.setMargin(list, new Insets(10, 20, 0, 20));
		
		root.getChildren().addAll(box1,lb1,log,lb2,list);
		primaryStage.setTitle("TCP Chat Room Server"); // Title of Windows
	    primaryStage.getIcons().add(new Image("/application/res/qq_icon.png")); // Icon of Windows
	    primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show(); 
	}
	
	public void startServer() {
		System.out.println("Server starting...");
		// Create and Start the Server Conmmunication Thread.
		//Thread serverThread = new Thread(new ServerThread());
		Thread serverThread = new Thread(new ServerThread());
		serverThread.start();
		startBtn.setDisable(true);
		stopBtn.setDisable(false);
		ipAddr.setEditable(false);
		port.setEditable(false);
		System.out.println("Server start success");
	}
	
	public void stopServer() {		
		try {
			System.out.println("Server stoping...");
			isRunning = false;
			// Close the Server socket and clean the Client Reference
			server.close();
			clientHandlerMap.clear();
			startBtn.setDisable(false);
			stopBtn.setDisable(true);
			ipAddr.setEditable(true);
			port.setEditable(true);
			log.appendText("Server Stoped!" + "\n");
			System.out.println("Server Stoped!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ServerThread class creates the Server Thread
	 * @author Zetian Qin zxq876
	 */
	class ServerThread implements Runnable {
		public void start(String serverIp, int serverPort) {
			try {
				// Create the socket address
				SocketAddress socketAddress = new InetSocketAddress(serverIp, serverPort);
				// Create the Server Socket to band with socket address
				server = new ServerSocket();
				server.bind(socketAddress);
				// Whether Server is running
				isRunning = true;
				addMsg("Server start success!");
			} catch (IOException e) {
				addMsg("Server failed to start. Please check the PortID.");
				isRunning = false;
			}
		}

		@Override
		public void run() {
			start(ipAddr.getText(), Integer.parseInt(port.getText()));
			// When Server is running, watch the request from client
			while (isRunning) {
				try {
					Socket socket = server.accept();
					// Create the thread to interact with client
					Thread thread = new Thread(new ClientHandler(socket));
					thread.start();
				} catch (IOException e) {

				}
			}
		}

	}
	
	/**
	 * Inner class ClientHandler to interact with Client.
	 * @author Zetian Qin zxq876
	 */
	class ClientHandler implements Runnable {
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isConnected;
		private String username;

		public ClientHandler(Socket socket) {
			this.socket = socket;
			try {
				this.dis = new DataInputStream(socket.getInputStream());
				this.dos = new DataOutputStream(socket.getOutputStream());
				isConnected = true;
			} catch (IOException e) {
				isConnected = false;
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while (isRunning && isConnected) {
				try {
					// Read the report from Client
					String msg = dis.readUTF();
					String[] parts = msg.split("#");
					switch (parts[0]) {
					// Handle the login report
					case "LOGIN":
						String loginUsername = parts[1];
						if (clientHandlerMap.containsKey(loginUsername)) {
							dos.writeUTF("FAIL");
						} else {
							dos.writeUTF("SUCCESS");
							addMsg("User "+loginUsername+" login successfully，login time：" + getTimeStr());
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									data.add(loginUsername);
									
								}
							});
							clientHandlerMap.put(loginUsername, this);
							// Send the message to other users
							StringBuffer msgUserList = new StringBuffer();
							msgUserList.append("USERLIST#");
							
							for (String username : clientHandlerMap.keySet()) {
								msgUserList.append(username + "#");
							}
							dos.writeUTF(msgUserList.toString());
							// broadcast the new login user to others
							String msgLogin = "LOGIN#" + loginUsername;
							broadcastMsg(loginUsername, msgLogin);
							// Store the username who is online
							this.username = loginUsername;
						}
						break;
					// Handle the logout report.
					case "LOGOUT":
						clientHandlerMap.remove(username);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								data.remove(username);
								
							}
						});
						
						addMsg("User "+username+" logout，logout time：" + getTimeStr());
						String msgLogout = "LOGOUT#" + username;
						broadcastMsg(username, msgLogout);
						isConnected = false;
						socket.close();
						break;
					case "TALKTO_ALL":
						if(parts[2].equals("false")) {
							System.out.println("User Messages");
							addMsg(Utils.getTimeStr() + " ["+username + "] said to All Users：" + parts[1]);
							String msgTalkToAll = "TALKTO_ALL#" + username + "#" + parts[1] + "#" + parts[2];
							broadcastMsg(username, msgTalkToAll);
						}else if(parts[2].equals("true")){
							System.out.println("System Messages:");
							String msgTalkToAll = "TALKTO_ALL#" + username + "#" + parts[1] + "#" + parts[2];
							broadcastMsg(username, msgTalkToAll);
						}
						break;
					case "TALKTO":
						ClientHandler clientHandler = clientHandlerMap.get(parts[1]);
						if (null != clientHandler) {
							String msgTalkTo = "TALKTO#" + username + "#" + parts[2];
							clientHandler.dos.writeUTF(msgTalkTo);
							clientHandler.dos.flush();
						}
						break;

					default:
						break;
					}
				} catch (IOException e) {
					isConnected = false;
					e.printStackTrace();
				}
			}
		}

		/**
		 * To broadcast the message to other users.
		 * @param fromUsername the user who send the message.
		 * @param msg the messages.
		 * @throws IOException IO exception
		 */
		private void broadcastMsg(String fromUsername, String msg) throws IOException {
			for (String toUserName : clientHandlerMap.keySet()) {
				if (fromUsername.equals(toUserName) == false) {
					DataOutputStream dos = clientHandlerMap.get(toUserName).dos;
					dos.writeUTF(msg);
					dos.flush();
				}
			}
		}
	}

	/**
	 * Add messages to Text Box
	 * @param msg The message add to Text Box
	 */
	private void addMsg(String msg) {
		// Add message in Text Box
		log.appendText(msg + "\n");
		// To last line of Text Box
		log.positionCaret(log.getText().length());
	}

	/**
	 * Get current time as format String
	 * @return the current time
	 */
	public String getTimeStr() {
		SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

		return fm.format(new Date());

	}
}
