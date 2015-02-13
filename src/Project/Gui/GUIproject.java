package Project.Gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Project.Agent.AgentBdd;
public class GUIproject extends JFrame implements ActionListener {
	//devlaraion des variables
	    private JFrame frame ;
	    private JScrollPane scroll;
		private JTextArea textArea;
		private PrintStream prints;
		private PrintStream outs;
		private JButton buttonStart;
		private JButton buttonExit;
		private JPanel panel;
		private Container container;
		
		private AgentBdd agentBdd;
		
		
public GUIproject(AgentBdd agentBdd) {
 
	       super(agentBdd.getLocalName());
	      this.agentBdd=agentBdd;
	        
	
			// instantiation des objets
			frame = new JFrame("University");
			container= frame.getContentPane();
			textArea = new JTextArea();
			panel = new JPanel();
			scroll = new JScrollPane(textArea);
			buttonStart= new JButton("Start");
			buttonExit= new JButton("Exit ");
			
		
			
			//
			container.add(panel);
			panel.setLayout(null);
			
			//frame
	        frame.setSize(940,640);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.setResizable(false);
			panel.setBackground(Color.LIGHT_GRAY);
		    frame.add(panel);
			
		
			//button start
			buttonStart.setBounds(10,0, 100, 30);
			buttonStart.setBackground(Color.WHITE);
			panel.add(buttonStart);
			
			//button exit
			buttonExit.setBounds(10,40,100,30);
			panel.add(buttonExit);
			
            //textArea
			textArea.setEditable(false);
			textArea.setFont(new Font("Segoe UI", Font.ITALIC, 12));
			textArea.setForeground(Color.WHITE);
			textArea.setBackground(Color.BLACK);
		    textArea.setEnabled(false);
            //Scrollpanel	      
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        scroll.setPreferredSize(new Dimension(100, 250));
	        scroll.setBounds(10,400,910,200);
	        panel.add(scroll);
		
			//Deviation de flux
			prints = new PrintStream(new MyOutputstream(textArea));
			outs= System.out;
			System.setOut(prints);
			System.setErr(prints);
			
			//ActionListner
			buttonStart.addActionListener(this);
			buttonExit.addActionListener(this);
			
			addWindowListener(new	WindowAdapter() {
				public void windowClosing(WindowEvent e) {
				agentBdd.doDelete();
					}} );}

public void actionPerformed(ActionEvent e) {
	if(e.getSource()==buttonStart){
		System.out.println("Une action va etre effectuer !");}
	
	if(e.getSource()==buttonExit){
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.dispose();
	}
}
}
