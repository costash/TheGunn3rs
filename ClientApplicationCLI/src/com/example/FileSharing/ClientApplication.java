package com.example.FileSharing;

import java.io.IOException;

/**
 * Clasa ce va primi conexiuni in acceptiunea p2p
 * adica de la alti clienti gen cereri pentru lista de fisiere 
 * ori de download pentru un fisier
 * @author laur
 *
 */
public class ClientApplication extends Thread{

	boolean connected = false;
	static int slot = 0;
	
	public ClientApplication(){
		connected = true;
	}
	
	public void run(){
		while(connected){
			synchronized (Main.connNotif[slot]) {
				try {
					Main.connNotif[slot] = Main.servSock.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Main.connNotif[slot].notify();
				slot++;
				if(slot == 5)
					slot = 0;
			}
		}
		
	}
}
