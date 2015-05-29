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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import Project.Agent.AgentScolar;

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
	private JButton buttonBiblio;
	private JButton buttonStartCours;
	private JButton buttonProf;
	private JButton buttonAbsProf;
	private JButton buttonGroupeRevision;
	private JButton buttonSendGroupe;
	private JButton buttonFinCours;
	private JPanel panel;
	private Container container;
	private JButton buttonGoClasse;

	// image
	private JPanel panelDepartement;

	private JLabel imageDepartement3D;
	protected JPanel panelUniv;
	private JLabel imageUniv3D;
	protected JLabel imageEtudiante;

	protected JLabel imageEtudianteSport;
	protected JLabel imageEtudianteGros;
	protected JLabel imageEtudianteSportif;
	protected JLabel imagetudianteSportif;
	protected JLabel imageEtudianteStudieux;
	protected JLabel imageProfLouche;
	protected JLabel imageProfBilou;
	protected JLabel imageProfSteev;

	// url image
	private final String urlImage3Departement3D = "pictures\\Depart3DTrans2.png";
	private final String urlImgaEtudiant = "pictures\\etudiante.png";
	private final String urlImageUniv3D = "pictures\\univ.png";
	private final String urlImageEtudianteSport = "pictures\\etudiante-sport.png";
	private final String urlImageEtudianteGros = "pictures\\etudiant-gros.png";
	private final String urlImageEtudianteSportif = "pictures\\etudiant-sportif.png";
	private final String urlImageEtudianteStudieux = "pictures\\etudiant-studieux.png";
	private final String urlImageProfLouche = "pictures\\prof-louche.png";
	private final String urlImageProfBilou = "pictures\\prof-bilou.png";
	private final String urlImageProfSteev = "pictures\\prof-steev.png";

	private MoveInClasse moveInClasse;
	private MoveToClasse moveToClasse;
	private MoveBiblio moveBiblio;
	protected AgentScolar agentScolar;
	private JButton buttonDemandeHelp;

	private JTextField textGroupeRevision;

	public GUIproject(final AgentScolar agentScolar) {
		this.agentScolar = agentScolar;

		/**
		 * Instantiation
		 */
		this.setTitle("Smart University ");
		container = this.getContentPane();
		textArea = new JTextArea();
		panel = new JPanel();
		scroll = new JScrollPane(textArea);

		// Button name
		buttonBiblio = new JButton("Bibliotheque");
		buttonProf = new JButton("Arrivé du prof");
		buttonStartCours = new JButton("Heure cours ");
		buttonGoClasse = new JButton("Entrer");
		buttonAbsProf = new JButton("Prof ABS");
		buttonGroupeRevision = new JButton("Creer groupe");
		textGroupeRevision = new JTextField();
		buttonSendGroupe = new JButton("Send ...");
		buttonFinCours = new JButton("Fin cours");
		buttonDemandeHelp = new JButton("Creer des demendes");
		// departement

		// ------------
		// Image des personnages
		imageEtudianteGros = new JLabel(new ImageIcon(urlImageEtudianteGros));
		imageEtudianteSport = new JLabel(new ImageIcon(urlImageEtudianteSport));
		imageEtudianteSportif = new JLabel(new ImageIcon(
				urlImageEtudianteSportif));
		imageEtudianteStudieux = new JLabel(new ImageIcon(
				urlImageEtudianteStudieux));
		imageProfBilou = new JLabel(new ImageIcon(urlImageProfBilou));
		imageProfLouche = new JLabel(new ImageIcon(urlImageProfLouche));
		imageProfSteev = new JLabel(new ImageIcon(urlImageProfSteev));
		// Layout Label to null
		imageEtudianteGros.setLayout(null);
		imageEtudianteSport.setLayout(null);
		imageEtudianteSportif.setLayout(null);
		imageEtudianteStudieux.setLayout(null);
		imageProfBilou.setLayout(null);
		imageProfLouche.setLayout(null);
		imageProfSteev.setLayout(null);
		imageEtudianteGros.setBounds(290, 650, 128, 128);
		imageEtudianteSport.setBounds(290, 1250, 128, 128);
		imageEtudianteSportif.setBounds(290, 1250, 128, 128);
		imageEtudianteStudieux.setBounds(290, 1250, 128, 128);
		imageProfBilou.setBounds(290, 1250, 128, 128);
		imageProfLouche.setBounds(290, 1250, 128, 128);
		imageProfSteev.setBounds(290, 1250, 128, 128);

		panel.add(imageEtudianteGros);
		panel.add(imageEtudianteSport);
		panel.add(imageEtudianteSportif);
		panel.add(imageEtudianteStudieux);
		panel.add(imageProfBilou);
		panel.add(imageProfLouche);
		panel.add(imageProfSteev);
		// /------------------
		panelDepartement = new JPanel();
		panelDepartement.setBounds(1500, 40, 970, 600);
		imageDepartement3D = new JLabel(new ImageIcon(urlImage3Departement3D));
		imageEtudiante = new JLabel(new ImageIcon(urlImgaEtudiant));
		imageEtudiante.setLayout(null);
		imageDepartement3D.setLayout(null);
		imageEtudiante.setBounds(280, 500, 100, 100);
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
		// button passer devant la bilbiothéque
		buttonBiblio.setBounds(1210, 300, 100, 30);
		panel.add(buttonBiblio);
		// button heurs cours commance le tel passe en mode silencieux
		buttonStartCours.setBounds(1210, 340, 100, 30);
		panel.add(buttonStartCours);
		// bouton Aller en classe avec l'arrivé des personnage
		buttonGoClasse.setBounds(1210, 380, 100, 30);
		panel.add(buttonGoClasse);
		// button Arriver au departement du prof bilou
		buttonProf.setBounds(1210, 420, 100, 30);
		panel.add(buttonProf);
		// button ABS du prof bilou n'est plus la
		buttonAbsProf.setBounds(1050, 300, 150, 30);
		panel.add(buttonAbsProf);
		// creer groupe de revision
		buttonGroupeRevision.setBounds(1050, 380, 150, 30);
		panel.add(buttonGroupeRevision);
		// Envoyer groupe de revision
		buttonSendGroupe.setBounds(980, 380, 70, 30);
		panel.add(buttonSendGroupe);
		buttonSendGroupe.setVisible(false);

		//
		buttonFinCours.setBounds(1050, 340, 150, 30);
		panel.add(buttonFinCours);
		//

		buttonDemandeHelp.setBounds(1050, 420, 150, 30);
		panel.add(buttonDemandeHelp);
		//
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
		textGroupeRevision.setBounds(980, 340, 50, 30);
		panel.add(textGroupeRevision);
		textGroupeRevision.setVisible(false);

		// Deviation de flux
		prints = new PrintStream(new MyOutputstream(textArea));
		System.setOut(prints);
		System.setErr(prints);
		// ActionListner
		buttonBiblio.addActionListener(this);
		buttonStartCours.addActionListener(this);
		buttonGoClasse.addActionListener(this);
		buttonProf.addActionListener(this);
		buttonAbsProf.addActionListener(this);
		buttonGroupeRevision.addActionListener(this);
		buttonSendGroupe.addActionListener(this);
		buttonFinCours.addActionListener(this);
		buttonDemandeHelp.addActionListener(this);

		//

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public GUIproject() {

	}

	// ------------------------------------------------------------------------------------------------------------
	/**
	 * Eveneent sur les buttons
	 */
	public void actionPerformed(ActionEvent e) {
		/**
		 * Enter et passer devant l'université
		 */
		if (e.getSource() == buttonBiblio) {
			System.out.println("L'etudiant entre dans l'université !");
			moveBiblio = new MoveBiblio(this);
			moveBiblio.start();

		}
		/**
		 * Boutton les cours commance l'etudiant va au departement
		 */
		if (e.getSource() == buttonStartCours) {
			moveToClasse = new MoveToClasse(this);
			System.out.println("C'est l'heur d'aller en classe ....");
			this.agentScolar.setStartCour();
			moveToClasse.start();
			moveTimer();

		}
		/**
		 * L'etudiant va en classe lalalaal
		 */
		if (e.getSource() == buttonGoClasse) {
			System.out.println("l'etudiant va en classe");
			moveInClasse = new MoveInClasse(this);
			moveInClasse.start();
			System.out.println("On attend le prof ... wait ");

			imageEtudianteGros.setBounds(650, 350, 128, 128); // ok
			imageEtudianteSport.setBounds(650, 15, 128, 128); // ok
			imageEtudianteSportif.setBounds(200, 150, 128, 128); // ok
			imageEtudianteStudieux.setBounds(80, 5, 128, 128);

			imageProfLouche.setBounds(800, 350, 128, 128); // ok
			imageProfSteev.setBounds(50, 150, 128, 128);// ok
		}
		/**
		 * Arrivé du prof
		 */
		if (e.getSource() == buttonProf) {
			imageProfBilou.setBounds(700, 180, 128, 128);
			System.out.println("Le prof estla, On peux commancer");
		}
		/**
		 * Boutton declanche abscence du prof et demende un changement de
		 * l'heure
		 */
		if (e.getSource() == buttonAbsProf) {
			agentScolar.setProfABS("Le module est changé");
			System.out.println("Le prof est abscent, vous etes liberes ...");
			new GuiUpdate(this.agentScolar);
			imageProfBilou.setBounds(700, 1180, 128, 128);

		}
		/**
		 * Button soumetre des demndes d'aide
		 * 
		 */
		if (e.getSource() == buttonDemandeHelp) {
			new GUIDemandeHelp(agentScolar);
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

		if (e.getSource() == buttonGroupeRevision) {

			/**
			 * 
			 */
			buttonSendGroupe.setVisible(true);
			textGroupeRevision.setVisible(true);
			buttonGroupeRevision.setEnabled(false);

		}
		/**
		 * Envoi du groupe de revision
		 */
		if (e.getSource() == buttonSendGroupe) {
			/**
			 * 
			 */
			Integer nbr = Integer.parseInt(textGroupeRevision.getText());
			if (nbr >= 5) {
				nbr = 4;
				agentScolar.setNombreGroupe(nbr.toString());

			} else
				agentScolar.setNombreGroupe(nbr.toString());
			//
			buttonSendGroupe.setVisible(false);
			textGroupeRevision.setText("");
			textGroupeRevision.setVisible(false);
			buttonGroupeRevision.setEnabled(true);
			//
			imageEtudianteGros.setBounds(200, 250, 128, 128);
			imageEtudianteSport.setBounds(130, 250, 128, 128);
			imageEtudiante.setBounds(50, 250, 128, 128);

		}

		if (e.getSource() == buttonFinCours) {
			imageProfBilou.setBounds(700, 1180, 128, 128);
			imageProfLouche.setBounds(700, 1180, 128, 128);
			imageProfSteev.setBounds(700, 1180, 128, 128);
		}
		// -------------------//

	}

	// --------------------------------------------------------------------------------------------

	/**
	 * Effect move left du panel, aller au departement utilisant un TIMER
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
