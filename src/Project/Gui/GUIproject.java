package Project.Gui;

import jade.core.behaviours.OneShotBehaviour;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Project.Agent.AgentBdd;
import Projet.Bdd.StartBdd;

public class GUIproject extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// devlaraion des variables
	private JFrame frame;
	private JScrollPane scroll;
	private JTextArea textArea;
	private PrintStream prints;
	private JButton buttonStart;
	private JButton buttonExit;
	private JPanel panel;
	private Container container;
	private JButton buttonMove;

	// image
	private JPanel panelDepartement;
	private JLabel imageDepartement3D;
	protected JPanel panelUniv;
	private JLabel imageUniv3D;
	protected JLabel imageEtudiante;

	// url image
	private final String urlImage3Departement3D = "pictures\\Depart3DTrans2.png";
	private final String urlImgaEtudiant = "pictures\\etudiante.png";
	private final String urlImageUniv3D  ="pictures\\univ.jpg";

	private DoMove doMove;
	

	

	public GUIproject(OneShotBehaviour oneShotBehaviour) {

		//super(oneShotBehaviour.getLocalName());
		// instantiation des objets
		frame = new JFrame("University");
		container = frame.getContentPane();
		textArea = new JTextArea();
		panel = new JPanel();
		scroll = new JScrollPane(textArea);
		buttonStart = new JButton("Start");
		buttonExit = new JButton("Exit ");
		buttonMove = new JButton("Bouge");
		// departement
		panelDepartement = new JPanel();
		panelDepartement.setBounds(5, 40, 970, 600);
		imageDepartement3D = new JLabel(new ImageIcon(urlImage3Departement3D));
		imageEtudiante = new JLabel(new ImageIcon(urlImgaEtudiant));
		imageEtudiante.setLayout(null);
		imageDepartement3D.setLayout(null);
		imageEtudiante.setBounds(380, 500, 100, 100);
		panelDepartement.setBackground(Color.LIGHT_GRAY);
		panelDepartement.setOpaque(false);
		panelDepartement.add(imageDepartement3D);
		panel.add(imageEtudiante);
		panel.add(panelDepartement);
		//univ
		panelUniv = new JPanel();
		
		panelUniv.setBackground(Color.LIGHT_GRAY);
		panelUniv.setOpaque(false);
		imageUniv3D = new JLabel(new ImageIcon(urlImageUniv3D));
		panelUniv.add(imageUniv3D);
		panel.add(panelUniv);
		//
		container.add(panel);
		panel.setLayout(null);
		// frame
		frame.setSize(1350, 700);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		panel.setBackground(Color.LIGHT_GRAY);
		frame.add(panel);
		// button start
		buttonStart.setBounds(1210, 300, 100, 30);
		panel.add(buttonStart);
		// button exit
		buttonExit.setBounds(1210, 340, 100, 30);
		panel.add(buttonExit);
		// bouton bouge
		buttonMove.setBounds(1210, 380, 100, 30);
		panel.add(buttonMove);
		// textArea
		textArea.setEditable(false);
		textArea.setFont(new Font("Segoe UI", Font.ITALIC, 12));
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);
		textArea.setEnabled(false);
		// Scrollpanel
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(100, 250));
		scroll.setBounds(880, 10, 460, 280);
		panel.add(scroll);
		panel.setOpaque(true);
		// Deviation de flux
		prints = new PrintStream(new MyOutputstream(textArea));
		System.setOut(prints);
		System.setErr(prints);
		// ActionListner
		buttonStart.addActionListener(this);
		buttonExit.addActionListener(this);
		buttonMove.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
	}
	public GUIproject() {
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonStart) {
			System.out.println("Une action va etre effectuer !");
			panelUniv.setBounds(5, 40, 970, 600);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					
					makeUI();
					
				}
			});
		}
		if (e.getSource() == buttonExit) {
			System.exit(0);
		}
		if (e.getSource() == buttonMove) {
			
			arriverEtudiant();
		}
	}

	private void arriverEtudiant() {
		doMove = new DoMove(this);
		doMove.start();
	}
	public void makeUI() {

		new Timer(5, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDepartement.setLocation(panelDepartement.getX() - 1, 0);
				if (panelDepartement.getX() + panelDepartement.getWidth() == 0) {
					((Timer) e.getSource()).stop();
					System.out.println("Timer stopped");
				}
			}
		}).start();

	}
	
}
