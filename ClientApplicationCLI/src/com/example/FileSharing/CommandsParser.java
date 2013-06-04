package com.example.FileSharing;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parserul de comenzi util pentru folosirea in linie de comanda necesar pentru
 * GUI
 * 
 * @author laur
 * 
 */
public class CommandsParser extends Thread {

	private Scanner input = null;

	public CommandsParser() {
		input = new Scanner(System.in);
		Main.sharedFiles = new ArrayList<String>();
	}

	public void run() {
		String com = null;
		while (true) {
			System.out.print(Main.alias + "> ");
			com = input.next();

			if (com.equals("alias")) {
				Main.alias = new String(input.next());
				Main.signature = new String(Main.alias + " says: ");
				continue;
			}

			if (com.equals("me")) {
				System.out.println(Main.alias);
				continue;
			}

			if (com.equals("upfold")) {
				Main.sharedFiles.clear();
				Main.folder = new String(input.next());
				File folderName = new File(Main.folder);
				File[] files = folderName.listFiles();
				for (int i = 0; i < files.length; i++)
					Main.sharedFiles.add(files[i].getName());
				continue;
			}

			if (com.equals("dfold")) {
				Main.downFolder = input.next();
				continue;
			}

			if (com.equals("upview")) {
				for (int i = 0; i < Main.sharedFiles.size(); i++) {
					System.out.println(Main.sharedFiles.get(i));
				}
				continue;
			}

			if (com.equals("sock")) {
				try {
					Main.servSock = new ServerSocket(0);
					System.out.println("Client waits connection on port "
							+ Main.servSock.getLocalPort());
				} catch (IOException e) {
					e.printStackTrace();
				}
				continue;
			}

			if (com.equals("connect") | com.equals("co")) {
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

			if (com.equals("msg")) {
				Main.peer = input.next();
				Main.msg = input.nextLine();
				Main.op = 1003;
				synchronized (DownloadSlot.peer) {
					DownloadSlot.peer.notify();
				}

			}

			if (com.equals("srvcli")) {
				// deblocam serverconnection si trimitem cerere la server
				ServerConnection.op_code = 1001;
				synchronized (ServerConnection.lock) {
					ServerConnection.lock.notify();
				}
				continue;
			}

			if (com.equals("infocli")) {
				ServerConnection.op_code = 1002;
				ServerConnection.client = input.next();
				synchronized (ServerConnection.lock) {
					ServerConnection.lock.notify();
				}
				continue;
			}

			if (com.equals("send_filelist")) {

				continue;
			}
			if (com.equals("search")) {
				String fname = input.next();
				System.out.println(fname);
				continue;
			}

			if (com.equals("download")) {
				String fname = input.next();
				String clientname = input.next();
				Main.peer = clientname;
				Main.fname = fname;
				Main.op = 1002;

				synchronized (DownloadSlot.peer) {
					DownloadSlot.peer.notify();
				}
				continue;
			}

			if (com.equals("uplist")) {
				String clientname = input.next();
				Main.peer = clientname;
				Main.op = 1001;
				synchronized (DownloadSlot.peer) {
					DownloadSlot.peer.notify();
				}
				continue;
			}

			if (com.equals("disc")) {
				// TODO send disconnect msg to server
			}

			if (com.equals("exit") || com.equals("quit")) {
				System.out.println("Ciao!!!");
				System.exit(0);
			}
		}

	}
}
