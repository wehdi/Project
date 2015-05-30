package Project.Agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Project.Gui.GUIproject;
import Project.Metiers.Const;
import Project.Metiers.Generate_Planning;
import Projet.Bdd.StartBdd;

public class AgentScolar extends Agent {

	/**
	 * @author ProBook 450g2 Agent qui est chargé de l'envoi de notification De
	 *         la resolution de perturbatiion et de transmition de celle ci sous
	 *         forme de notification
	 */

	private StartBdd startBdd;
	private ArrayList<String> dayList;
	private ArrayList<String> heurList;
	private ArrayList<String> moduleList;
	private ArrayList<String> messageTab;
	private Generate_Planning generate_Planning;

	// --------------------------------------------------------------
	private static final long serialVersionUID = 1L;

	protected void setup() {
		startBdd = new StartBdd();
		dayList = new ArrayList<>();
		heurList = new ArrayList<>();
		moduleList = new ArrayList<>();
		generate_Planning = new Generate_Planning();
		messageTab = new ArrayList<>();
		startBdd.openConecction();

		// --------------------------------------------------------------------------
		/**
		 * Lancement du Gui de l'application
		 */
		addBehaviour(new VerifyIdsBehviour());

		new GUIproject(this);
	}

	/**
	 * Methodes appelees depuis un boutton de la classe GUIproject qui ajoute un
	 * comportement a l'agent
	 * 
	 * @param event2
	 * @param event
	 */

	public void NotifyEntreeInUniv(String event, String event2) {
		this.addBehaviour(new InfoEvents(event, event2));
	}

	/**
	 * Methode qui notify l'abscence du prof
	 * 
	 * @param message
	 */
	public void notifyABS(String message) {
		this.addBehaviour(new InfoEventABSBehaviour(message));
	}

	/**
	 * 
	 * @author ProBook 450g2
	 *
	 */
	private class InfoEventABSBehaviour extends OneShotBehaviour {
		String message;

		public InfoEventABSBehaviour(String message) {
			this.message = message;
		}

		@Override
		public void action() {
			ACLMessage message = new ACLMessage(ACLMessage.INFORM);
			message.setConversationId("notify");
			AID dummyAid2 = new AID();
			dummyAid2
					.setName("agentContexte@" + Const.ipAddress + ":1099/JADE");
			dummyAid2.addAddresses("http://" + Const.ipAddress + ":7778/acc");
			message.addReceiver(dummyAid2);
			message.setContent(this.message);
			myAgent.send(message);

		}

	}

	/**
	 * 
	 * @param envoyer
	 *            nbr groupe
	 */

	public void setNombreGroupe(String nbr) {

		this.addBehaviour(new setNbrGroupeBehaviour(nbr));
	}

	/**
	 * 
	 * @author ProBook 450g2 Comportement de l'agent notify les evenement
	 */
	private class InfoEvents extends OneShotBehaviour {

		private String event;
		private String event2;
		private static final long serialVersionUID = 1L;

		public InfoEvents(String event, String event2) {
			this.event = event;
			this.event2 = event2;
		}

		@Override
		public void action() {
			ACLMessage message = new ACLMessage(ACLMessage.INFORM);
			message.setConversationId("notify");
			AID dummyAid2 = new AID();
			dummyAid2
					.setName("agentContexte@" + Const.ipAddress + ":1099/JADE");
			dummyAid2.addAddresses("http://" + Const.ipAddress + ":7778/acc");
			message.addReceiver(dummyAid2);
			message.setContent(event + "|" + event2);
			myAgent.send(message);

		}
	}

	/**
	 * 
	 * @author ProBook 450g2 Behaviour qui envoi le nmbr de groupe a creer
	 *
	 */
	@SuppressWarnings("serial")
	private class setNbrGroupeBehaviour extends OneShotBehaviour {
		private String nbr;

		public setNbrGroupeBehaviour(String nbr) {
			this.nbr = nbr;

		}

		public void action() {
			ACLMessage message = new ACLMessage(ACLMessage.INFORM);
			message.setConversationId("revision");
			AID dummyAid2 = new AID();
			dummyAid2.setName("agentGestion@" + Const.ipAddress + ":1099/JADE");
			dummyAid2.addAddresses("http://" + Const.ipAddress + ":7778/acc");
			message.addReceiver(dummyAid2);
			message.setContent(this.nbr);
			myAgent.send(message);

		}
	}

	/**
	 * 
	 * @author ProBook 450g2 Envoi un signale quand le prof est abcent
	 *
	 */

	private class SetProfABSBehaviour extends OneShotBehaviour {

		private static final long serialVersionUID = 1L;
		private String message;

