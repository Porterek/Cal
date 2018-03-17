package pl.pj.calc;

import javax.swing.SwingUtilities;

/**
 * Klasa g��wna
 */
public class CalcStarter {

	public static void main(String[] args) {
		/**
		 * Za "rysowanie" odpowiada jeden w�tek wi�c nie mo�na mu kaza� od razu
		 * rysowa�.
		 */
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	/**
	 * Tworzenie instancji kalkulatora. Wo�ane tylko raz na starcie.
	 */
	private static void createAndShowGUI() {
		CalcView calcView = new CalcView("Calc");
		calcView.setVisible(true);
		new CalcController(calcView);
	}

}
