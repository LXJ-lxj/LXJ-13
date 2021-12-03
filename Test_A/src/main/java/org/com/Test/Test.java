package org.com.Test;

import javax.swing.*;
import java.awt.*;

public class Test extends JFrame{
    public Test()
    {
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    Font font1=new Font("Times New Roman", Font.BOLD,100);
    Font font2=new Font("SansSerif",Font.ITALIC,66);
    Font font3=new Font("Serif",Font.PLAIN,78);
    public void paint(Graphics g)
    {
        g.setFont(font1);
        g.drawString("这是Times New Roman字体",20,30);
        g.setFont(font2);
        g.drawString("这是SanSerif字体",60,30);
        g.setFont(font3);
        g.drawString("这是Srrif字体",80,30);
        g.setColor(Color.blue);
        g.drawLine(50,30,450,260);
    }
    public static void main(String[] args)
    {
        new Test().setVisible(true);
    }
}
