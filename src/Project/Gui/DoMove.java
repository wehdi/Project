package Project.Gui;


public class DoMove extends Thread {
  private GUIproject projet;
  private bas b;
	public DoMove(GUIproject projet){
		this.projet = projet;
		
	}
	
	public void run () {
		
		for (int i = 500; i>200; i--){
			projet.imageEtudiante.setBounds(380, i, 100, 100);
			try {
				sleep(3);
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b= new bas(projet);
		b.start();
		this.stop();
		
		
	}
}

//
class bas extends Thread {
	
	GUIproject projet;
	public bas (GUIproject projet){
		this.projet=projet;
	}
	public void run() {
		for (int i = 380; i<600; i++){
			projet.imageEtudiante.setBounds(i, 200, 100, 100);
			try {
				sleep(3);
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.stop();
	}
}
