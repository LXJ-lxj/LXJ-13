package org;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MyRun implements Runnable{
private Socket s=null;
public MyRun(Socket s){
    this.s=s;

}
    @Override
    public void run() {
        while (true) {
            InputStream in = null; //设备流进行接收
            try {
                in = s.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            int len;
            byte[] bs = new byte[5];
            try {
                if ((len = in.read(bs)) > 0) {
                    System.out.println(new String(bs));

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            OutputStream out = null;
            try {
                out = s.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.write("world".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
