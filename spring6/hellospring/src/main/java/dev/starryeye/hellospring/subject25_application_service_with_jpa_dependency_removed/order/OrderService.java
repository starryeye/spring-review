package dev.starryeye.hellospring.subject25_application_service_with_jpa_dependency_removed.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    /**
     * subject24 의 OrderService 는 3 가지 포인트에서 JPA 를 의존한다고 볼 수 있음
     * 1. OrderRepository 가 JPA 기술에 의존하여 OrderService 도 JPA 기술을 의존
     * 2. Order 가 컴파일시점에 JPA 기술에 의존하여 OrderService 도 JPA 기술을 의존
     * 3. JpaTransactionManager 를 사용하여 OrderService 가 JPA 기술을 의존
     *
     * subject25 에서는 OrderService 가 JPA 기술을 의존하지 않도록 바꿔본다.
     * 1 번은 DIP 를 이용하여 해결
     * 2 번은 orm.xml 을 이용하여 JPA 메타정보를 대신 전달하도록 하여 해결
     * 3 번은 Spring 에서 제공하는 트랜잭션 관리 추상화(인터페이스)에 의존하여 해결 -> 결국 DIP
     *
     * 결론 subject25 의 OrderService 는 기술(JPA) 에 의존하지 않는다.
     */

    private final OrderRepository orderRepository; // DIP 적용

    private final PlatformTransactionManager transactionManager;

    public Order createOrder(String no, BigDecimal totalPrice) {

        Order order = Order.create(no, totalPrice); // Order 에는 컴파일 시점에도 JPA 의존성이 없도록 함

        /**
         * @Transactional 없이 TransactionTemplate 을 사용하여 트랜잭션 처리를 했다.
         */
        return new TransactionTemplate(transactionManager).execute(status -> {

            orderRepository.save(order);
            return order; // createOrder 메서드의 최종 리턴 값이 된다.
        });
    }
}
