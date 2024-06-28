package dev.starryeye.hellospring.subject5_separate_configuration;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) throws IOException {

        PaymentService paymentService = new PaymentService(new WebApiExchangeRateProvider());
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.9));
        System.out.println(payment);
    }
}
