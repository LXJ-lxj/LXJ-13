package nettychat.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;


public class GroupchatServerHandler extends SimpleChannelInboundHandler<String> {



    //定义一个channel组，管理所有的channel
    //GlobalEventExecutor.INSTANCE:是全局的事件执行器，是一个单例
    private  static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    //handlerAdded  表示连接建立，一旦链接，第一个被执行
    //将单前channel加入到  channelGroup
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        //将该客户加入聊天的信息退送给其他在线的客户

        //该方法会将channelGroup中所有的channel 遍历，并且发送 消息
        //我们不需要自己遍历
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"加入聊天"+sdf.
                format(new java.util.Date())+"\n");
        channelGroup.add(channel);
    }


    //表示channel 处于活动状态 ，提示xxx上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"上线了～");
    }

    //表示channel  处于不活动态，，表示xxx下线了
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"离线了～");
    }

    //端开连接时，将xx客户离开信息退送给当前在线的客户
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress()+"离开了"+sdf.
                format(new java.util.Date())+"\n");
        System.out.println("channelGroup  size"+channelGroup.size());
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取到
        Channel channel=ctx.channel();
       //这时我们遍历channelGroup，根据不同的情况，回送不同的消息

        channelGroup.forEach(ch->{
            if (channel!=ch){
                ch.writeAndFlush("[客户]"+channel.remoteAddress()+"发送了消息"+msg+sdf.
                        format(new java.util.Date())+"\n");
            }else {
                ch.writeAndFlush("[自己发送了消息]"+msg+sdf.
                        format(new java.util.Date())+"\n");
            }
        });
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        //关闭通道
        ctx.close();
    }


    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

    }
}
