package com.example.FileSharing;

import java.io.Serializable;

public class ClientInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String alias;
	private String ip;
	private int port;
	
	ClientInfo(String alias,String ip, int port) {
		this.setIp(ip);
		this.setPort(port);
		this.setAlias(alias);
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
		return "{ " + this.getAlias() +" on "+ this.getIp() + ":" + this.getPort() + " }";
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
