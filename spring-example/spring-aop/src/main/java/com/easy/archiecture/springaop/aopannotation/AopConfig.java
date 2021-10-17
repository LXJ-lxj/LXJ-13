package com.easy.archiecture.springaop.aopannotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration  //表示当前类是一个配置类，相当于宇哥xml
@ComponentScan(basePackages = "com.easy.archiecture.springaop.aopannotation")//扫描哪些包让spring管理起来
@EnableAspectJAutoProxy  //当前的spring程序可以用AspectJ annotation，并且用spring实现
public class AopConfig {
}
