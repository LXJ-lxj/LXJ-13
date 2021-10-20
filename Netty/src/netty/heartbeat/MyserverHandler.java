package netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyserverHandler extends ChannelInboundHandlerAdapter {
    /*
    * ctx  上下文
    * evt  事件
    * */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        //将 evt 向下转型IdleStateEvent
        IdleStateEvent event=(IdleStateEvent) evt;
        String eventType=null;
        switch (event.state()){
            case ALL_IDLE:
                eventType="读写空闲";
                break;
            case READER_IDLE:
                eventType="读空闲";
                break;
            case WRITER_IDLE:
                eventType="写空闲";
                break;
        }
        System.out.println(ctx.channel().remoteAddress()+"---超时---"+eventType);
        System.out.println("服务器做相应处理...");
        ctx.channel().close();
    }
}
