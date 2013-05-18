/**
 * 
 */
package FileSharing;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 * @author Costash
 * 
 */
public class Main {
	public static HashSet<Socket> clientList = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int listeningPort;

		if (args.length < 1) {
			System.err.println("Usage: port.");
			System.exit(-1);
		}

		listeningPort = Integer.parseInt(args[0]);

		System.err.println("Port is " + listeningPort);

		ServerSocket serverSocket = null;
		boolean listening = true;

		try {
			serverSocket = new ServerSocket(listeningPort);
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + listeningPort
					+ ".");
			System.exit(-1);
		}
		
		clientList = new HashSet<Socket>();

		while (listening) {
			Socket client = null;
			try {
				client = serverSocket.accept();
				System.out.println("Conection with " + client.toString()
						+ " established");
			} catch (IOException e) {
				System.err.println("Cound not accept connection");
				e.printStackTrace();
			}

			clientList.add(client);

			new ServerApplication(client).start();
		}

		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
