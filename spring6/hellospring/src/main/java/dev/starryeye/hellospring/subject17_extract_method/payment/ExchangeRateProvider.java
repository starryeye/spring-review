package dev.starryeye.hellospring.subject17_extract_method.payment;

import java.math.BigDecimal;

public interface ExchangeRateProvider {

    BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency);
}
