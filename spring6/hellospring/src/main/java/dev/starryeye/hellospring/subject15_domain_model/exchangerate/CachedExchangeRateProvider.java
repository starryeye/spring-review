package dev.starryeye.hellospring.subject15_domain_model.exchangerate;

import dev.starryeye.hellospring.subject15_domain_model.payment.ExchangeRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExchangeRateProvider implements ExchangeRateProvider {

    private final ExchangeRateProvider target;

    private BigDecimal USDKRWRate;
    private LocalDateTime USDKRWExpiryTime = LocalDateTime.now();

    public CachedExchangeRateProvider(ExchangeRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) throws IOException {

        if (!baseCurrency.equals("USD") || !quoteCurrency.equals("KRW")) {
            return target.getExchangeRate(baseCurrency, quoteCurrency);
        }

        if (USDKRWRate == null || USDKRWExpiryTime.isBefore(LocalDateTime.now())) {
            USDKRWRate = this.target.getExchangeRate(baseCurrency, quoteCurrency);
            USDKRWExpiryTime = LocalDateTime.now().plusSeconds(3);
            System.out.println("Cache updated..");
        }

        return USDKRWRate;
    }
}
