package br.com.zenon.fraud;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TransactionIngestor {

	public List<Transaction> ingest(String fileName) {
		List<Transaction> transactionList = new ArrayList<>();
		Path path = Path.of("data/" + fileName);
		// Use try-with-resources to automatically close the file
		int countTransactions = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
			boolean firstLine = true;
			String line;
			while ((line = br.readLine()) != null) {
				if (!firstLine) {
					try {
						transactionList.add(parseTransaction(line));
						countTransactions++;
					} catch (IllegalArgumentException iae) {
						IO.println("Erro: " + line + " | " + iae);
					}
				} else {
					firstLine = false;
				}
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Arquivo não encontrado.", e);
		} catch (
				IOException e) {
			throw new RuntimeException("Erro ao ler o arquivo.", e);
		}

		IO.println(countTransactions);
		transactionList.forEach(IO::println);
		return transactionList;
	}

	private Transaction parseTransaction(String line) {
		String[] columns = line.split(",");
		return new Transaction(
				Integer.parseInt(columns[0]),
				TransactionType.valueOf(columns[1]),
				Double.parseDouble(columns[2]),
				new TransactionCustomer(columns[3], Double.parseDouble(columns[4]), Double.parseDouble(columns[5])),
				new TransactionCustomer(columns[6], Double.parseDouble(columns[7]), Double.parseDouble(columns[8])),
				Integer.parseInt(columns[9]) == 0,
				Integer.parseInt(columns[10]) == 0);
	}
}
