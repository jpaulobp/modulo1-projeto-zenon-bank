package br.com.zenon.fraud;

import java.util.ArrayList;
import java.util.List;

public class Main {

	void main() {

		List<Transaction> transactionList = new ArrayList<>();
		TransactionIngestor ingestor = new TransactionIngestor();
		//transactionList = ingestor.ingest("PS_20174392719_1491204439457_log.csv");
		transactionList = ingestor.ingest("paysim_with_bad_data.csv");
		IO.println(transactionList.size());
		transactionList.forEach(IO::println);

	}
}
