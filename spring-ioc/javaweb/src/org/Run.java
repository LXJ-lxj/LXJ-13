package org;




import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


/**
  * Processor 用于接收请求内容，分析请求内容，处理响应，发送响应
  *
  */
class Run implements Runnable
{
    private Socket socket;
    // 定义虚拟服务器所在的根目录
// 当访问请求地址为 http://localhost:8888/doc/index.html
// 程序将读取并返回 WEB_ROOT\doc\index.html 文件的内容
    private final static String WEB_ROOT = "/home/lxj/LI_x/spring-ioc/javaweb/web/Test.html";

    public Run(Socket s)
    {
        this.socket = s;
    }

    @Override
    public void run()
    {
// 分析请求内容
        String filePath = parse();
// 处理响应，读取页面文件内容
        String fileContent = readFile(filePath);
        if(fileContent != null)
        {
// 发送响应
            sendResult(200, "OK", fileContent);
        }
    }

    /**
     * 解析请求内容，根据请求中的URL内容，获取请求对应的页面文件路径
     * @return 页面相对路径
     */
    public String parse()
    {
        try
        {
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String requestContent = reader.readLine();
// System.out.println(requestContent);
            String[] contents = requestContent.split(" ");
            String url = contents[1];
// System.out.println(url);
// in.close();
// reader.close();
            return url;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传入的文件相对路径，通过文件IO流读取内容，并返回
     * 如果没有找到文件，发送404错误响应
     */
    public String readFile(String filePath)
    {
        String absFilePath = WEB_ROOT + filePath;
        File file = new File(absFilePath);
        System.out.println(file.getAbsolutePath() + " 文件是否存在："+file.exists());

// File IO流读取文件内容
        if(file.exists())
        {
            try
            {
                byte[] buff = new byte[(int) file.length()];
                FileInputStream fis = new FileInputStream(file);
                fis.read(buff);
                String fileContent = new String(buff);
                fis.close();
                return fileContent;
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            sendResult(404, "File not found", "404... 你懂的！");
        }
        return null;
    }

    /**
     * 通过响应发送响应内容

     */
    public void sendResult(int code, String message, String result)
    {
// 通过socket对象的输出流 发送响应内容
        try
        {
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            osw.write("HTTP/1.1 " + code + " " + message + "\r\n");
            osw.write("Content-Length:" + result.getBytes().length + "\r\n");
            osw.write("Content-Type:text/html\r\n");
            osw.write("\r\n");
            osw.write(result);
            osw.flush();
            osw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
