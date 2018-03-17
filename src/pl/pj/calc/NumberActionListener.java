package pl.pj.calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberActionListener implements ActionListener {

	private CalcController controller;

	public NumberActionListener(CalcController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.onNumberAction(e);
	}

}
