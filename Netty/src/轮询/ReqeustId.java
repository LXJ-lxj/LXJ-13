package 轮询;

public class ReqeustId {
    public static Integer num=0;
    public  static  Integer getAndIncrement(){
        return num++;
    }
}
