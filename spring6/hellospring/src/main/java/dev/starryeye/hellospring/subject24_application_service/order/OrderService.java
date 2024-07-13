package dev.starryeye.hellospring.subject24_application_service.order;

import dev.starryeye.hellospring.subject24_application_service.data.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final JpaTransactionManager transactionManager;

    public Order createOrder(String no, BigDecimal totalPrice) {

        Order order = Order.create(no, totalPrice);

        /**
         * @Transactional 없이 TransactionTemplate 을 사용하여 트랜잭션 처리를 했다.
         */
        return new TransactionTemplate(transactionManager).execute(status -> {

            orderRepository.save(order);
            return order; // createOrder 메서드의 최종 리턴 값이 된다.
        });
    }
}
