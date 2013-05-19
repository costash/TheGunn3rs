/**
 * 
 */
package com.example.FileSharing;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Costash
 * 
 */
public class Main {
	public static HashSet<Socket> clientList = new HashSet<Socket>();
	public static HashMap<Socket, ObjectInputStream> inputStreams = new HashMap<Socket, ObjectInputStream>();

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
			try {
				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
				inputStreams.put(client, ois);
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
