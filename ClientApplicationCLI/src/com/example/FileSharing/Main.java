/**
 * 
 */
package com.example.FileSharing;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream.GetField;
import java.net.Socket;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket s = null;
		/*
		 * if(args.length < 2){ System.err.println("Usage : ip + port"); }
		 */
		try {
			s = new Socket("127.0.0.1", 6969);
			System.err.println("Connected to server");
		} catch (IOException e) {
			e.printStackTrace();
		}

		ObjectOutputStream oos = null;
		//ObjectInputStream ois = null;
		try {
			System.err.println("Creating oos");
			oos = new ObjectOutputStream(s.getOutputStream());
			System.err.println("Created oos");
			//oos.flush();
			//ois = new ObjectInputStream(s.getInputStream());
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
		try {
			s.close();
			System.err.println("Client closed connection");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
