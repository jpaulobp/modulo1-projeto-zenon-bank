package br.com.zenon.fraud;

public record Transaction(int step, TransactionType type, double amount, TransactionCustomer origin,
                          TransactionCustomer recipient, boolean isFraud,
                          boolean isFlaggedFraud) {
}