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
	 * Methode appeleer depuis un boutton de la classe GUIproject qui ajoute un
	 * comportement a l'agent
	 */
	public void NotifyEntreeInUniv() {
		this.addBehaviour(new InfoEvents());
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
}
