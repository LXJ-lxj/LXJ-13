package netty.simple.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NettyByteBuf {
    public static void main(String[] args) {
       /* 创建一个ByteBuf
        说明
        1、创建 对象，该对象包含一个数组arr，是一个byte[10]
        2、在netty中 的buffer中 不需要使用flip 进行反转
        底层维护了readerindex 和writerIndex 和copacity，将buffer分成三个区域
        0-----rederindex已经读取的区域
        rederindex------writerIndex   可读取的区域
        writerIndex ------copacity 可写的区域
        */
        ByteBuf buffer= Unpooled.buffer(10);

        for (int i=0;i<10;i++){
            buffer.writeByte(i);
        }
        System.out.println("capacity"+buffer.capacity());
        //输出
        for (int i=0;i<buffer.capacity();i++){
            System.out.println(buffer.readByte());
        }
        System.out.println("执行完毕");
    }
}
