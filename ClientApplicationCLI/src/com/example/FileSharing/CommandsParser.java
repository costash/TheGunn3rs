package com.example.FileSharing;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandsParser extends Thread {

	private Scanner input = null;

	public CommandsParser() {
		input = new Scanner(System.in);
		Main.sharedFiles = new ArrayList<String>();
	}

	public void run() {
		String com = null;
		while (true) {
			System.out.print("laur@laur> ");
			com = input.next();

			if (com.equals("setalias")) {
				Main.alias = new String(input.next());
				continue;
			}

			if (com.equals("alias")) {
				System.out.println(Main.alias);
				continue;
			}

			if (com.equals("setshared")) {
				Main.sharedFiles.clear();
				Main.folder = new String(input.next());
				File folderName = new File(Main.folder);
				File[] files = folderName.listFiles();
				for (int i = 0; i < files.length; i++)
					Main.sharedFiles.add(files[i].getName());
				continue;
			}

			if (com.equals("showshared")) {
				for (int i = 0; i < Main.sharedFiles.size(); i++) {
					System.out.println(Main.sharedFiles.get(i));
				}
				continue;
			}

			if (com.equals("setsock")) {
				int port = Integer.parseInt(input.next());
				try {
					Main.servSock = new ServerSocket(port);
					System.err
							.println("Client wait connection on port " + port);
					System.err.println(Main.servSock.getInetAddress()
							.getHostAddress().toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				continue;
			}

			if (com.equals("connect")) {
				String ip = input.next();
				int port = Integer.parseInt(input.next());
				try {
					Main.connectionSock = new Socket(ip, port);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (Main.notifier) {
					Main.notifier.notify();
				}
				continue;
			}

			if (com.equals("getclients")) {
				// deblocam serverconnection si trimitem cerere la server
				ServerConnection.op_code = 1001;
				synchronized (ServerConnection.lock) {
					ServerConnection.lock.notify();
				}
				continue;
			}

			if (com.equals("getinfo")) {
				ServerConnection.op_code = 1002;
				ServerConnection.client = input.next();
				synchronized (ServerConnection.lock) {
					System.err.println("notificam");
					ServerConnection.lock.notify();
				}
				continue;
			}

			if (com.equals("send_filelist")) {

				continue;
			}
			if (com.equals("search")) {
				String fname = input.next();
				// TODO req all clients + req to clients for their filelist +
				// search

				continue;
			}

			if (com.equals("download")) {
				String fname = input.next();
				String clientname = input.next();
				// TODO in uploadslot fac req file in download slot primesc file

				continue;
			}

			if (com.equals("get_filelist")) {
				String clientname = input.next();
				synchronized (Main.downslot[Main.sid_down]) {
					Main.downslot[Main.sid_down] = clientname;
					Main.downslot[Main.sid_down].notify();
					Main.sid_down++;
					if (Main.sid_down == 5)
						Main.sid_down = 0;
				}
				// TODO in uploadslot req fl in down recv fl
				continue;
			}

			if (com.equals("disconnect")) {
				// TODO send disconnect msg to server
			}

			if (com.equals("exit")) {
				System.out.println("Ciao!!!");
				System.exit(0);
			}
		}

	}
}
