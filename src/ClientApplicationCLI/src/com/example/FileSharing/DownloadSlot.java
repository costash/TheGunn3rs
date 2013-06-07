package com.example.FileSharing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class DownloadSlot extends Thread {

	public static String peer = new String();

	public void run() {
		while (true) {
			String peer = null;
			String fname = null;
			int op;
			// asteptam conexiune catre un peer
			synchronized (DownloadSlot.peer) {
				try {
					DownloadSlot.peer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				peer = Main.peer;
				fname = Main.fname;
				op = Main.op;
			}

			Info i = Main.peers.get(peer);
			Socket s = null;
			ObjectOutputStream oos = null;
			ObjectInputStream ois = null;

			try {
				s = new Socket(i.ip, i.port);
				oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeInt(op);
				if (op == 1002)
					oos.writeObject(fname);
				oos.flush();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				ois = new ObjectInputStream(s.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}

			switch (op) {
			case 1001:
				try {
					@SuppressWarnings("unchecked")
					ArrayList<String> files = (ArrayList<String>) ois
							.readObject();
					System.out.println(files.toString());
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				break;
			case 1002:
				try {
					long start = System.nanoTime();
					recvFile(ois);
					long end = System.nanoTime();
					System.out.println("Transfer fisier realizat in "
							+ (end - start)/1000000000 + " secunde");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			case 1003:
				try {
					sendMsg(oos);
					System.out.println(Main.signature+Main.msg);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			default:
				break;
			}

			try {
				ois.close();
				oos.close();
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void recvFile(ObjectInputStream ois) throws IOException {

		String pathname = Main.downFolder + "/" + Main.fname;
		File f = new File(pathname);
		FileOutputStream fos = new FileOutputStream(f);
		long length = ois.readLong();
		System.out.println("File : " + pathname + " dim : " + length);
		byte[] buf = new byte[UploadSlot.MAXSEND];
		long recv = 0;
		while (recv < length) {
			int next = ois.read(buf);
			fos.write(buf, 0, next);
			recv += next;
		}
		System.out.println(recv);
		fos.close();
		
	}
	
	public static void sendMsg(ObjectOutputStream oos) throws IOException{
		oos.writeObject(Main.signature+Main.msg);
	}
}