		public SetProfABSBehaviour(String message) {
			this.message = message;
		}

		public void action() {
			ACLMessage message = new ACLMessage(ACLMessage.INFORM);
			message.setConversationId("abs");
			AID dummyAid2 = new AID();
			dummyAid2.setName("agentGestion@" + Const.ipAddress + ":1099/JADE");
			dummyAid2.addAddresses("http://" + Const.ipAddress + ":7778/acc");
			message.addReceiver(dummyAid2);
			message.setContent(this.message);
			myAgent.send(message);
		}
	}

	/*
	 * public void notifyAbs(String message) { this.addBehaviour(new
	 * SetProfABSBehaviour(message)); }
	 */

	private class WaitProfABSBehaviour extends CyclicBehaviour {

		@Override
		public void action() {
			MessageTemplate model = MessageTemplate.and(
					MessageTemplate.MatchPerformative(ACLMessage.INFORM),
					MessageTemplate.MatchConversationId("tick"));
			ACLMessage msg = myAgent.receive(model);
			if (msg != null) {
				addBehaviour(new SetProfABSBehaviour("Le module est changé"));

				block();
			} else
				block();

		}

		// this.addBehaviour(new SetProfABSBehaviour(message));
	}

	/**
	 * 
	 * @author ProBook 450g2
	 *
	 */

	private class WaitCreatGroupe extends CyclicBehaviour {
		private static final long serialVersionUID = 1L;
		private ArrayList<String> infoArray;

		public void action() {
			MessageTemplate model = MessageTemplate.and(
					MessageTemplate.MatchPerformative(ACLMessage.INFORM),
					MessageTemplate.MatchConversationId("creat"));
			ACLMessage msg = myAgent.receive(model);
			if (msg != null) {
				infoArray = new ArrayList<String>();
				try {
					infoArray = (ArrayList<String>) msg.getContentObject();
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String name = infoArray.get(0);
				String module = infoArray.get(1);
				String jour = infoArray.get(2);
				String heur = infoArray.get(3);

				block();
			} else {
				block();
			}

		}

	}

	/***
	 * 
	 * @author ProBook 450g2
	 *
	 */

	private class WaitDemandeHelp extends CyclicBehaviour {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private ArrayList<String> infoArray;

		public void action() {
			MessageTemplate model = MessageTemplate.and(
					MessageTemplate.MatchPerformative(ACLMessage.INFORM),
					MessageTemplate.MatchConversationId("helpme"));
			ACLMessage msg = myAgent.receive(model);
			if (msg != null) {
				infoArray = new ArrayList<String>();
				try {
					infoArray = (ArrayList<String>) msg.getContentObject();
				} catch (UnreadableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String message = infoArray.get(0);
				String module = infoArray.get(1);
				String name = infoArray.get(2);

				block();
			} else {
				block();
			}

		}

	}

	/**
	 * 
	 * @author ProBook 450g2 envoi un signale qui passera le telephone en mode
	 *         vibreur
	 *
	 */
	private class SendStartCourBehaviour extends OneShotBehaviour {

		@Override
		public void action() {
			doWait(1000);

			ACLMessage sendDay = new ACLMessage(ACLMessage.INFORM);
			sendDay.setConversationId("mode");
			sendDay.setContent("0");
			AID dummyAid = new AID();
			dummyAid.setName("agentContexte@" + Const.ipAddress + ":1099/JADE");
			dummyAid.addAddresses("http://" + Const.ipAddress + ":7778/acc");
			sendDay.addReceiver(dummyAid);
			myAgent.send(sendDay);

		}

	}

	/**
	 * Methode qui appel un comportement
	 */
	public void setStartCour() {
		addBehaviour(new SendStartCourBehaviour());
	}

	// --------------------------------------------------------------------------------
	private class VerifyIdsBehviour extends CyclicBehaviour {

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
						addBehaviour(new SendPlanning());
						addBehaviour(new WaitCreatGroupe());
						addBehaviour(new WaitDemandeHelp());
						addBehaviour(new WaitProfABSBehaviour());
						block();

					} else {

						ACLMessage reponseMessage = new ACLMessage(
								ACLMessage.INFORM);
						reponseMessage.setConversationId("resp");
						AID dummyAid = new AID();
						dummyAid.setName("agentInterface@" + Const.ipAddress
								+ ":1099/JADE");
						dummyAid.addAddresses("http://" + Const.ipAddress
								+ ":7778/acc");
						reponseMessage.addReceiver(dummyAid);
						reponseMessage.setContent("other");
						send(reponseMessage);

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
	}

