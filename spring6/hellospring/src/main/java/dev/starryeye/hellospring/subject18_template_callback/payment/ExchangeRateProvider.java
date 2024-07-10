package dev.starryeye.hellospring.subject18_template_callback.payment;

import java.math.BigDecimal;

public interface ExchangeRateProvider {

    BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency);
}
