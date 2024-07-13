package dev.starryeye.hellospring.subject28_transaction_proxy;

import dev.starryeye.hellospring.subject28_transaction_proxy.data.JdbcOrderRepository;
import dev.starryeye.hellospring.subject28_transaction_proxy.order.OrderRepository;
import dev.starryeye.hellospring.subject28_transaction_proxy.order.OrderService;
import dev.starryeye.hellospring.subject28_transaction_proxy.order.OrderServiceImpl;
import dev.starryeye.hellospring.subject28_transaction_proxy.order.OrderServiceTransactionProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Import(DataConfig.class) // OrderConfig 를 구성할 때, DataConfig 도 구성한다.
public class OrderConfig {

    @Bean
    public OrderService orderService(
            OrderRepository orderRepository,
            PlatformTransactionManager transactionManager
    ) {
        return new OrderServiceTransactionProxy(new OrderServiceImpl(orderRepository), transactionManager);
    }

    @Bean
    public OrderRepository orderRepository(JdbcClient jdbcClient) {
        return new JdbcOrderRepository(jdbcClient);
    }
}
