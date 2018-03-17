package pl.pj.calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperatorsActionListener implements ActionListener {
	private CalcController controller;

	public OperatorsActionListener(CalcController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		controller.onOperatorAction(e);
	}

}
