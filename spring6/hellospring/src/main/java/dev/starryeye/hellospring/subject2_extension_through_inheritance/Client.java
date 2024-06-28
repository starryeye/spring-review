package dev.starryeye.hellospring.subject2_extension_through_inheritance;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) throws IOException {

        /**
         * [2. 상속을 통한 확장]
         * 환율을 어떻게 가져올 건지를 변경할 필요가 생기면, 비즈니스 코드를 수정하지 않고 확장하고 변경이 가능하다. (OCP)
         * 구현체만 변경하면 됨
         */

        PaymentService paymentService = new WebApiExchangeRatePaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.9));
        System.out.println(payment);
    }
}
