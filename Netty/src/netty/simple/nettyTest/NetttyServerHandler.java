package netty.simple.nettyTest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;
/*
* 1、自定义一个Handler 需要继续netty 规定好的某个HandlerAdapter
* 2、这时我们自定义一个Handler,才能称为一个Handler*/

public class NetttyServerHandler  extends ChannelInboundHandlerAdapter {

//读取客户端发送的消息
    /*
    * ChannelHandlerContext ctx：上下文对象，含有 管道pipeline, 管道channel\
    *  Object msg:客户端发送的数据，默认是Object*/
    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {


        //解决方案1  用户程序自定义的普通任务   自定义普通任务TaskQueue队列
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端～喵 2", CharsetUtil.UTF_8));
                }catch (Exception e){
                    System.out.println("发生异常" +e.getMessage());
                }
            }
        });
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端～喵 3", CharsetUtil.UTF_8));
                }catch (Exception e){
                    System.out.println("发生异常" +e.getMessage());
                }
            }
        });
        //解决方案2 用户自定义定时任务---》该任务提交到scheduleTaskQueue
        ctx.channel().eventLoop().schedule(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(20 * 1000);
                            ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端～喵 4 ", CharsetUtil.UTF_8));
                        }catch (Exception e){
                            System.out.println("发生异常" +e.getMessage());
                        }
                    }
                },5, TimeUnit.SECONDS);

        System.out.println("go  on .....");
/*        System.out.println("server ctx= "+ctx);
        //将msg转成一个ByteBuf
        ByteBuf buf= (ByteBuf) msg;
        System.out.println("客户端发送的消息是："+buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址："+ctx.channel().remoteAddress());*/
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端～喵 1",CharsetUtil.UTF_8));
        //将数据写入到缓存，并刷新
        //一般讲，我们对发送的数据进行编码
       // ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端～～",CharsetUtil.UTF_8));
    }

    //处理异常，需要关闭通道

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
