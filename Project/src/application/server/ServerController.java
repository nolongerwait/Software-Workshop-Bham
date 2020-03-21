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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ServerController {
	// Whether the Server is running or not
	private boolean isRunning;
	// Using HashMap<String, ClientHandler> to store each user and client
	private HashMap<String, ClientHandler> clientHandlerMap = new HashMap<String, ClientHandler>();
	private ServerSocket server;
	@FXML
	TextField ipAddr;
	@FXML
	TextField port;
	@FXML
	Button start;
	@FXML
	Button stop;

	@FXML
	TextArea log;
	@FXML
	ListView<String> list;
	@FXML
	public void unchange() {
		log.setEditable(false);
	}
	
	@FXML
	public void startServer() {
		System.out.println("Server starting...");
		// Create and Start the Server Conmmunication Thread.
		//Thread serverThread = new Thread(new ServerThread());
		Thread serverThread = new Thread(new ServerThread());
		serverThread.start();
		start.setDisable(true);
		stop.setDisable(false);
		ipAddr.setEditable(false);
		port.setEditable(false);
		System.out.println("Server start success.");
	}

	@FXML
	public void stopServer() {
		
		try {
			System.out.println("Server stoping...");
			isRunning = false;
			// Close the Server socket and clean the Client Reference
			server.close();
			clientHandlerMap.clear();
			start.setDisable(false);
			stop.setDisable(true);
			ipAddr.setEditable(true);
			port.setEditable(true);
			log.appendText("Server Stoped!" + "\n");
			System.out.println("Server Stoped!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
				// e.printStackTrace();
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

		public Object getInetAddress() {
			// TODO Auto-generated method stub
			return null;
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
							addMsg("User "+loginUsername+" Login. Login Time: " + getTimeStr());
							clientHandlerMap.put(loginUsername, this);
							// Send the message to other users
							StringBuffer msgUserList = new StringBuffer();
							msgUserList.append("USERLIST#");
							
							for (String username : clientHandlerMap.keySet()) {
								msgUserList.append(username + "#");
								System.out.println(username);
							}
							dos.writeUTF(msgUserList.toString());
							// broadcast the new login user to others
							String msgLogin = "LOGIN#" + loginUsername;

							broadcastMsg(loginUsername, msgLogin);
							// updateUserTbl();
							// Store the username who is online
							this.username = loginUsername;
							updateUserTbl();
						}
						break;
					// Handle the logout report.
					case "LOGOUT":
						clientHandlerMap.remove(username);
						addMsg("User "+username+" logout. Logout time: " + getTimeStr());
						String msgLogout = "LOGOUT#" + username;
						broadcastMsg(username, msgLogout);
						isConnected = false;
						socket.close();
						updateUserTbl();
						break;
					case "TALKTO_ALL":
						String msgTalkToAll = "TALKTO_ALL#" + username + "#" + parts[1];
						broadcastMsg(username, msgTalkToAll);

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

	public void updateUserTbl() {
		// TODO Auto-generated method stub

	}

	public Object getInetAddress() {
		// TODO Auto-generated method stub
		return null;
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