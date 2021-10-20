package com;

import java.util.*;

public class ServerIps {
    public static final List<String> LIST= Arrays.asList(
            "192.168.0.1",
            "192.168.0.2",
            "192.168.0.4",
            "192.168.0.8"
    );
    public static final Map<String, Integer>WEIGHT_MAP=new LinkedHashMap<String, Integer>();
    static {
        String A="192.168.1.4";
        String B="172.4.2.6";
        String C="192.168.3.5";
        String D="192.168.0.2";
        String E="192.168.0.4";
        WEIGHT_MAP.put( A,2);
        WEIGHT_MAP.put( B,8);
        WEIGHT_MAP.put( C,1);
        WEIGHT_MAP.put( D,9);
        WEIGHT_MAP.put( E,4);
       /* WEIGHT_MAP.put(   "192.168.0.4",7);
        WEIGHT_MAP.put(  "192.168.0.8",9);*/
    }
/*public static final ArrayList<String>WEIGHT_MAP=new ArrayList<String>();
static {
    WEIGHT_MAP.add("192.168.1.4");
    WEIGHT_MAP.add("192.168.3.5");
    WEIGHT_MAP.add("192.168.0.2");
    WEIGHT_MAP.add("192.168.0.4");
    WEIGHT_MAP.add("192.173.0.5");
    WEIGHT_MAP.add("172.0.1.0");
}*/
}
