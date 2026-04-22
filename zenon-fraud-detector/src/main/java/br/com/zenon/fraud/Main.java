package br.com.zenon.fraud;

public class Main {

    static void main() {

        Transaction t1 = new Transaction(1, TypeEnum.PAYMENT, 9839.64, "C1231006815", 170136.0,
                160296.36, "M1979787155", 0.0, 0.0, false, false);

        Transaction t2 = new Transaction(743, TypeEnum.CASH_OUT, 850002.52, "C1280323807", 850002.52,
                0.0, "C873221189", 6510099.11, 7360101.63, true, false);

        System.out.println(t1);
        System.out.println(t2);

    }
}
