package br.com.zenon.fraud;

public record Transaction(int step, TransactionType type, double amount, TransactionCustomer origin,
                          TransactionCustomer recipient, boolean isFraud,
                          boolean isFlaggedFraud) {

	public Transaction(int step, TransactionType type, double amount, TransactionCustomer origin, TransactionCustomer recipient, boolean isFraud, boolean isFlaggedFraud) {
		validarParametros(step, amount);
		this.step = step;
		this.type = type;
		this.amount = amount;
		this.origin = origin;
		this.recipient = recipient;
		this.isFraud = isFraud;
		this.isFlaggedFraud = isFlaggedFraud;
	}

	private void validarParametros(int step, double amount ) {
		if(step <= 0) throw new IllegalArgumentException("step should be positive: " + step);
		if(amount < 0.0) throw new IllegalArgumentException("amount should be positive: " + amount);
	}
}