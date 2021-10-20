package netty.ProxyRPC;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

public class ProxyFrameWork {

    //暴露服务
    public static void export(final Object service,int port) throws IOException {
        //认证
        if (service==null){
            throw new IllegalArgumentException("argument");

        }
        if (port<=0||port>65535){
            throw new IllegalArgumentException("Invalid port"+port);

        }
        System.out.println("Export service"+service.getClass().getName()+"port"+port);
        ServerSocket serverSocket=new ServerSocket(port); //打开一个服务端的Socket来接收外部的连接（绑定一个端口让客户端来访问）
        while (true){
            //一直循环去处理连接
            Socket accept = serverSocket.accept();
            accept.getInputStream();
        }
    }
}
