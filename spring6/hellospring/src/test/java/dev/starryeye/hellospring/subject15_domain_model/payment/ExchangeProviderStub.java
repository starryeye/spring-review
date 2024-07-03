package dev.starryeye.hellospring.subject15_domain_model.payment;

import java.io.IOException;
import java.math.BigDecimal;

public class ExchangeProviderStub implements ExchangeRateProvider {

    private final BigDecimal exchangeRate;

    public ExchangeProviderStub(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) throws IOException {
        return exchangeRate;
    }
}
