package Project.Agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import Project.Gui.GUIproject;

public class AgentInterface extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
				new GUIproject(this);
				//addBehaviour(new InfoEvents());
				
			//addBehaviour(new NotifyCoursStart());	
				//addBehaviour(new InfoEvents());
			//	addBehaviour(new InfoEvents());
	}
	
	public void dodis(){
	this.addBehaviour(new InfoEvents());
	}

	
	class InfoEvents extends OneShotBehaviour  {

		@Override
		public void action() {
			ACLMessage message = new ACLMessage(ACLMessage.INFORM);
			message.setConversationId("notify");	
			AID dummyAid2 = new AID();
			dummyAid2.setName("agentContexte@192.168.2.3:1099/JADE");
			dummyAid2.addAddresses("http://192.168.2.3:7778/acc");
			message.addReceiver(dummyAid2);
			message.setContent("kkk");
			myAgent.send(message);
			System.out.println("deois Interface " +message.getConversationId() + message.getContent());
			
			
		}

	
		
	}
}
