package Project.Metiers;

import java.util.ArrayList;

public class Generate_Planning {
	private static ArrayList<String> planningTab = new ArrayList<>();

	/**
	 * Classe qui genere le planning et le stock dans un ArrayList
	 */
	public Generate_Planning() {

	}

	public ArrayList<String> setPlanning(ArrayList<String> jourTab,
			ArrayList<String> heurTab, ArrayList<String> moduleTab) {
		for (int j = 0; j < 63; j++) {
			planningTab.add(" ");
		}
		planningTab.set(0, "   ");
		planningTab.set(1, "Dimanche");
		planningTab.set(2, "Lundi");
		planningTab.set(3, "Mardi");
		planningTab.set(4, "Mercredi");
		planningTab.set(5, "Jeudi");
		planningTab.set(6, "Samedi");

		planningTab.set(7, "8h-9h");
		planningTab.set(14, "9h-10h");
		planningTab.set(21, "10h-11h");
		planningTab.set(28, "11h-12");
		planningTab.set(35, "12h-13h");
		planningTab.set(42, "13h-14h");
		planningTab.set(49, "14h-15h");
		planningTab.set(56, "15h-16h");

		while (!jourTab.isEmpty()) {
			if (jourTab.get(0).equalsIgnoreCase("dimanche")) {
				if (heurTab.get(0).equals("8"))
					planningTab.set(8, moduleTab.get(0));
				else if (heurTab.get(0).equals("9")) {
					planningTab.set(15, moduleTab.get(0));
				} else if (heurTab.get(0).equals("10")) {
					planningTab.set(22, moduleTab.get(0));
				} else if (heurTab.get(0).equals("11")) {
					planningTab.set(29, moduleTab.get(0));
				} else if (heurTab.get(0).equals("12")) {
					planningTab.set(36, moduleTab.get(0));
				} else if (heurTab.get(0).equals("13")) {
					planningTab.set(43, moduleTab.get(0));
				} else if (heurTab.get(0).equals("14")) {
					planningTab.set(50, moduleTab.get(0));
				} else if (heurTab.get(0).equals("15")) {
					planningTab.set(57, moduleTab.get(0));
				}

			} else if (jourTab.get(0).equalsIgnoreCase("lundi")) {
				if (heurTab.get(0).equals("8"))
					planningTab.set(9, moduleTab.get(0));
				else if (heurTab.get(0).equals("9")) {
					planningTab.set(16, moduleTab.get(0));
				} else if (heurTab.get(0).equals("10")) {
					planningTab.set(23, moduleTab.get(0));
				} else if (heurTab.get(0).equals("11")) {
					planningTab.set(30, moduleTab.get(0));
				} else if (heurTab.get(0).equals("12")) {
					planningTab.set(37, moduleTab.get(0));
				} else if (heurTab.get(0).equals("13")) {
					planningTab.set(44, moduleTab.get(0));
				} else if (heurTab.get(0).equals("14")) {
					planningTab.set(51, moduleTab.get(0));
				} else if (heurTab.get(0).equals("15")) {
					planningTab.set(58, moduleTab.get(0));
				}

			} else if (jourTab.get(0).equalsIgnoreCase("mardi")) {
				if (heurTab.get(0).equals("8")) {
					planningTab.set(10, moduleTab.get(0));

				} else if (heurTab.get(0).equals("9")) {
					planningTab.set(17, moduleTab.get(0));
				} else if (heurTab.get(0).equals("10")) {
					planningTab.set(24, moduleTab.get(0));
				} else if (heurTab.get(0).equals("11")) {
					planningTab.set(31, moduleTab.get(0));
				} else if (heurTab.get(0).equals("12")) {
					planningTab.set(38, moduleTab.get(0));
				} else if (heurTab.get(0).equals("13")) {
					planningTab.set(45, moduleTab.get(0));
				} else if (heurTab.get(0).equals("14")) {
					planningTab.set(52, moduleTab.get(0));
				} else if (heurTab.get(0).equals("15")) {
					planningTab.set(59, moduleTab.get(0));
				}

			} else if (jourTab.get(0).equalsIgnoreCase("mercredi")) {
				if (heurTab.get(0).equals("8"))
					planningTab.set(11, moduleTab.get(0));
				else if (heurTab.get(0).equals("9")) {
					planningTab.set(18, moduleTab.get(0));
				} else if (heurTab.get(0).equals("10")) {
					planningTab.set(25, moduleTab.get(0));
				} else if (heurTab.get(0).equals("11")) {
					planningTab.set(32, moduleTab.get(0));
				} else if (heurTab.get(0).equals("12")) {
					planningTab.set(39, moduleTab.get(0));
				} else if (heurTab.get(0).equals("13")) {
					planningTab.set(46, moduleTab.get(0));
				} else if (heurTab.get(0).equals("14")) {
					planningTab.set(53, moduleTab.get(0));
				} else if (heurTab.get(0).equals("15")) {
					planningTab.set(60, moduleTab.get(0));
				}

			} else if (jourTab.get(0).equalsIgnoreCase("jeudi")) {
				if (heurTab.get(0).equals("8"))
					planningTab.set(12, moduleTab.get(0));
				else if (heurTab.get(0).equals("9")) {
					planningTab.set(19, moduleTab.get(0));
				} else if (heurTab.get(0).equals("10")) {
					planningTab.set(26, moduleTab.get(0));
				} else if (heurTab.get(0).equals("11")) {
					planningTab.set(33, moduleTab.get(0));
				} else if (heurTab.get(0).equals("12")) {
					planningTab.set(40, moduleTab.get(0));
				} else if (heurTab.get(0).equals("13")) {
					planningTab.set(47, moduleTab.get(0));
				} else if (heurTab.get(0).equals("14")) {
					planningTab.set(54, moduleTab.get(0));
				} else if (heurTab.get(0).equals("15")) {
					planningTab.set(61, moduleTab.get(0));
				}

			} else if (jourTab.get(0).equalsIgnoreCase("samedi")) {
				if (heurTab.get(0).equals("8"))
					planningTab.set(13, moduleTab.get(0));
				else if (heurTab.get(0).equals("9")) {
					planningTab.set(20, moduleTab.get(0));
				} else if (heurTab.get(0).equals("10")) {
					planningTab.set(27, moduleTab.get(0));
				} else if (heurTab.get(0).equals("11")) {
					planningTab.set(34, moduleTab.get(0));
				} else if (heurTab.get(0).equals("12")) {
					planningTab.set(41, moduleTab.get(0));
				} else if (heurTab.get(0).equals("13")) {
					planningTab.set(48, moduleTab.get(0));
				} else if (heurTab.get(0).equals("14")) {
					planningTab.set(55, moduleTab.get(0));
				} else if (heurTab.get(0).equals("15")) {
					planningTab.set(62, moduleTab.get(0));
				}

			}
			moduleTab.remove(0);
			jourTab.remove(0);
			heurTab.remove(0);

		}
		return planningTab;

	}

}
