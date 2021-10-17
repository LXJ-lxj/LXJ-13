package com.easy.archiecture.springaop.aopannotation;


import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect  //表示当前是一个要进行aop操作的类
@Component
public class AopTest {

/*SpringAopTest.xml和配置文件中的含义一样*/
    @Pointcut("execution(* com.easy.archiecture.springaop.aopannotation..*.*(..))") // 这个包下边的所有方法都要
    //执行下边的操作    {例如在执行主函数中的       user.print()；方法时由于此方法在这个包的范围内
    // 所以会先执行下边的操作然后在执行       user.print();方法}
    private void anyOldTransfer() {
    } // the pointcut signature


    @Before("com.easy.archiecture.springaop.aopannotation.AopTest.anyOldTransfer()")
//    @After()
//    @Around()
    private void addBeforePrint() {
        System.out.println("add----before");
    }
}
