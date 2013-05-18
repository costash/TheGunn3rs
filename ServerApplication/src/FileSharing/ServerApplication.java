/**
 * 
 */
package FileSharing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;

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
			InputStream is = null;
			ObjectInputStream oin = null;
			try {
				is = selfSocket.getInputStream();
			} catch (IOException e) {
				System.out.println("Could not read from stream from " + selfSocket.toString());
				e.printStackTrace();
			}
			try {
				oin = new ObjectInputStream(is);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
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
