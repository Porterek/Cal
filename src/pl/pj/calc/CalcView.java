package pl.pj.calc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalcView extends JFrame {
	public static final String CLEAR_CHAR = "C";

	private final Dimension DEFAULT_SIZE = new Dimension(400, 300);
	private final String DEFAULT_TEXT = "0";

	private OperatorButton devideButton;
	private OperatorButton multiplyButton;
	private OperatorButton plusButton;
	private OperatorButton minusButton;
	private OperatorButton calcButton;

	private JButton[] numberButtons;
	private OperatorButton moduloButton;
	private JTextField topTextField;
	private JTextField bottomTextField;

	public CalcView(String title) {
		super(title);

		// dodanie akcji na zamkniêcie.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// rozmiar.
		setSize(DEFAULT_SIZE);
		setMinimumSize(DEFAULT_SIZE);

		// tworzenie rozmieszenia przycisków.
		createLayout();
	}

	/**
	 * Tworzenie i rozmieszczenie elementów widoku.
	 */
	private void createLayout() {
		JPanel pane = new JPanel(new GridBagLayout());
		// ustawienie jako glowny kontener
		setContentPane(pane);

		// padding, tak ¿eby to nie by³o od razu przy krawêdzi.
		pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		// rozszerzanie komponentow w poziomie i pionie.
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;

		// góra, panel z polami dla wpisywanych wartoœci i wyniku.
		JPanel resultPanel = createResultPanel();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		pane.add(resultPanel, c);

		// dó³
		// panel po lewej z cyframi
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		pane.add(createNumbersPanel(), c);

		// panel po prawej z operantami.
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		pane.add(createBasicOperatorsPanel(), c);
	}

	private JPanel createResultPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		topTextField = new JTextField();
		bottomTextField = new JTextField();

		// zablokowany, wpisywanie i wyniki s¹ z przycisków.
		topTextField.setEnabled(false);
		bottomTextField.setEnabled(false);

		// ¿eby tekst pokazywa³ siê od prawej
		topTextField.setHorizontalAlignment(JTextField.RIGHT);
		bottomTextField.setHorizontalAlignment(JTextField.RIGHT);

		topTextField.setDisabledTextColor(Color.BLACK);
		bottomTextField.setDisabledTextColor(Color.BLACK);

		// zeby nie by³o obranowania.
		topTextField.setBorder(BorderFactory.createEmptyBorder());
		bottomTextField.setBorder(BorderFactory.createEmptyBorder());

		panel.add(topTextField);
		panel.add(bottomTextField);

		return panel;
	}

	private JPanel createNumbersPanel() {
		JPanel numbersPanel = new JPanel(new GridBagLayout());
		// padding, tak ¿eby to nie by³o od razu przy krawêdzi.
		numbersPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		numberButtons = new JButton[12];
		numbersPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.33;
		c.weighty = 1.0;

		int rowIdx = 3;
		int buttonIdx = 0;
		// dodawanie 9 przycisków.
		for (int i = 0; i < 9; i++) {
			numberButtons[i] = new JButton(String.valueOf(i + 1));
			// modulo, reszta z dzielenia przez 3, to bêdzie pozycja w x
			c.gridx = i % 3;
			c.gridy = rowIdx;
			numbersPanel.add(numberButtons[i], c);

			// je¿eli to nie jest 1. index i reszta to 0 kolejna kolumna
			// (i+1) bo mamy indeksowany od 0 wiêc je¿eli chcemy ¿eby co 3 by³
			// zmieniany to musimy tu dodaæ 1 do indeksu.
			if (i > 0 && (i + 1) % 3 == 0) {
				rowIdx--;
			}
			buttonIdx++;
		}

		numberButtons[buttonIdx] = new JButton(String.valueOf(0));
		c.gridx = 1;
		c.gridy = 4;
		numbersPanel.add(numberButtons[buttonIdx], c);
		buttonIdx++;

		numberButtons[buttonIdx] = new JButton(".");
		c.gridx = 2;
		c.gridy = 4;
		numbersPanel.add(numberButtons[buttonIdx], c);
		buttonIdx++;

		numberButtons[buttonIdx] = new JButton(CLEAR_CHAR);
		c.gridx = 0;
		c.gridy = 4;
		numbersPanel.add(numberButtons[buttonIdx], c);

		return numbersPanel;
	}

	private JPanel createBasicOperatorsPanel() {
		JPanel basicOperatorsPanel = new JPanel(new GridBagLayout());
		// padding, tak ¿eby to nie by³o od razu przy krawêdzi.
		basicOperatorsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		basicOperatorsPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;

		int gridYIdx = 0;

		moduloButton = new OperatorButton(Operator.MODULO);
		c.gridy = gridYIdx++;
		basicOperatorsPanel.add(moduloButton, c);

		devideButton = new OperatorButton(Operator.DEVIDE);
		c.gridy = gridYIdx++;
		basicOperatorsPanel.add(devideButton, c);

		multiplyButton = new OperatorButton(Operator.MULTIPLY);
		c.gridy = gridYIdx++;
		basicOperatorsPanel.add(multiplyButton, c);

		plusButton = new OperatorButton(Operator.PLUS);
		c.gridy = gridYIdx++;
		basicOperatorsPanel.add(plusButton, c);

		minusButton = new OperatorButton(Operator.MINUS);
		c.gridy = gridYIdx++;
		basicOperatorsPanel.add(minusButton, c);

		calcButton = new OperatorButton(Operator.CALC);
		c.gridy = gridYIdx++;
		basicOperatorsPanel.add(calcButton, c);

		return basicOperatorsPanel;
	}

	public void registerOperatorsListener(ActionListener actionListener) {
		devideButton.addActionListener(actionListener);
		multiplyButton.addActionListener(actionListener);
		plusButton.addActionListener(actionListener);
		minusButton.addActionListener(actionListener);
		calcButton.addActionListener(actionListener);
		moduloButton.addActionListener(actionListener);
	}

	public void registerNumbersListener(ActionListener actionListener) {
		for (int i = 0; i < numberButtons.length; i++) {
			numberButtons[i].addActionListener(actionListener);
		}
	}

	public String getTopText() {
		return topTextField.getText();
	}

	public void setTopText(String text) {
		topTextField.setText(text);
	}

	public String getBottomText() {
		return bottomTextField.getText();
	}

	public void setBottomText(String text) {
		bottomTextField.setText(text);
	}

	public void resetValue() {
		topTextField.setText("");
		bottomTextField.setText(DEFAULT_TEXT);
	}

}
