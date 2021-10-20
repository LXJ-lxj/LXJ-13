package ChatHandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 *
 * 接收到前端的数据封装到Frame中
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //创建一个静态变量用来保存所有的客户端连接
private static ChannelGroup clients=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
//创建一个时间的格式化器
private SimpleDateFormat sdf=new SimpleDateFormat("yyy-mm-dd hh:mm");
    @Override
    //当有新的客户端连接服务器之后，会自动调用这个方法
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
       //将新的通道加入到clienet

        clients.add(ctx.channel());
    }

    @Override
    //当channel中有新的事件消息会自动调用
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame msg) throws Exception {
        //当接收到数据后会自动调用

        //获取客户端发过来的文本消息
        String text=msg.text();
        System.out.println("接收到的消息数据为："+text);

        for (Channel client:clients){
            //将消息发送到所有的客户端
            client.writeAndFlush(new TextWebSocketFrame(sdf.format(new Date()))+":"+text);
        }
    }
}
