package com.example.FileSharing;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;

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
@SuppressWarnings("serial")
public class SearchFrame extends JFrame{
	private JPanel jPanel1;
	private JButton searchButton;
	private JList jList1;
	private JScrollPane jScrollPane1;
	private JPanel jPanel2;
	private JTextArea jTextArea1;

	public SearchFrame(){
		super("Search");
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				this.setSize(369, 227);
				this.setVisible(true);
				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1, BorderLayout.NORTH);
					jPanel1.setPreferredSize(new java.awt.Dimension(359, 39));
					{
						jTextArea1 = new JTextArea();
						jPanel1.add(jTextArea1);
						jTextArea1.setText("");
						jTextArea1.setPreferredSize(new java.awt.Dimension(237, 22));
					}
					{
						searchButton = new JButton();
						jPanel1.add(searchButton);
						searchButton.setText("Search");
						searchButton.setLocation(10, 10);
						searchButton.addMouseListener(new MouseListener() {
							
							@Override
							public void mouseReleased(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mousePressed(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mouseExited(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mouseEntered(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mouseClicked(MouseEvent e) {
								// TODO Auto-generated method stub
								System.out.println("Cuvantul cautat este "+jTextArea1.getText());
								
							}
						});
					}
					
				}
				{
					jPanel2 = new JPanel();
					getContentPane().add(jPanel2, BorderLayout.CENTER);
					{
						jScrollPane1 = new JScrollPane();
						getContentPane().add(jScrollPane1, BorderLayout.CENTER);
					}
					{
						ListModel jList1Model = 
								new DefaultComboBoxModel();
						jList1 = new JList();
						jScrollPane1.add(jList1);
						jList1.setModel(jList1Model);
						jList1.setPreferredSize(new java.awt.Dimension(240, 140));
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
