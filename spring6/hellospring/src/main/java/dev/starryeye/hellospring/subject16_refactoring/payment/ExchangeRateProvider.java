package dev.starryeye.hellospring.subject16_refactoring.payment;

import java.math.BigDecimal;

public interface ExchangeRateProvider {

    BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency);
}
