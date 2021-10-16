package cn.org;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Cli {
    public static void main(String[] args) throws Exception {
        Socket s=new Socket();
        s.bind(new InetSocketAddress(6666));//绑定本地端口
        s.connect(new InetSocketAddress("localhost", 9000));//连接远程服务端接口
        OutputStream out=s.getOutputStream();
        out.write("a1".getBytes(StandardCharsets.UTF_8));
        out.flush();
        InputStream in = s.getInputStream();
        byte[] b = new byte[1024];
        int len = in.read(b);//block
        String str=new String(b);
        System.out.println(str);
        System.in.read();
        s.close();
    }
}
