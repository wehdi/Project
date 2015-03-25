package Project.Agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.messaging.MessageStorage;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

import javax.swing.ViewportLayout;
import javax.xml.ws.Response;

import Project.Metiers.Generate_Planning;
import Projet.Bdd.StartBdd;

@SuppressWarnings("serial")
public class AgentBdd extends Agent {
	private StartBdd startBdd;
	private ArrayList<String> dayList;
	private ArrayList<String> heurList;
	private ArrayList<String> moduleList;
	private ArrayList<String> messageTab;
	private Generate_Planning generate_Planning;
	private boolean stop = false;
	private boolean stop_Planning = false;

	protected void setup() {
		startBdd = new StartBdd();
		dayList = new ArrayList<>();
		heurList = new ArrayList<>();
		moduleList = new ArrayList<>();
		generate_Planning = new Generate_Planning();
		messageTab = new ArrayList<>();
		ParallelBehaviour comportementparallele = new ParallelBehaviour(
				ParallelBehaviour.WHEN_ALL);
		SequentialBehaviour comportementSequenctielle = new SequentialBehaviour();
		// addBehaviour(comportementparallele);
		addBehaviour(comportementSequenctielle);
		try {
			startBdd.openConecction();
			System.out.println("Connexion is now Open !");
		} catch (Exception e1) {
			System.err.println("Erreur when connection open" + e1);
		}
		// verrifi l'inscription
		comportementSequenctielle.addSubBehaviour(new Behaviour() {

			public void action() {
				MessageTemplate modele = MessageTemplate.and(
						MessageTemplate.MatchPerformative(ACLMessage.INFORM),
						MessageTemplate.MatchConversationId("id"));

				ACLMessage receiveMessage = myAgent.receive(modele);

				if (receiveMessage != null) {
					String requestMessage = receiveMessage.getContent()
							.toString();
					String userName = requestMessage.substring(0,
							requestMessage.indexOf("|"));
					String pass = requestMessage.substring(requestMessage
							.indexOf("|") + 1);
					System.out.println("voici le msg receiveMessage vla "
							+ userName + "  " + pass);

					if (userName.equals("aa") && pass.equals("aa")) {
						ACLMessage reponseMessage = new ACLMessage(
								ACLMessage.INFORM);
						AID dummyAid = new AID();
						dummyAid.setName("agentInterface@192.168.2.3:1099/JADE");
						dummyAid.addAddresses("http://192.168.2.3:7778/acc");
						reponseMessage.addReceiver(dummyAid);
						reponseMessage.setContent("ok");
						send(reponseMessage);
						stop = true;
					} else {
						block();
					}

				} else {
					block();
				}
			}

			@Override
			public boolean done() {

				return stop;
			}

		});

		// envoi le planning
		comportementSequenctielle.addSubBehaviour(new Behaviour() {
			@Override
			public void action() {

				try {
					if (dayList.isEmpty() || heurList.isEmpty()
							|| moduleList.isEmpty()) {
						dayList = startBdd.getDay();
						heurList = startBdd.getHeur();
						moduleList = startBdd.getModule();
						messageTab.addAll(generate_Planning.setPlanning(
								dayList, heurList, moduleList));
						sendMessage(myAgent, messageTab);

					} else
						sendMessage(myAgent, messageTab);

				} catch (SQLException | IOException e) {
					e.printStackTrace();
				}

			}

			@Override
			public boolean done() {
				
				return stop_Planning;
			}

		});

	}

	private void sendMessage(Agent myAgent, ArrayList<String> msgList)
			throws IOException {
		ACLMessage sendDay = new ACLMessage(ACLMessage.INFORM);
		sendDay.setConversationId("go");
		sendDay.setContentObject(msgList);
		AID dummyAid = new AID();
		dummyAid.setName("agentGestion@192.168.2.3:1099/JADE");
		dummyAid.addAddresses("http://192.168.2.3:7778/acc");
		sendDay.addReceiver(dummyAid);
		myAgent.send(sendDay);
		System.out.println("send message ..");

		addBehaviour(new Behaviour() {

			@Override
			public void action() {
				MessageTemplate model = MessageTemplate.and(
						MessageTemplate.MatchPerformative(ACLMessage.INFORM),
						MessageTemplate.MatchConversationId("stop_planning"));
				ACLMessage reponse = receive(model);
				if (reponse != null) {
					String test = reponse.getContent().toString();
					if (test.equals("stop")){
						System.out.println("mesasge recu");
						stop_Planning = true;
						
						}
					
				} else
					block();

			}
			public boolean done() {
				// TODO Auto-generated method stub
				return stop_Planning;
			}});

	}

	protected void takeDown() {
		System.out.println("Agent data base clos");
		try {
			startBdd.closeConnection();
			System.out.println("Fermeture de la connexion");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
