package netty.simple.buf;

import com.sun.org.apache.xpath.internal.operations.String;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;


import java.nio.charset.Charset;

public class NettyByteBuf02 {
    public static void main(String[] args) {
        //创建ByteBuf
        ByteBuf byteBuf= Unpooled.copiedBuffer("hello,world", Charset.forName("utf-8"));

        //使用相关的方法
        if (byteBuf.hasArray()){//true
            byte[] content=byteBuf.array();
            //将content 转成字符串
            System.out.println(new java.lang.String(content,Charset.forName("UTF-8")));
            System.out.println("byteBuf"+byteBuf);

            System.out.println(byteBuf.arrayOffset());//打印偏移量   0
            System.out.println(byteBuf.readerIndex());//打印读取的数据的索引 0
            System.out.println(byteBuf.writerIndex());//12
            System.out.println(byteBuf.capacity());//36  byteBuf总容量

//            System.out.println(byteBuf.readByte());
            System.out.println(byteBuf.getByte(0));  //以二进制的形式输出第一个字符104

            int len=byteBuf.readableBytes();  //可读的字节数  12
            System.out.println("len"+len);


            //用for取出各个字节
            for (int i=0;i<len;i++){
                System.out.println((char) byteBuf.getByte(i));
            }

             }
    }
}
