package dev.starryeye.hellospring.subject14_clock.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class PaymentService {

    /**
     * [5. 관계구성(설정) 책임의 분리]
     * [4. 인터페이스 도입] 에서 구현체 변경을 하고 싶으면 기존 코드(PaymentService 생성자)를 변경했어야 했는데..
     * 외부에서 구현체를 넘겨주기(DI, Dependency Injection) 때문에 구현체에 대한 의존성이 완전히 사라졌다.
     *
     */

    private final ExchangeRateProvider exchangeRateProvider;
    private final Clock clock;

    public PaymentService(ExchangeRateProvider exchangeRateProvider, Clock clock) {
        this.exchangeRateProvider = exchangeRateProvider;
        this.clock = clock;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {

        /**
         * [1. 관심사의 분리]
         * prepare 비즈니스 로직이 변경될 때, 이 부분에 변경이 일어난다. (SRP)
         */

        BigDecimal exchangeRate = exchangeRateProvider.getExchangeRate(currency, "KRW");
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exchangeRate);
        LocalDateTime validUntil = LocalDateTime.now(clock).plusMinutes(30);

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
