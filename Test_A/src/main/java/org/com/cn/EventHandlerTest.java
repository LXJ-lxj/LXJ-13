package org.com.cn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class EventHandlerTest {
    public static void main(String[] args) {
        JButton btn=new JButton("Test");
        btn.setBounds(100,40,80,26);
        //使用匿名内部类给按钮注册一个动作事件监听器
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton btn= (JButton) e.getSource();//事件源
                    System.out.println("事件发生在：”+\""+btn.getText()+"\"按钮上");
                    Date date=new Date(e.getWhen());
                    System.out.println("事件发生时间："+date);
                }
            });
        JFrame frame=new JFrame("事件处理示例");
        frame.setLayout(null);
        frame.setBounds(100,100,300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
