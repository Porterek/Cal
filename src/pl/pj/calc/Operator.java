package pl.pj.calc;

public enum Operator {
	// unicode \u00F7 to jest znak dzielenia.
	DEVIDE("\u00F7"), MULTIPLY("x"), MINUS("-"), PLUS("+"), MODULO(" MOD "), CALC("=");

	private String text;

	Operator(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
