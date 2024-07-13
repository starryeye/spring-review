package dev.starryeye.hellospring.subject25_application_service_with_jpa_dependency_removed.order;

import dev.starryeye.hellospring.subject25_application_service_with_jpa_dependency_removed.OrderConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
class OrderServiceSpringTest {

    @Autowired
    private OrderService orderService;

    @Test
    void  createOrder() {

        var order = orderService.createOrder("0100", BigDecimal.TWO);

        assertThat(order.getId()).isPositive();
    }

}