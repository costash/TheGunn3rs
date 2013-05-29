package com.example.FileSharing;

import java.io.Serializable;

public class ClientInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ip;
	private int port;
	
	ClientInfo(String ip, int port) {
		this.setIp(ip);
		this.setPort(port);
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public String toString() {
		return "{ " + this.getIp() + ":" + this.getPort() + " }";
	}

}
