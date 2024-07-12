package dev.starryeye.hellospring.subject21_order.order;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@ToString
@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String no;

    private BigDecimal totalPrice;

    @Builder
    private Order(Long id, String no, BigDecimal totalPrice) {
        this.id = id;
        this.no = no;
        this.totalPrice = totalPrice;
    }

    public static Order create(String no, BigDecimal totalPrice) {
        return Order.builder()
                .id(null)
                .no(no)
                .totalPrice(totalPrice)
                .build();
    }
}
