package com.org;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class classload {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
/*        String str="aaaa";
        ClassLoader cl=str.getClass().getClassLoader();
        System.out.println(cl);
        // 产生class对象
        Class clazz=Class.forName("com.sun.crypto.provider.AESCipher");
        //getClassLoader()方法用于获取此实体的classLoader。
        System.out.println(clazz.getClassLoader());
        classload c=new classload();
        System.out.println(c.getClass().getClassLoader());*/


/*        ClassLoader systemLoader=ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器"+systemLoader);

        Enumeration<URL> em1=systemLoader.getResources("");
        while (em1.hasMoreElements()){
            System.out.println(em1.nextElement());
        }
        //获取系统类加载器的父类加载器，得到扩展类加载器
        ClassLoader extensionLader= systemLoader.getParent();
        System.out.println("扩展类加载器:"+extensionLader);
        System.out.println("扩展类加载器的加载路径："+System.getProperty("java.ext.dirs"));
        System.out.println("扩展类加载器的parent："+extensionLader.getParent());

        System.out.println("======================================");
        ClassLoader cl=Class.forName("com.org.classload").getClassLoader();
        System.out.println(cl);*/

        ClassLoader cl = ClassLoader.getSystemClassLoader();
        //下面语句仅仅是加载classload类
        cl.loadClass("com.org.classload");
        System.out.println("系统加载classload类");
        //下面语句会初始化classload类
        Class.forName("com.org.classload");
        System.out.println("系统初始化类");
    }


}
