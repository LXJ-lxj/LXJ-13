package proxy4.com.JDBCdemo.JDBC.JDBCdemo;

import proxy4.com.JDBC工具类.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCdemo1 {
    //Resultset:结果集对象，封装查询结果
    /**
     * next()方法：游标向下移动一行
     * getxxx(参数):获取数据：一次只能获取某一行的一列数据
     * xxx：指数据类型  如：int getint（）  String getString()
     * 参数：1 int: 代表列的编号，从1开始 如： getString(1)
     *      2 String :代表类名称。如： getDouble("name")
     */

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        long startTime= System.nanoTime();   //获取开始时间
        Statement stmt=null;
        Connection conn=null;
        ResultSet rs=null;
        try {
            //1 导入驱动jar包
            //2 注册驱动
            //Class.forName("com.mysql.cj.jdbc.Driver");用工具类代替
            //3 获取数据库连接对象
            conn= JDBCUtils.getConnection();
            //DriverManager.getConnection("jdbc:mysql://localhost/testdb?useSSL = false&serverTimezone = UTC&","root","root");使用工具类代替
            //4 定义sql语句
            String sql="select *from testdb";//c查询所有testdb表的数据
            //5 获取执行sql的对象 Statement
            stmt =conn.createStatement();
            // 6 执行sql
            rs=stmt.executeQuery(sql);//结果集对象
            //6.1 先让游标向下移动一行
            rs.next();
            //6.2 获取数据
            int id=rs.getInt(3);
            String name=rs.getString("name");
            int password;
            password = rs.getInt("pass");
            System.out.println(id+"---"+name+"---"+password);

            //测试的代码段

            long endTime= System.nanoTime(); //获取结束时间

            System.out.println("程序运行时间： "+(endTime-startTime)+"ns");



        }/*catch (ClassNotFoundException e){
            e.printStackTrace();
        }*/
        finally {
            //7释放资源
            //避免空指针
            if (rs!=null){
                try {
                    rs.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (stmt!=null){
                try {
                    stmt.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            //避免空指针
            if (conn!=null){
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

        //stmt.close();
        //conn.close();

    }
}
