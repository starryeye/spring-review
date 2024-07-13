package dev.starryeye.hellospring.subject27_transaction_test.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    /**
     * subject26 에서는 JPA 를 사용하던 것에서 JdbcClient 로 DB 접근 기술을 변경한다.
     * subject25 에서 Application Service 인 OrderService 를 기술에 의존하지 않도록 변경해놨기 때문에
     * OrderService 는 변경이 일어나지 않는다.
     */

    private final OrderRepository orderRepository; // DIP 적용

    private final PlatformTransactionManager transactionManager;

    public Order createOrder(String no, BigDecimal totalPrice) {

        Order order = Order.create(no, totalPrice); // Order 에는 컴파일 시점에도 JPA 의존성이 없도록 함


        return new TransactionTemplate(transactionManager).execute(status -> {

            orderRepository.save(order);
            return order; // createOrder 메서드의 최종 리턴 값이 된다.
        });
    }

    public List<Order> createOrders(List<CreateOrderRequest> orderRequests) {

        return new TransactionTemplate(transactionManager)
                .execute(status -> orderRequests.stream()
                        .map(orderRequest -> createOrder(orderRequest.no(), orderRequest.totalPrice()))
                        .toList());
    }
}
