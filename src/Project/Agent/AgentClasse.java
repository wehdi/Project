package Project.Agent;

import jade.core.Agent;

/**
 * 
 * @author ProBook 450g2 Agent chargé de la detection et de la transmition des
 *         perturbations
 */

@SuppressWarnings("serial")
public class AgentClasse extends Agent {
	@Override
	protected void setup() {
		super.setup();
		System.out.println(getLocalName() + " Strat ...");
	}

}
