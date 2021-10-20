package DataSource.pool;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class MYpool implements DataSource{
    //连接对象的容器（集合，数组）集合有 List set Map
    //这里用带 LinkedList 集合
    private static List<Connection>pool=new LinkedList<Connection>();//创建一个集合
   //使用静态代码快
    static {

       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           for (int i=0;i<10;i++) {//循环添加是个conn对象
               Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/testdb?useSSL = false&serverTimezone = UTC&", "root", "root");
               pool.add(conn);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }


   }
    @Override
    public Connection getConnection() throws SQLException {//从连接池获取对象
        if (pool.size()==0){//判断池中的对象是否有，如果没有就继续添加10个
            for (int i=0;i<10;i++) {//循环添加是个conn对象
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/testdb?useSSL = false&serverTimezone = UTC&", "root", "root");
                pool.add(conn);
            }
        }
        Connection conn=pool.remove(0);
        System.out.println("获取了1个连接对象，还剩"+pool.size()+"个");
        return conn;//获取坐标返回给Connection对象
    }
    //自定义一个还回去的方法
    public void returnConn(Connection conn){
        try {
            if (conn!=null&&!conn.isClosed())
            pool.add(conn);
            System.out.println("还回了1个连接对象，还剩"+pool.size()+"个");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
