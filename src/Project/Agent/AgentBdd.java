package Project.Agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.messaging.MessageStorage;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Serializable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Projet.Bdd.StartBdd;

@SuppressWarnings("serial")
public class AgentBdd extends Agent implements Serializable,
		java.io.Serializable {

	// private final String serviceName = "BDD";
	// private Object[] obj = null;

	// private GUIproject guiProject;
	private StartBdd startBdd;
	private ArrayList<String> dayList;
	private ArrayList<String> heurList;
	private ArrayList<String> moduleList;
	private ArrayList<String> messageList;

	protected void setup() {
		//
		startBdd = new StartBdd();
		dayList = new ArrayList<>();
		heurList = new ArrayList<>();
		moduleList = new ArrayList<>();
		messageList = new ArrayList<>();

		//
		try {
			startBdd.openConecction();
			System.out.println("Connexion is now Open !");
		} catch (Exception e1) {
			System.err.println("Erreur when connection open" + e1);
		}
		addBehaviour(new CyclicBehaviour() {
			
			public void action() {
				try {
					if (dayList.isEmpty() || heurList.isEmpty()|| moduleList.isEmpty()) {
					dayList = startBdd.getDay();
					heurList = startBdd.getHeur();
					moduleList = startBdd.getModule();
					
					messageList.addAll(dayList);
					messageList.add("day");
					messageList.addAll(heurList);
					messageList.add("heur");
					messageList.addAll(moduleList);
					messageList.add("module");
					sendMessage(myAgent,messageList);
					
					//couper l'arrayList
					System.out.println("days :" +messageList.subList(0, messageList.indexOf("day")));
					System.out.println("heur : "+ messageList.subList(messageList.indexOf("day")+1, messageList.indexOf("heur")));
					System.out.println("module : "+ messageList.subList(messageList.indexOf("heur"), messageList.size()-1));
                    System.out.println(messageList.get(messageList.indexOf("day")).equals("day"));
                    System.out.println(messageList.get(messageList.indexOf("heur")).equals("heur"));
                    System.out.println(messageList.get(messageList.indexOf("module")).equals("module"));
					
					}
					else {
						sendMessage(myAgent,messageList);
					System.out.println("days :" +messageList.subList(0, messageList.lastIndexOf("day")));
					System.out.println("heur : "+ messageList.subList(messageList.indexOf("day")+1, messageList.lastIndexOf("heur")));
					System.out.println("module : "+ messageList.subList(messageList.indexOf("heur"), messageList.size()-1));

					}
										attendre();
				} catch (SQLException | IOException  e) {
					e.printStackTrace();
				}
				attendre();
			}
		});

	}
	
	private void sendMessage(Agent myAgent, ArrayList<String>mesmsageList) throws IOException{
		ACLMessage sendDay = new ACLMessage(ACLMessage.INFORM);
		sendDay.setContentObject(messageList);
		AID dummyAid = new AID();
		dummyAid.setName("agentGestion@192.168.2.10:1099/JADE");
		dummyAid.addAddresses("http://192.168.2.10:7778/acc");
		sendDay.addReceiver(dummyAid);	
		myAgent.send(sendDay);
		System.out.println("envoi" + sendDay.toString());
	}

	@Override
	protected void takeDown() {
		System.out.println("Agent data base clos");
		try {
			startBdd.closeConnection();
			System.out.println("Fermeture de la connexion");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void updateBdd(String jour, String heur, String nom_Module) {
	 * addBehaviour(new OneShotBehaviour() { public void action() {
	 * System.out.println("Mise a jour de la BDD"); } }); }
	 */

	public boolean done() {
		System.out.println("Fermeture de la connexion");
		attendre();
		return true;
	}

	protected void attendre() {
		doWait(3500);
	}
}
