package DataSource.pool;

import JDBC.main.java.JdbcPool.JDBC工具类.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class MyDataSource {//连接池
    static LinkedList pool = new LinkedList<>();
    //初始化连接
    static{
        for(int i = 0;i < 5;i++){
            try {
                pool.add(JDBCUtils.getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    //定义一个方法，从池子中获取connection，从头部获取
    public Connection getConnectionFromPool() throws SQLException {
        if (pool.size() > 0) {
            //池子中有connection
            return (Connection) pool.removeFirst();
        }else {
            //如果池子中没有connection，则先加入等待队列，等待队列满了的话就新建connection--是不需要放回池子的，用完后直接销毁
            return JDBCUtils.getConnection();
        }
    }
    //定义一个方法，将connection放回池子中
    public void addBack(Connection connection){
        pool.addLast(connection);//将新建的connection添加进池子;
        try {
            //写一个自己的connection，然后重写close()方法，通过close()方法来添加进池子。
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //返回pool里面连接的个数
    public int getCount(){
        return pool.size();
    }


}