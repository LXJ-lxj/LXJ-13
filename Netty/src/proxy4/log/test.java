package proxy4.log;

import java.util.Date;

import org.apache.poi.ss.formula.functions.T;
import org.lib.com.TestAddr;

public class test {
    public static void main(String[] args) {
        Date date=new Date();
        System.out.println(date.toString());
        System.out.println(TestAddr.getWinLocalIP());

    }
}
