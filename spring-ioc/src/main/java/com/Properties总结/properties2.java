package com.Properties总结;
//需求： 使用properties实现本软件只能 运行三次，超过了三次之后就提示购买正版，退jvm.


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class properties2 {
    public static void main(String[] args) throws Exception {
        File file=new File("/opt/李晓军/bbb.properties");
        if (!file.exists()){
            //如果配置文件不存在，则创建该配置文件
            file.createNewFile();
        }

        //创建Properties对象
        Properties properties=new Properties();
        //把配置文件的信息加载到properties中
        properties.load(new FileInputStream(file));
        FileOutputStream fileOutputStream=new FileOutputStream(file);

        int count =0;

        //读取配置文件的运行次数
        String value=properties.getProperty("count");

        if (value!=null){
            count=Integer.parseInt(value);
        }

        //判断使用的此书是否已经超过了三次
        if (count==3){
            System.out.println("你已经超过了使用次数，请购买正版软件！");
            System.exit(0);
        }

        count++;
        System.out.println("你已经使用了本软件第"+count+"次了");
        properties.setProperty("count",count+"");
        //使用Properties生成配置文件
        properties.store(fileOutputStream,"runtime");
    }
}
