package com.test.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
/*
 * 飞机游戏的主窗口
 */
public class MyGameFrame01 extends JFrame {
	
	Image air = GameUtil.getImage("images/air.png");
	
	@Override
	public void paint(Graphics g) {//自动被调用，g相当于一只画笔
		// TODO Auto-generated method stub
		super.paint(g);
		Color c = g.getColor();//保存原来的颜色
		Font f = g.getFont();//保存原来的字体
		g.setColor(Color.BLUE);
		
		g.drawLine(100, 100, 300, 300);//画线
		g.drawRect(100, 100, 300, 300);//画矩形
		g.drawOval(100, 100, 300, 300);//画椭圆
		g.fillRect(100, 100, 30, 30);//画实心矩形
		g.setColor(Color.red);
		g.setFont(new Font("宋体", Font.BOLD, 50));
		g.drawString("好菜啊", 100, 200);//写字
		
		g.drawImage(air, 250, 250, null);
		
		g.setColor(c);
		g.setFont(f);
	}

	
	/*
	 * 初始化窗口
	 */
	
	public void launchFrame(){
		this.setTitle("吴的飞机游戏");//窗口名
		this.setVisible(true);//设置窗口可见
		this.setSize(500,500);//设置开启窗口的大小
		this.setLocation(300, 300);//设置窗口打开所在位置
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}
	public static void main(String[] args) {
		MyGameFrame01 f= new MyGameFrame01();
		f.launchFrame();
	}
}
