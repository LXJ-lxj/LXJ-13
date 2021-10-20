import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    //读取数据事件
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        System.out.println("Server"+ctx);//服务器端的打印
        ByteBuf buf= (ByteBuf)msg;//将传过来的数据放到缓冲区
        System.out.println("客户端发来的消息："+buf.toString(CharsetUtil.UTF_8));//将数据转成字符串形式
        //添加参数防止中文乱码


    }
    //数据读取完毕事件
    public void ChannelReadComplete(ChannelHandlerContext ctx){
        ctx.writeAndFlush(Unpooled.copiedBuffer("就是没钱",CharsetUtil.UTF_8));

    }
    //异常发生事件
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable t){
        ctx.close();
    }
}
