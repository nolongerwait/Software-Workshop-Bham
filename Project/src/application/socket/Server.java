package application.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import application.utils.CloseUtil;

/**
 * Server Class created the server.
 * @author Zetian Qin zxq876
 */
public class Server {
	private List<MyChannel> all = new ArrayList<MyChannel>();
	private ServerSocket server;
	public static void main(String[] args) throws IOException {
			new Server().start();
	}
	
	public void start() throws IOException {
		server = new ServerSocket(9999);
		while(true) {
			Socket client = server.accept();
			
			MyChannel channel = new MyChannel(client);
			all.add(channel);
			new Thread(channel).start();
		}	
	}
	
	public void stop(){
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		all.clear();
		System.out.println("Server has closed.");
	}
	
	/**
	 * MyChannel Class defined that one client refers to one channel
	 * 1.Input Stream
	 * 2.Output Stream
	 * 3.Receive Data Stream
	 * 4.Send Data Stream
	 * @author Zetian Qin zxq876
	 *
	 */
	class MyChannel implements Runnable{
		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isRunning = true;
		private String name;

		/**
		 * Constructor for Server class
		 * @param client the client socket
		 */
		public MyChannel(Socket client) {
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
				
				this.name = dis.readUTF();
				System.out.println(this.name + "Login successfully!");
				this.send("Welcome to Chat Room!");
				this.sendOthers(this.name + "entred the Chat Room", true);
			} catch (IOException e) {
				//e.printStackTrace();
				isRunning = false;
				CloseUtil.closeAll(dis,dos);
			}
		}

		/**
		 * Read Data
		 * @return the message received
		 */
		private String receive() {
			String msg = "";
			try {
				msg = dis.readUTF();
			} catch (IOException e) {
				//e.printStackTrace();
				CloseUtil.closeAll(dis);
				isRunning = false;
				all.remove(this); // Remove itself.
				this.sendOthers(this.name + "exited the Chat Room.", true);
			}
			return msg;
		}

		/**
		 * Send Data
		 * @param msg the message which would send to others
		 */
		private void send(String msg) {
			if(msg == null || msg.equals("")) {
				return ;
			}
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				//e.printStackTrace();
				CloseUtil.closeAll(dos);
				isRunning = false;
				all.remove(this);
			}
		}

		/**
		 * Send to other Clients
		 * @param msg the messages which would send to others
		 * @param sys the system information
		 */
		private void sendOthers(String msg, boolean sys) {
			/**
			 * Whether it is in private chat
			 */
			if(!msg.equals(null) && msg != "") {
				// In private chat
				if(msg.startsWith("@") && msg.indexOf(":") > -1) {
					// Get the users in private chat
					String name = msg.substring(1, msg.indexOf(":"));
					String content = msg.substring(msg.indexOf(":")+1);
					for(MyChannel other: all) {
						if(other.name.equals(name)) {
							other.send(this.name + "said privately to you:" + content);
						}
					}
				}
				// In group chat
				else {
					// Traversing the container(Find all users in group chat)
					for(MyChannel other: all) {
						if(other == this) {
							continue;
						}
						if(sys) { // System Infor
							other.send("System Infor:" + msg);
							System.out.println(12);
						}else {
							// Send to other Clients
							System.out.println(34);
							other.send(this.name + "said：" + msg);
						}
					}
				}
			}
			
		}
		
		@Override
		public void run() {
			while(isRunning) {
				sendOthers(receive(), false);
			}
		}
		
	}
}