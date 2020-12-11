package com.sexyguys.suhang;

import com.sexyguys.suhang.repository.JPAUserRepository;
import com.sexyguys.suhang.repository.UserRepository;
import com.sexyguys.suhang.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfigure {

    private final EntityManager entityManager;
    private final DataSource dataSource;

    public SpringConfigure(DataSource dataSource, EntityManager entityManager) {
        this.dataSource = dataSource;
        this.entityManager = entityManager;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new JPAUserRepository(entityManager);
    }
}
