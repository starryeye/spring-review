package dev.starryeye.hellospring.subject20_rest_template.payment;

import java.math.BigDecimal;

public interface ExchangeRateProvider {

    BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency);
}
