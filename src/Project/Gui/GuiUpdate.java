package Project.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Project.Agent.AgentController;
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
	private AgentController agentController;
	private StartBdd startBdd;

	private static final long serialVersionUID = 2009081343946305877L;

	public GuiUpdate(AgentController agentController) {
		this.agentController = agentController;

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

		//
		this.setBounds(280, 200, 500, 400);
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
		panel.add(dayBox);

		moduleBox.addActionListener(this);
		moduleBox.setBounds(280, 80, 100, 50);
		panel.add(moduleBox);

		typeBox.addActionListener(this);
		typeBox.setBounds(280, 150, 100, 50);
		panel.add(typeBox);

		heurBox.addActionListener(this);
		heurBox.setBounds(280, 220, 100, 50);
		panel.add(heurBox);

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
				startBdd.insetInPlanning(d, m, t, h);
				// Thread.sleep(5000);
				this.agentController.sendPlanning();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
