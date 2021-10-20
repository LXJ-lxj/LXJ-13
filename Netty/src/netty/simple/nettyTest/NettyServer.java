package netty.simple.nettyTest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyServer {
    public static void main(String[] args) throws InterruptedException {

        //创建BossGroup和 WorkerGroup
        /*
         * 说明：
         * 1、创建两个线程组BossGroup和 WorkerGroup
         * 2、BossGroup只是处理连接请求，真正的和客户端业务处理，会交给workerGroup完成
         * 3、两个都是无限循环*/

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建服务器端的启动的对象，配置启动参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            //使用链式编程来进行设置
            bootstrap.group(bossGroup, workerGroup)//设置两个线程组,
                    .channel(NioServerSocketChannel.class)        // 使用NioServerSocketChannel作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列得到链接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//设置保持活动链接状态
                    .handler(new LoggingHandler(LogLevel.INFO))//增加一个日志处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {//创建一个通道初始化（匿名对象）
                        //给pipeline 设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NetttyServerHandler()); //在管道添加一个服务器
                        }
                    });  //给我们的workerGroup的EventLoop对应的管道设置处理器

            System.out.println("服务器启动～～");

            //绑定一个端口且同步，生成一个ChannelFuture对象
            //启动服务器（并绑定端口）
            final ChannelFuture cf = bootstrap.bind(9999).sync();

            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (cf.isSuccess()){
                        System.out.println("监听端口 9999 成功");
                    }else {
                        System.out.println("监听端口 9999 失败");
                    }
                }
            });

//        对关闭通道进行监听
            cf.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
