package dev.starryeye.hellospring.subject7_spring_container;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class WebApiExchangeRateProvider implements ExchangeRateProvider {

    @Override
    public BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) throws IOException {

        URL url = new URL("https://open.er-api.com/v6/latest/" + baseCurrency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = bufferedReader.lines().collect(Collectors.joining());
        bufferedReader.close();

        ObjectMapper objectMapper = new ObjectMapper();
        ExchangeRateData data = objectMapper.readValue(response, ExchangeRateData.class);
        return data.rates().get(quoteCurrency);
    }
}
