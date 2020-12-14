package com.sexyguys.suhang;

import com.sexyguys.suhang.repository.ArticleRepository;
import com.sexyguys.suhang.repository.JPAArticleRepository;
import com.sexyguys.suhang.repository.JPAUserRepository;
import com.sexyguys.suhang.repository.UserRepository;
import com.sexyguys.suhang.service.ArticleService;
import com.sexyguys.suhang.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfigure {

    private final EntityManager entityManager;

    public SpringConfigure(DataSource dataSource, EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public ArticleService articleService() {
        return new ArticleService(articleRepository());
    }

    @Bean
    public ArticleRepository articleRepository() {
        return new JPAArticleRepository(entityManager);
    }

    @Bean
    public UserRepository userRepository() {
        return new JPAUserRepository(entityManager);
    }
}