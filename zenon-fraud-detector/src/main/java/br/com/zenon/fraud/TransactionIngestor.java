package br.com.zenon.fraud;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionIngestor {

	public List<Transaction> ingest(String fileName) {
		List<String> lines = new ArrayList<>();
		Path path = Path.of("data/" + fileName);
		// Use try-with-resources to automatically close the file
		try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
			String line;
			while ((line = br.readLine()) != null) {
					lines.add(line);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Arquivo não encontrado.", e);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler o arquivo.", e);
		}

		return lines.stream().skip(1).map(this::parseTransaction).filter(Optional::isPresent).map(Optional::get).toList();
	}

	private Optional<Transaction> parseTransaction(String line) {
		String[] columns = line.split(",");
		Transaction transaction = null;
		try {
			int step = Integer.parseInt(columns[0]);
			TransactionType transactionType = TransactionType.valueOf(columns[1]);
			double amount = Double.parseDouble(columns[2]);
			TransactionCustomer origin = new TransactionCustomer(columns[3], Double.parseDouble(columns[4]), Double.parseDouble(columns[5]));
			TransactionCustomer recipient = new TransactionCustomer(columns[6], Double.parseDouble(columns[7]), Double.parseDouble(columns[8]));
			boolean isFraud = Integer.parseInt(columns[9]) == 0;
			boolean isFlaggedFraud = Integer.parseInt(columns[10]) == 0;
			transaction = new Transaction(step, transactionType, amount, origin, recipient, isFraud, isFlaggedFraud);
		} catch (Exception e) {
			IO.println("Erro: " + line + " | " + e);
		}
		return Optional.ofNullable(transaction);
	}
}
