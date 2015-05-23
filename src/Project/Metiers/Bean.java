package Project.Metiers;

import Project.Agent.AgentController;

public class Bean {

	private static AgentController agentController;

	public static AgentController getAgentController() {
		return agentController;
	}

	public static  void setAgentController(AgentController agentController) {
		Bean.agentController = agentController;
	}

}
