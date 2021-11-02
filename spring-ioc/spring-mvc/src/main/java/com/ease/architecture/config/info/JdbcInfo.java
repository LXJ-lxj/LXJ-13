package com.ease.architecture.config.info;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:mysql.properties")
public class JdbcInfo {
//将配置映射到响应的变量上，通过get让外界访问
    @Value("${JDBC.url}")//通过properies 拿到当中的key为url的值赋值个url变量
    private String url;

    @Value("${JDBC.driver}")
    private String driver;

    @Value("${JDBC.username}")
    private String username;

    @Value("${JDBC.password}")
    private String password;

    @Value("${JDBC.filters}")
    private String filters;


    @Value("${JDBC.connectionProperties}")
    private String connectionProperties;

//    @Value("${mariaDB.validationQuery}")
//    private String validationQuery;
//
//    public String getValidationQuery() {
//        return validationQuery;
//    }


    public String getFilters() {
        return filters;
    }

    public String getConnectionProperties() {
        return connectionProperties;
    }


    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
