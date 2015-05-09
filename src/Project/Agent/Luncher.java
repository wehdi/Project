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

import Project.Gui.GUIproject;

public class Luncher {
	private Runtime runtime;
	private Profile profile;
	private ContainerController container;;
	private AgentController agentFils;
	private AgentController agentFils1;
	private AgentController agentFils2;
	private Properties proprties;
	
	/**
	 * Classe qui se charge du lancement des agents du systemes !
	 * @throws InterruptedException 
	 * 
	 */

	public Luncher() throws InterruptedException {
		proprties = new ExtendedProperties();
		proprties.setProperty(Profile.GUI, "false");
		profile = new ProfileImpl(proprties);
		Runtime.instance().setCloseVM(true);
		runtime = Runtime.instance();
		if (profile.getBooleanProperty(Profile.MAIN, true)) {
			container = runtime.createMainContainer(profile);
		} else {
			container = runtime.createAgentContainer(profile);
		}
		try {
			/**
			 * Lancement des 3 agent qui compose notre systemes
			 */
	
			agentFils = container.createNewAgent("agentScolar",
					"Project.Agent.AgentScolar", null);
			agentFils.start();
			
 Thread.sleep(200);
			agentFils1 = container.createNewAgent("agentController",
					"Project.Agent.AgentController", null);
			agentFils1.start();
			Thread.sleep(200);
			agentFils2 = container.createNewAgent("agentClasse",
					"Project.Agent.AgentClasse", null);
			agentFils2.start();
			Thread.sleep(200);
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {

		/**
		 * Changement du look and feel et lancement de la classe
		 */
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			new Luncher();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException ex) {
		}

	}
}
