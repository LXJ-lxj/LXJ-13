import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static void main(String[] args) throws InterruptedException {

        //1 创建第一个线程池，接收客户端连接
       NioEventLoopGroup MainGop = new NioEventLoopGroup();
        //2 处理网络io操作读写等
       NioEventLoopGroup subGop=new NioEventLoopGroup();
        //3 创建服务器端启动对象
        ServerBootstrap b = new ServerBootstrap();
        b.group(MainGop,subGop)//4 设置两个线程池
        .channel(NioServerSocketChannel.class)//5 使用NioServerSocketChannel作为服务器通道的实现
        .option(ChannelOption.SO_BACKLOG,128)//设置线程队列中等待连接的个数
        .childOption(ChannelOption.SO_KEEPALIVE,true)//7 保持活动连接状态
        .childHandler(new ChannelInitializer<SocketChannel>(){//8 匿名内部类,创建一个通道初始化对象
            public void initChannel(SocketChannel sc){//9 添加自定义的Handlerl类

                sc.pipeline().addLast(new NettyServerHandler());
        }
        });
        System.out.println("-------Server is ready----------");


            ChannelFuture fuetur = b.bind(9999).sync();//10 绑定端口号

        System.out.println("Server 启动成功～");


        //11 关闭通道、关闭线程
        fuetur.channel().closeFuture().sync();
        MainGop.shutdownGracefully();
        subGop.shutdownGracefully();
    }
}
