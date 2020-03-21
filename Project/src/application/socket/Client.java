package application.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client Class create the client socket, which includes the send thread and receive thread.
 * @author Zetian Qin zxq876
 */
public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Please input the username:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name = br.readLine();
		if(name.equals("")) {
			return ;
		}
		
		Socket client = new Socket("localhost", 9999); // IP address and PortID
		
		new Thread(new Send(client,name)).start();
		
		new Thread(new Receive(client)).start();
		
	}
}
