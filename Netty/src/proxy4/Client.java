package proxy4;

import Proxy3.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.apache.commons.logging.Log;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Client {

    public void connect(String host, int port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(
                            new ObjectEncoder(),
                            new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                            new ClientHandler()
                    );
                }
            });

            //启动客户端
            ChannelFuture f = b.connect(host, port).sync();

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();

        }  finally {
            workerGroup.shutdownGracefully();
        }

    }
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
        String host="127.0.0.1";
/*
        //创建一个输出流将nio的fileChannel封装起来
        FileOutputStream fileOutputStream=new FileOutputStream("2.txt");
        //通过fileOutputStream获取对应的FileChannel
        FileChannel fileChannel=fileOutputStream.getChannel();
        //创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        //将String写入byteBuffer
        byteBuffer.put(host.getBytes());

//        对byteBuffer进行flip
        byteBuffer.flip();

//        将byteBuffer 数据写入到byteChannel
        try {
            fileChannel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fileOutputStream.close();
        }*/
        new Client().connect(host, 12359);
    }
}