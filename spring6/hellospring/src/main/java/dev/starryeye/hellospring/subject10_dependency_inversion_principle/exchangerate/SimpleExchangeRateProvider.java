package dev.starryeye.hellospring.subject10_dependency_inversion_principle.exchangerate;

import dev.starryeye.hellospring.subject10_dependency_inversion_principle.payment.ExchangeRateProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class SimpleExchangeRateProvider implements ExchangeRateProvider {

    @Override
    public BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) throws IOException {

        if (baseCurrency.equals("USD") && quoteCurrency.equals("KRW"))
            return BigDecimal.valueOf(1000);

        throw new IllegalArgumentException("지원되지 않는 통화쌍입니다.");
    }
}
