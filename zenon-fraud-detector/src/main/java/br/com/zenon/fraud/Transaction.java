package br.com.zenon.fraud;

public record Transaction(int step, TransactionType type, double amount, TransactionCustomer origin,
                          TransactionCustomer recipient, boolean isFraud,
                          boolean isFlaggedFraud) {

	public Transaction {
		validateParameters(step, amount);
	}

	private void validateParameters(int step, double amount ) {
		if(step <= 0) throw new IllegalArgumentException("step should be positive: " + step);
		if(amount < 0.0) throw new IllegalArgumentException("amount should be positive: " + amount);
	}
}