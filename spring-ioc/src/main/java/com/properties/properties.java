package com.properties;


import com.fastjson.Person;

import java.io.*;

//properties实现序列化反序列化：   底层就是hashmap 通过k获取v
public class properties {
    public static void main(String[] args) throws Exception {
        Person person=new Person("小明","435436543",24);

        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("aa.txt"));
        oos.writeObject(person);
        oos.close();

        //反序列化
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("aa.txt"));
        Person person1=(Person) ois.readObject();
        System.out.println(person1);

    }
}
