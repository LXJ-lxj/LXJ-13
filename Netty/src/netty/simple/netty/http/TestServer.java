/*
package netty.simple.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import netty.simple.nettyTest.NetttyServerHandler;

public class TestServer {
    public static void main(String[] args) {
*/
/*
        //创建BossGroup和 WorkerGroup
         * 说明：
         * 1、创建两个线程组BossGroup和 WorkerGroup
         * 2、BossGroup只是处理连接请求，真正的和客户端业务处理，会交给workerGroup完成
         * 3、两个都是无限循环

*//*


        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建服务器端的启动的对象，配置启动参数
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //使用链式编程来进行设置
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new TestServerInitializer());

            //绑定端口启动服务
            ChannelFuture channelFuture=serverBootstrap.bind(6668).sync();

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
*/
