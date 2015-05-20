package Project.Agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Project.Metiers.Bean;
import Project.Metiers.Const;
import Project.Metiers.Generate_Planning;
import Projet.Bdd.StartBdd;

@SuppressWarnings("serial")
/**
 * 
 * @author ProBook 450g2
 *Agent qui gere l'authentification de l'etudiant et l'envoi des donnees
 */
public class AgentController extends Agent {
	private StartBdd startBdd;
	//private Bean bean;
	private ArrayList<String> dayList;
	private ArrayList<String> heurList;
	private ArrayList<String> moduleList;

	private ArrayList<String> messageTab;
	private Generate_Planning generate_Planning;
	private boolean stop = false;

	// private boolean stop_Planning = false;

	protected void setup() {
		System.out.println(getLocalName() + " Strat ...");
		//bean = new Bean();

		startBdd = new StartBdd();
		dayList = new ArrayList<>();
		heurList = new ArrayList<>();
		moduleList = new ArrayList<>();

		generate_Planning = new Generate_Planning();
		messageTab = new ArrayList<>();
		/**
		 * Utilisation d'un behavior sequentiel
		 */
		SequentialBehaviour comportementSequenctielle = new SequentialBehaviour();
		addBehaviour(comportementSequenctielle);
		try {
			startBdd.openConecction();
			System.out.println("Connexion is now Open !");
		} catch (Exception e1) {
			System.err.println("Erreur when connection open" + e1);
		}
		/**
		 * Behavior qui verifie les ids
		 */
		comportementSequenctielle.addSubBehaviour(new VerifyIds());

		/**
		 * Behavior chargé de l'envoi le planning
		 */
		comportementSequenctielle.addSubBehaviour(new SendPlanning());

		Bean.setAgentController(this);

	}

	/**
	 * 
	 * Methode d'envoi de message
	 * 
	 * @throws IOException
	 */
	private void sendMessage(Agent myAgent, ArrayList<String> msgList)

	throws IOException {
		ACLMessage sendDay = new ACLMessage(ACLMessage.INFORM);
		sendDay.setConversationId("go");
		sendDay.setContentObject(msgList);
		AID dummyAid = new AID();
		dummyAid.setName("agentGestion@" + Const.ipAddress + ":1099/JADE");
		dummyAid.addAddresses("http://" + Const.ipAddress + ":7778/acc");
		sendDay.addReceiver(dummyAid);
		myAgent.send(sendDay);
		System.out.println("send message ..");

	}

	/**
	 * 
	 * @param myAgent
	 * @param msgList
	 * @throws IOException
	 */
	private void sendMessageToUpdate(Agent myAgent, ArrayList<String> msgList)

	throws IOException {
		System.out.println("send message to update Called..");
		doWait(4000);
		ACLMessage sendDay = new ACLMessage(ACLMessage.INFORM);
		sendDay.setConversationId("update");
		sendDay.setContentObject(msgList);
		AID dummyAid = new AID();
		dummyAid.setName("agentGestion@" + Const.ipAddress + ":1099/JADE");
		dummyAid.addAddresses("http://" + Const.ipAddress + ":7778/acc");
		sendDay.addReceiver(dummyAid);
		myAgent.send(sendDay);

	}

	public void sendPlanning() {
		this.addBehaviour(new SendPlanningToUpdate());
	}

	protected void takeDown() {

		System.out.println("Agent data base clos");
		try {
			startBdd.closeConnection();
			System.out.println("Fermeture de la connexion");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Comportement qui verifie les ids
	 * 
	 * @author ProBook 450g2
	 *
	 */

	private class VerifyIds extends Behaviour {

		public void action() {

			MessageTemplate modele = MessageTemplate.and(
					MessageTemplate.MatchPerformative(ACLMessage.INFORM),
					MessageTemplate.MatchConversationId("id"));

			ACLMessage receiveMessage = myAgent.receive(modele);

			if (receiveMessage != null) {
				String requestMessage = receiveMessage.getContent().toString();
				String userName = requestMessage.substring(0,
						requestMessage.indexOf("|"));
				String pass = requestMessage.substring(requestMessage
						.indexOf("|") + 1);
				System.out.println("voici le msg receiveMessage vla "
						+ userName + "  " + pass);
				/**
				 * mehdi = 1234 rahim = 12345
				 */

				try {
					startBdd.openConecction();
					// change 0 to 1

					if (startBdd.getUserName(userName) == 0
							&& startBdd.getPassword(pass) == 0) {
						ACLMessage reponseMessage = new ACLMessage(
								ACLMessage.INFORM);
						reponseMessage.setConversationId("resp");
						AID dummyAid = new AID();
						dummyAid.setName("agentInterface@" + Const.ipAddress
								+ ":1099/JADE");
						dummyAid.addAddresses("http://" + Const.ipAddress
								+ ":7778/acc");
						reponseMessage.addReceiver(dummyAid);
						reponseMessage.setContent("ok|" + userName);
						send(reponseMessage);
						System.out.println("Le mdp est good");
						// doWake();
						stop = true;
						// startBdd.closeConnection();

					} else {

						System.out.println("Le dmp est bad");
						block();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				block();
			}
		}

		@Override
		public boolean done() {

			return stop;
		}

	}

	/**
	 * Comportement qui se cahrge de l'envoi du planning
	 * 
	 * @author ProBook 450g2
	 *
	 */
	private class SendPlanning extends OneShotBehaviour {

		@Override
		public void action() {
			try {
				ArrayList<String> messageTab = new ArrayList<>();
				if (dayList.isEmpty() || heurList.isEmpty()
						|| moduleList.isEmpty()) {
					dayList = startBdd.getDay();
					heurList = startBdd.getHeur();
					moduleList = startBdd.getModule();

					messageTab.addAll(generate_Planning.setPlanning(dayList,
							heurList, moduleList));
					sendMessage(myAgent, messageTab);
					messageTab.clear();
				} else {
					sendMessage(myAgent, messageTab);
					messageTab.clear();
				}

			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * 
	 * @author ProBook 450g2
	 *
	 */

	private class SendPlanningToUpdate extends OneShotBehaviour {

		@Override
		public void action() {
			try {
				System.out.println("Behavior SendPlanning Update Called");
				startBdd.openConecction();

				dayList = startBdd.getDay();
				heurList = startBdd.getHeur();
				moduleList = startBdd.getModule();
				messageTab.addAll(generate_Planning.setPlanning(dayList,
						heurList, moduleList));
				sendMessageToUpdate(myAgent, messageTab);
				messageTab.clear();
				dayList.clear();
				moduleList.clear();
				heurList.clear();
				System.err.println("im tel ...");
			} catch (SQLException | IOException e) {
				System.out.println("catch");
				e.printStackTrace();
			}

		}

	}

	


}
