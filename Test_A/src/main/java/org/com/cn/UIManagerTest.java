package org.com.cn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIManagerTest {
    public static void main(String[] args) {
        new LookAndFeelFrame();
    }
}
class LookAndFeelFrame extends JFrame{
    //当前安装的所有的观感数组
    private UIManager.LookAndFeelInfo[] infos;
    public LookAndFeelFrame(){
        this.initMenu();
        this.init();
    }
    public void init(){
        this.setTitle("动态切换观感的示例");
        this.setBounds(100,100,400,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public void initMenu(){
        JMenuBar bar=new JMenuBar();
        this.setJMenuBar(bar);
        JMenu menu=new JMenu("更改观感");
        bar.add(menu);
        infos=UIManager.getInstalledLookAndFeels();
        //获取当前安装的所有观感并用名作为选项添加到菜单中
        for (UIManager.LookAndFeelInfo info:infos){
            JMenuItem item=new JMenuItem(info.getName());
            menu.add(item);
            //注册事件监听器
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd=e.getActionCommand();
                    for (UIManager.LookAndFeelInfo info:infos)
                    {
                        if (info.getName().equals(cmd))
                        {
                            //把指定的观感设置为当前的观感
                            try {
                                UIManager.setLookAndFeel(info.getClassName());
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            //更新所有组件的观感
                            SwingUtilities.updateComponentTreeUI(LookAndFeelFrame.this);
                        }
                    }
                }
            });
        }
    }
}
