package dev.starryeye.hellospring.subject8_component_scan;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExchangeRateProvider {

    BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) throws IOException;
}
