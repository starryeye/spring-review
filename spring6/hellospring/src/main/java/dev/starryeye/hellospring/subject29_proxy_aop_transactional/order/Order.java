package dev.starryeye.hellospring.subject29_proxy_aop_transactional.order;

import lombok.*;

import java.math.BigDecimal;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    /**
     * META-INF/orm.xml 을 이용하여 JPA 메타데이터를 전달하도록하여
     * JPA 기술 관련 어노테이션을 모두 제거할 수 있다.
     *
     * 참고
     * 엔티티는 JPA 어노테이션을 씀으로 해서.. 컴파일 타임에만 JPA 에 의존하는 것이다.
     * .class 파일 코드에는 JPA 기술 관련 내용이 없으며, 런타임에도 JPA 라이브러리에 의존하지 않는다.
     */

    private Long id;

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

    public void setId(Long id) {
        this.id = id;
    }
}
