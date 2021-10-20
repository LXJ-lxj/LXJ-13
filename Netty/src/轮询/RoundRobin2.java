package 轮询;

import com.ServerIps;

import javax.print.attribute.standard.RequestingUserName;

public class RoundRobin2 {
    public static String getServer(){

 int totalWeight=0;

 for (Integer weight:ServerIps.WEIGHT_MAP.values()){
     totalWeight +=weight;
 }
    //    System.out.println(totalWeight);                offset=offset - weight;

 int requestId=ReqeustId.getAndIncrement();
 int offset=requestId %totalWeight;  //取余

        //遍历
        for (String ip:ServerIps.WEIGHT_MAP.keySet()){

            Integer weight=ServerIps.WEIGHT_MAP.get(ip);
            if (offset<weight){
                return ip;
            }
            offset=offset - weight;
        }
 return null;
    }


    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
