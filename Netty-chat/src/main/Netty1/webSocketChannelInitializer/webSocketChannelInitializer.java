package webSocketChannelInitializer;

import ChatHandler.ChatHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 通道初始化器
 * 用来加载通道处理器（ChannelHandler）
 */
public class webSocketChannelInitializer extends ChannelInitializer<SocketChannel> {//指定的通道就是Socket
    //初始化通道
    ///在这个方法中去加载对应的ChannelHandler
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        /**
         * 只要支持http不需要写的几个Handler
         */
        //获取管道，将一个个的ChannelHandler添加到管道中
        ChannelPipeline pipeline= socketChannel.pipeline();

                //添加一个http编解码器
        pipeline.addLast(new HttpServerCodec());
        //添加一个用于支持大数据流 的支持
        pipeline.addLast(new ChunkedWriteHandler());
        //添加一个聚合器，这个聚合器主要将HttpMessage聚合成FullHttpRequest/Response
        pipeline.addLast(new HttpObjectAggregator(1024*64));//缓存

        //需要指定接收请求的路由
        //必须使用以ws后缀结尾的url才能访问
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //添加自定义的Handler
        pipeline.addLast(new ChatHandler());
    }
}
