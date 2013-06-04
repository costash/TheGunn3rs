package com.example.FileSharing;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

	public static final int MAXSLOTS = 5;
	public static int slots;
	public static ServerSocket servSock = null;
	public static String[] filelist = null;
	public static HashSet<String> waitingFiles = null;
	public static Socket connectionSock = null;
	public static String notifier = new String("notify");
	public static String folder = null;
	public static ArrayList<String> sharedFiles = null;
	public static ArrayList<String> allClients = null;
	public static String alias = null;
	public static String servnotify = new String("servnotify");
	public static String peer = new String();
	public static int op = 1000;
	public static String fname;
	public static HashMap<String, Info> peers = new HashMap<String, Info>();
	public static String downFolder;
	public static String msg;
	public static String signature;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//(new Gui()).run();

		if (args.length < 3) {
			System.err.println("Usage : ip + port + slots");
			System.exit(1);
		}

		(new CommandsParser()).start();
		synchronized (notifier) {
			try {
				notifier.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		(new ServerConnection("127.0.0.1")).start();

		slots = Integer.parseInt(args[2]);
		if (slots > MAXSLOTS)
			slots = MAXSLOTS;

		for (int i = 0; i < slots; i++) {
			(new UploadSlot()).start();
			(new DownloadSlot()).start();
		}
	}

}

class Info {
	public String ip;
	public int port;

	Info(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
}
