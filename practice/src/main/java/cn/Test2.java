package cn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Arch{
    int sale(int a);
}
class MyIBM implements Arch{

    @Override
    public int sale(int a) {
        System.out.println("ibm的电脑:"+a);
        return 12;
    }
}
class MyHW implements Arch{

    @Override
    public int sale(int a) {
        System.out.println("交换机:"+a);
        return 12;
    }
}
class MyHandler implements InvocationHandler {
    private Arch ibm;
    public MyHandler(Arch ibm){
        this.ibm=ibm;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("送鼠标");
        int c= (int) method.invoke(this.ibm,args);
        System.out.println("送键盘");
        return c;
    }
}
public class Test2 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        String str="cn.MyHW";
        //Proxy.newProxyInstance 将里边的三个参数动态拼出一个proxy  class类
        Arch a= (Arch) Proxy.newProxyInstance(Arch.class.getClassLoader(), //对生成的代理对象进行加载
                new Class[]{Arch.class},  //将要给我需要代理的对象提供一组什么接口（将继承那个接口）
                new MyHandler((Arch) Class.forName(str).newInstance()));//表示代理对象调用的方法转发到的那个接口InvocationHandler
        int c=a.sale(11);
        System.out.println(c);
    }
}
