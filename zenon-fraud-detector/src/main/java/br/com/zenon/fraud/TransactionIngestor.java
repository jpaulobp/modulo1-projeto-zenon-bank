package br.com.zenon.fraud;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TransactionIngestor {

	public List<Transaction> ingest1000(String fileName) {
		List<Transaction> transactionList = new ArrayList<>();
		Path path = Path.of("data/"+fileName);
		// Use try-with-resources to automatically close the file
		int count = 1;
		try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
			for(int i=0; i<= 1000;i++){
				String line = br.readLine();
				if(i == 0){
					continue;
				}
				transactionList.add(parseTransaction(line));
			}
		} catch (FileNotFoundException e) {
			IO.println("Arquivo não encontrado.");
			throw new RuntimeException(e);
		} catch (IOException e) {
			IO.println("Erro ao ler o arquivo.");
			throw new RuntimeException(e);
		}
		return transactionList;
	}

	private Transaction parseTransaction(String line) {
		String[] columns = line.split(",");
		Transaction transaction = null;
		try {
			transaction = new Transaction(
					Integer.parseInt(columns[0]),
					TransactionType.valueOf(columns[1]),
					Double.parseDouble(columns[2]),
					new TransactionCustomer(columns[3], Double.parseDouble(columns[4]), Double.parseDouble(columns[5])),
					new TransactionCustomer(columns[6], Double.parseDouble(columns[7]), Double.parseDouble(columns[8])),
					Integer.parseInt(columns[9]) == 0,
					Integer.parseInt(columns[10]) == 0);
		} catch (Exception e) {
			IO.println("Erro ao converter a linha para Transaction.");
			throw new RuntimeException(e);
		}
		return transaction;
	}
}
