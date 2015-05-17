package Project.Agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import Project.Gui.GUIproject;

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
}
