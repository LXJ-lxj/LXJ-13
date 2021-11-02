package com.fastjson;



import com.alibaba.fastjson.JSONObject;

import javax.swing.*;

public class json {
    public static void main(String[] args) {
        Person person=new Person("李小军","123",23);
        String json=new JSONObject().toJSONString(person);
        System.out.println("序列化json："+json);

        //反序列化json
        Person person1=JSONObject.parseObject(json,Person.class);
        System.out.println("反序列化json："+person1);
    }
}
