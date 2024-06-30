package dev.starryeye.hellospring.subject7_spring_container;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExchangeRateProvider {

    BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) throws IOException;
}
