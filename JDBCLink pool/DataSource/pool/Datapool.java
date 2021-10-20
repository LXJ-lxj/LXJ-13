package DataSource.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Datapool {
    private LinkedList<Connection> pool = new LinkedList<>();
    private static final int INIT_CONNECTIONS = 10;
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:8080/";
    //ReentrantLock实现了Lock，和synchronized相同，但是添加了锁投票、定时锁等候和可中断锁等候的一些特性。
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();//控制线程的等待与通知
    //加载驱动
    static {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 通过构造方法初始化连接，每个新建对象都会自动构建连接
    public Datapool() {
        for (int i = 0; i < INIT_CONNECTIONS; i++) {
            try {
                Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                pool.addLast(conn);  //addLast(E e)：在链表尾部添加一个元素
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 获取数据库连接
    public Connection getConnection() {
        Connection conn = null;
        lock.lock();
        try {
            while (pool.size() < 0) {
                try {
                    condition.await();//使当前线程加入等待队列中，并释放当锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!pool.isEmpty()) {
                conn = pool.removeFirst();//把第一个移开
            }
            return conn;
        } finally {
            lock.unlock();
        }
    }

    // 断开释放数据库连接，连接放回连接池过程
    public void releaseConnection(Connection conn) {
        if (conn != null) {
            lock.lock();
            try {
                pool.addLast(conn);
                condition.signal();//线程调用signal()会重新请求锁
            } finally {
                lock.unlock();
            }
        }
    }
}
