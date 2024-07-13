package dev.starryeye.hellospring.subject28_transaction_proxy.order;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceTransactionProxy implements OrderService{

    /**
     * subject28
     * Proxy pattern 을 사용하여 target 에 transaction 처리 로직을 적용한다.
     * -> target 에서 transaction 처리 로직을 분리하여 target 은 transaction 기술 의존성이 없어짐
     */

    private final OrderService target;
    private final PlatformTransactionManager transactionManager;

    public OrderServiceTransactionProxy(OrderService target, PlatformTransactionManager transactionManager) {
        this.target = target;
        this.transactionManager = transactionManager;
    }

    @Override
    public Order createOrder(String no, BigDecimal totalPrice) {

        return new TransactionTemplate(transactionManager)
                .execute(status -> target.createOrder(no, totalPrice));
    }

    @Override
    public List<Order> createOrders(List<CreateOrderRequest> orderRequests) {

        return new TransactionTemplate(transactionManager)
                .execute(status -> target.createOrders(orderRequests));
    }
}
