package org.com.Test;

import java.applet.Applet;
import java.awt.*;

public class showfont extends Applet {
    Font font1=new Font("Times New Roman", Font.BOLD,14);
    Font font2=new Font("SansSerif",Font.ITALIC,48);
    Font font3=new Font("Serif",Font.PLAIN,60);
    public void paint(Graphics g) {
        g.setFont(font1);
        g.drawString("这是Times New Roman字体",20,30);
        g.setFont(font2);
        g.drawString("这是SanSerif字体",60,30);
        g.setFont(font3);
        g.drawString("这是Srrif字体",80,30);
    }

}