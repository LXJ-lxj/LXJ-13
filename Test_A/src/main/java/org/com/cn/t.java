package org.com.cn;

class  Mouse{
    private String name;
    private boolean isAlive=true;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Mouse(String s, int age) {
        this.name=s;
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

}
class Cat extends Mouse{
    private String name;
    private int record=0;

    public Cat(String s, int age) {
        super(s, age);
        System.out.println("cat:"+"\n");
        System.out.println("姓名："+s+"\n"+"年龄："+age);
    }

    public  int catchMouse(Mouse mouse){
        if (mouse instanceof Mouse){
            mouse.setAlive(false);//将isAlive改为false
            System.out.println(mouse.isAlive());
            System.out.println("传进来的对象属性Mouse"+"\n"+"姓名："+mouse.getName()+mouse.isAlive()+ mouse.getAge()+"岁");
            record=record+1;
        }
        return record;
    }
}
public class t {
    public static void main(String[] args) {
        Mouse m1=new Mouse("小明",11);
        Mouse m2=new Mouse("小红",22);
        Mouse m3=new Mouse("小芳",33);
        Cat c=new Cat("晓杰",50);
        System.out.println(m1.isAlive()+"姓名："+m1.getName()+m1.getAge()+"岁");
        System.out.println(m2.isAlive()+"姓名："+m2.getName()+m2.getAge()+"岁");
        System.out.println(m3.isAlive()+"姓名："+m3.getName()+m3.getAge()+"岁");
        System.out.println("捕捉的mouse对象个数"+ c.catchMouse(m1));
    }
}
