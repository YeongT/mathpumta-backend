package com.sexyguys.suhang;

import com.sexyguys.suhang.repository.ArticleRepository;
import com.sexyguys.suhang.repository.JPAArticleRepository;
import com.sexyguys.suhang.repository.JPAUserRepository;
import com.sexyguys.suhang.repository.UserRepository;
import com.sexyguys.suhang.service.ArticleService;
import com.sexyguys.suhang.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManager;

//DI Injection 구현을 위해 Bean과 Autowired를 조합해 필요할때 의존성이 주입되도록 함.
@Configuration
public class SpringConfigure implements WebMvcConfigurer {

    private final EntityManager entityManager;

    public SpringConfigure(EntityManager entityManager) {
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

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*");
    }
}
