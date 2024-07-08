package dev.starryeye.hellospring.subject16_refactoring.exchangerate;

import dev.starryeye.hellospring.subject16_refactoring.payment.ExchangeRateProvider;

import java.math.BigDecimal;

public class SimpleExchangeRateProvider implements ExchangeRateProvider {

    @Override
    public BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) {

        if (baseCurrency.equals("USD") && quoteCurrency.equals("KRW"))
            return BigDecimal.valueOf(1000);

        throw new IllegalArgumentException("지원되지 않는 통화쌍입니다.");
    }
}
