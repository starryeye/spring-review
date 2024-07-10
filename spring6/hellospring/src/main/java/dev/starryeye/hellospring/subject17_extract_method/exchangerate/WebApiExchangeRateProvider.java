package dev.starryeye.hellospring.subject17_extract_method.exchangerate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.starryeye.hellospring.subject17_extract_method.payment.ExchangeRateProvider;

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

        /**
         * subject17, 변경 가능성이 높은 코드는 메서드로 추출하였다.
         */

        String url = "https://open.er-api.com/v6/latest/" + baseCurrency;

        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = executeApi(uri);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return parseExchangeRate(quoteCurrency, response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static BigDecimal parseExchangeRate(String quoteCurrency, String response) throws JsonProcessingException {
        /**
         * JSON 문자열을 파싱하고 필요한 환율정보를 추출하는 코드
         * -> API 응답의 JSON 구조에 따라 정보를 추출하는 방식이 변경될 가능성이 있는 부분이라 메서드로 추출
         */

        ObjectMapper objectMapper = new ObjectMapper();
        ExchangeRateData data = objectMapper.readValue(response, ExchangeRateData.class);
        return data.rates().get(quoteCurrency);
    }

    private static String executeApi(URI uri) throws IOException {
        /**
         * API 를 실행하고 서버로부터 받은 응답을 가져오는 코드.
         * -> API 를 호출하는 기술과 방법이 변경될 가능성이 있는 부분이라 메서드로 추출
         */

        String response;
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();

        // try-with-resource
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            response = bufferedReader.lines().collect(Collectors.joining());
        }
        return response;
    }
}
