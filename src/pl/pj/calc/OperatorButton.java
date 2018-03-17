package pl.pj.calc;

import javax.swing.JButton;

public class OperatorButton extends JButton {
	private Operator operatorEnum;

	public OperatorButton(Operator operatorEnum) {
		// tworzenie buttona.
		super(operatorEnum.getText());
		// przypisywanie mu enum którego u¿ywa.
		this.operatorEnum = operatorEnum;
	}

	public Operator getOperatorEnum() {
		return operatorEnum;
	}
}