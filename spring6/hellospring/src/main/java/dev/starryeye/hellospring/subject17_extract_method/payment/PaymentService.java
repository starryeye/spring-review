package dev.starryeye.hellospring.subject17_extract_method.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class PaymentService {

    private final ExchangeRateProvider exchangeRateProvider;
    private final Clock clock;

    public PaymentService(ExchangeRateProvider exchangeRateProvider, Clock clock) {
        this.exchangeRateProvider = exchangeRateProvider;
        this.clock = clock;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) {

        BigDecimal exchangeRate = exchangeRateProvider.getExchangeRate(currency, "KRW");

        return Payment.createPrepared(orderId, currency, foreignCurrencyAmount, exchangeRate, LocalDateTime.now(clock));
    }
}
