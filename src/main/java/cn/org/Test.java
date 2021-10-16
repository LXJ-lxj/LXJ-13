package cn.org;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Myrun implements Runnable{
    private Socket socket=null;
    public Myrun(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            /*inputStreamServer*/
            InputStream inputStreamServer = socket.getInputStream();
            byte[] bytes = new byte[1024];

            int len1 = inputStreamServer.read(bytes);
            System.out.println(new String(bytes));
            /*context*/
            byte[] bytes1 = new byte[10];
            int t=0;
            for (int i = 5; i < bytes.length; i++) {
                if (bytes[i]==' '){
                    break;
                }
                bytes1[i-5] = bytes[i];
                t=i;
//                t=i-5;
            }
//            String s = new String(bytes1);
//            String substring = s.substring(0, t + 1);
            String substring = new String(bytes).substring(5, t + 1);
            System.out.println(substring);
//            System.out.println(t);

//            if (substring == null) {
//                substring = "aaa";
//            }
            /*FileInputStream*/
            InputStream fis = new FileInputStream("src/main/java/cn/org/"
                    +substring/*bytes1.toString()*/);

            byte[] bbuf = new byte[100];
            int hasRead = 0;
            hasRead = fis.read(bbuf);/* >0;*/
            fis.close();
            /*outputStreamServer*/
            OutputStream outputStreamServer = socket.getOutputStream();
            String httpGetResponseHeadStr = new String(
                    "HTTP/1.1 200 OK\n" +
                    "Content-Length: "+ hasRead+"\n"+
                    "Content-Type: text/html\n" +
                    "\n");
            outputStreamServer.write(httpGetResponseHeadStr.getBytes(StandardCharsets.UTF_8));
            outputStreamServer.write(bbuf);
            outputStreamServer.write("\n".getBytes(StandardCharsets.UTF_8));
            outputStreamServer.flush();
            /*socket close*/
            outputStreamServer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
public class Test {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000, 10);
        ExecutorService executorServicePool = Executors.newCachedThreadPool();
        while (true) {
            System.out.println("----wait...-----");
            Socket accept = serverSocket.accept();
            System.out.println("-----连接-----");
            executorServicePool.submit(new Myrun(accept));
        }
    }
}
