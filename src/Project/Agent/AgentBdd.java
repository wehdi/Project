package Project.Agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Serializable;

import java.io.IOException;
import java.sql.SQLException;

import Projet.Bdd.StartBdd;

@SuppressWarnings("serial")
public class AgentBdd extends Agent implements Serializable,
		java.io.Serializable {

	// private final String serviceName = "BDD";
	// private Object[] obj = null;

	// private GUIproject guiProject;
	private StartBdd startBdd;

	protected void setup() {
		startBdd = new StartBdd();
		// Start Gui
		// new GUIproject(this);

		try {

			startBdd.openConecction();

			System.out.println("Connexion is now Open !");
		} catch (Exception e1) {
			System.err.println("Erreur when connection open" + e1);
		}
		addBehaviour(new CyclicBehaviour() {

			public void action() {
				try {
					String sql = "SELECT Jour FROM planning";
					System.out.println("AGENT SAY :" + startBdd.read(sql));

					ACLMessage message = new ACLMessage(ACLMessage.INFORM);
					message.setContentObject(startBdd.read(sql));
					AID dummyAid = new AID();
					dummyAid.setName("agentGestion@192.168.2.10:1099/JADE");
					dummyAid.addAddresses("http://192.168.2.10:7778/acc");
					message.addReceiver(dummyAid);
					myAgent.send(message);
					System.out.println("envoi" + message.toString());
					// attendre();

				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		/*
		 * addBehaviour(new CyclicBehaviour() {
		 * 
		 * private static final long serialVersionUID = 1L;
		 * 
		 * public void action() { /* System.out.println("alalal"); ACLMessage
		 * msg = myAgent.receive(); if (msg != null) { String content =
		 * msg.getContent();
		 * 
		 * System.out.println("Received Request from " +
		 * msg.getSender().getLocalName());
		 * System.out.println("Received Message : " + content +
		 * myAgent.getLocalName()); } else { System.out.println("null" +
		 * myAgent.getLocalName()); block(); }
		 */
		/*
		 * try { startBdd.read("mehdi"); attendre(); } catch (SQLException e) {
		 * e.printStackTrace(); } } });
		 */
	}

	@Override
	protected void takeDown() {
		System.out.println("Agent data base clos");

		try {
			startBdd.closeConnection();
			System.out.println("Fermeture de la connexion");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * public void updateBdd(String jour, String heur, String nom_Module) {
	 * addBehaviour(new OneShotBehaviour() { public void action() {
	 * System.out.println("Mise a jour de la BDD"); } }); }
	 */

	public boolean done() {
		System.out.println("Fermeture de la connexion");
		attendre();
		return true;
	}

	protected void attendre() {
		doWait(2000);
	}
}
