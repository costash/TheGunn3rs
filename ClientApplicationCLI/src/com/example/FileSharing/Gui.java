package com.example.FileSharing;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
@SuppressWarnings("serial")
class Gui extends JFrame implements Runnable {
	@SuppressWarnings("rawtypes")
	public DefaultListModel model1, usrListModel;
	public static String usrListNotif = new String();
	private JMenuItem dFold;
	private JMenuItem settingsMenu;
	private JMenu jSearchMenu;
	private JMenuItem detMenu;
	private JMenuItem upFold;
	public JPanel usrPanel = new JPanel();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Gui() {
		super("Gunn3rsDC++");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		JMenu ViewMenu = new JMenu("View");
		JMenu optiuniMenu = new JMenu("Options");
		menuBar.add(fileMenu);
		menuBar.add(ViewMenu);
		menuBar.add(optiuniMenu);
		{
			jSearchMenu = new JMenu();
			menuBar.add(jSearchMenu);
			jSearchMenu.setText("Search");
			jSearchMenu.addMouseListener(new MouseListener() {
				
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
					// SearchFrame sf = new SearchFrame();
					// SettingsFrame sf = new SettingsFrame();
					ChatAndDownloadFrame chat = new ChatAndDownloadFrame(null);
				}
			});
		}
		{
			JMenu helpMenu = new JMenu("Help");
			menuBar.add(helpMenu);
			{
				detMenu = new JMenuItem();
				helpMenu.add(detMenu);
				detMenu.setText("Details");
			}
			{
				JMenuItem infoAction = new JMenuItem("About...");
				helpMenu.add(infoAction);
				infoAction.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane
								.showMessageDialog(
										new JFrame(),
										"TheGunn3rs Project\n"
												+ "Created by:\n\nStudentii de la 333CA\n"
												+ "Constantin Serban-Radoi\n"
												+ "Laurentiu Tuca\n"
												+ "\nSi studentii de la 333CC\n"
												+ "Mihaela Culcus\n"
												+ "Rares Petrescu\n"
												+ "\n�2013 TheGunn3rs",
										"Despre program",
										JOptionPane.PLAIN_MESSAGE);// informatii
						// despre
						// program
					}
				});
			}
		}
		{
			upFold = new JMenuItem();
			optiuniMenu.add(upFold);
			upFold.setText("Select share folder");
		}
		{
			dFold = new JMenuItem();
			optiuniMenu.add(dFold);
			dFold.setText("Select download folder");
		}
		JMenuItem connectAction = new JMenuItem("Connect");
		JMenuItem exitAction = new JMenuItem("Exit");
		fileMenu.add(connectAction);
		{
			settingsMenu = new JMenuItem();
			fileMenu.add(settingsMenu);
			settingsMenu.setText("Settings");
			settingsMenu.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent evt) {
					settingsMenuMouseClicked(evt);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					JFrame set = new JFrame("Settings");
					set.setVisible(true);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}
		fileMenu.add(exitAction);

		/* Listener for the current window */
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {

			}

			@Override
			public void windowIconified(WindowEvent arg0) {

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				dispose();
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				dispose();
				System.exit(0);
			}

			@Override
			public void windowActivated(WindowEvent arg0) {

			}
		});

		final JCheckBoxMenuItem FinishedUploads = new JCheckBoxMenuItem(
				"FinishedUploads");
		final JCheckBoxMenuItem FinishedDownloads = new JCheckBoxMenuItem(
				"FinishedDownloads");

		ViewMenu.add(FinishedUploads);
		ViewMenu.addSeparator();
		ViewMenu.add(FinishedDownloads);
		FinishedUploads.setSelected(false);
		FinishedDownloads.setSelected(false);

		exitAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);// iesire la apasarea butonului exit din meniu
			}
		});

		connectAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFrame frameGetConnectIp = new JFrame("Connect to server");

				JPanel getIpPanel = new JPanel();
				JLabel labelIp = new JLabel(
						"Enter the IP address of the server:");
				JLabel labelPort = new JLabel("Enter port:");
				JButton connectButton = new JButton("Connect");

				final JTextField textIp = new JTextField(30);
				textIp.getText();

				final JTextField textPort = new JTextField(10);
				textPort.getText();

				connectButton.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent arg0) {
						connectServerActivity(frameGetConnectIp, textIp,
								textPort);
					}

					@Override
					public void mouseEntered(MouseEvent arg0) {

					}

					@Override
					public void mouseExited(MouseEvent arg0) {

					}

					@Override
					public void mousePressed(MouseEvent arg0) {

					}

					@Override
					public void mouseReleased(MouseEvent arg0) {

					}

				});

				connectButton.addKeyListener(new KeyListener() {

					@Override
					public void keyTyped(KeyEvent arg0) {
					}

					@Override
					public void keyReleased(KeyEvent arg0) {

					}

					@Override
					public void keyPressed(KeyEvent arg0) {
						if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
							System.err.println("enter pressed on button");
							connectServerActivity(frameGetConnectIp, textIp,
									textPort);
						}
					}
				});

				textPort.addKeyListener(connectButton.getKeyListeners()[0]);

				getIpPanel.add(labelIp);
				getIpPanel.add(textIp);
				getIpPanel.add(labelPort);
				getIpPanel.add(textPort);
				getIpPanel.add(connectButton);

				frameGetConnectIp.add(getIpPanel);
				frameGetConnectIp.setLayout(new FlowLayout());
				frameGetConnectIp.pack();
				frameGetConnectIp.setVisible(true);

			}
		});

		FinishedUploads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (FinishedUploads.isSelected()) {
					// afisare fisiere up
				}
				if (!FinishedUploads.isSelected()) {
					// oprire afisare fisiere up
				}
			}
		});
		FinishedDownloads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (FinishedDownloads.isSelected()) {
					// afisare fisiere down

				}
				if (!FinishedDownloads.isSelected()) {
					// oprire afisare fisiere down
				}
			}
		});

		/* PANOU CAUTARE */

		// butSearch.setSize(5, 3);

		/* PANOU USER CONECT */

		/* PANOU REZULTATE */
		JPanel Results = new JPanel();
		Results.setPreferredSize(new java.awt.Dimension(246, 133));
		JList lst = new JList();
		model1 = new DefaultListModel();
		lst.setModel(model1);
		lst.setSize(100, 100);
		model1.addElement(null);
		Results.add(lst);

		/* PANOU Mesaje */
		JPanel usrListPanel = new JPanel();
		usrListPanel.setPreferredSize(new java.awt.Dimension(358, 165));
		JList lstmsg = new JList();
		model1 = new DefaultListModel();
		lstmsg.setModel(model1);
		lstmsg.setSize(300, 300);

		/* PANOU Userlist */
		JList lstuser = new JList();
		JButton refresh = new JButton("Refresh");
		userListHandle(usrListPanel, lstuser, refresh);

		/* PANOU Jos */
		JPanel JDown = new JPanel();
		JDown.setPreferredSize(new java.awt.Dimension(679, 72));

		JSplitPane JUpRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				usrPanel, usrListPanel);
		JUpRight.setPreferredSize(new java.awt.Dimension(272, 135));
		JSplitPane JUpCenter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				null, Results);
		JUpCenter.setPreferredSize(new java.awt.Dimension(310, 75));
		JSplitPane JUpAll = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				JUpCenter, JUpRight);
		JSplitPane JFinalPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				JUpAll, JDown);

		add(JFinalPane);

		// add(JUpCenter,BorderLayout.CENTER);
		// add(JMessageSend);

		try {
			{
				usrPanel.setPreferredSize(new java.awt.Dimension(261, 32));
			}
			javax.swing.UIManager
					.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
		} catch (Exception e) {
		}
		pack();
		this.setSize(691, 323);
		setVisible(true);

	}

	@Override
	public void run() {

	}

	private void connectToServer(String ip, int port) {
		try {
			Main.servSock = new ServerSocket(0);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		{
			
		}
		try {
			Main.connectionSock = new Socket(ip, port);
		} catch (UnknownHostException e2) {
			e2.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		synchronized (Main.notifier) {
			Main.notifier.notify();
		}
	}

	private boolean checkIpAndPort(String ip, int port) {
		if (port < 1024 || port > 65000)
			return false;

		StringTokenizer st = new StringTokenizer(ip, ".");
		int step = 0;
		while (st.hasMoreTokens()) {
			int tok = Integer.parseInt(st.nextToken());
			if (tok < 0 || tok > 255 || step > 4)
				return false;
			++step;
		}

		return true;
	}

	/**
	 * @param frameGetConnectIp
	 * @param textIp
	 * @param textPort
	 */
	private void connectServerActivity(final JFrame frameGetConnectIp,
			final JTextField textIp, final JTextField textPort) {
		String ip = textIp.getText();
		int port = Integer.parseInt(textPort.getText());
		if (checkIpAndPort(ip, port) == true) {
			connectToServer(ip, port);
			frameGetConnectIp.transferFocus();
			frameGetConnectIp.dispose();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void userListHandle(JPanel usrListPanel, JList usrList,
			JButton refreshButton) {
		usrListModel = new DefaultListModel();
		usrList.setModel(usrListModel);
		usrList.setSize(300, 300);
		getServerUserList(refreshButton);
		usrPanel.add(refreshButton);
		usrListPanel.add(usrList);
	}

	private void getServerUserList(JButton refreshButton) {
		refreshButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@SuppressWarnings("unchecked")
			@Override
			public void mouseClicked(MouseEvent e) {
				ServerConnection.op_code = 1001;
				synchronized (ServerConnection.lock) {
					ServerConnection.lock.notify();
				}
				usrListModel.removeAllElements();
				synchronized (usrListNotif) {
					try {
						usrListNotif.wait();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					;
				}
				for (int i = 0; i < Main.allClients.size(); i++)
					usrListModel.addElement(Main.allClients.get(i));
				pack();
			}
		});
	}

	private void settingsMenuMouseClicked(MouseEvent evt) {
		System.out.println("settingsMenu.mouseClicked, event=" + evt);
		// TODO add your code for settingsMenu.mouseClicked
		System.exit(0);
	}

}
