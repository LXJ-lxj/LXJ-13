package proxy4.com.JDBC工具类;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private  static  String url;
    private  static String user;
    private  static  String password;
    private  static  String  driver;
    //文件的读取，只需要读取一次即可拿到这些值
    //定义静态代码快
    static {
//读取资源文件，获取值。使用静态代码块
    //1、创建Properties集合类
    Properties pro=new Properties();
    //2\加载文件
    try {
        pro.load(new FileReader("src/druid.properties"));
        //3、获取数据，赋值
        url=pro.getProperty("url");
        user=pro.getProperty("user");
        password=pro.getProperty("password");
        driver=pro.getProperty("driver");//读取配置文件中的的driver
         //注册驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    public static Connection getConnection()throws SQLException{
        //创建获取一个连接
        //返回一个连接对象
        return DriverManager.getConnection(url,user,password);
    }
    public static void close(Statement stmt,Connection conn){
        /**
         * 释放资源方法
         */
       if (stmt!=null){
           try {
               stmt.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(ResultSet res,Statement stmt, Connection conn){
        /**
         * 释放资源方法
         */
        if (res!=null){
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
