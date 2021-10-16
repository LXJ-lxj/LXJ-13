package com;
import java.io.*;

class Tea extends yyy {
int id;
String name;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Tea(int id,String name){
        this.id=id;
        this.name=name;
    }
}

class MyWriter  implements xxx {

    public MyWriter(FileOutputStream fileOutputStream) {
        System.out.println(fileOutputStream);
    }

    @Override
    public void write(Tea xx,FileOutputStream path) throws Exception {
      /*  ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(xx);
        OutputStream out =new FileOutputStream("/home/lxh/文档/aaaa.txt");
        byte[] b=value.getBytes();
        for(int i=0;i<b.length;i++){
            out.write(b[i]);
        }
        out.close();*/
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() {

    }


}


public class LXJ {

    public static void main(String[] args) throws Exception {
        MyWriter myWriter= new MyWriter(new FileOutputStream("/home/lxh/文档/aaaa.txt"));
     Tea t=new Tea(1,"xxx");


    }


}
