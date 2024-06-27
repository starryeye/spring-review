package dev.starryeye.hellospring;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) {

        /**
         * todo
         *  환율 가져오기
         *  금액 계산
         *  유효시간 계산
         */
        return Payment.builder()
                .orderId(orderId)
                .currency(currency)
                .foreignCurrencyAmount(foreignCurrencyAmount)
                .exchangeRate(BigDecimal.ZERO)
                .convertedAmount(BigDecimal.ZERO)
                .validUntil(LocalDateTime.now())
                .build();
    }

    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.9));
        System.out.println(payment);
    }
}
