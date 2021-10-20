package proxy4;

import Proxy3.TargetHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.apache.commons.logging.Log;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static 轮询.WeightRoundRobin.getServer;

public class Target {
    private static Log DEBUGGER  ;
    private static Logger logger = Logger.getLogger(Proxy.class);
    public static void main(String[] args) throws Exception {

        if (logger.isInfoEnabled()) {
            // 如果日志内容比较复杂的话，可以通过这种判断，提升效率
            logger.info("this is a info msg");
            System.out.print("info...");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("this is a debug msg");
            System.out.print("debug...");
        }
        if (logger.isEnabledFor(Level.ERROR)) {
            logger.error("this is a error msg");
            System.out.print("error...");
        }
        if (logger.isEnabledFor(Level.WARN)) {
            logger.warn("this is a warn msg");
            System.out.print("warn...");
        }
        //创建文件的输出流

        //logs logs=new logs();

        String ss=null;
        ArrayList<String> ips = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            ips.add(getServer());
            System.out.println(getServer());
        }
        int k = 0;
        ss = ips.get(k);
        ss = ips.get(k+1);
        ips.remove(k);
        //logs.log(ss);
        new Target(/*"192.168.0.4"*/ss, 12358).run();


    }

    private String targetHost;
    private int targetPort;

    public Target(String targetHost, int targetPort) {
        this.targetHost = targetHost;
        this.targetPort = targetPort;
    }

    public static String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
    public Target run() throws Exception {
        System.err.println("Target host:" + targetHost + " targetPort:" + targetPort);


        // Configure the bootstrap.
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            // 注册handler
                            ch.pipeline().addLast(
                                    new ObjectEncoder(),
                                    new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                                    new TargetHandler()
                            );
                        }
                    })
                    .bind(targetPort).sync().channel().closeFuture().sync();
            //监听本地的一个端口，当有客户端请求时，然后向目标服务器发送请求，获取消息，然后发送给客户端
        }catch (Exception e){
            DEBUGGER.info("开始捕获异常");

            DEBUGGER.fatal("error info is:"+e.toString());

            DEBUGGER.fatal(getTrace(e));


        }
         finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }
        return null;
    }

}
/*
class logs{
public void log(String ll) throws IOException {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    String date=df.format(new Date());
    System.out.println(date);// new Date()为获取当前系统时间
    System.out.println("此服务器的ip地址为："+ll);
    String data= date;
    //创建一个输出流将nio的fileChannel封装起来
    FileOutputStream fileOutputStream=new FileOutputStream("2.txt");
    //通过fileOutputStream获取对应的FileChannel
    FileChannel fileChannel=fileOutputStream.getChannel();
    //创建一个缓冲区 ByteBuffer
    ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
    //将String写入byteBuffer
    byteBuffer.put(data.getBytes());

//        对byteBuffer进行flip
    byteBuffer.flip();

//        将byteBuffer 数据写入到byteChannel
    try {
        fileChannel.write(byteBuffer);
    } catch (IOException e) {
        e.printStackTrace();
    }finally {
        fileOutputStream.close();
    }
}
}*/
