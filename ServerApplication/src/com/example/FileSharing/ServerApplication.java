/**
 * 
 */
package com.example.FileSharing;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;

import com.example.FileSharing.Main;

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
			
			//int buf;
			Integer buf;

			try {
				buf = (Integer)ois.readObject();//ois.read();
				System.err.println("Read buf " + buf);
				if (buf == -1) {
					System.err.println("Client has closed");
					selfSocket.close();
					connected = false;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (EOFException e) {
				connected = false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/* Here comes the logic of the server */

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
