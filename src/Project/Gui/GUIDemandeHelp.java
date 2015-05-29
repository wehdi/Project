package Project.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Project.Agent.AgentScolar;

/**
 * 
 * @author ProBook 450g2
 *
 */
public class GUIDemandeHelp extends JFrame implements ActionListener {
	private AgentScolar agentScolar;
	private JTextArea textArea;
	private JButton buttonSend;
	private JButton buttonCreat;
	private JTextField textTitle;
	private JPanel panel;
	private ArrayList<String> content;

	public GUIDemandeHelp(AgentScolar agentScolar) {
		this.agentScolar = agentScolar;
		content = new ArrayList<String>();

		this.setBounds(230, 200, 500, 400);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("Demande d'aide simulation");

		buttonCreat = new JButton("Creer demande");
		buttonSend = new JButton("Envoyer");
		textArea = new JTextArea();
		textTitle = new JTextField();
		panel = new JPanel();
		panel.setLayout(null);
		panel.add(buttonCreat);
		panel.add(buttonSend);
		panel.add(textArea);
		panel.add(textTitle);
		buttonSend.setBounds(150, 290, 200, 50);
		buttonCreat.setBounds(180, 250, 130, 30);
		textTitle.setBounds(150, 10, 100, 30);
		textArea.setBounds(150, 60, 300, 160);
		buttonCreat.addActionListener(this);
		buttonSend.addActionListener(this);

		this.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == buttonSend) {
			agentScolar.setDemandeHelp(content);
			System.out.println(content);
			this.dispose();
		}
		if (arg0.getSource() == buttonCreat) {
			content.add(textTitle.getText().toString() + "|"
					+ textArea.getText().toString());

			textArea.setText("");
			textTitle.setText("");

		}

	}

}
