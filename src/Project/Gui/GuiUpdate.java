package Project.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Project.Agent.AgentScolar;
import Project.Metiers.Beans;
import Projet.Bdd.StartBdd;

/**
 * 
 * @author ProBook 450g2 Update le planning
 */
public class GuiUpdate extends JFrame implements ActionListener {
	private JPanel panel;
	private JButton buttonUpdate;
	private JComboBox<String> dayBox;
	private JComboBox<String> moduleBox;
	private JComboBox<String> typeBox;
	private JComboBox<String> heurBox;
	private AgentScolar agentScolar;
	private StartBdd startBdd;
	private JLabel labelDay;
	private JLabel labelModule;
	private JLabel labelType;
	private JLabel labelHeur;

	private static final long serialVersionUID = 2009081343946305877L;

	public GuiUpdate(AgentScolar agentScolar) {
		this.agentScolar = agentScolar;

		String[] day = { "Samedi", "Dimanche", "Lundi", "Mardi", "Mercredi",
				"Jeudi" };
		String[] module = { "xml", "test", "JMX", "POO", "web" };
		String[] type = { "C", "TD" };
		String[] heur = { "8", "9", "10", "11", "12", "13", "14", "15" };
		panel = new JPanel();
		buttonUpdate = new JButton("Update");
		dayBox = new JComboBox<>(day);
		moduleBox = new JComboBox<>(module);
		typeBox = new JComboBox<>(type);
		heurBox = new JComboBox<>(heur);
		labelDay = new JLabel("Choisissez un jour :");
		labelHeur = new JLabel("Choisissez une Heure :");
		labelModule = new JLabel("Choisissez un Module:");
		labelType = new JLabel("Choisissez Le type de sceance :");

		//
		this.setBounds(230, 150, 500, 400);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("Update planning");

		//
		panel.setLayout(null);
		buttonUpdate.setBounds(150, 290, 200, 50);
		panel.add(buttonUpdate);
		//
		dayBox.addActionListener(this);
		dayBox.setBounds(280, 10, 100, 50);
		labelDay.setBounds(80, 10, 200, 50);
		panel.add(dayBox);
		panel.add(labelDay);

		moduleBox.addActionListener(this);
		moduleBox.setBounds(280, 80, 100, 50);
		labelModule.setBounds(80, 80, 200, 50);
		panel.add(labelModule);
		panel.add(moduleBox);

		typeBox.addActionListener(this);
		typeBox.setBounds(280, 150, 100, 50);
		panel.add(typeBox);
		labelType.setBounds(80, 150, 200, 50);
		panel.add(labelType);

		heurBox.addActionListener(this);
		heurBox.setBounds(280, 220, 100, 50);
		panel.add(heurBox);
		labelHeur.setBounds(80, 220, 200, 50);
		panel.add(labelHeur);

		//

		this.add(panel);

		buttonUpdate.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == buttonUpdate) {
			String d = dayBox.getSelectedItem().toString();
			String m = moduleBox.getSelectedItem().toString();
			String t = typeBox.getSelectedItem().toString();
			String h = heurBox.getSelectedItem().toString();
			startBdd = new StartBdd();
			try {
				// agentScolar.test();
				startBdd.openConecction();
				if (startBdd.verifyPlanning(h, d) == 0) {
					startBdd.insetInPlanning(d, m, t, h);
					this.agentScolar.sendPlanning();
					System.out.println();
					System.out.println("La planning est mise a jour");
					this.dispose();
					Beans.getAgentClass()
							.setABSProf(
									"Le prof est absent, un amenagement de l'emploi du temps a ete effectue");
				} else
					System.out.println();
				System.out
						.println("La date est deja prise, choisissez en une autre");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
