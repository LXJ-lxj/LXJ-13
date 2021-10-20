package netty.webSocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;


public class webSocket {
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
                          //因为基于http协议，使用http的编码和解码器
                            pipeline.addLast(new HttpServerCodec());
                            //是以块方式写，添加chunkedWriteHandler处理器
                            pipeline.addLast(new ChunkedWriteHandler());
                            /*
                            * 说明：
                            * 1、http数据在传输过程中是分段。httpObjectAggregator，就是可以将多个分段聚合
                            * 2、这就是为什么，当浏览器发送大量数据时，就会发出多次http请求
                            * */
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            /*
                            * 说明：
                            * 1、对应websocket，它的数据是以帧形式传递
                            * 2、可以看到websocketFrame 下面的六个子类
                            * 3、浏览器请求时ws：//localhost：7000/hello 表示请求的uri
                            * websocketServerProtocolHandler 核心功能就是将http协议升级为ws协议，保持长连接
                            * */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                            //自定义的handler，处理业务逻辑
                            pipeline.addLast(new MyTextWebSocketFrameHandler());
                        }
                    });
            //启动服务器TextWebSocketFrame
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
