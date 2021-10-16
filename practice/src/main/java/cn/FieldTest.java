package cn;

import java.lang.reflect.Field;

class Obj{
    protected String xx;
}
class Person extends  Obj{
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
public class FieldTest {
    public static void main(String[] args) throws Exception {
        //创建一个对象
        Person p=new Person();
        //获取Person类对用的class对象
        Class<Person> clz=Person.class;
        //获取Person的名为name的成员变量
        //使用getDeclaredField()方法表明可获取各种访问控制符的成员变量
        Field nameField=clz.getDeclaredField("name");
        //设置通过反射访问该成员变量时取消访问权限检查
        nameField.setAccessible(true);
        //调用set()方法为p对象的name成员变量设置值
        nameField.set(p,"xxx");
        System.out.println(p);

        Field ageField=clz.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.setInt(p,20);
        System.out.println(p);

    }
}
