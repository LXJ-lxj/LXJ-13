package netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServer {
    public static void main(String[] args) {

        //创建两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)//设置两个线程组,
                    .channel(NioServerSocketChannel.class)        // 使用NioServerSocketChannel作为服务器的通道实现
                    .handler(new LoggingHandler(LogLevel.INFO))//增加一个日志处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline=ch.pipeline();
                            //加入一个netty 提供 IdleStateHandler
                            /*
                            * 1.IdleStateHandler   由netty提供的处理空闲状态的处理器
                            * long readerIdleTime,  表示多长时间没有读，就会发送一个心跳检测包检测是否衔接
                            * long writerIdleTime,  表示有多长时间没有写操作，就会发送一个心跳检测包检测是否衔接
                            * long allIdleTime，     表示多长时间既没有读也没有写，就会发送一个心跳检测包检测是否衔接
                            * */
                            pipeline.addLast(new IdleStateHandler(3,5,7, TimeUnit.SECONDS));
                            //加入一个对空闲检测进一步处理的handler（自定义）
                            pipeline.addLast(new MyserverHandler());
                        }
                    });
            //启动服务器
            ChannelFuture channelFuture= null;
            try {
                channelFuture = serverBootstrap.bind(7000).sync();

            channelFuture.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
