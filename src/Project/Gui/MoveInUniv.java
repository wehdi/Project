package Project.Gui;


public class MoveInUniv extends Thread {

	private GUIproject projet;

	public MoveInUniv(GUIproject projet) {
		this.projet = projet;
		
	}

	@Override
	public void run() {
		super.run();
		for (int i = 500; i >200; i--) {
			projet.imageEtudiante.setBounds(380, i, 100, 100);
			try {
				sleep(3);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			sleep(900);
			this.projet.agentScolar.NotifyEntreeInUniv();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
   

}
