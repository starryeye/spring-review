package dev.starryeye.hellospring.subject20_rest_template.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

public interface ExchangeRateExtractor {

    BigDecimal extract(String quoteCurrency, String response) throws JsonProcessingException;
}
