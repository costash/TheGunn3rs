/**
 * 
 */
package com.example.FileSharing;

/**
 * @author Costash
 *
 */
public class MessageType {
	private static final Integer InvalidMessage = 0;
	private static final Integer ListOfClients = 1;
	private static final Integer ClientInfo = 2;
	private static final Integer FileRequest = 3;
	private static final Integer FileResponse = 4;
	
	public static Integer getMessageType(MessageTypeEnum type) {
		switch (type) {
		case LIST_OF_CLIENTS:
			return ListOfClients;
		case CLIENT_INFO:
			return ClientInfo;
		case FILE_REQUEST:
			return FileRequest;
		case FILE_RESPONSE:
			return FileResponse;
		default:
			return InvalidMessage;
		}
	}
}
