package dev.starryeye.hellospring.subject21_order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class DataConfig {

    // DataSource 만들기
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    // EntityManagerFactory 만들기
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        /**
         * 참고..
         * 중괄호 두개.. 더블 브래킷 기법..
         * -> 익명클래스 블록 + 인스턴스 초기화 블록
         */

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource()); // DataSource
        emf.setPackagesToScan("dev.starryeye.hellospring"); // @Entity scan
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {{
            setDatabase(Database.H2);
            setGenerateDdl(true);
            setShowSql(true);
        }});

        return emf;
    }
}
