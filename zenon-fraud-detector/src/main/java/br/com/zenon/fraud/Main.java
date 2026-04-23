package br.com.zenon.fraud;

import java.util.ArrayList;
import java.util.List;

public class Main {

	void main() {

		List<Transaction> transactionList = new ArrayList<>();
		TransactionIngestor ingestor = new TransactionIngestor();
		try {
			transactionList = ingestor.ingest1000("PS_20174392719_1491204439457_log.csv");
		} catch (Exception e) {
			IO.println("Erro ao ler o arquivo.");
			e.printStackTrace();
		}

		for (int i = 0; i < 10; i++) {
			IO.println(transactionList.get(i));
		}

	}
}
