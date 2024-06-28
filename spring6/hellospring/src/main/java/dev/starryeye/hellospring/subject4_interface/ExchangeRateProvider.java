package dev.starryeye.hellospring.subject4_interface;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExchangeRateProvider {

    BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) throws IOException;
}
