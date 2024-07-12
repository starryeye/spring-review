package dev.starryeye.hellospring.subject20_rest_template.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.starryeye.hellospring.subject20_rest_template.exchangerate.ExchangeRateData;

import java.math.BigDecimal;

public class ErApiExchangeRateExtractor implements ExchangeRateExtractor {

    @Override
    public BigDecimal extract(String quoteCurrency, String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ExchangeRateData data = objectMapper.readValue(response, ExchangeRateData.class);
        return data.rates().get(quoteCurrency);
    }
}
