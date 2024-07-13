package dev.starryeye.hellospring.subject29_proxy_aop_transactional.data;

import dev.starryeye.hellospring.subject29_proxy_aop_transactional.order.Order;
import dev.starryeye.hellospring.subject29_proxy_aop_transactional.order.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.sql.Types;

public class JdbcOrderRepository implements OrderRepository {

    /**
     * Spring 6.1 에서 추가된 JdbcTemplate 의 모던한 버전이다.
     */
    private final JdbcClient jdbcClient;

    public JdbcOrderRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @PostConstruct
    public void initDb() {

        /**
         * spring boot 를 이용하여 db schema 를 실행할때 구성하는 기능을 사용하지 않고
         * 그냥 빈 초기화 시점에 table 생성 sql 을 JdbcClient 를 통해 전달한다.
         */

        jdbcClient.sql("""
                create table orders (id bigint not null, no varchar(255), totalPrice numeric(38,2), primary key (id));
                alter table if exists orders drop constraint if exists UK43egxxciqr9ncgmxbdx2avi8n;
                alter table if exists orders add constraint UK43egxxciqr9ncgmxbdx2avi8n unique (no);
                create sequence orders_SEQ start with 1 increment by 50;
                """).update();
    }

    @Override
    public void save(Order order) {
        Long id = jdbcClient.sql("select next value for orders_SEQ")
                .query(Long.class)
                .single();

        order.setId(id); // 원래 save 메서드 리턴을 Order 로 해놓으면 setId 이런거 필요 없는데.. (JpaRepository 를 사용해도 save 리턴 타입이 엔티티 타입이다..)

        jdbcClient.sql("insert into orders (no,totalPrice,id) values (:no, :totalPrice, :id)")
                .param("no", order.getNo(), Types.VARCHAR)
                .param("totalPrice", order.getTotalPrice(), Types.NUMERIC)
                .param("id", order.getId(), Types.BIGINT)
                .update();
    }
}
