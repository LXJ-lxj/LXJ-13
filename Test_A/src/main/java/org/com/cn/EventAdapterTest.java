package org.com.cn;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class EventAdapterTest {
    public static void main(String[] args) {
        new MyFrame();
    }
}
class MyFrame extends JFrame{
    private static Image image;
    private int heroX=5;//人物的X坐标
    private int heroY=3;//人物的Y坐标
    public MyFrame(){
        this.setTitle("事件适配器类的使用示例");
        this.addPanel();
        this.setBounds(109,100,400,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void addPanel(){
        this.add(new MyPanel());
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode==KeyEvent.VK_UP){
                    //如果是向上键
                    heroY--;
                }else if (keyCode== KeyEvent.VK_DOWN){
                    heroY++;
                }else if (keyCode== KeyEvent.VK_LEFT){
                    heroX--;
                }else if (keyCode== KeyEvent.VK_RIGHT){
                    heroX++;
                }
                MyFrame.this.repaint();//重绘
            }
        });
    }
    class MyPanel extends JPanel{//成员内部类
        public MyPanel(){this.setLayout(null);}
        public void paintComponent(Graphics g){
            Image i=new ImageIcon("src/DIDI打车.jpeg").getImage();
            g.drawImage(i,heroX*32,heroY*32,this);//画人物
        }
         }

}
