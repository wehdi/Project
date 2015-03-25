package Project.Agent;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AgentLuncher {
	private Runtime runtime;
	private Profile profile;
	private ContainerController container;;
	private AgentController agentFils;
	private AgentController agentFils1;
	private Properties proprties;

	public AgentLuncher() {
		proprties = new ExtendedProperties();
		proprties.setProperty(Profile.GUI, "true");
		profile = new ProfileImpl(proprties);
		Runtime.instance().setCloseVM(true);
		runtime = Runtime.instance();
		if (profile.getBooleanProperty(Profile.MAIN, true)) {
			container = runtime.createMainContainer(profile);
		} else {
			container = runtime.createAgentContainer(profile);
		}
		try {
			agentFils = container.createNewAgent("agentInterface",
					"Project.Agent.AgentInterface", null);
			agentFils.start();

			agentFils1 = container.createNewAgent("agentBdd",
					"Project.Agent.AgentBdd", null);
			agentFils1.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// Look And Feel
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			new AgentLuncher();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException ex) {
		}

	}
}
