package netty.ProxyRPC;

public class Client {
     public static void main(String[] args) {
        HelloServiceImpl helloService = new HelloServiceImpl(); //工厂
        helloService.hello("hello");
    }
}
