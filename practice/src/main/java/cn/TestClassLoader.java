package cn;

import java.io.*;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;

public class TestClassLoader {

    public static <yyy> void main(String[] args) throws Exception {
        String encoding="utf-8";
        FileReader f= new FileReader("src/main/java/cn/yyy.txt");
        BufferedReader br=new BufferedReader(f);
        String s="";
        while ((s=br.readLine())!=null){
            System.out.println(s);
        }
        f.close();
        String path="src/main/java/cn/yyy.java";
        File file = new File(path);
        if(!file.exists()){
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        FileOutputStream fout = new FileOutputStream(path);
        PrintStream ps = new PrintStream(fout, true, encoding);

        ps.print("package cn;");
 ps.print("\n"+"public class"+" "+"yyy"+"{"+"\n");


        FileInputStream fis=new FileInputStream("src/main/java/cn/yyy.txt");
        byte[] bbuf=new byte[64];
        int hasRead=0;
        while ((hasRead=fis.read(bbuf))>0){
            ps.write(bbuf , 0 , hasRead);

        }
        ps.print("\n"+"}");
        ps.close();
        fis.close();
/*        //创建类的实例
        Class c = Class.forName("cn.yyy");
        Constructor constructor=c.getConstructor();
        //通过构造器对象，初始化一个无参构造
        yyy y= (yyy) constructor.newInstance();
        System.out.println(y.getClass().getTypeName());*/

     /*   Field name=c.getField("name");
        name.set(y,"xxx");
        System.out.println(name.get(y));*/
       /* FileOutputStream f1=new FileOutputStream(path);*/



    }
}
