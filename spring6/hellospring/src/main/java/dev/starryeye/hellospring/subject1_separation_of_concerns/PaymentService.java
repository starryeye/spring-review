package dev.starryeye.hellospring.subject1_separation_of_concerns;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PaymentService {

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {

        /**
         * [1. 관심사의 분리]
         * prepare 비즈니스 로직이 변경될 때, 이 부분에 변경이 일어난다. (SRP)
         */

        BigDecimal exchangeRate = getExchangeRate(currency, "KRW");
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exchangeRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return Payment.builder()
                .orderId(orderId)
                .currency(currency)
                .foreignCurrencyAmount(foreignCurrencyAmount)
                .exchangeRate(exchangeRate)
                .convertedAmount(convertedAmount)
                .validUntil(validUntil)
                .build();
    }

    private BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) throws IOException {

        /**
         * [1. 관심사의 분리]
         * 환율을 어떻게 가져올지가 변경될 때, 이 부분에 변경이 일어난다. (SRP)
         */

        URL url = new URL("https://open.er-api.com/v6/latest/" + baseCurrency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = bufferedReader.lines().collect(Collectors.joining());
        bufferedReader.close();

        ObjectMapper objectMapper = new ObjectMapper();
        ExchangeRateData data = objectMapper.readValue(response, ExchangeRateData.class);
        BigDecimal exchangeRate = data.rates().get(quoteCurrency);
        return exchangeRate;
    }

    public static void main(String[] args) throws IOException {

        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.9));
        System.out.println(payment);
    }
}
