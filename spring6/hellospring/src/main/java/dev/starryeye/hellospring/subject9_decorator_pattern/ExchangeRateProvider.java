package dev.starryeye.hellospring.subject9_decorator_pattern;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExchangeRateProvider {

    BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) throws IOException;
}
