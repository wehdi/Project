package Project.Gui;

/**
 * Deplacement devant la bibliothéque 1er
 * 
 * @author ProBook 450g2
 *
 */
public class MoveBiblio extends Thread {
	private GUIproject gui;

	public MoveBiblio(GUIproject gui) {
		super();
		this.gui = gui;
	}

	@Override
	public void run() {
		super.run();
		for (int i = 500; i > 160; i--) {
			gui.imageEtudiante.setBounds(280, i, 100, 100);
			try {
				sleep(3);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		/**
			 * 
			 */
		try {
			sleep(900);
			this.gui.agentScolar.NotifyEntreeInUniv("Conferance sur le climat",
					"Formation sur le devloppement");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
