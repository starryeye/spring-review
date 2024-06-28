package dev.starryeye.hellospring;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class WebApiExchangeRatePaymentService extends PaymentService {

    @Override
    BigDecimal getExchangeRate(String baseCurrency, String quoteCurrency) throws IOException {

        /**
         * [1. 관심사의 분리]
         * 환율을 어떻게 가져올지가 변경될 때, 이 부분에 변경이 일어난다. (SRP)
         *
         * [2. 상속을 통한 확장]
         * 상속을 통해 비즈니스 코드는 재사용하면서, 환율을 가져오는 다양한 방법을 만들 수 있다.
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
}
