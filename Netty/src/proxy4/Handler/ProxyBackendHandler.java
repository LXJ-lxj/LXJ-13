package proxy4.Handler;

import io.netty.channel.*;


public class ProxyBackendHandler extends ChannelInboundHandlerAdapter {

    private final Channel inboundChannel;

    public ProxyBackendHandler(Channel inboundChannel) {
        this.inboundChannel = inboundChannel;
    }

    //当和目标服务器的通道连接建立时
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(">>>>>>>>>>活跃>>>>>>>>>>");
    }


    /**
     * msg是从目标服务器返回的消息
     *
     */
    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("目标服务器返回数据：" + msg.toString());
        /**
         * 接收目标服务器发送来的数据并打印
         * 然后把数据写入代理服务器和客户端的通道里
         */
        //通过inboundChannel向客户端写入数据
        System.out.println("代理服务器收到响应后，目标服务器给客户端说：我是代理服务器，目标服务器来代替他，我对你说，谢谢。");
        String resDataToClient = "我是代理服务器，目标服务器要代替他，我对你说，谢谢。";
        inboundChannel.writeAndFlush(resDataToClient).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    inboundChannel.close();
                } else {
                    future.channel().close();
                }
            }
        });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(">>>>>>>>>>\n" +
                "\n" +
                "处于活动状态 >>>>>>>>>>");
        ProxyFrontendHandler.closeOnFlush(inboundChannel);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ProxyFrontendHandler.closeOnFlush(ctx.channel());
    }
}