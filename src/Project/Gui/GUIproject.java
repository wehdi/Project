package Project.Gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Project.Agent.AgentInterface;

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
	private JButton buttonChanger;
	private JButton buttoinArriverProf;
	private JButton buttonArriver;
	private JButton buttonAbsProf;
	private JPanel panel;
	private Container container;
	private JButton buttonGoClasse;
	

	// image
	private JPanel panelDepartement;
	
	private JLabel imageDepartement3D;
	protected JPanel panelUniv;
	private JLabel imageUniv3D;
	protected JLabel imageEtudiante;

	// url image
	private final String urlImage3Departement3D = "pictures\\Depart3DTrans2.png";
	private final String urlImgaEtudiant = "pictures\\etudiante.png";
	private final String urlImageUniv3D  ="pictures\\univ.png";

	private DoMove doMove;
	AgentInterface  agentInterfaceInerface;
	

	

	public GUIproject(final AgentInterface agentInterface) {
		this.agentInterfaceInerface = agentInterface;

		//super(oneShotBehaviour.getLocalName());
		// instantiation des objets
		frame = new JFrame("University");
		container = frame.getContentPane();
		textArea = new JTextArea();
		panel = new JPanel();
		scroll = new JScrollPane(textArea);
		buttonChanger = new JButton("Changer lieu");
		buttonArriver = new JButton("Arriver a l'univ");
		buttoinArriverProf = new JButton("Prof present ");
		buttonGoClasse = new JButton("Strat cours");
		buttonAbsProf = new JButton("Prof ABS");
		// departement
		panelDepartement = new JPanel();
		panelDepartement.setBounds(1500, 40, 970, 600);
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
		
		panelUniv.setBackground(Color.black);
		panelUniv.setOpaque(false);
		imageUniv3D = new JLabel(new ImageIcon(urlImageUniv3D));
		panelUniv.add(imageUniv3D);
		panelUniv.setVisible(true);
		panelUniv.setBounds(0, 2, 870, 500);
		panel.add(panelUniv);
		//
		container.add(panel);
		panel.setLayout(null);
		// frame
		frame.setSize(1360, 700);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		panel.setBackground(Color.LIGHT_GRAY);
		frame.add(panel);
		// button start
		buttonChanger.setBounds(1210, 300, 100, 30);
		panel.add(buttonChanger);
		// button exit
		buttoinArriverProf.setBounds(1210, 340, 100, 30);
		panel.add(buttoinArriverProf);
		// bouton bouge
		buttonGoClasse.setBounds(1210, 380, 100, 30);
		panel.add(buttonGoClasse);
		// button Arriver
		buttonArriver.setBounds(1050, 300, 130, 30);
		panel.add(buttonArriver);
		//buttonABS
		buttonAbsProf.setBounds(1210, 420, 100, 30);
		panel.add(buttonAbsProf);
		// textArea
		textArea.setEditable(false);
		textArea.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 15));
		//textArea.setForeground(Color.BLUE);
		//textArea.setBackground(Color.GREEN);
	
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
		buttonChanger.addActionListener(this);
		buttoinArriverProf.addActionListener(this);
		buttonGoClasse.addActionListener(this);
		buttonArriver.addActionListener(this);
		buttonAbsProf.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
	}
	public GUIproject() {
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonChanger) {
			System.out.println("Une action va etre effectuer !");
			//panelUniv.setBounds(300, 40, 1970, 900);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					
					makeUI();
					
				}
			});
		}
		if (e.getSource() == buttoinArriverProf) {
			System.out.println("Le prof est en classe; le cour commance ....");
		}
		if (e.getSource() == buttonGoClasse) {
			System.out.println("l'etudiant va en classe");
			arriverEtudiant();
		}
		if (e.getSource()== buttonArriver) {
			agentInterfaceInerface.dodis();
			
		}
		if (e.getSource()==buttonAbsProf){
			System.out.println("Le prof est abscent, vous etes liberes ...");
			
		}
			
	}

	private void arriverEtudiant() {
		doMove = new DoMove(this);
		doMove.start();
		System.out.println("On attend le prof ... wait ");
	
	}
	
	public void makeUI() {

		new Timer(5, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelUniv.setLocation(panelUniv.getX() - 2, 0);
				
				if (panelUniv.getX() + panelUniv.getWidth() ==0) {
					
					((Timer) e.getSource()).stop();
					panelDepartement.setBounds(5, 40, 970, 600);
					System.out.println("Timer stopped");
					
				}
			}
		}).start();
		
		/*new Timer(1/2, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelDepartement.setLocation(panelDepartement.getX() +1, 1);
				if (panelDepartement.getX() + panelDepartement.getWidth() == 0) {
					((Timer) e.getSource()).stop();
					System.out.println("Timer stopped");
				}
			}
		}).start();*/

	}
	
	
	
	
}
