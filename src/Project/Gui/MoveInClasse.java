package Project.Gui;

public class MoveInClasse extends Thread {
	private GUIproject projet;
	private EnterClass entrer;

	public MoveInClasse(GUIproject projet) {
		this.projet = projet;

	}

	public void run() {
		/**
		 * Mon etudiant
		 */
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
			sleep(900);
			entrer = new EnterClass(projet);
			entrer.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * Gros Etudiant
		 */
		for (int i = 650; i > 200; i--) {
			projet.imageEtudianteGros.setBounds(380, i, 128, 128);
			projet.imageEtudianteSport.setBounds(330, i-100, 128, 128);
			try {
				sleep(3);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// etudiant entre en classe
	private class EnterClass extends Thread {

		GUIproject projet;

		public EnterClass(GUIproject projet) {
			this.projet = projet;
		}

		public void run() {
			/**
			 * Mon etudiant
			 */
			// mouvement sur l'axe X
			for (int i = 380; i < 600; i++) {
				projet.imageEtudiante.setBounds(i, 200, 100, 100);
				try {
					sleep(3);
					// this.stop();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			/**
		 * Gros etudiant
		 */
			for (int i = 380; i < 600; i++) {
				projet.imageEtudianteGros.setBounds(i, 200, 128, 128);
				projet.imageEtudianteSport.setBounds(i, 200, 128, 128);
				try {
					sleep(3);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}
}
