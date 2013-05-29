package com.example.FileSharing;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class DownloadSlot extends Thread{
	
	private int slot_id;
	private Socket self;

	public DownloadSlot(int id){
		this.slot_id = id;
		this.self = null;
	}
	
	public void run(){
		while(true){
			synchronized (Main.downslot[slot_id]) {
				try {
					Main.downslot[slot_id].wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			Info i = Main.peers.get(Main.downslot[slot_id]);
			
			try {
				self = new Socket(i.ip,i.port);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	public void recvFile(ObjectInputStream ois){
		
	}
}
