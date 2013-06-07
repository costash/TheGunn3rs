/**
 * 
 */
package com.example.FileSharing;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Costash
 * 
 */
public class Main {
	public static ArrayList<String> clients = new ArrayList<String>();
	public static HashMap<String,Info> pairing = new HashMap<String,Info>();

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

		System.err.println("Server is listening on port " + listeningPort);

		ServerSocket serverSocket = null;
		boolean listening = true;

		try {
			serverSocket = new ServerSocket(listeningPort);
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + listeningPort
					+ ".");
			System.exit(-1);
		}
		
		/* Wait for quit command on separate thread */
		new ReadStdin().start();
		
		/* Listen for new socket connections */
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

			new ServerApplication(client).start();
		}

		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class Info {
	public String ip;
	public int port;
	
	Info(String ip,int port){
		this.ip = ip;
		this.port = port;
	}
}