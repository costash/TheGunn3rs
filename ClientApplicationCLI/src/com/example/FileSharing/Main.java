package com.example.FileSharing;

import java.io.IOException;
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
	public static boolean gui = false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		
		if(args.length > 1 || (args.length == 1 && !args[0].equals("-gui"))){
			System.err.println("Usage : [/-gui]");
			System.exit(1);
		}
		try {
			servSock = new ServerSocket(0);
		} catch (IOException e1) {
			System.err.println("Cannot bind any port");
			e1.printStackTrace();
		}
		if(args.length == 1){
			gui = true;
			(new Gui()).run();//daca aplicatia este pornita cu -gui
		}
		else
			(new CommandsParser()).start();
		
		synchronized (notifier) {
			try {
				notifier.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		(new ServerConnection("127.0.0.1")).start();//ne conectam la un server,de modif partea cu 127....

		for (int i = 0; i < MAXSLOTS; i++) {
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
