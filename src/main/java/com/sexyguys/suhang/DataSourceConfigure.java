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
    @Value("${databaseUrl}")
    private String databaseUrl;

    @Value("${databaseUser}")
    private String databaseUser;

    @Value("${databasePassword}")
    private String databasePassword;

    @Bean
    @Primary
    public DataSource getDataSource() {
        if (databaseUrl.isEmpty()) databaseUrl = System.getenv("DB_HOST_URL");
        if (databaseUser.isEmpty()) databaseUrl = System.getenv("DB_USER_ID");
        if (databasePassword.isEmpty()) databaseUrl = System.getenv("DB_USER_PW");

        return DataSourceBuilder
                .create()
                .url(databaseUrl)
                .username(databaseUser)
                .password(databasePassword)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
