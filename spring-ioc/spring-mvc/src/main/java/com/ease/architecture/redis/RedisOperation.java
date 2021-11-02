package com.hu.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.stereotype.Component;

@Component
public class RedisOperation {

    private RedisClient redisClient;
    private StatefulRedisConnection<String, String> connection;

    public void init() {
        redisClient = RedisClient.create("redis://localhost");
        connection = redisClient.connect();
    }

    public boolean select(String username, String password) {
        String value = connection.sync().hget("redis-test", username);
        return password.equals(value);
    }

    public boolean delete(String username) {
        Long variety = connection.sync().hdel("redis-test", username);
        return variety != 0;
    }

    public boolean insert(String username, String password) {
        return connection.sync().hset("redis-test", username, password);
    }

    public void destroy() {
        connection.close();
        redisClient.shutdown();
    }

    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public StatefulRedisConnection<String, String> getConnection() {
        return connection;
    }

    public void setConnection(StatefulRedisConnection<String, String> connection) {
        this.connection = connection;
    }

}
