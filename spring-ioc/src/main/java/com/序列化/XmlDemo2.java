package com.序列化;

import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import com.thoughtworks.xstream.XStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class XmlDemo2 {

    //序列化到xml文档
    public static void serializeToXml() throws IOException {
        Person[] myPerson = new Person[2];
        myPerson[0] = new Person("lxj","root",24);
        myPerson[1] = new Person("Tom","2324",23);

        XStream xstream = new XStream();
        FileOutputStream fos = new FileOutputStream("myPerson.xml");
        xstream.toXML(myPerson,fos);

        System.out.println(xstream.toXML(myPerson));
    }


    //反序列化xml文档
    public static void deSerializeFromXm() throws IOException {
        XStream xs = new XStream();
        Person[] myPerson = null;

        FileInputStream fis = new FileInputStream("myPerson.xml");

        myPerson=(Person[])xs.fromXML(fis);

        if (myPerson != null) {
            for (int i=0;i<myPerson.length;i++) {
                System.out.println("用户名："+myPerson[i].getUserName()+"  密码："+myPerson[i].getPassword()+"  年龄："+myPerson[i].getAge());
            }
        }

    }

    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入 S1执行 ，exit结束！");
        String S=scanner.next();
        if ("S1".equals(S)) {
            serializeToXml();
            deSerializeFromXm();
        } else if ("exit".equals(S)) {
            System.exit(0);
        }



    }

}
