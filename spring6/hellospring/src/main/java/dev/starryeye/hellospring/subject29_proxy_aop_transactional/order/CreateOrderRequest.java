package dev.starryeye.hellospring.subject29_proxy_aop_transactional.order;

import java.math.BigDecimal;

public record CreateOrderRequest(
        String no,
        BigDecimal totalPrice
) {
}
