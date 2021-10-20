package proxy4.com.JDBCdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCdemo2 {
 private static Connection conn;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        long startTime= System.nanoTime();   //获取开始时间
        Statement stmt=null;
        Connection conn=null;
        try {
        /** 1、导入注册驱动的jar包
         * 2、注册驱动
         */
        Class.forName("com.mysql.cj.jdbc.Driver");
       //3、获取数据库连接对象
        conn= DriverManager.getConnection("jdbc:mysql://localhost/testdb?useSSL = false&serverTimezone = UTC&","root","root");
        //4、定义sql语句
        String sql="update testdb set name='军哥'where id=6";//更改数据
       // String sql="insert into login value('张赫',123435)";//添加数据
        //String sql="delete from login where name=李晓军";//删除数据
        //5、获取执行sql的对象，Statement
        stmt = conn.createStatement();
        //6、执行sql
            int count=stmt.executeUpdate(sql);
        System.out.println(count);
        if (count>0){
            System.out.println("操作成功！");
         /*   long start = System.currentTimeMillis();
            *//*运行的程序主体*//*
            long end = System.currentTimeMillis();
            System.out.println("程序运行时间："+(end-start)+"ms");
            输出程序的运行时间*/



        }else {
            System.out.println("操作失败！");
        }
            //测试的代码段

            long endTime= System.nanoTime(); //获取结束时间

            System.out.println("程序运行时间： "+(endTime-startTime)+"ns");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
            //7释放资源
            //避免空指针
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
    }

}
