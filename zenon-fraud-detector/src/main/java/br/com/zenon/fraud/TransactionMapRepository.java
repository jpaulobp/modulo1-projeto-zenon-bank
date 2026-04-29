package br.com.zenon.fraud;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionMapRepository implements TransactionRepository {

	private final Map<String, Transaction> transactionMap;

	TransactionMapRepository(List<Transaction> transactionList){
		Objects.requireNonNull(transactionList);
		this.transactionMap = transactionList.stream().collect(Collectors.toMap(transaction -> transaction.origin().name(), Function.identity()));
	}

	@Override
	public Optional<Transaction> findByOriginName(String name) {
		return Optional.ofNullable(transactionMap.get(name));
	}
}
