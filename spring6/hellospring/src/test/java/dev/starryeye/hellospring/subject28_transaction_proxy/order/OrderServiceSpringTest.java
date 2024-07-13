package dev.starryeye.hellospring.subject28_transaction_proxy.order;

import dev.starryeye.hellospring.subject28_transaction_proxy.OrderConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
class OrderServiceSpringTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private JdbcClient jdbcClient;

    @AfterEach
    void tearDown() {
        jdbcClient.sql("truncate table orders").update();
    }

    @Test
    void createOrders() {

        // given
        List<CreateOrderRequest> requests = List.of(
                new CreateOrderRequest("0100", BigDecimal.ONE),
                new CreateOrderRequest("0200", BigDecimal.TWO)
        );

        // when
        List<Order> orders = orderService.createOrders(requests);

        // then
        assertThat(orders).hasSize(2)
                .extracting("no", "totalPrice")
                .containsExactlyInAnyOrder(
                        tuple("0100", BigDecimal.ONE),
                        tuple("0200", BigDecimal.TWO)
                );

        orders.forEach(order -> assertThat(order.getId()).isPositive());
    }

    @Test
    void createDuplicationOrders() {
        /**
         * Transaction 이 orderService.createOrders 에 전 범위로 걸려있는지 테스트..
         * 트랜잭션 중간에 예외를 발생시켜 모두가 롤백되는지 확인해본다.
         */

        // given
        List<CreateOrderRequest> requests = List.of(
                new CreateOrderRequest("0100", BigDecimal.ONE),
                new CreateOrderRequest("0100", BigDecimal.TWO) // no 는 DB 에 유니크 제약이 걸려있어서 예외가 발생
        );

        // when
        // then
        assertThatThrownBy(() -> orderService.createOrders(requests))
                .isInstanceOf(DuplicateKeyException.class);

        Long count = jdbcClient.sql("select count(*) from orders where no = '0100'")
                .query(Long.class)
                .single();
        assertThat(count).isZero();
    }
}