	/**
	 * 
	 * @author ProBook 450g2
	 *
	 */
	private class SendPlanning extends OneShotBehaviour {

		private static final long serialVersionUID = 1L;

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
	 * @param myAgent
	 * @param msgList
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

	}

	/**
	 * 
	 * @author ProBook 450g2
	 *
	 */
	private class SendPlanningToUpdate extends OneShotBehaviour {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			try {

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
			} catch (SQLException | IOException e) {

				e.printStackTrace();
			}

		}

	}

	/**
	 * 
	 * @param myAgent
	 * @param msgList
	 * @throws IOException
	 */

	private void sendMessageToUpdate(Agent myAgent, ArrayList<String> msgList)

	throws IOException {

		doWait(2000);
		ACLMessage sendDay = new ACLMessage(ACLMessage.INFORM);
		sendDay.setConversationId("update");
		sendDay.setContentObject(msgList);
		AID dummyAid = new AID();
		dummyAid.setName("agentGestion@" + Const.ipAddress + ":1099/JADE");
		dummyAid.addAddresses("http://" + Const.ipAddress + ":7778/acc");
		sendDay.addReceiver(dummyAid);
		myAgent.send(sendDay);

	}

	/**
	 * 
	 */

	public void sendPlanning() {
		this.addBehaviour(new SendPlanningToUpdate());
	}

	private class SendHelpDemandeListBehaviour extends OneShotBehaviour {
		ArrayList<String> content = new ArrayList<>();

		public SendHelpDemandeListBehaviour(ArrayList<String> content) {
			this.content = content;
		}

		public void action() {

			ACLMessage sendDay = new ACLMessage(ACLMessage.INFORM);
			sendDay.setConversationId("helpme");
			try {
				sendDay.setContentObject(this.content);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			AID dummyAid = new AID();
			dummyAid.setName("agentGestion@" + Const.ipAddress + ":1099/JADE");
			dummyAid.addAddresses("http://" + Const.ipAddress + ":7778/acc");
			sendDay.addReceiver(dummyAid);
			myAgent.send(sendDay);

		}

	}

	public void setDemandeHelp(ArrayList<String> content) {
		addBehaviour(new SendHelpDemandeListBehaviour(content));
	}

	// ---------------------------------------------------------------------------------------
	public void simulate(GUIproject gui) {
		addBehaviour(new SimulateBehavior(gui));
	}

	private class SimulateBehavior extends OneShotBehaviour {
		GUIproject gui;

		public SimulateBehavior(GUIproject gui) {
			this.gui = gui;
		}

		@Override
		public void action() {
			this.gui.buttonBiblio.doClick();
			doWait(2000);
			addBehaviour(new SimulateBehavior2(this.gui));
		}

		/*
		 * t this.gui.buttonGoClasse.doClick(); doWait(3500);
		 * this.gui.buttonProf.doClick(); doWait(900);
		 * System.out.println("Entrain d'etudier . . ."); //doWait(2000);
		 * //System.out.println("fin des cours, l"); doWait(3000);
		 * this.gui.buttonAbsProf.doClick();
		 */

	}

	/**
	 * 
	 * @author ProBook 450g2
	 *
	 */
	private class SimulateBehavior2 extends OneShotBehaviour {
		GUIproject gui;

		public SimulateBehavior2(GUIproject gui) {
			this.gui = gui;
		}

		@Override
		public void action() {
			this.gui.buttonStartCours.doClick();
			doWait(4000);
			addBehaviour(new SimulateBehavior3(this.gui));
		}

	}

	/**
	 * 
	 * @author ProBook 450g2
	 *
	 */
	private class SimulateBehavior3 extends OneShotBehaviour {
		GUIproject gui;

		public SimulateBehavior3(GUIproject gui) {
			this.gui = gui;
		}

		@Override
		public void action() {
			this.gui.buttonGoClasse.doClick();
			doWait(3500);
			addBehaviour(new SimulateBehavior4(this.gui));
		}

	}

	private class SimulateBehavior4 extends OneShotBehaviour {
		GUIproject gui;

		public SimulateBehavior4(GUIproject gui) {
			this.gui = gui;
		}

		@Override
		public void action() {
			this.gui.buttonProf.doClick();
			doWait(900);
			addBehaviour(new SimulateBehavior5(this.gui));
		}

	}

	/**
	 * 
	 * @author ProBook 450g2
	 *
	 */
	private class SimulateBehavior5 extends OneShotBehaviour {
		GUIproject gui;

		public SimulateBehavior5(GUIproject gui) {
			this.gui = gui;
		}

		@Override
		public void action() {
			doWait(3000);
			this.gui.buttonAbsProf.doClick();
		}
	}
}
