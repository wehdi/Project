package Project.Agent;

import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import Project.Gui.*;

public class AgentLuncher extends Agent {
	private Runtime runtime;
	private Profile profile;
	private Profile profile2;
	private  ContainerController container;
	private  ContainerController container2;
	
	private AgentController agentFils;
	private AgentController agentFils1;
	
	
	

	protected void setup() {
		 runtime = Runtime.instance();
		 profile = new ProfileImpl();
		// profile2 = new ProfileImpl();
		 
		 container = runtime.createAgentContainer(profile);
		// container2= runtime.createAgentContainer(profile2);
		
		 try {
			agentFils = container.createNewAgent("agentInterface","Project.Agent.AgentInterface",null);
			agentFils.start();
			
			agentFils1 = container.createNewAgent("agentBdd", "Project.Agent.AgentBdd", null);
			agentFils1.start();
		 
			//doDelete();
			
			addBehaviour(new CyclicBehaviour()
			{
			    public void action()
			    {
			        ACLMessage  msg = myAgent.receive();
			        if(msg != null)
			        { String content = msg.getContent();
			        
			                    System.out.println("Received Request from "+msg.getSender().getLocalName());
			                    System.out.println("Received Message : "+content+ myAgent.getLocalName());
			                }
			                else
			                {   
			                	System.out.println("null "+myAgent.getLocalName());
			                    block();
			                }
			            
			        }});
			
			
		} catch (StaleProxyException e) {
	e.printStackTrace();
		}}}
