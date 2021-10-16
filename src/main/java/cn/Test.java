package cn;


import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Properties;

class Moble{
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
public class Test {
    public static void main(String[] args) throws Exception {
        Properties properties=new Properties();
        Class clazz=Class.forName("cn.Moble");
        Moble moble=(Moble) clazz.newInstance();
        properties.load(new FileInputStream("/xxx.properties"));
        Field[] fields=clazz.getDeclaredFields();
        for (Field f:fields){
            String propname=f.getName();
            String methodname="set"+propname.substring(0,1).toUpperCase()
                    +propname.substring(1,propname.length());
            Method method=clazz.getMethod(methodname,f.getType());
            method.invoke(moble,properties.getProperty(propname));
        }
    }

}
