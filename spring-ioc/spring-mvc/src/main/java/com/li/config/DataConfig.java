package com.li.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.hu.config.info.JdbcInfo;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DataConfig {

    @Resource
    private JdbcInfo jdbcInfo;

    @Bean
    public DataSource getDataSource() throws SQLException { //拿到mariaDb的数据源
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl(jdbcInfo.getUrl());
//        dataSource.setDriverClassName(jdbcInfo.getDriver());
//        dataSource.setPassword(jdbcInfo.getPassword());
//        dataSource.setUsername(jdbcInfo.getUsername());
//        return dataSource;

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(jdbcInfo.getUrl());
        hikariDataSource.setDriverClassName(jdbcInfo.getDriver());
        hikariDataSource.setUsername(jdbcInfo.getUsername());
        hikariDataSource.setPassword(jdbcInfo.getPassword());
        hikariDataSource.setValidationTimeout(jdbcInfo.getValidationTimeout());
        hikariDataSource.setConnectionTimeout(jdbcInfo.getConnectionTimeout());
        hikariDataSource.setIdleTimeout(jdbcInfo.getIdleTimeout());
        hikariDataSource.setMaxLifetime(jdbcInfo.getMaxLifetime());
        hikariDataSource.setMaximumPoolSize(jdbcInfo.getMaximumPoolSize());
        hikariDataSource.setReadOnly(jdbcInfo.getReadOnly());
        hikariDataSource.setConnectionTestQuery(jdbcInfo.getConnectionTestQuery());
        return hikariDataSource;

//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(jdbcInfo.getUrl());
//        dataSource.setDriverClassName(jdbcInfo.getDriver());
//        dataSource.setPassword(jdbcInfo.getPassword());
//        dataSource.setUsername(jdbcInfo.getUsername());
//        dataSource.setFilters(jdbcInfo.getFilters());
//        dataSource.setConnectionProperties(jdbcInfo.getConnectionProperties());
//        return dataSource;

    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) { //直接从spring容器里拿datasource
        return new JdbcTemplate(dataSource);
    }

}
