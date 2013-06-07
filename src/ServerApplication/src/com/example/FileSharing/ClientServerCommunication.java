/**
 * 
 */
package com.example.FileSharing;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Costash
 *
 */
public interface ClientServerCommunication {
	/**
	 * Send information through a Socket
	 * @throws IOException 
	 */
	public void SendInfo(Socket socket, String stream) throws IOException;
	
	/**
	 * Receive info from a Socket
	 * @throws IOException 
	 */
	public String ReceiveInfo(Socket socket) throws IOException;
}
