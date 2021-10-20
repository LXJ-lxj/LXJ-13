package Proxy3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static 轮询.WeightRoundRobin.getServer;
import static 轮询.WeightRoundRobin.ipCheck;

/**
 * Created with IntelliJ IDEA.
 * User: ASUS
 * Date: 14-6-24
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
public class Target {

    private String targetHost;
    private int targetPort;

    public Target(String targetHost, int targetPort) {
        this.targetHost = targetHost;
        this.targetPort = targetPort;
    }

    public void run() throws Exception {
        System.err.println("Target host:" + targetHost + " targetPort:" + targetPort);

        // 配置引导程序。 定义两个线程
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
        String ss=null;
        ArrayList<String> ips = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            ips.add(getServer());
            System.out.println(getServer());
         /*   if (ipCheck(ips.get(i))) {
                System.out.println("~ip地址合法");
            }
*/
        }
       // for (int j = 0; j < ips.size(); j++) {


            int k = 0;
            ss=ips.get(k);
//            if (ss.equals(ips.get(k))) {
//                ss = ips.get(k + 2);
//            }

                ips.remove(ss);
                k--;

        /*        for (int i = k; i < ips.size(); i++) {

                    //System.out.println(ips.get(j));
                    // System.out.print(ips.get(i) + ",");

                    //String ss=ips.get(i);
                    // ips.remove(ips.get(i));
                    //System.out.println(ss);
                    // break;
                }*/
    /*        System.out.println();
            System.out.println("----------------");
            System.out.println(ss);*/


       // }
        new Target(/*"172.0.0.1"*/ss, 1212).run();
    }
}