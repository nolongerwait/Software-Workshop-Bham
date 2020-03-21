package application.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import application.utils.CloseUtil;

/**
 * Receive Class defined the thread receiving the data
 * @author Zetian Qin zxq876
 */
public class Receive implements Runnable{
	// Input Stream
	private DataInputStream dis;
	// Thread Flag
	private boolean isRunning = true;
	
	/**
	 * Empyt Constructor
	 */
	public Receive() {
	}

	/**
	 * Constructor for Receive class
	 * @param client the client socket
	 */
	public Receive(Socket client){
		try {
			dis = new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			//e.printStackTrace();
			isRunning = false;
			CloseUtil.closeAll(dis);
		}
	}
	/**
	 * Receiving the Data
	 * @return the msg received.
	 */
	public String receive(){
		String msg = "";
		try {
			msg = dis.readUTF();
		} catch (IOException e) {
			isRunning = false;
			CloseUtil.closeAll(dis);
		}
		return msg;
	}
	@Override
	public void run() {
		// Thread Body
		while(isRunning) {
			System.out.println(receive());
		}
	}

}
