package Project.Agent;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

import java.sql.DriverManager;
import java.sql.SQLException;

import Project.Gui.GUIproject;
import Projet.Bdd.StartBdd;

public class AgentBdd extends Agent{
	
	
	private final String serviceName ="BDD";
	private Object[] obj = null;
	
	private GUIproject guiProject;
	private StartBdd startBdd;
	
protected void setup() {
	//Start Gui
	new GUIproject(this);
	System.out.println("Interface Start ..." + getLocalName() +"Mais aussi :" + getName());	 
	
	
			/*try {new StartBdd().openConecction();} catch (Exception e1) {
				System.err.println("Erreur when connection open"+e1);}
	       
			//execution de la requette
			attendre();
			
			*/
			System.out.println("Agent "+getLocalName());
			
			try {
		/*	DFAgentDescription dfaAgentDescription = new DFAgentDescription();
			dfaAgentDescription.setName(getAID());
			ServiceDescription serviceDescription = new ServiceDescription();
			serviceDescription.setName(serviceName);
			serviceDescription.setType("data_base");
			dfaAgentDescription.addServices(serviceDescription);
			DFService.register(this, dfaAgentDescription); 
			*/
			System.out.println("its OK");
			//addBehaviour(new OfferRequestsServer());
			//addBehaviour(new PurchaseOrdersServer());
			

			addBehaviour(new CyclicBehaviour()
			{
			    public void action()
			    {
			        ACLMessage  msg = myAgent.receive();
			        if(msg != null)
			        { String content = msg.getContent();
			                
			                    System.out.println("Received Request from "+msg.getSender().getLocalName());
			                    System.out.println("Received Message : "+content + myAgent.getLocalName());
			                }
			                else
			                {   
			                	System.out.println("null"+myAgent.getLocalName());
			                    block();
			                }
			            
			        }});

			
			}catch (Exception e){
				System.out.println("Erreur"+ e);
				doWait(3000);
			}
			
			
			//
			}

@Override
protected void takeDown() {
	try {DFService.deregister(this);} 
	catch (FIPAException e) {System.err.println("Erreur takeDown"+e);}
	System.out.println("Agent data base clos");
	
	try {
		startBdd.closeConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	guiProject.dispose();
	System.out.println("Fermeture de la connexion");}


public void updateBdd(String jour,String heur,String nom_Module){
	addBehaviour(new OneShotBehaviour(){
		public void action(){
			System.out.println("Mise a jour de la BDD");
		}});}

/*
private class OfferRequestsServer extends CyclicBehaviour {

	
	public void action() {
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
	    jade.lang.acl.ACLMessage msg = myAgent.receive(mt);
	    
	    if (msg !=null){
	    	String jour = msg.getContent();
	    	jade.lang.acl.ACLMessage reply = msg.createReply();}
	    if (msg!=null){
	    	
	    }
		
	}
	
}*/


		
		public boolean done() {

			System.out.println("Fermeture de la connexion");
			attendre();
			return true;
		}
		
	
protected void attendre() {
	doWait(2000);}    
}

//

