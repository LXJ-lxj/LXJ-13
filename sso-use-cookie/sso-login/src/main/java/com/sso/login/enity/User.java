package com.sso.login.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data //添加getter setter方法
@NoArgsConstructor //创建一个无参构造器
@AllArgsConstructor //创建一个全参构造器
@Accessors(chain = true) //添加链式调用   例如 ： new User().SetId();
public class User {
    private Integer id;
    private String username;
    private String password;

}
