package com.li.config.info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:mysql.properties")
public class JdbcInfo {

    @Value("${mariadb.driver}")
    private String driver;

    @Value("${mariadb.url}")
    private String url;

    @Value("${mariadb.username}")
    private String username;

    @Value("${mariadb.password}")
    private String password;

//    @Value("${mariadb.filters}")
//    private String filters;
//
//    @Value("${mariadb.connectionProperties}")
//    private String connectionProperties;

    @Value("${mariadb.validationTimeout}")
    private long validationTimeout;

    @Value("${mariadb.connectionTimeout}")
    private long connectionTimeout;

    @Value("${mariadb.idleTimeout}")
    private long idleTimeout;

    @Value("${mariadb.maxLifetime}")
    private long maxLifetime;

    @Value("${mariadb.maximumPoolSize}")
    private int maximumPoolSize;

    @Value("${mariadb.readOnly}")
    private boolean readOnly;

    @Value("${mariadb.connectionTestQuery}")
    private String connectionTestQuery;

    public boolean getReadOnly() {
        return readOnly;
    }

    public String getConnectionTestQuery() {
        return connectionTestQuery;
    }

    public void setConnectionTestQuery(String connectionTestQuery) {
        this.connectionTestQuery = connectionTestQuery;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

//    public String getFilters() {
//        return filters;
//    }
//
//    public void setFilters(String filters) {
//        this.filters = filters;
//    }
//
//    public String getConnectionProperties() {
//        return connectionProperties;
//    }
//
//    public void setConnectionProperties(String connectionProperties) {
//        this.connectionProperties = connectionProperties;
//    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getValidationTimeout() {
        return validationTimeout;
    }

    public void setValidationTimeout(long validationTimeout) {
        this.validationTimeout = validationTimeout;
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public long getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(long idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public long getMaxLifetime() {
        return maxLifetime;
    }

    public void setMaxLifetime(long maxLifetime) {
        this.maxLifetime = maxLifetime;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

}
