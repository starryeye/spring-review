package dev.starryeye.hellospring.subject26_jdbc_client;

import dev.starryeye.hellospring.subject26_jdbc_client.data.JdbcOrderRepository;
import dev.starryeye.hellospring.subject26_jdbc_client.data.JpaOrderRepository;
import dev.starryeye.hellospring.subject26_jdbc_client.order.OrderRepository;
import dev.starryeye.hellospring.subject26_jdbc_client.order.OrderService;
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
            PlatformTransactionManager transactionManager,
            OrderRepository orderRepository
    ) {
        return new OrderService(orderRepository, transactionManager);
    }

    @Bean
    public OrderRepository orderRepository(JdbcClient jdbcClient) {
        return new JdbcOrderRepository(jdbcClient);
    }
}
