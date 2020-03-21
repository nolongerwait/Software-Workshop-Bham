package application.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import application.utils.CloseUtil;

/**
 * Send Class defined the thread sending the data
 * @author Zetian Qin zxq876
 *
 */
public class Send implements Runnable{
	// Input Stream from console
	private BufferedReader console;
	// Output Stream from channel
	private DataOutputStream dos;
	// Thread which control the operation
	private boolean isRunning = true;
	// Username
	private String name;

	/**
	 * Empty Constructor
	 */
	public Send() {
		console = new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	 * Constructor for Send class
	 * @param client the client socket
	 * @param name the name of user
	 */
	public Send(Socket client,String name) {
		this();
		try {
			dos = new DataOutputStream(client.getOutputStream());
			this.name = name;
			send(this.name);
		} catch (IOException e) {
			isRunning = false;
			CloseUtil.closeAll(dos,console);
		}
	}

	/**
	 * Receive messages from console
	 * @return the message from console
	 */
	private String getMsgFromConsole() {
		try {
			return console.readLine();
		} catch (IOException e) {
			//e.printStackTrace();
			return "";
		}
	}

	/**
	 * Send messages to others
	 * @param msg the message which would send to others
	 */
	public void send(String msg) {
		if(null!=msg && !msg.equals("")) {
			try {
				dos.writeUTF(msg);
				dos.flush(); // 强制刷新
			} catch (IOException e) {
				isRunning = false;
				CloseUtil.closeAll(dos,console);
			}
		}
	}
	@Override
	public void run() {
		// Thread Body
		while(isRunning) {
			send(getMsgFromConsole());
		}
	}

}
