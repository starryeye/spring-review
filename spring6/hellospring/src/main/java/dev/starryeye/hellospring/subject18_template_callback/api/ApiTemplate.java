package dev.starryeye.hellospring.subject18_template_callback.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {

    public BigDecimal getExchangeRate(String quoteCurrency, String url, ApiExecutor apiExecutor, ExchangeRateExtractor exchangeRateExtractor) {
        /**
         * template
         */

        // 1. API 호출을 위한 사전 작업
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // 2. API 호출
        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 3. API 호출 응답에서 원하는 값 추출
        try {
            return exchangeRateExtractor.extract(quoteCurrency, response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
