package 随机;

import com.ServerIps;

import java.util.ArrayList;
import java.util.List;

public class Random {
    public static String getServer(){
        List<String>ips=new ArrayList<String>();
        for (String ip:ServerIps.WEIGHT_MAP.keySet()){
            Integer weight=ServerIps.WEIGHT_MAP.get(ip);

            for (int i=0;i<weight;i++){
                ips.add(ip);
            }
        }
        java.util.Random random=new java.util.Random();
        return ServerIps.LIST.get(random.nextInt(ips.size()));
    }


    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            System.out.println(getServer());
        }
    }
}
