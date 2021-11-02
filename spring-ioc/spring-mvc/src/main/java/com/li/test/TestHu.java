package com.li.test;

public class TestLi {

    public void test() {
        System.out.println("main方法中");
    }

    public void test2() {
        System.out.println("static静态块中");
    }

    static {
        TestLi li = new TestLi();
        li.test2();
    }

    public static void main(String[] args) {
        TestLi li = new TestLi();
        li.test();
    }

}
