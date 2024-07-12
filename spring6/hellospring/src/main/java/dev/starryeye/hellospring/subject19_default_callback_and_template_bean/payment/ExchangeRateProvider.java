package dev.starryeye.hellospring.subject19_default_callback_and_template_bean.payment;

import java.math.BigDecimal;

public interface ExchangeRateProvider {

    BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency);
}
