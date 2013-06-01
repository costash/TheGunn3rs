package com.example.FileSharing;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServerConnection extends Thread {

	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	public static String lock = "srv";
	public static int op_code = 1000;
	public static String client = "null";

	public ServerConnection() {
		try {
			oos = new ObjectOutputStream(Main.connectionSock.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {
		ClientInfo myInfo = new ClientInfo(Main.alias, "127.0.0.1",
				Main.servSock.getLocalPort());
		System.out.println("port: " + Main.servSock.getLocalPort());
		try {
			oos.writeObject(myInfo);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ois = new ObjectInputStream(Main.connectionSock.getInputStream());
		} catch (IOException e) {
			System.err.println("Nu exista inputstream");
			e.printStackTrace();
		}

		while (true) {
			synchronized (lock) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			switch (op_code) {
			case 1001:
				try {
					oos.writeInt(op_code);
					oos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 1002:
				try {
					oos.writeInt(op_code);
					oos.flush();
					oos.writeObject(client);
					oos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				break;
			}

			switch (op_code) {
			case 1001:
				try {
					ArrayList<String> clients = (ArrayList<String>) ois
							.readObject();
					System.out.println(clients.toString());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 1002:
				ClientInfo info = null;
				try {
					info = (ClientInfo) ois.readObject();
					synchronized (Main.peers) {
						Main.peers.put(info.getAlias(), new Info(info.getIp(),
								info.getPort()));
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(info.toString());
				break;

			default:
				break;
			}

		}

	}
}
