package redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRedis {

    private RedisClient redisClient;
    private StatefulRedisConnection<String, String> connection;
    private RedisCommands<String, String> syncCommands;

    @Before
    public void before() {
        redisClient = RedisClient.create("redis://localhost");
        System.out.println("init");
    }

    @Test
    public void test() {
        connection = redisClient.connect();
//        syncCommands = connection.sync();
////        syncCommands.set("key", "Hello, Redis!");
        connection.sync().hset("hash-key","hu","hello");
        String value = connection.sync().hget("hash-key", "hu");
        Assert.assertEquals("hello", value);

    }

    @After
    public void after() {
        connection.close();
        redisClient.shutdown();
        System.out.println("after");
    }

}
