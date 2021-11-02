package com.Student;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;

public class DomJ4 {
    public static void main(String[] args) throws DocumentException {

        /*
       首先导入jar包
         */
        SAXReader reader=new SAXReader();   //SAXReader表示一个管道，通过流的方式将xml文件读出来
        Document document=reader.read(new File("student.xml"));
//        System.out.println(document.asXML());  //打印这个文件中的所有内容
        Element root=document.getRootElement();  //获取根节点students
//        迭代跟跟元素下的所有名叫student的子元素
         for (Iterator i=root.elementIterator("student");i.hasNext();){
             Element student= (Element) i.next();//获得student


             //         获得student元素的属性number
             String number=student.attributeValue("id");
//             student子元素的内容（name age sex）
             /*String name=student.elementText(" name");
             String age=student.elementText(" age");
             String sex=student.elementText(" sex");*/          //elementText  有两种方法属性
             String name=student.elementText("name");
             String age=student.elementText("age");
             String sex=student.elementText("sex");

             System.out.println("当前学生的学号是："+number+"姓名是："+name+"年龄是："+age+"性别是："+sex);

         }


    }
}
