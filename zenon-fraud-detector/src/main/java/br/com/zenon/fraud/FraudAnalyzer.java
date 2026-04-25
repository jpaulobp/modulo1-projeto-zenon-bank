package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class FraudAnalyzer {

	private final List<Transaction> transactions;

	FraudAnalyzer(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public int fraudCount() {
		return transactions.stream().filter(Transaction::isFraud).toList().size();
	}

	/**
	 * Obtém a lista das maiores fraudes.
	 *
	 * @param value quantidade de fraudes desejada
	 * @return Lista com as maiores fraudes de acordo com a quantidade solicitada	.
	 */
	public List<Transaction> getHigherValueFrauds(int value) {
		return this.getFraudsDescendingByAmount().stream().limit(value).toList();
	}

	/**
	 * Obtém a lista das fraudes ordenada de forma decrescente de 'amount'.
	 */
	private List<Transaction> getFraudsDescendingByAmount() {
		return transactions.stream().
				filter(Transaction::isFraud).
				sorted(Comparator.comparing(Transaction::amount))
				.toList()
				.reversed();
	}

	public Set<TransactionCustomer> getOriginByHigherValueFrauds() {
		return this.getFraudsDescendingByAmount().stream().map(Transaction::origin).collect((Collectors.toSet()));
	}

	public BigDecimal getTotalLoss() {
		return transactions.stream().map(Transaction::amount) // Extract the BigDecimal
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public Map<TransactionType, Long> getCountByTransactionType(){
		return transactions.stream().
				collect(Collectors.groupingBy(Transaction::type, Collectors.counting()));
	}
}
