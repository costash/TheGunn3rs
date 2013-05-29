/**
 * 
 */
package com.example.FileSharing;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream
import java.net.ServerSocket;
import java.io.ObjectInputStream.GetField;
import java.net.Socket;
import java.util.HashSet;

public class Main {

	public static final int MAXSLOTS = 5;
	public static final int P2PServPort = 10001;
	public static int slots;
	public static ServerSocket servSock = null;
	public static String[] filelist = null;
	public static HashSet<String> waitingFiles = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length < 3){
			System.err.println("Usage : ip + port + slots");
			System.exit(1);
		}
		
		Socket s = null;
		String serverIP = args[0];
		int serverPort = Integer.parseInt(args[1]);

		try {
			s = new Socket(args[0], Integer.parseInt(args[1]));
			System.err.println("Connected to server");
			servSock = new ServerSocket(P2PServPort);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		slots = Integer.parseInt(args[2]);
		if (slots > MAXSLOTS)
			slots = MAXSLOTS;

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Integer type = MessageType.getMessageType(MessageTypeEnum.CLIENT_INFO);
			System.err.println("Sending message type " + type);
			//oos.write(42);
			oos.writeObject(type);
			
			oos.flush();
			System.err.println("Written message type " + type);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			ClientInfo cInfo = new ClientInfo("192.168.0.1", 8888);
			System.err.println("Sending clientInfo " + cInfo);
			oos.writeObject(cInfo);
			System.err.println("Sent clientInfo " + cInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		

		for (int i = 0; i < slots; i++){
			(new UploadSlot()).start();
			(new DownloadSlot()).start();
		}
	}

}
