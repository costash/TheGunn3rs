package com.example.FileSharing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UploadSlot extends Thread {

	public static final int MAXSEND = 64000;
	Socket self = null;

	public void run() {
		while (true) {
			synchronized (Main.servSock) {
				try {
					self = Main.servSock.accept();
				} catch (IOException e) {
					System.err.println("Cannot accept connection");
					e.printStackTrace();
				}
			}
			
			System.out.println(self.getLocalPort());
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(self.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(self.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//TODO tratam cazurile de tipuri de mesaje
			int msg_type = 1000;
			try {
				msg_type = ois.readInt();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			
			switch (msg_type) {
			case 1002:
				String fname = null;
				try {
					fname = (String) ois.readObject();
					fname = Main.folder+"/"+fname;
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					sendFile(new File(fname), oos);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 1001:
				try {
					sendFileList(oos);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 1003:
				try {
					System.out.println((String)ois.readObject());;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			default:
				break;
			}
			
			//inchidem streamurile si socketul
			try {
				ois.close();
				oos.close();
				self.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void sendFile(File f, ObjectOutputStream oos)
			throws FileNotFoundException {
		System.err.println("Trimitem fisierul "+f.getName());
		long length = f.length();
		FileInputStream fis = new FileInputStream(f);
		long sent = 0;
		byte[] buf = new byte[MAXSEND];
		try {
			oos.writeLong(length);
			oos.flush();

			while (sent < length) {
				int next = (int) ((sent + MAXSEND > length) ? length - sent : MAXSEND);
				fis.read(buf);
				oos.write(buf, 0, next);
				oos.flush();
				sent += MAXSEND;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void sendFileList(ObjectOutputStream oos) throws IOException {
		oos.writeObject(Main.sharedFiles);
		oos.flush();
	}
}
