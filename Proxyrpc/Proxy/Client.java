package Proxy;


public class Client {

    public static void main(String[] args) {
//        HelloService helloService = new HelloServiceImpl();就相当于建立一个汽车工厂
        HelloService helloService = ProxyFrameWork.refer(HelloService.class, "localhost", 6666);//动态代理代替了实例化
        //只是一个代理的对象
        helloService.hello("hello");
    }
}
