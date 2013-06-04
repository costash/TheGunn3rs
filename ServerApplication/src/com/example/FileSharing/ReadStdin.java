/**
 * 
 */
package com.example.FileSharing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Costash
 *
 * Reads from stdin waiting for the word "quit" to finish the program
 */
public class ReadStdin extends Thread {

	@Override
	public void run() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			try {
				String line = in.readLine();
				
				if (line.compareTo("quit") == 0) {
					System.out.println("Server is shutting down");
					System.exit(0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
