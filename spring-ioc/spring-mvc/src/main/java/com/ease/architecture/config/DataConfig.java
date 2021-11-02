package com.ease.architecture.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.ease.architecture.config.info.JdbcInfo;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DataConfig {
    /**
     * 初始化jdbc
     *
     */
    @Resource//表示注入一个资源
    private JdbcInfo jdbcInfo;


    @Bean//通过bean用spring管理起来
    public DataSource getDataSource() throws SQLException {
        //加载数据源，通过datasource可以随意的切换数据库 来获取数据源
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();  通过驱动器拿到DataSource
//        dataSource.setUrl(jdbcInfo.getUrl());    以下进行属性配置
//        dataSource.setUsername(jdbcInfo.getUsername());
//        dataSource.setPassword(jdbcInfo.getPassword());
//        dataSource.setDriverClassName(jdbcInfo.getDriver());
       /* DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(jdbcInfo.getUrl());
        dataSource.setUsername(jdbcInfo.getUsername());
        dataSource.setPassword(jdbcInfo.getPassword());
        dataSource.setDriverClassName(jdbcInfo.getDriver());
        dataSource.setFilters(jdbcInfo.getFilters());
        dataSource.setConnectionProperties(jdbcInfo.getConnectionProperties());*/
//        dataSource.setValidationQuery(jdbcInfo.getValidationQuery());
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(jdbcInfo.getDriver());
        dataSource.setJdbcUrl(jdbcInfo.getUrl());
        dataSource.setPassword(jdbcInfo.getPassword());
        dataSource.setUsername(jdbcInfo.getUsername());
        return dataSource;
    }

    @Bean
    //操作sql的工具
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
