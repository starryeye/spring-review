package dev.starryeye.hellospring.subject23_exception_abstraction;

import dev.starryeye.hellospring.subject23_exception_abstraction.data.OrderRepository;
import dev.starryeye.hellospring.subject23_exception_abstraction.order.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

@Slf4j
public class DataClient {

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

        /**
         * TransactionTemplate..
         * 트랜잭션 처리를 템플릿 콜백 패턴으로 사용할 수 있게 만들었다. (성공하면 commit, 실패하면 rollback 처리.. 를 템플릿화 함)
         * -> @Transactional 선언적 트랜잭션 처리를 안하면 이런식으로 처리..
         *
         * 콜백은 수행할 작업을 넘겨주면 된다. (여기선, order 엔티티 저장)
         *
         * 콜백에서 무슨 기술을 사용하던지.. Spring Data Access Exception 으로 변환 해서 최종 throw 되기 때문에
         * 기술이 변하더라도.. (ex, MyBatis -> JPA)
         * catch 의 코드가 변경 되지 않는다.
         */
        try {
            new TransactionTemplate(transactionManager)
                    .execute(status -> {
                        Order order1 = Order.create("100", BigDecimal.TEN);
                        orderRepository.save(order1);

                        Order order2 = Order.create("100", BigDecimal.TEN);
                        orderRepository.save(order2); // exception 발생!! no 는 유니크 제약조건이 DB 에 걸려있다.

                        return null;
                    });
        } catch (DataIntegrityViolationException e) { // DataAccessException (스프링 DB access 추상화 예외) 의 하위 예외이다.
            // 여기선 원하는 스프링의 추상 예외를 잡아서 적절한 처리(재시도, 새로운 예외 등) 를 하면 된다.
            log.error(e.getMessage());
            throw e;
        }
    }
}
