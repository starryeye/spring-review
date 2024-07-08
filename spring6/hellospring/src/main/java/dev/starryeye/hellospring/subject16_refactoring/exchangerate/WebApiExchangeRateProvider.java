package dev.starryeye.hellospring.subject16_refactoring.exchangerate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.starryeye.hellospring.subject16_refactoring.payment.ExchangeRateProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class WebApiExchangeRateProvider implements ExchangeRateProvider {

    @Override
    public BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) {

        String url = "https://open.er-api.com/v6/latest/" + baseCurrency;

        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();

            // try-with-resource
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = bufferedReader.lines().collect(Collectors.joining());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ExchangeRateData data = objectMapper.readValue(response, ExchangeRateData.class);
            return data.rates().get(quoteCurrency);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
