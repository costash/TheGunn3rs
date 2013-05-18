import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

class Gui extends JFrame{
	public DefaultListModel model1;
	public Gui(){
		super();
		
		/*Bara de meniu BEGIN*/

		JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenu ViewMenu = new JMenu("View");
        JMenu helpMenu = new JMenu("Help");
        JMenu optiuniMenu = new JMenu("Optiuni");
        menuBar.add(fileMenu);
        menuBar.add(ViewMenu);
        menuBar.add(optiuniMenu);
        menuBar.add(helpMenu);
        JMenuItem exitAction = new JMenuItem("Exit");
        fileMenu.add(exitAction);
        
        final JCheckBoxMenuItem FinishedUploads = new JCheckBoxMenuItem("FinishedUploads");
        final JCheckBoxMenuItem FinishedDownloads = new JCheckBoxMenuItem("FinishedDownloads");
        
        ViewMenu.add(FinishedUploads);
        ViewMenu.addSeparator();
        ViewMenu.add(FinishedDownloads);
        FinishedUploads.setSelected(false);
        FinishedDownloads.setSelected(false);
                
        JMenuItem infoAction = new JMenuItem("Despre...");
        helpMenu.add(infoAction);		
        
        exitAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);//iesire la apasarea butonului exit din meniu
            }
        });
        infoAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(new JFrame(),"Made by TheGunn3rs","Despre program",
                								JOptionPane.PLAIN_MESSAGE);//informatii despre program
            }
        });
        FinishedUploads.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (FinishedUploads.isSelected()) {
                	 //afisare fisiere up
                }	
                if (!FinishedUploads.isSelected()) {
                	 //oprire afisare fisiere up
                }
            }
        });
        FinishedDownloads.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (FinishedDownloads.isSelected()){ 
                     //afisare fisiere down
                	
                }
                if (!FinishedDownloads.isSelected()){              
                	//oprire afisare fisiere down
                }
            }
        });
/*Bara de meniu END*/
           
      	
      	JPanel Mesaj=new JPanel();
      	JPanel ToConnect=new JPanel();
      	JPanel Stare=new JPanel();
      	JTextField tf=new JTextField(30);
           	
      	
      	/*PANOU CAUTARE*/
      	JPanel JSearch = new JPanel();
      	JLabel LabelSearch=new JLabel("Cautare:");
      	JTextField SearchText = new JTextField(15);
      	JButton butSearch = new JButton("Search");
      	
      //	butSearch.setSize(5, 3);
      	
      	JSearch.add(LabelSearch);
      	JSearch.add(SearchText);
      	JSearch.add(butSearch);
      	JSearch.setLayout(new FlowLayout());
      	
      	/*PANOU USER CONECT*/
      	JPanel JMessageSend=new JPanel();
      	JLabel labelmes=new JLabel("Scrie mesajul:");
      	JButton SendMessage = new JButton("Send");
      	JTextField Message = new JTextField(15);
      	JMessageSend.add(labelmes);
      	JMessageSend.add(Message);
      	JMessageSend.add(SendMessage);
      	
      	
      	/*PANOU REZULTATE*/
      	JPanel Results=new JPanel();
      	JList lst = new JList();
        model1 = new DefaultListModel();
        lst.setModel(model1);
        lst.setSize(100, 100);
        model1.addElement("PETRESCU  Rares");
      	Results.add(lst);
      	
      	/*PANOU Userlist*/
      	JPanel JUser = new JPanel();
      	JList lstuser = new JList();
        model1 = new DefaultListModel();
        lstuser.setModel(model1);
        lstuser.setSize(100, 100);
        model1.addElement("PETRESCU  Rares");
      	JUser.add(lstuser);
      	
      	/*PANOU Mesaje*/
      	JPanel JMesReceived = new JPanel();
      	JList lstmsg = new JList();
        model1 = new DefaultListModel();
        lstmsg.setModel(model1);
        lstmsg.setSize(100, 100);
        model1.addElement("PETRESCU  Rares");
        JMesReceived.add(lstmsg);
      	
        /*PANOU Jos*/
      	JPanel JDown = new JPanel();
      	JButton jos = new JButton("sgfsg");
      	JDown.add(jos);
        
      	
      	
      	JSplitPane JUpLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT, JSearch, JMessageSend);
      	JSplitPane JUpRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT, JUser, JMesReceived);
      	JSplitPane JUpCenter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, JUpLeft, Results);
      	JSplitPane JUpAll = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, JUpCenter, JUpRight);
      	JSplitPane JFinalPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, JUpAll,JDown);
      	
      	
        add(JFinalPane);
        
      //  add(JUpCenter,BorderLayout.CENTER);
        //add(JMessageSend);

		try {
			UIManager.setLookAndFeel (
				UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {}
		pack();
		setVisible(true);

	}
		
	
	public static void main(String arg[]){
		JFrame jf= new Gui();
                jf.setLocation(400, 200);
                jf.getSize();
                jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                jf.setExtendedState(MAXIMIZED_BOTH);
                
                
	}
}