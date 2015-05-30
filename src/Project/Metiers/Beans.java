package Project.Metiers;

import java.io.Serializable;

import Project.Agent.AgentClasse;

/**
 * 
 * @author ProBook 450g2
 *
 */
public class Beans implements Serializable {

	private static final long serialVersionUID = 1L;
	private static AgentClasse agentClass;

	public static AgentClasse getAgentClass() {
		return agentClass;
	}

	public static void setAgentClass(AgentClasse agentClass) {
		Beans.agentClass = agentClass;
	}
}
