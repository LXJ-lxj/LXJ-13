package netty.ProxyRPC;

public class HelloServiceImpl implements HelloService{
    //接口的实现类
    public void hello(String msg){
        System.out.println(msg);
    }
}
