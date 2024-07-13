package dev.starryeye.hellospring.subject29_proxy_aop_transactional;

import dev.starryeye.hellospring.subject29_proxy_aop_transactional.data.JdbcOrderRepository;
import dev.starryeye.hellospring.subject29_proxy_aop_transactional.order.OrderRepository;
import dev.starryeye.hellospring.subject29_proxy_aop_transactional.order.OrderService;
import dev.starryeye.hellospring.subject29_proxy_aop_transactional.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(DataConfig.class) // OrderConfig 를 구성할 때, DataConfig 도 구성한다.
@EnableTransactionManagement // spring boot 를 사용하고 있지 않으므로 활성화 어노테이션이 필요하다.
public class OrderConfig {

    @Bean
    public OrderService orderService(OrderRepository orderRepository) {
        return new OrderServiceImpl(orderRepository);
    }

    @Bean
    public OrderRepository orderRepository(JdbcClient jdbcClient) {
        return new JdbcOrderRepository(jdbcClient);
    }
}
