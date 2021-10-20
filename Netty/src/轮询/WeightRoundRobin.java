package 轮询;

import com.ServerIps;
import com.Weight;

import java.util.*;

public class WeightRoundRobin {
    private static Map<String, Weight> weightMap = new LinkedHashMap<String, Weight>();

    public static String getServer() {

        int totalWeight = 0;

        for (Integer weight : ServerIps.WEIGHT_MAP.values()) {
            totalWeight += weight;
        }

        if (weightMap.isEmpty()) {
            for (String ip : ServerIps.WEIGHT_MAP.keySet()) {
                Integer weight = ServerIps.WEIGHT_MAP.get(ip);
                weightMap.put(ip, new Weight(ip, weight, 0));
            }
        }
        for (Weight weight : weightMap.values()) {
            weight.setCurrentWeight(weight.getCurrentWeight() + weight.getWeight());
        }
        Weight maxCurrentWeight = null;
        for (Weight weight : weightMap.values()) {
            if (maxCurrentWeight == null || weight.getCurrentWeight() > maxCurrentWeight.getCurrentWeight()) {
                maxCurrentWeight = weight;
            }

        }
        maxCurrentWeight.setCurrentWeight(maxCurrentWeight.getCurrentWeight() - totalWeight);
        return maxCurrentWeight.getIp();

    }

    public static boolean ipCheck(String text) {
        if (text != null && !text.isEmpty()) {
            // 定义正则表达式
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            // 判断ip地址是否与正则表达式匹配
            if (text.matches(regex)) {
                // 返回判断信息
                return true;
            } else {
                // 返回判断信息
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        List<String> ips = new ArrayList<String>();
        for (int i = 0; i < 12; i++) {
            ips.add(getServer());
            System.out.println(getServer());
        }
/*        for (int j = 0; j < ips.size(); j++) {

      *//*      if (ipCheck(ips.get(j))) {

                System.out.println(ips.get(j));
                System.out.println("~ip地址合法");
            }*//*

            int k = 0;
            String ss=ips.get(k);
            ips.remove(k);
            for (int i = k; i < ips.size(); i++) {

                //System.out.println(ips.get(j));
               // System.out.print(ips.get(i) + ",");

                //String ss=ips.get(i);
               // ips.remove(ips.get(i));
                //System.out.println(ss);
                // break;
            }

        }*/

            /*   ss(ips);*/
            // System.out.println(ss(ips));

    }
}
