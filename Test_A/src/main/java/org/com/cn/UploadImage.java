package org.com.cn;

import java.io.*;
import java.util.Scanner;

public class UploadImage {
    public static final int MaxFile=1024*1024*5;//图片最大为5M
    public static void main(String[] args) {
        try {
            Scanner sc=new Scanner(System.in);
            System.out.println("图片复制到地址为D盘根目录!");
            System.out.print("请输入图片的绝对地址和全称:");
            String filePath="src/DIDI打车.jpeg";
//            filePath=sc.nextLine();
            String fileName="";
            //System.out.print("请输入复制后的图片名:");
            fileName="DIDI打车";
            BufferedInputStream bis=new BufferedInputStream(new FileInputStream(new File(filePath)));
            byte[] b=new byte[UploadImage.MaxFile];
            int len=bis.read(b);
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(new File("111"+fileName+".jpeg")));
            bos.write(b, 0, len);
            bos.flush();
            bis.close();
            bos.close();
            System.out.println("图片复制成功!");
        } catch (FileNotFoundException e) {
            System.out.println("路径有错,文件没找到!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}