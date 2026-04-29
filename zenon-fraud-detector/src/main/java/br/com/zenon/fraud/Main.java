package br.com.zenon.fraud;

import java.util.List;
import java.util.Optional;

public class Main {

	void main() {

		TransactionIngestor ingestor = new TransactionIngestor();
		List<Transaction> transactionList = ingestor.ingest("PS_20174392719_1491204439457_log.csv", 100_000);
		//List<Transaction> transactionList = ingestor.ingest("paysim_with_bad_data.csv", 0);
		FraudAnalyzer analyzer = new FraudAnalyzer(transactionList);
		IO.println("1. Total de fraudes: " + analyzer.fraudCount());
		IO.println("2. Top 3 Fraudes de Maior Valor:");
		analyzer.getHighestValueFrauds(3).forEach(amount -> IO.println("%.2f".formatted(amount)));
		IO.println("3. Clientes Suspeitos:");
		analyzer.getTopSuspiciousClients(5).forEach(IO::println);
		IO.println("4. Prejuízo Total: %.2f".formatted(analyzer.getTotalLoss()));
		IO.println("5.  Fraudes por Tipo:");
		analyzer.getCountByTransactionType().forEach((chave, valor) -> IO.println("- " + chave + ": " + valor));

		IO.println("----------");
		TransactionListRepository listRepository = new TransactionListRepository(transactionList);
		String originName = "C12345";
		Optional<Transaction> transaction = listRepository.findByOriginName(originName);
		if (transaction.isPresent()) {
			IO.println(transaction);
		} else {
			IO.println("Transação não encontrada para o cliente " + originName);
		}

		originName = "C1231006815";
		findTransactionByOriginName(listRepository, originName);

		IO.println("----------");

		originName = "C1868032458";
		long ini0 = System.nanoTime();
		findTransactionByOriginName(listRepository, originName);
		long fim0 = System.nanoTime();
		long tempo0 = fim0 - ini0;
		IO.println("Tempo da busca: " + tempo0 + " ns");

		IO.println("----------");
		TransactionMapRepository mapRepository = new TransactionMapRepository(transactionList);
		long ini1 = System.nanoTime();
		findTransactionByOriginName(mapRepository, originName);
		long fim1 = System.nanoTime();
		long tempo1 = fim1 - ini1;
		IO.println("Tempo da busca: " + tempo1 + " ns");

	}

	private static void findTransactionByOriginName(TransactionRepository repository, String originName) {
		Optional<Transaction> transaction;
		transaction = repository.findByOriginName(originName);
		if (transaction.isPresent()) {
			IO.println(transaction);
		} else {
			IO.println("Transação não encontrada para o cliente " + originName);
		}
	}
}
