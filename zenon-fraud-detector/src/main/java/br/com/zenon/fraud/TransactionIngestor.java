package br.com.zenon.fraud;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionIngestor {

	/**
	 * Lê determinada quantidade de linhas do arquivo.
	 */
	public List<Transaction> ingest(String fileName, int numberOfLines ) {
		List<String> lines = new ArrayList<>();
		Path path = Path.of("data/" + fileName);
		// Use try-with-resources to automatically close the file
		int linesCount = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
			String line;
			while ((line = br.readLine()) != null) {
					linesCount++;
					lines.add(line);
					if(linesCount == numberOfLines) break;
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
			BigDecimal amount = new BigDecimal(columns[2]);
			TransactionCustomer origin = new TransactionCustomer(columns[3], new BigDecimal(columns[4]), new BigDecimal(columns[5]));
			TransactionCustomer recipient = new TransactionCustomer(columns[6], new BigDecimal(columns[7]), new BigDecimal(columns[8]));
			boolean isFraud = Integer.parseInt(columns[9]) != 0;
			boolean isFlaggedFraud = Integer.parseInt(columns[10]) != 0;
			transaction = new Transaction(step, transactionType, amount, origin, recipient, isFraud, isFlaggedFraud);
		} catch (Exception e) {
			IO.println("Erro: " + line + " | " + e);
		}
		return Optional.ofNullable(transaction);
	}
}
