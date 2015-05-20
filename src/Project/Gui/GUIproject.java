package Project.Gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Beans;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Project.Agent.AgentController;
import Project.Agent.AgentScolar;
import Project.Metiers.Bean;

public class GUIproject extends JFrame implements ActionListener {
	/**
	 * @author ProBook 450g2
	 * 
	 *         Gui du project
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Declaration des variables
	 */
	// private JFrame this;
	private JScrollPane scroll;
	private JTextArea textArea;
	private PrintStream prints;
	private JButton buttonArriveUniv;
	private JButton buttoinArriverProf;
	private JButton ButtonDepart;
	private JButton buttonAbsProf;
	private JButton buttonGroupeRevision;
	private JButton buttonSendGroupe;
	private JButton buttonChangePlanning;
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
	private final String urlImageUniv3D = "pictures\\univ.png";

	private MoveInClasse moveInClasse;
	private MoveInUniv moveInUniv;
	protected AgentScolar agentScolar;
	private AgentController agentController;
	private Bean bean;

	private JTextField textGroupeevision;

	public GUIproject(final AgentScolar agentScolar) {
		this.agentScolar = agentScolar;
		bean = new Bean();

		/**
		 * Instantiation
		 */
		this.setTitle("Smart University ");
		container = this.getContentPane();
		textArea = new JTextArea();
		panel = new JPanel();
		scroll = new JScrollPane(textArea);
		buttonArriveUniv = new JButton("Entrer");
		ButtonDepart = new JButton("Aller au departement");
		buttoinArriverProf = new JButton("Prof present ");
		buttonGoClasse = new JButton("Strat cours");
		buttonAbsProf = new JButton("Prof ABS");
		buttonGroupeRevision = new JButton("Creer groupe");
		textGroupeevision = new JTextField();
		buttonSendGroupe = new JButton("Send ...");
		buttonChangePlanning = new JButton("Modifier Planning");
		// departement
		panelDepartement = new JPanel();
		panelDepartement.setBounds(1500, 40, 970, 600);
		imageDepartement3D = new JLabel(new ImageIcon(urlImage3Departement3D));
		imageEtudiante = new JLabel(new ImageIcon(urlImgaEtudiant));
		imageEtudiante.setLayout(null);
		imageDepartement3D.setLayout(null);
		imageEtudiante.setBounds(380, 500, 100, 100);
		panelDepartement.setBackground(Color.WHITE);
		panelDepartement.setOpaque(false);
		panelDepartement.add(imageDepartement3D);
		panel.add(imageEtudiante);
		panel.add(panelDepartement);
		panel.setBackground(Color.white);
		// univ
		panelUniv = new JPanel();

		panelUniv.setBackground(Color.white);
		panelUniv.setOpaque(false);
		imageUniv3D = new JLabel(new ImageIcon(urlImageUniv3D));
		panelUniv.add(imageUniv3D);
		panelUniv.setVisible(true);
		panelUniv.setBounds(0, 2, 870, 500);
		panel.add(panelUniv);
		//
		container.add(panel);
		panel.setLayout(null);
		// this
		this.setSize(1360, 640);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		panel.setBackground(Color.white);
		this.add(panel);
		// button start
		buttonArriveUniv.setBounds(1210, 300, 100, 30);
		panel.add(buttonArriveUniv);
		// button exit
		buttoinArriverProf.setBounds(1210, 340, 100, 30);
		panel.add(buttoinArriverProf);
		// bouton bouge
		buttonGoClasse.setBounds(1210, 380, 100, 30);
		panel.add(buttonGoClasse);
		// button Arriver au departement
		ButtonDepart.setBounds(1050, 300, 150, 30);
		panel.add(ButtonDepart);
		// buttonABS
		buttonAbsProf.setBounds(1210, 420, 100, 30);
		panel.add(buttonAbsProf);
		//
		buttonGroupeRevision.setBounds(1050, 340, 150, 30);
		panel.add(buttonGroupeRevision);
		//
		buttonSendGroupe.setBounds(880, 340, 70, 30);
		panel.add(buttonSendGroupe);
		buttonSendGroupe.setVisible(false);

		//
		buttonChangePlanning.setBounds(1050, 380, 150, 30);
		panel.add(buttonChangePlanning);
		// textArea
		textArea.setEditable(false);
		textArea.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 15));

		textArea.setEnabled(false);
		// Scrollpanel
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(100, 250));
		scroll.setBounds(880, 10, 460, 280);
		panel.add(scroll);
		panel.setOpaque(true);
		// textField
		textGroupeevision.setBounds(980, 340, 50, 30);
		panel.add(textGroupeevision);
		textGroupeevision.setVisible(false);

		// Deviation de flux
		prints = new PrintStream(new MyOutputstream(textArea));
		System.setOut(prints);
		System.setErr(prints);
		// ActionListner
		buttonArriveUniv.addActionListener(this);
		buttoinArriverProf.addActionListener(this);
		buttonGoClasse.addActionListener(this);
		ButtonDepart.addActionListener(this);
		buttonAbsProf.addActionListener(this);
		buttonGroupeRevision.addActionListener(this);
		buttonSendGroupe.addActionListener(this);
		buttonChangePlanning.addActionListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.agentController = Bean.getAgentController();

	}

	public GUIproject() {

	}

	public void actionPerformed(ActionEvent e) {
		/**
		 * Arriver a l'universit�
		 */
		if (e.getSource() == buttonArriveUniv) {
			System.out.println("L'etudiant entre dans l'universit� !");

		}
		/**
		 * Boutton les cours commance
		 */
		if (e.getSource() == buttoinArriverProf) {
			System.out.println("Le prof est en classe; le cour commance ....");
			this.agentScolar.setStartCour();
		}
		/**
		 * L'etudiant va en classe
		 */
		if (e.getSource() == buttonGoClasse) {
			System.out.println("l'etudiant va en classe");
			moveInClasse = new MoveInClasse(this);
			moveInClasse.start();
			System.out.println("On attend le prof ... wait ");
		}
		/**
		 * Aller au departement
		 */
		if (e.getSource() == ButtonDepart) {
			moveInUniv = new MoveInUniv(this);
			moveInUniv.start();

			moveTimer();

			// agentScolar.NotifyEntreeInUniv();

		}
		/**
		 * Boutton declanche abscence du prof
		 */
		if (e.getSource() == buttonAbsProf) {
			agentScolar.setProfABS("Le module est chang�");
			System.out.println("Le prof est abscent, vous etes liberes ...");

		}
		// ----------------------------//

		/**
		 * Periode d'exam
		 */
		// ----------------------------//

		/**
		 * perdiode de vaccs
		 */

		// --------------------------//

		/**
		 * Groupe revision
		 */
		if (e.getSource() == buttonGroupeRevision) {
			// agentScolar.setNombreGroupe();
			buttonSendGroupe.setVisible(true);
			textGroupeevision.setVisible(true);
			buttonGroupeRevision.setEnabled(false);

		}
		// -------------------//
		/**
		 * Envoi du groupe
		 */
		if (e.getSource() == buttonSendGroupe) {
			Integer nbr = Integer.parseInt(textGroupeevision.getText());
			if (nbr >= 5) {
				nbr = 4;
				agentScolar.setNombreGroupe(nbr.toString());

			} else
				agentScolar.setNombreGroupe(nbr.toString());

		}
		/**
		 * Lance l'update de planning !
		 */
		// -------------------//
		if (e.getSource() == buttonChangePlanning) {
			new GuiUpdate(bean.getAgentController());
			System.out.println(bean.getAgentController());

		}
		// -------------------//

	}

	/**
	 * Effect move left
	 */

	public void makeUI() {

		new Timer(5, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panelUniv.setLocation(panelUniv.getX() - 3, 0);

				if (panelUniv.getX() + panelUniv.getWidth() == 0) {

					((Timer) e.getSource()).stop();
					panelDepartement.setBounds(5, 40, 970, 600);
					imageEtudiante.setBounds(380, 500, 100, 100);
					System.out.println("Timer stopped");

				}
			}
		}).start();

		/*
		 * new Timer(1/2, new ActionListener() { public void
		 * actionPerformed(ActionEvent e) {
		 * panelDepartement.setLocation(panelDepartement.getX() +1, 1); if
		 * (panelDepartement.getX() + panelDepartement.getWidth() == 0) {
		 * ((Timer) e.getSource()).stop(); System.out.println("Timer stopped");
		 * } } }).start();
		 */

	}

	/**
	 * retarde le mouvement du panel univ
	 */

	private void moveTimer() {

		new Timer(1500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SwingUtilities.invokeLater(new Runnable() {

					public void run() {

						makeUI();

					}
				});

			}
		}).start();
	}
}
