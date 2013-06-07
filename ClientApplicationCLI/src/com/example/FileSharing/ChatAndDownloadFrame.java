package com.example.FileSharing;
import java.awt.BorderLayout;
import javax.swing.JFileChooser;

import javax.swing.JFrame;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ChatAndDownloadFrame extends JFrame{
	private JFileChooser jFileChooser1;

	public ChatAndDownloadFrame(String clientName){
		super("Chat "+clientName);
		this.setVisible(true);
		initGUI();
		
	}
	
	private void initGUI() {
		try {
			{
				jFileChooser1 = new JFileChooser();
				getContentPane().add(jFileChooser1, BorderLayout.CENTER);
			}
			{
				this.setSize(356, 180);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
