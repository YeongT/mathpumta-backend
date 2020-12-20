package com.sexyguys.suhang;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

//외부에 있는 데이터베이스와 통신하도록 data.properties 파일에 분리하여 데이터베이스 설정을 저장함
//해당 파일이 존재하지 않는 경우에는(Travis CI/Heroku CD) 환경 변수를 불러와 적용하도록 함.
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
    //처음 시작할때 데이터소스를 지정해주게끔 설계함.
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
