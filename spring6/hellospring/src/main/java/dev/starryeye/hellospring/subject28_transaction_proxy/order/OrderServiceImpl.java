package dev.starryeye.hellospring.subject28_transaction_proxy.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    /**
     * subject28
     * OrderService 는 비즈니스 로직만 존재해야한다.
     * Transaction 처리 관련 로직은 있으면 안된다. (비즈니스 로직과 트랜잭션 처리 로직은 서로 관심사가 다르다.)
     *
     * Transaction 처리는 Proxy 패턴을 이용하여 처리해본다.
     * OrderClient -> OrderServiceTransactionProxy -> OrderServiceImpl
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
