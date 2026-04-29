package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FraudAnalyzer {

	private final List<Transaction> transactions;

	FraudAnalyzer(List<Transaction> transactions) {
		Objects.requireNonNull(transactions);
		this.transactions = transactions;
	}

	public long fraudCount() {
		return fraudStream().count();
	}

	public List<BigDecimal> getHighestValueFrauds(int limit) {
		return highValueFraudStream().map(Transaction::amount).limit(limit).toList();
	}

	public List<String> getTopSuspiciousClients(int limit) {
		return highValueFraudStream().map(transaction -> transaction.origin().name()).distinct().limit(limit).toList();
	}

	public BigDecimal getTotalLoss() {
		return fraudStream().map(Transaction::amount).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public Map<TransactionType, Long> getCountByTransactionType() {
		return fraudStream().collect(Collectors.groupingBy(Transaction::type, Collectors.counting()));
	}

	private Stream<Transaction> fraudStream() {
		return transactions.stream().filter(Transaction::isFraud);
	}

	private Stream<Transaction> highValueFraudStream() {
		return fraudStream().sorted(Comparator.comparing(Transaction::amount).reversed());
	}

}
