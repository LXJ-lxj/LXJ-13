package Proxy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

public class ProxyFrameWork {


    //首相给服务端干一件事情就是暴露服务
    //暴露服务目的是为了将某些程序暴露在某一个端口上，让其他人能进行访问
    public static void export(final Object service, int port) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (service == null) {//进行校验
            throw new IllegalArgumentException("argument");
        }
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port" + port);
        }
        //输出用的是那个服务和端口
        System.out.println("Export service" + service.getClass().getName() + "port" + port);
      //打开一个服务端的Socket去接收客户端连接，服务端要去绑定一个端口让客户端去访问
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            //通过无限循环不断的接收请求
            Socket accept = serverSocket.accept();
            //使用流进行读取请求
            //objectInputStream是个二进制的流，所以这里将他包装成一个对象
            ObjectInputStream objectInputStream = new ObjectInputStream(accept.getInputStream());
            String methodName = objectInputStream.readUTF();//拿到相应Strung类型的方法名
            //拿到参数列表：以数组的形式多个传递。就像java的累加载器：将相应的类的字节码变成类的一个对象
            // 为了避免传递的参数类型不一致，所以直接用Class的数组
            //得到参数列表  <?>泛型：支持任何类型
            Class<?>[] paramTypes = (Class<?>[]) objectInputStream.readObject();
            //拿参数值，所有的值都是Object对象的子类
            Object[] args = (Object[]) objectInputStream.readObject();
            //这里将拿出来的二进制流包装成一个对象
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());
            try {
                //先找这个对象的类再找到这个类中的某个方法 (名字，参数类型)为了避免重载所以必须写名字跟参数类型；进行方法的调用
                Method method = service.getClass().getMethod(methodName, paramTypes);
                //调用方法，所以在此之前要先实例化一个对象才能调用
                //第一个参数是实例化的一个对象，第二个参数就是参数值
                Object invoke = method.invoke(service, args);
                //将得到的结果写回到客户端
                objectOutputStream.writeObject(invoke);
                //flush方法是将 objectOutputStream的流信息刷新到Socket中发送出去
                objectOutputStream.flush();
            } catch (Throwable throwable) {
                objectOutputStream.close();
            } finally {
                objectOutputStream.close();
            }

        }
    }

    @SuppressWarnings("unchecked")
    //拿到一个相应的实例对象在去调用方法
    //一定要知道的一个interface抽象类还有就是获取相应的port和host
    //<T> T 泛型：可以传任何类进去，可以返回任何类型的数据
    //要访问类对象中的方法、属性等，所以这里直接访问这个类对象就全包括了
    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) {
        if (interfaceClass == null) {//校验
            throw new IllegalArgumentException("interface class == null");
        }
        if (!interfaceClass.isInterface()) {//判断这个方法是否是个接口。
            // 因为动态代理是由接口承担相应任务的，如果是个实例类就可以直接去调用就可以，就用不到动态代理了
            throw new IllegalArgumentException("The" + interfaceClass.getName() + "must be interface");
        }

        //端口号
        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("host==null!");
        }
        //ip
        if (port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port" + port);
        }
        System.out.println("Get remote service" + interfaceClass.getName());
         //动态代理
        //拿到一个类的实例。。第一个参数interfaceClass要进入jvm中既要拿到他的一个类加载器（getClassLoader()）.
        //第二个参数传一个interface类型的Class数组
        //new InvocationHandler()进行调用（匿名内部实现）
        //加载相应的ClassLoader和class对象
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            @Override
            //实现接口中的方法
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //Object proxy这里是一个实例，但是这里没获取真正实例的过程
                //调用相应实例中的方法
                //首先通过Socket将上边的请求传过来
                Socket socket = new Socket(host, port);
                //拿到这个请求之后
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                //进行写
                objectOutputStream.writeUTF(method.getName());
                //写入参数列表
                objectOutputStream.writeObject(method.getParameterTypes());
                //写入参数值
                objectOutputStream.writeObject(args);
                objectOutputStream.flush();
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                return objectInputStream.readObject();
            }
        });
    }
}
