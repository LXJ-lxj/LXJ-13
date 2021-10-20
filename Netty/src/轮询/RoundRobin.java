package 轮询;

import com.ServerIps;

public class RoundRobin {
    private static Integer pos=0;
    private static String getServer(){
        if (pos>=ServerIps.LIST.size()){
            pos=0;
        }
        String ip= ServerIps.LIST.get(pos);
        pos++;

        return ip;
    }

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            System.out.println(getServer());
        }
    }
}
