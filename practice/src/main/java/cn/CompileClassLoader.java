package cn;
import cn.LXJ.xxx;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;


public class CompileClassLoader {

    public static void main(String[] args) throws Exception {
        //获取classinfo   寻找名称的类文件，加载进内存 产生class对象
        Class c = Class.forName("cn.LXJ.xxx");
        Constructor constructor=c.getConstructor();
        /*构造函数新建对象实例*/
        //通过构造器对象 newInstance 方法对对象进行初始化 无参数构造函数
        //也可以添加参数，成为有参构造
        xxx t= (xxx) constructor.newInstance();
       //获取此类中的所有属性字段
/*        Field[] field=c.getDeclaredFields();*/
        //获取指定字段
        Field nameField=c.getDeclaredField("name");
        //设置通过反射访问该成员变量时取消访问权限检查(因为private是私有权限)
        nameField.setAccessible(true);
        // 调用set()方法为t对象的name成员变量设置值
        nameField.set(t,"xxx");
        String encoding="utf-8";
        System.out.println(nameField.get(t));
        System.out.println(nameField+"("+")");
        String n=nameField.toString().substring(0,7)+" "+nameField.toString().substring(18,24)+" "+
                nameField.toString().substring(36);
        System.out.println(n);
        System.out.println(nameField.getType());
        Field id=c.getDeclaredField("id");
        id.setAccessible(true);
        id.setInt(t,99);
        System.out.println(id.get(t));
        System.out.println(id+"("+")");
        String cm=id.toString().substring(0,11)+" "+
                id.toString().substring(23);
        System.out.println(cm);
        Field sex=c.getDeclaredField("sex");
        sex.setAccessible(true);
        sex.set(t,"女");
        System.out.println(sex.get(t));
        System.out.println(sex+"("+")");
        String cn=sex.toString().substring(0,7)+" "+sex.toString().substring(18,24)+" "+
                sex.toString().substring(36);
        System.out.println(cn);
        Field address=c.getDeclaredField("address");
        address.setAccessible(true);
        address.set(t,"山东");
        System.out.println(address.get(t));
        System.out.println(address+"("+")");
        String add=address.toString().substring(0,7)+" "+address.toString().substring(18,24)+" "+
                address.toString().substring(36);
        System.out.println(add);
        FileOutputStream fout = new FileOutputStream("src/main/java/cn/LXJ/aaaa.txt");
        PrintStream ps = new PrintStream(fout, true, encoding);
        ps.print("public"+" "+c.toString().substring(0,5)+" "+c.toString().substring(13)+"{"+"\n");
        ps.print(cm+"\n");
        ps.print(cn+"\n");
        ps.print(n+"\n");
        ps.print(add+"\n");
        ps.print("}");
    /*    oos.writeObject(cn.getBytes(StandardCharsets.UTF_8)+"\n");
        oos.writeObject(n.getBytes(StandardCharsets.UTF_8)+"\n");
        oos.writeObject(add.getBytes(StandardCharsets.UTF_8)+"\n");
*/
        ps.close();
       /* for (Field f:field){
            String propname=f.getName();
            String methodname = "set" +
                    propname.substring(0, 1).toUpperCase()
                    + propname.substring(1, propname.length());

            //Method method = c.getMethod(methodname, f.getType());
  *//*          System.out.println(methodname);
            System.out.println(propname);*//*
        }*/

    }


}
