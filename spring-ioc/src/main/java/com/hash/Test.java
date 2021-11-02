package com.hash;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        HashMap hm=new HashMap();
        String str="Card.a.cid=1";   //拆成k - v对
        String[] ss=str.split("="); //分割
        str=str.replace("=",","); //把等号换成逗号
        System.out.println(str);
        System.out.println(str.substring(0,3));  //取子串
       /* hm.put(ss[0],ss[1]);
        System.out.println(hm.get("Card.a.cid"));*/
    }
}
