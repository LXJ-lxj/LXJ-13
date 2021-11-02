package com.Properties总结;


import org.junit.Test;

import java.io.*;
import java.util.Properties;

/**
 * Properties文件是java中很常用的一种配置文件，文件后缀为“.properties”，属文本文件，文件的内容格式是“键=值”的格式，
 * 可以用“#”作为注释，java编程中用到的地方很多，运用配置文件
 * java中提供了配置文件的操作类Properties类（java.util.Properties）：
 * public class Properties extends Hashtable.可见Properties类继承了Hashtable，而HashTable又实现了Map接口，所以可对 Properties 对象应用 put 和 putAll 方法。
 * 但不建议使用这两个方法，因为它们允许调用者插入其键或值不是 String 的项。
 * 相反，应该使用 setProperty 方法。如果在“不安全”的 Properties 对象（即包含非 String 的键或值）上调用 store 或 save 方法，则该调用将失败。
 *
 * Properties的常用方法：
 * 1.setProperty(String key, String value)
 * 调用 Hashtable 的方法 put。
 * 2.
 * getProperty(String key)
 * 用指定的键在此属性列表中搜索属性
 * 3.
 * getProperty(String key, String defaultValue)
 * 用指定的键在属性列表中搜索属性。
 * 4.
 * load(InputStream inStream)
 * 从输入流中读取属性列表（键和元素对）。
 * 5.
 * load(Reader reader)
 * 按简单的面向行的格式从输入字符流中读取属性列表（键和元素对）。
 * 6.
 * loadFromXML(InputStream in)
 * 将指定输入流中由 XML 文档所表示的所有属性加载到此属性表中。
 * 7.store(OutputStream out, String comments)
 * 以适合使用 load(InputStream) 方法加载到 Properties 表中的格式，将此 Properties 表中的属性列表（键和元素对）写入输出流。
 * 8.store(Writer writer, String comments)
 * 以适合使用 load(Reader) 方法的格式，将此 Properties 表中的属性列表（键和元素对）写入输出字符。
 * 9.storeToXML(OutputStream os, String comment)
 * 发出一个表示此表中包含的所有属性的 XML 文档。
 * 10.storeToXML(OutputStream os, String comment, String encoding)
 * 使用指定的编码发出一个表示此表中包含的所有属性的 XML 文档。
 */

public class Properties1 {
    @Test

    public void Properties() throws Exception {
        Properties pro=new Properties();
        //设置值
        pro.setProperty("driver", "com.mysql.jdbc.Driver");
        pro.setProperty("url", "jdbc:mysql///user");
        pro.setProperty("user", "root");
        pro.setProperty("password", "451535");
        //获取值：
        //1、getProperty(String key)方法  通过键获取值
        String str= pro.getProperty("driver");
        String str1=pro.getProperty("user");
        System.out.println(str1);
        System.out.println(str);
        //2、getProperty(String key, String defaultValue)重载方法
        //当properties对象中没有所指定的键值时，显示给定的默认值
        String str2=pro.getProperty("driver", "没有该值");
        String str3=pro.getProperty("haha", "没有该值");
        System.out.println(str2);
        System.out.println(str3);



        //下边代码将内容写入指定文件
       //通过字节流的形式
            pro.store(new FileOutputStream(new File("JDBC.properties")),"数据库配置文件");
            //第一个参数是一个输出流对象，第二参数是使用一个字符串描述这个配置文件的信息。

            //2通过字符流的形式

        pro.store(new FileWriter("JDBC.properties"),"数据库配置文件");

    }
}
