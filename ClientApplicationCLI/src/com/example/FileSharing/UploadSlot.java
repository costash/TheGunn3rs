package com.example.FileSharing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UploadSlot extends Thread {

	private static final int MAXSEND = 1000000;
	Socket self;
	private int slot_id;

	public UploadSlot(int id) {
		self = null;
		this.slot_id = id;
	}

	public void run() {
		while (true) {
			synchronized (Main.connNotif[slot_id]) {
				try {
					Main.connNotif[slot_id].wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			self = Main.connNotif[slot_id];
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
			case 1001:
				String fname = null;
				try {
					fname = (String) ois.readObject();
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
			case 1002:
				try {
					sendFileList(oos);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;

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
		long length = f.length();
		FileInputStream fis = new FileInputStream(f);
		long sent = 0;
		byte[] buf = new byte[MAXSEND];
		try {
			oos.writeLong(length);

			while (sent < length) {
				fis.read(buf);
				oos.write(buf);
				sent += MAXSEND;
			}

			oos.writeInt(-1);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void sendFileList(ObjectOutputStream oos) throws IOException {
		oos.writeObject(Main.sharedFiles);
	}
	

}
