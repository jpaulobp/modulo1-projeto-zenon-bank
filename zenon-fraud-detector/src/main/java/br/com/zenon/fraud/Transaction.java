package br.com.zenon.fraud;

public record Transaction(int step, TypeEnum type, double amount, String nameOrig, double oldbalanceOrg,
                          double newbalanceOrig, String nameDest, double oldbalanceDest,
                          double newbalanceDest, boolean isFraud, boolean isFlaggedFraud) {
}