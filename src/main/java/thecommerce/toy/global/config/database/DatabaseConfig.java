package thecommerce.toy.global.config.database;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// QueryDSL 설정
@Configuration
public class DatabaseConfig {

    //    EntityManager 설정
    @PersistenceContext
    private EntityManager entityManager;

    //    JPAQueryFactory Bean 등록
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
