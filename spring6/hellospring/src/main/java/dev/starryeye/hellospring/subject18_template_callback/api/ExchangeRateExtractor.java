package dev.starryeye.hellospring.subject18_template_callback.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

public interface ExchangeRateExtractor {

    BigDecimal extract(String quoteCurrency, String response) throws JsonProcessingException;
}
