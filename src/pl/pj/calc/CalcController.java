package pl.pj.calc;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class CalcController {
	private CalcView view;
	private Double firstNumber;
	private Operator usedOperator;
	private boolean isResetFirstNumber;

	public CalcController(CalcView view) {
		this.view = view;
		registerListeners();
		view.resetValue();
	}

	public void registerListeners() {
		view.registerOperatorsListener(new OperatorsActionListener(this));
		view.registerNumbersListener(new NumberActionListener(this));
	}

	public void onOperatorAction(ActionEvent e) {
		OperatorButton operatorButton = null;

		// tu powinien wejœæ jako source OperatorButton
		try {
			operatorButton = (OperatorButton) e.getSource();
		} catch (ClassCastException cce) {
			cce.printStackTrace(System.out);
		}

		// je¿eli operator to nie =
		if (operatorButton != null && !Operator.CALC.equals(operatorButton.getOperatorEnum())) {
			// je¿eli nie by³o ¿adnego operatora to ustawiamy liczbe jako
			// pierwsza.
			if (usedOperator == null) {
				firstNumber = getNumber(view.getBottomText());
			}
			usedOperator = operatorButton.getOperatorEnum();
			view.setTopText(view.getBottomText() + usedOperator.getText());

		}
		// jezeli operator to =
		else {
			if (firstNumber != null && usedOperator != null) {
				Double secondNumber = getNumber(view.getBottomText());
				if (secondNumber != null) {
					System.out.println("OPERATOR: " + usedOperator);
					view.resetValue();
					Double result = calcValue(firstNumber, secondNumber, usedOperator);
					if (result != null) {
						view.setBottomText(String.valueOf(result));
					}

					reset();
				}
			}
		}

	}

	private Double calcValue(Double first, Double second, Operator operator) {
		Double result = 0.0;

		try {
			switch (operator) {
			case DEVIDE:
				if (second == 0) {
					throw new DivideByZeroException();
				}
				result = first / second;
				break;
			case MINUS:
				result = first - second;
				break;
			case MODULO:
				result = first % second;
				break;
			case MULTIPLY:
				result = first * second;
				break;
			case PLUS:
				result = first + second;
				break;
			default:
				break;
			}
		} catch (DivideByZeroException e) {
			view.setBottomText(e.getMessage());
			return null;
		} catch (Exception e) {
			view.setBottomText("Niespodziewany b³¹d");
			return null;
		}

		return result;
	}

	public void onNumberAction(ActionEvent e) {
		JButton numberButton = null;

		// tu powinien wejœæ jako source OperatorButton
		try {
			numberButton = (JButton) e.getSource();
		} catch (ClassCastException cce) {
			cce.printStackTrace(System.out);
		}

		if (numberButton != null) {
			if (CalcView.CLEAR_CHAR == numberButton.getText()) {
				reset();
				view.resetValue();
			} else {
				// poczatek nastepnej liczby
				if (usedOperator != null && !isResetFirstNumber) {
					view.setBottomText("0");
					isResetFirstNumber = true;
				}
				String oldText = view.getBottomText();
				if (getNumber(oldText) == 0 && numberButton.getText() != ".") {
					oldText = "";
				}
				String newText = oldText + numberButton.getText();

				// spróbujemy utworzyæ wartoœc liczbow¹
				if (getNumber(newText) != null) {
					view.setBottomText(newText);
				}
			}
		}
	}

	private void reset() {
		firstNumber = null;
		usedOperator = null;
		isResetFirstNumber = false;
	}

	/**
	 * Próbowanie parsowania tesktu na double.
	 */
	private Double getNumber(String text) {
		try {
			return Double.parseDouble(text);
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace(System.out);
			return null;
		}
	}

	// tylko pusta klasa ¿eby obs³u¿yæ ten wyj¹tek osobno
	class DivideByZeroException extends ArithmeticException {
		public DivideByZeroException() {
			super("Nie dziel przez zero...");
		}
	}
}
