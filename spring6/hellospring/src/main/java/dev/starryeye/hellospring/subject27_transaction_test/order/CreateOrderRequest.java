package dev.starryeye.hellospring.subject27_transaction_test.order;

import java.math.BigDecimal;

public record CreateOrderRequest(
        String no,
        BigDecimal totalPrice
) {
}
