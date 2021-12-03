package org.com.cn;

import java.util.Scanner;

class  Circle{
    private double radius;//半径
    private double area;//面积
    private  double length;//周长
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Double area(double radius){
        double t=3.14;
        return radius*radius*t;
    }

}
public class demo {
    public static void main(String[] args) {
        System.out.println("请输入半径！");
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        Circle c1=new Circle();
        Double area = c1.area(a);
        System.out.println("面积为："+area);
        Circle c2=new Circle();
    }
}
