package netty.simple.NIOFileChannel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class niofile {
    public static void main(String[] args) throws Exception {
       FileInputStream fileInputStream =new FileInputStream("1.txt");//文件的输入流对象
        FileChannel fileChannel=fileInputStream.getChannel();//读取文件内容到nio管道中

        FileOutputStream fileOutputStream=new FileOutputStream("2.txt");//文件的输出流：其实就是2.txt这个文件关联了fileOutputStream，而它又关联了fileChannel1
        FileChannel fileChannel1=fileOutputStream.getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024); /*声明一个缓冲区大小为1024*/
//        public int read(ByteBuffer dst)   这个是从通道读取数据并放到缓冲区
//        public int write(ByteBuffer dst)  是把缓冲区的数据写到通道中
         while (true){
//            循环读取
             byteBuffer.clear(); //清空数据
         /*    position = 0;
             limit = capacity;
             mark = -1;*/
             int read = fileChannel.read(byteBuffer); //就是从文件的管道中fileChannel读到缓冲区
             if (read==-1){//表示读完
                 break;  }
//将buffer中的数据写入到fileChannel1-----2.txt
             byteBuffer.flip();
             fileChannel1.write(byteBuffer);
         }
        System.out.println("copy成功！");
         //关闭相关的流
        fileInputStream.close();
        fileOutputStream.close();
    }
}
