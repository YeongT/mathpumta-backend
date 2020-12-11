package com.sexyguys.suhang;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource(ignoreResourceNotFound = true, value = "classpath:config/database.properties")
class DataSourceConfigure {
    @Value("${database.host:}")
    private String databaseUrl;

    @Value("${database.user:}")
    private String databaseUser;

    @Value("${database.password:}")
    private String databasePassword;

    @Bean
    @Primary
    public DataSource getDataSource() {
        if (databaseUrl.isBlank()) databaseUrl = System.getenv("DB_HOST");
        if (databaseUser.isBlank()) databaseUser = System.getenv("DB_USER_ID");
        if (databasePassword.isBlank()) databasePassword = System.getenv("DB_USER_PW");
        return DataSourceBuilder
                .create()
                .url(databaseUrl)
                .username(databaseUser)
                .password(databasePassword)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
