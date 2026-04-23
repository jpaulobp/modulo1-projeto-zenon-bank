package br.com.zenon.fraud;

public class Main {

	void main() {

		Transaction t1 = new Transaction(1, TransactionType.PAYMENT, 9839.64, new TransactionCustomer("C1231006815", 170136.0, 160296.36), new TransactionCustomer("M1979787155", 0.0, 0.0), false, false);
		Transaction t2 = new Transaction(743, TransactionType.CASH_OUT, 850002.52, new TransactionCustomer("C1280323807", 850002.52, 0.0), new TransactionCustomer("C873221189", 6510099.11, 7360101.63), true, false);

		IO.println(t1);
		IO.println(t2);

	}
}
