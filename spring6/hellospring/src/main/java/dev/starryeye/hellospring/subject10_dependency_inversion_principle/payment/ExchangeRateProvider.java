package dev.starryeye.hellospring.subject10_dependency_inversion_principle.payment;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExchangeRateProvider {

    BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) throws IOException;
}
