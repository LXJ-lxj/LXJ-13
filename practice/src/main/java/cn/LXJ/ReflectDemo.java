package cn.LXJ;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        /*得到该类的所有数据字段*/
        String className="cn.LXJ";
        Class c=Class.forName(className);
        showFields(c); //显示该类所有属性信息
        showConstructors(c); // 显示所有构造方法
        showMethods(c);// 显示该类所有方法信息
    }
/*得到构造器*/
    private static void showConstructors(Class c) {
        Constructor[] cons=c.getDeclaredConstructors();
        for (Constructor con:cons){
            String m=Modifier.toString(con.getModifiers());//返回int类型值表示该字段的修饰符。
            String  n=con.getName();
            System.out.print(m + " " + n + "(");
            Class[] params = con.getParameterTypes();//方法返回一个Class对象数组
            for (int j = 0; j < params.length; j++) {
                if (j == params.length - 1) {
                    System.out.print(params[j].getSimpleName());
                } else
                    System.out.print(params[j].getSimpleName() + ",");
            }
            System.out.println(")");
        }
    }

    private static void showMethods(Class c) {

        Method[]m=c.getMethods();
        for (int i=0;i<m.length;i++){
            /*方法修饰符*/
            String modify=Modifier.toString(m[i].getModifiers());
            System.out.println(modify+"");
            /* 方法返回类型 */
            Class returntype=m[i].getReturnType();
            System.out.print(returntype.getName() + " ");
            /* 方法名称 */
        }
    }
/*得到该类的所有数据字段*/
    private static void showFields(Class c) {
        Field field[]=c.getDeclaredFields();
        for (Field f:field){
            //数据字段修饰符
            String m= Modifier.toString(f.getModifiers());
            //字段数据类型名
            Class type=f.getType();
            String t=f.getName();
            //类的属性名称
            String n=f.getName();
            System.out.println(m+""+t+""+n);

        }
    }
}
