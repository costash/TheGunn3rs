/**
 * 
 */
package com.example.FileSharing;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import com.example.FileSharing.Main;

/**
 * @author Costash
 * 
 */
public class ServerApplication extends Thread {

	private Socket selfSocket = null;
	private boolean connected = false;
	private String alias = null;

	public ServerApplication(Socket client) {
		selfSocket = client;
		connected = true;
	}

	@Override
	public void run() {
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(selfSocket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(selfSocket.getInputStream());
			ClientInfo info = (ClientInfo) ois.readObject();

			alias = info.getAlias();

			synchronized (Main.clients) {
				Main.clients.add(alias);
			}

			synchronized (Main.pairing) {
				Main.pairing.put(alias, new Info(info.getIp(), info.getPort()));
			}

			System.out.println(info.toString());
		} catch (IOException | ClassNotFoundException e2) {
			e2.printStackTrace();
		}

		while (connected) {
			int code = 1000;
			String clientRequest = new String();
			try {
				code = ois.readInt();

				if (code == 1002)
					clientRequest = (String) ois.readObject();

			} catch (SocketException e) {
				dispatchClient();
			} catch (IOException e) {
				dispatchClient();
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			switch (code) {
			case 1001:
				try {
					oos.writeObject(Main.clients.clone());
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 1002:
				Info i = Main.pairing.get(clientRequest);
				try {
					oos.writeObject(new ClientInfo(clientRequest, i.ip, i.port));
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}

		}
	}

	private void dispatchClient() {
		connected = false;
		synchronized (Main.pairing) {
			Main.pairing.remove(alias);
		}
		synchronized (Main.clients) {
			Main.clients.remove(alias);
		}
		System.err.println("Client " + alias + " has closed connection");
		System.err.println("Current list of clients " + Main.clients);
	}
}
