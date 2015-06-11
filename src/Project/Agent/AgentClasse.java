package Project.Agent;

import Project.Gui.GUIproject;
import Project.Metiers.Const;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * 
 * @author ProBook 450g2 Agent chargé de la detection et de la transmition des
 *         perturbations
 */

@SuppressWarnings("serial")
public class AgentClasse extends Agent {
	@Override
	protected void setup() {

		Project.Metiers.Beans.setAgentClass(this);
	}

	public void setABSProf(String message) {
		addBehaviour(new ServeillerBehaviour(message));
	}

	private class ServeillerBehaviour extends OneShotBehaviour {
		private String message;

		public ServeillerBehaviour(String message) {
			this.message = message;
		}

		@Override
		public void action() {
			System.out.println("L'agent : " + getLocalName()
					+ " envoi une alerte à l'agent Scolar");
			ACLMessage message = new ACLMessage(ACLMessage.INFORM);
			message.setConversationId("tick");
			message.addReceiver(new AID("agentScolar", AID.ISLOCALNAME));
			message.setContent(this.message);
			myAgent.send(message);

		}

	}

}
