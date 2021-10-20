import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import webSocketChannelInitializer.webSocketChannelInitializer;

public class nettychatserver {
    public static void main(String[] args) {
        //创建两个线程池
        NioEventLoopGroup mainGrp = new NioEventLoopGroup();//主线程池
        NioEventLoopGroup subGrp=new NioEventLoopGroup();//从线程池

        //创建两个线程池一个专门负责读写操作，一个专门负责接收客户端连接
        //每线程都有一个selector用于轮循监听在其上的socket网络通道（如果一时处理不了他将会放到队列中）
try {


    //创建netty服务器启动对象
    ServerBootstrap serverBootstrap = new ServerBootstrap();

    //初始化服务器启动对象
    serverBootstrap
            ///指定使用上边的两个线程池
            .group(mainGrp, subGrp)
            //指定Netty通道类型
            .channel(NioServerSocketChannel.class)
           // .channel(NioSctpServerChannel.class)不可以用这个
            //指定通道初始化器用来加载当channel收到事件消息后。
            //如何进行业务处理
            .childHandler(new webSocketChannelInitializer());
//绑定服务器端口，以同步的方式启动服务器
    ChannelFuture future = serverBootstrap.bind( 9090).sync();//返回一个回调消息future
    //等待服务器关闭
    future.channel().closeFuture().sync();
}catch(InterruptedException e){
    e.printStackTrace();
} finally {
    //关闭服务器
    mainGrp.shutdownGracefully();
    subGrp.shutdownGracefully();
}


    }
}
