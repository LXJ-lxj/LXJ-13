package proxy4.Handler;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import org.lib.com.TestAddr;
import proxy4.com.JDBCdemo.JDBCdemo2;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    private static Connection conn;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("客户对代理服务器说：我是客户，代我问候目标服务器！");
        ChannelFuture f = ctx.writeAndFlush("我是客户，向目标服务器问好！");
        Date date=new Date();
        ChannelFuture f2=ctx.writeAndFlush(date.toString());
        ChannelFuture f3 = ctx.writeAndFlush( TestAddr.getWinLocalIP());
        f2.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
              //  System.out.println("===========写入消息成功==========");
            }
        });
        f3.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
               // System.out.println("===========写入消息成功==========");
            }
        });
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("===========写入消息成功==========");
            }
        });
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
            //String sql="update testdb set name='弟弟'where id=4";//更改数据
             String sql3="insert into testdb value(f3)";//添加数据
            String sql2="insert into testdb value(f2)";//添加数据
            String sql="insert into testdb value(f)";//添加数据
            //String sql="delete from login where name=李晓军";//删除数据
            //5、获取执行sql的对象，Statement
            stmt = conn.createStatement();
            //6、执行sql
            int count=stmt.executeUpdate(sql);
            int count2=stmt.executeUpdate(sql2);
            int count3=stmt.executeUpdate(sql3);
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


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String mess = (String) msg;
        System.out.println("从代理服务器接收消息到：" + mess);
        ctx.close();
    }
}