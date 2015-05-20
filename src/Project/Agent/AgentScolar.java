package Project.Agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.util.ArrayList;

import Project.Gui.GUIproject;
import Project.Metiers.Const;

public class AgentScolar extends Agent {

	/**
	 * @author ProBook 450g2 Agent qui est chargé de l'envoi de notification De
	 *         la resolution de perturbatiion et de transmition de celle ci sous
	 *         forme de notification
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
		/**
		 * Lancement du Gui de l'application
		 */
		addBehaviour(new WaitCreatGroupe());
		addBehaviour(new WaitDemandeHelp());
		new GUIproject(this);

		System.out.println(getLocalName() + " Strat ...");
	}

	/**
	 * Methodes appelees depuis un boutton de la classe GUIproject qui ajoute un
	 * comportement a l'agent
	 */

	public void NotifyEntreeInUniv() {
		this.addBehaviour(new InfoEvents());
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
	 * @param message
	 */
	public void setProfABS(String message) {

		this.addBehaviour(new SetProfABSBehaviour(message));
	}

	/**
	 * 
	 * @author ProBook 450g2 Comportement de l'agent
	 */
	private class InfoEvents extends OneShotBehaviour {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			ACLMessage message = new ACLMessage(ACLMessage.INFORM);
			message.setConversationId("notify");
			AID dummyAid2 = new AID();
			dummyAid2
					.setName("agentContexte@" + Const.ipAddress + ":1099/JADE");
			dummyAid2.addAddresses("http://" + Const.ipAddress + ":7778/acc");
			message.addReceiver(dummyAid2);
			message.setContent("kkk");
			myAgent.send(message);
			System.out.println("deois Interface " + message.getConversationId()
					+ message.getContent());
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

	/**
	 * 
	 * @author ProBook 450g2
	 *
	 */

	private class WaitCreatGroupe extends CyclicBehaviour {

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
				System.out.println(name + " " + module + " " + jour + " "
						+ heur);
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

				System.out.println(name + " " + module + " " + message + " ");
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
			ACLMessage sendDay = new ACLMessage(ACLMessage.INFORM);
			sendDay.setConversationId("mode");
			sendDay.setContent("0");
			AID dummyAid = new AID();
			dummyAid.setName("agentContexte@" + Const.ipAddress + ":1099/JADE");
			dummyAid.addAddresses("http://" + Const.ipAddress + ":7778/acc");
			sendDay.addReceiver(dummyAid);
			myAgent.send(sendDay);
			System.out.println("send message ..");

		}

	}

	/**
	 * Methode qui appel un comportement
	 */
	public void setStartCour() {
		addBehaviour(new SendStartCourBehaviour());
	}
}
