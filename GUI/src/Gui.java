import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Gui extends JFrame{
	public DefaultListModel modelmsg;
	public DefaultListModel modelres;
	public DefaultListModel modeluser;
	final JList lstuser = new JList();
	final JList lstmsg = new JList();
	final JList lstres = new JList();
  	final JTextField Message = new JTextField(15);
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
      	
      	/*PANOU MESAJE*/
      	JPanel JMessage=new JPanel();
      	JLabel labelmes=new JLabel("Scrie mesajul:");
      	JButton SendMessage = new JButton("Send");
      	
      	JMessage.add(labelmes);
      	JMessage.add(Message);
      	JMessage.add(SendMessage);  	
        modelmsg = new DefaultListModel();
        lstmsg.setModel(modelmsg);
        lstmsg.setSize(500, 500);
        lstmsg.getAutoscrolls();
        JMessage.add(lstmsg);
      	
      	/*PANOU REZULTATE*/
      	JPanel Results=new JPanel();
      	
        modelres = new DefaultListModel();
        lstres.setModel(modelres);
        lstres.setSize(100, 100);
        modelres.addElement("PETRESCU  Rares");
      	Results.add(lstres);
      	
      	/*PANOU Userlist*/
      	JPanel JUser = new JPanel();
      	
        modeluser = new DefaultListModel();
        lstuser.setModel(modeluser);
        lstuser.setSize(100, 100);
        modeluser.addElement("PETRESCU  Rares");
      	JUser.add(lstuser);
      	
      	
      	
        /*PANOU Jos*/
      	JPanel JDown = new JPanel();
      	JButton jos = new JButton("sgfsg");
      	JDown.add(jos);
        
      	
      	
      	JSplitPane JUpLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT, JSearch, JMessage);
      //	JSplitPane JLeftDown = new JSplitPane(JSplitPane.VERTICAL_SPLIT, JUser, JMesReceived);
      	JSplitPane JUpCenter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, JUpLeft, Results);
      	JSplitPane JUpAll = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, JUpCenter, JUser);
      		JUpAll.setResizeWeight(0.75);
      		JUpAll.setOneTouchExpandable(true);
      		JUpAll.setContinuousLayout(true);
      	JSplitPane JFinalPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, JUpAll,JDown);
      		JFinalPane.setResizeWeight(0.75);
      		JFinalPane.setOneTouchExpandable(true);
      		JFinalPane.setContinuousLayout(true);
      	
        add(JFinalPane);
        
        /*Trimitere mesaje*/
        SendMessage.addActionListener(new AddButtonListenerM());
        
        /*Cautare fisiere*/
        butSearch.addActionListener(new AddButtonListenerSearch());
        

		try {
			UIManager.setLookAndFeel (
				UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {}
		pack();
		setVisible(true);

	}
	
	class AddButtonListenerM implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (Message.getText().equals("")) {
               return;
            }
 
            int index = lstmsg.getSelectedIndex();
            modelmsg.insertElementAt(Message.getText(), index+1);
            lstmsg.setSelectedIndex(index+1);
            
            
        }
    }
	
	class AddButtonListenerSearch implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (Message.getText().equals("")) {
               return;
            }
 
            int index = lstmsg.getSelectedIndex();
            modelmsg.insertElementAt(Message.getText(), index+1);
            lstmsg.setSelectedIndex(index+1);
            
            
        }
    }
	
	public static void main(String arg[]){
		JFrame jf= new Gui();
                jf.setLocation(400, 200);
                jf.getSize();
                jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                jf.setExtendedState(MAXIMIZED_BOTH);
                
                
	}
}