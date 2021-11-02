package com.序列化;

import com.properties.teacher;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class XmlDemo {
//设备流序列化
        public static void main(String[] args)
        {
            try{

                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream("/opt/data2/bbb.txt"));
                teacher per = new teacher("aaa");
//            Person per1 = new Person("aaa", 500);
                oos.writeObject(per);
//			oos.writeObject(per1);
                oos.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }


}
