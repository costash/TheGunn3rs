/**
 * 
 */
package FileSharing;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;

/**
 * @author Costash
 *
 */
public interface ServerApplicationInterface {
	
	/**
	 * Disconnects a client
	 * @param socketClient The client to be disconnected
	 */
	public void DisconnectClient(Socket socketClient);
	
	/**
	 * Sends the list of clients connected to the server to a given client
	 * @param socketClient client to whom to send list of clients
	 * @param clients The list of clients
	 * @throws IOException 
	 */
	public void SendListOfClients(Socket socketClient,
			HashSet<Socket> clients) throws IOException;
	
	/**
	 * Sends info of a client to another client
	 * @param socketClient The client to whom to send info
	 * @param info The info of a client, that must be sent
	 * @throws IOException 
	 */
	public void SendClientInfo(Socket socketClient, Socket info) throws IOException;
}
