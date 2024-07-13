package dev.starryeye.hellospring.subject29_proxy_aop_transactional.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    /**
     * subject29
     *
     * spring 에서 제공하는 선언적 트랜잭션..
     * Spring proxy AOP 를 이용한 Transaction Proxy AOP
     * @Transactional 을 이용하여 트랜잭션 처리 로직을 수행하도록한다.
     */

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(String no, BigDecimal totalPrice) {

        Order order = Order.create(no, totalPrice);

        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> createOrders(List<CreateOrderRequest> orderRequests) {

        return orderRequests.stream()
                .map(orderRequest -> createOrder(orderRequest.no(), orderRequest.totalPrice()))
                .toList();
    }
}
