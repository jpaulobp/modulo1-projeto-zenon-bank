package br.com.zenon.fraud;

public record TransactionCustomer(String name, double oldBalance, double newBalance) {

	public TransactionCustomer(String name, double oldBalance, double newBalance) {
		validarParametros(name, oldBalance, newBalance);
		this.name = name;
		this.oldBalance = oldBalance;
		this.newBalance = newBalance;
	}

	private void validarParametros(String name, double oldBalance, double newBalance) {
		if(oldBalance < 0.0) throw new IllegalArgumentException("oldBalance should be positive: " + oldBalance);
		if(newBalance < 0.0) throw new IllegalArgumentException("newBalance step should be positive: " + newBalance);
		if(name == null || name.isEmpty()) throw new IllegalArgumentException("name should not be empty");
	}
}
