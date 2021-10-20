package DataSource;

import JDBC.main.java.JdbcPool.JDBC工具类.JDBCUtils;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

public class Mydatasource implements DataSource {
    //1、创建1个容器用于存储Connection对象
    private static LinkedList<Connection> pool=new LinkedList<Connection>();
    //2、创建5个连接放到容器中

    static {
        Connection conn=null;
        for (int i=0;i<5;i++){
            try {
                conn= JDBCUtils.getConnection();//使用工具类获取连接
                pool.add(conn);//将连接放到创建好的pool中
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public Connection getConnection() throws SQLException {
        Connection conn=null;
        if (pool.size()==0){
            //使用前先判断池子中有没有，如果没有在创建5个扔到池中
            for (int i=0;i<5;i++){
                try {
                    conn= JDBCUtils.getConnection();//使用工具类获取连接
                    pool.add(conn);//将连接放到创建好的pool中
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //从池子中获取一个Connection连接对象
            conn=pool.remove(0);
        }
        return conn;
    }

    /**
     * 归还连接对象到连接池中
     * @param s
     * @param s1
     * @return
     * @throws SQLException
     */

    public void backConnection(Connection conn){
        pool.add(conn);//就相当于再加一个进去
    }
    @Override
    public Connection getConnection(String s, String s1) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter printWriter) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int i) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return false;
    }
}
