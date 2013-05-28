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
	
	public static MessageTypeEnum getMessageTypeEnum(Integer type) {
		if (type.compareTo(InvalidMessage) == 0) {
			return MessageTypeEnum.INVALID_MESSAGE;
		}
		else if (type.compareTo(ListOfClients) == 0) {
			return MessageTypeEnum.LIST_OF_CLIENTS;
		}
		else if (type.compareTo(ClientInfo) == 0) {
			return MessageTypeEnum.CLIENT_INFO;
		}
		else if (type.compareTo(FileRequest) == 0) {
			return MessageTypeEnum.FILE_REQUEST;
		}
		else if (type.compareTo(FileResponse) == 0) {
			return MessageTypeEnum.FILE_RESPONSE;
		}
		else
			return MessageTypeEnum.INVALID_MESSAGE;
	}
}
