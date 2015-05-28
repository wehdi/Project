package Project.Gui;

/**
 * Classe qui gére l'annimation d'aller en classe 2eme
 * 
 * @author ProBook 450g2
 *
 */

public class MoveToClasse extends Thread {
	private GUIproject projet;

	public MoveToClasse(GUIproject projet) {
		this.projet = projet;

	}

	@Override
	public void run() {
		super.run();
		for (int i = 280; i > 100; i--) {
			projet.imageEtudiante.setBounds(i,161, 100, 100);
			try {
				sleep(3);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			sleep(100);
			//this.projet.agentScolar.NotifyEntreeInUniv("me", null);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

}
