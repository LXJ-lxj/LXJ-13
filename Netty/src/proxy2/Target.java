package proxy2;

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
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.ArrayList;

import static 轮询.WeightRoundRobin.getServer;

/**
 * Created with IntelliJ IDEA.
 * User: ASUS
 * Date: 14-6-24
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */

public class Target {
    private static Logger logger = Logger.getLogger(Proxy.class);
    private String targetHost;
    private int targetPort;

    public Target(String targetHost, int targetPort) {
        this.targetHost = targetHost;
        this.targetPort = targetPort;
    }

    public void run() throws Exception {
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
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

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
 /*       List<String> ips=new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            ips.add(getServer());
            //System.out.println(getServer());
        }
       String iip = null;
               int x=1,j;
        for ( j=x;j<ips.size();j++){
            if (ipCheck(ips.get(j))){
                System.out.println("ip地址合法");
                 iip=ips.get(j);
                System.out.println(ips.get(j));
                ips.remove(j);

                break;
            }else {
                System.out.println("非法ip地址");
            }
        }*/
        String ss=null;
        ArrayList<String> ips = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            ips.add(getServer());
           System.out.println(getServer());
        }
        //for (int j = 0; j < ips.size(); j++) {

      /*      if (ipCheck(ips.get(j))) {

                System.out.println(ips.get(j));
                System.out.println("~ip地址合法");
            }*/

            int k = 0;
           ss = ips.get(k);
        /*    if (ss.equals(ips.get(k))) {
                ss = ips.get(k + 1);
            } */
                ss = ips.get(k+1);
                ips.remove(k);

              // for (int i = k; i < ips.size(); i++) {

                    //System.out.println(ips.get(j));
                    // System.out.print(ips.get(i) + ",");

                    //String ss=ips.get(i);
                    // ips.remove(ips.get(i));
                    //System.out.println(ss);
                    // break;
             //}
    /*        System.out.println();
            System.out.println("----------------");
            System.out.println(ss);*/



        new Target(/*"192.168.0.4"*/ss, 6666).run();
    }
}