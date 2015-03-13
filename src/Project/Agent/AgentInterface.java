package Project.Agent;

import Project.Gui.GUIproject;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class AgentInterface extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
		addBehaviour(new OneShotBehaviour(this) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				new GUIproject(this);
				System.out
						.println("Lanement de l'interface par l'agent responsable !");

				doWait(3000);
				doDelete();
			}

		});

	}

	@Override
	protected void takeDown() {
		System.out.println("L'agent Interface vous dit sayonara !");
		super.takeDown();
	}
}
