/*
package netty.simple.netty.http;


//Handler用来接收信息和发送信息

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import javax.print.DocFlavor;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
*/
/*

* 说明
* SimpleChannelInboundHandler  是继承的ChannelHandlerAdapter
* HttpObject 客户端和服务器端相互通信的数据被封装成HttpObject
*
*//*


public  class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject>{

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //判断msg是不是 httprequest请求
        if (msg instanceof HttpObject) {
            System.out.println("msg类型=" + msg.getClass());
            System.out.println("客户端地址" + ctx.channel().remoteAddress());

            //回复信息给浏览器【http协议】

            ByteBuf content = Unpooled.copiedBuffer("hello,我是服务器", CharsetUtil.UTF_8);


            //构造一个http的相应，即httpresponse
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());


            //将构建好的 respones返回
            ctx.writeAndFlush(response);
        }
    }

}
*/
