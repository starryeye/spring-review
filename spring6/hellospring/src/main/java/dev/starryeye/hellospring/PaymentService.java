package dev.starryeye.hellospring;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class PaymentService {

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {

        /**
         * [1. 관심사의 분리]
         * prepare 비즈니스 로직이 변경될 때, 이 부분에 변경이 일어난다. (SRP)
         */

        BigDecimal exchangeRate = getExchangeRate(currency, "KRW");
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

    abstract BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) throws IOException;
}
