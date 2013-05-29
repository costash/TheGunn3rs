/**
 * 
 */
package com.example.FileSharing;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

import com.example.FileSharing.Main;
import com.example.FileSharing.MessageTypeEnum;
import com.example.FileSharing.MessageType;

/**
 * @author Costash
 * 
 */
public class ServerApplication extends Thread implements
		ServerApplicationInterface, ClientServerCommunication {

	private Socket selfSocket = null;
	private boolean connected = false;

	public ServerApplication(Socket client) {
		selfSocket = client;
		connected = true;
	}

	@Override
	public void run() {
		while (connected) {
			ObjectInputStream ois = Main.inputStreams.get(selfSocket);

			Object buf;

			try {
				/* Get message type */
				buf = ois.readObject();
				Integer messageType = (Integer) buf;
				System.err.println("Message type " + messageType);

				completeRequest(messageType);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (EOFException e) {
				/* Close socket connection */
				System.err.println("Client has closed connection");
				try {
					selfSocket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				connected = false;
			} catch (IOException e) {
				e.printStackTrace();
			}

			/* Here comes the logic of the server */

		}
	}

	private void completeRequest(Integer messageType) {
		switch (MessageType.getMessageTypeEnum(messageType)) {
		case CLIENT_INFO:
			/* Parseaza client */
			parseClientInfoRequest();
			break;

		default:
			break;
		}
	}

	private void parseClientInfoRequest() {
		ObjectInputStream ois = Main.inputStreams.get(selfSocket);

		try {
			ClientInfo cInfo = (ClientInfo) ois.readObject();
			System.err.println("Client info " + cInfo);
			Main.clientInfos.put(selfSocket, cInfo);

		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see FileSharing.ClientServerCommunication#SendInfo(java.net.Socket,
	 * java.io.OutputStream)
	 */
	@Override
	public void SendInfo(Socket socket, String stream) throws IOException {
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.write(stream);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see FileSharing.ClientServerCommunication#ReceiveInfo(java.net.Socket)
	 */
	@Override
	public String ReceiveInfo(Socket socket) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		return in.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see FileSharing.ServerApplicationInterface#DisconnectClient(FileSharing.
	 * ClientApplicationInterface)
	 */
	@Override
	public void DisconnectClient(Socket socketClient) {
		try {
			socketClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		synchronized (Main.clientList) {
			Main.clientList.remove(socketClient);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * FileSharing.ServerApplicationInterface#SendListOfClients(FileSharing.
	 * ClientApplicationInterface, java.util.Collection)
	 */
	@Override
	public void SendListOfClients(Socket socketClient, HashSet<Socket> clients)
			throws IOException {
		OutputStream out = socketClient.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(MessageType
				.getMessageType(MessageTypeEnum.LIST_OF_CLIENTS));
		for (Socket soc : Main.clientList) {
			oos.writeObject(soc.getInetAddress());
			oos.writeObject(new Integer(soc.getPort()));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see FileSharing.ServerApplicationInterface#SendClientInfo(FileSharing.
	 * ClientApplicationInterface, FileSharing.ClientApplicationInterface)
	 */
	@Override
	public void SendClientInfo(Socket socketClient, Socket info)
			throws IOException {
		OutputStream out = socketClient.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(MessageType.getMessageType(MessageTypeEnum.CLIENT_INFO));

		oos.writeObject(info.getInetAddress());
		oos.writeObject(new Integer(info.getPort()));

	}
}
