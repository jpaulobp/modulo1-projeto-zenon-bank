package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(int step, TransactionType type, BigDecimal amount, TransactionCustomer origin,
                          TransactionCustomer recipient, boolean isFraud,
                          boolean isFlaggedFraud) {

	public Transaction {
		validateParameters(step, amount);
	}

	private void validateParameters(int step, BigDecimal amount ) {
		if(step <= 0) throw new IllegalArgumentException("step should be positive: " + step);
		if(amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("amount should be positive: " + amount);
	}
}