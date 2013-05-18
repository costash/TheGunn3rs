import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket s = null;
		/*if(args.length < 2){
			System.err.println("Usage : ip + port");
		}*/
		try {
			s = new Socket("127.0.0.1", 6969);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
