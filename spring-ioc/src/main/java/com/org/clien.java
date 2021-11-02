package com.org;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class clien {
    public static void main(String[] args) {
        try {
            Socket s=new Socket("127.0.0.1",9999); //需要填写ip地址和端口号
                OutputStream out = s.getOutputStream();
                out.write("hello".getBytes());
                out.flush();

                InputStream in = s.getInputStream(); //设备流进行接收

                int len;
                byte[] bs = new byte[5];
                if ((len = in.read(bs)) > 0) {
                    System.out.println(new String(bs));

                }
                s.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
