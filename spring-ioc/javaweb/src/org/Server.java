package org;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        ServerSocket ss=null;    //网络接口
        Socket s =null;
        ExecutorService es= Executors.newCachedThreadPool();
        try {

            /*
            * BIO (频发) 线程太多 --------nio（偶发）
            * */
            ss = new ServerSocket(9999, 2);  //backlog 表示等待队列，不能太大。占内存
            while (true) {
                s=ss.accept();
                System.out.println("接收到一个请求！");

              /*  Thread t = new Thread(new MyRun(s));
                t.start();*/
              es.submit(new Run(s));

            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
