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
	private String ip = null;
	public static String[] crtClients = null;

	public ServerConnection(String ip) {
		try {
			oos = new ObjectOutputStream(Main.connectionSock.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.ip = ip;

	}
	
	public String GetIp() {
		return this.ip;
	}

	public void run() {
		ClientInfo myInfo = new ClientInfo(Main.alias, this.ip,
				Main.servSock.getLocalPort());
		System.out.println("port: " + Main.servSock.getLocalPort());
		try {
			oos.writeObject(myInfo);
			oos.flush();
		} catch (IOException e) {
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
					e.printStackTrace();
				}
			}

			switch (op_code) {
			case 1001:
				try {
					oos.writeInt(op_code);
					oos.flush();
				} catch (IOException e) {
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
					e.printStackTrace();
				}
				break;
			default:
				break;
			}

			switch (op_code) {
			case 1001:
				try {
					@SuppressWarnings("unchecked")
					ArrayList<String> clients = (ArrayList<String>) ois
							.readObject();
					System.out.println(clients.toString());
					Main.allClients = clients;
					if(Main.gui){
						synchronized (Gui.usrListNotif) {
							Gui.usrListNotif.notify();
						}
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
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
					e1.printStackTrace();
				} catch (IOException e1) {
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
