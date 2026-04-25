package br.com.zenon.fraud;

import java.math.BigDecimal;

public record TransactionCustomer(String name, BigDecimal oldBalance, BigDecimal newBalance) {

	public TransactionCustomer {
		validateParameters(name, oldBalance, newBalance);
	}

	private void validateParameters(String name, BigDecimal oldBalance, BigDecimal newBalance) {
		if(oldBalance.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("oldBalance should be positive: " + oldBalance);
		if(newBalance.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("newBalance should be positive: " + newBalance);
		if(name == null || name.isEmpty()) throw new IllegalArgumentException("name should not be empty");
	}
}
