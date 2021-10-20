/*
package netty.simple.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import org.Test;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        //向管道加入处理器
        //得到管道
        ChannelPipeline pipeline=ch.pipeline();
        //加入一个netty 提供的httpServerCodec codec =>[coder   decoder]

*/
/*HttpServerCodec说明：
        * HttpServerCodec 是netty体东的处理http的 编-解码器
        * *//*


        pipeline.addLast("MyHttpServerCodec",new HttpServerCodec());
        //增加一个自定义的handler
        pipeline.addLast("MyHttpServerCodec",new TestHttpServerHandler());
    }
}
*/
