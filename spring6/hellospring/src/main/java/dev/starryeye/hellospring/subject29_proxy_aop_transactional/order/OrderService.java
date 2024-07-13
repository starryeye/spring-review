package dev.starryeye.hellospring.subject29_proxy_aop_transactional.order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order createOrder(String no, BigDecimal totalPrice);

    List<Order> createOrders(List<CreateOrderRequest> orderRequests);
}
