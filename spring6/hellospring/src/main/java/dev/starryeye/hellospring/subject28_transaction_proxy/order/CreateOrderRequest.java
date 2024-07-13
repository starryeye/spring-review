package dev.starryeye.hellospring.subject28_transaction_proxy.order;

import java.math.BigDecimal;

public record CreateOrderRequest(
        String no,
        BigDecimal totalPrice
) {
}
