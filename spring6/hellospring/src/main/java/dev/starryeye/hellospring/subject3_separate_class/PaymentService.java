package dev.starryeye.hellospring.subject3_separate_class;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentService {

    /**
     * [3. 클래스 분리]
     * [2. 상속을 통한 확장] 에서 상속의 한계(상위 하위 클래스간 강한 결합 문제) 때문에 의존관계를 더 느슨하도록 변경하기 위해
     * 클래스 자체를 분리하였다.
     *
     * 문제점..
     * 구현체를 변경하고 싶은데 기존 코드를 건드려야한다.. (SRP, OCP 위반)
     */

    private final WebApiExchangeRateProvider exchangeRateProvider;

    public PaymentService() {
        this.exchangeRateProvider = new WebApiExchangeRateProvider();
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {

        /**
         * [1. 관심사의 분리]
         * prepare 비즈니스 로직이 변경될 때, 이 부분에 변경이 일어난다. (SRP)
         */

        BigDecimal exchangeRate = exchangeRateProvider.getWebExchangeRate(currency, "KRW");
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exchangeRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return Payment.builder()
                .orderId(orderId)
                .currency(currency)
                .foreignCurrencyAmount(foreignCurrencyAmount)
                .exchangeRate(exchangeRate)
                .convertedAmount(convertedAmount)
                .validUntil(validUntil)
                .build();
    }
}
