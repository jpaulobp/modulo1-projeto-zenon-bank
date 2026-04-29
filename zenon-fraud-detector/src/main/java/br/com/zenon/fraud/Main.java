package br.com.zenon.fraud;

import java.util.List;

public class Main {

	void main() {

		TransactionIngestor ingestor = new TransactionIngestor();
		List<Transaction> transactionList = ingestor.ingest("PS_20174392719_1491204439457_log.csv", 50_000);
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

	}
}
