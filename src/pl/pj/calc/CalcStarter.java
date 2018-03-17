package pl.pj.calc;

import javax.swing.SwingUtilities;

/**
 * Klasa g³ówna
 */
public class CalcStarter {

	public static void main(String[] args) {
		/**
		 * Za "rysowanie" odpowiada jeden w¹tek wiêc nie mo¿na mu kazaæ od razu
		 * rysowaæ.
		 */
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	/**
	 * Tworzenie instancji kalkulatora. Wo³ane tylko raz na starcie.
	 */
	private static void createAndShowGUI() {
		CalcView calcView = new CalcView("Calc");
		calcView.setVisible(true);
		new CalcController(calcView);
	}

}
