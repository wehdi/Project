package Project.Gui;

public class MoveInClasse extends Thread {
	private GUIproject projet;
	private bas b;

	public MoveInClasse(GUIproject projet) {
		this.projet = projet;

	}

	public void run() {
		// mouvement sur l'axe Y
		for (int i = 500; i > 200; i--) {
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
		b = new bas(projet);
		b.start();
		this.stop();

	}
}

// etudiant entre en classe
class bas extends Thread {

	GUIproject projet;

	public bas(GUIproject projet) {
		this.projet = projet;
	}

	public void run() {
		// mouvement sur l'axe X
		for (int i = 380; i < 600; i++) {
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
