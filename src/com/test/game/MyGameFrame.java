package com.test.game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;
/*
 * 飞机游戏的主窗口
 */
public class MyGameFrame extends Frame {
	
	Image planeImg = GameUtil.getImage("images/plane.png");
	Image bg = GameUtil.getImage("images/bg.jpg");
	
	Plane plane = new Plane(planeImg, 250, 250);
	Shell shell = new Shell();
	Shell[] shells = new Shell[50];
	
	Explode bao;
	Date startTime = new Date();
	Date endTime;
	int period; //游戏的持续时间
	
	@Override
	public void paint(Graphics g) {//自动被调用，g相当于一只画笔
		// TODO Auto-generated method stub
		g.drawImage(bg, 0, 0, null);
		
		plane.drawSelf(g);//画飞机
		for(int i=0;i<shells.length;i++){
			shells[i].draw(g);
			
			//检测子弹是否与飞机相撞
			boolean peng = shells[i].getRect().intersects(plane.getRect());
			if(peng){
				plane.live = false;
				shell.live = false;
				if(bao == null){
				bao = new Explode(plane.x, plane.y);
				
				endTime = new Date();
				period = (int)((endTime.getTime()-startTime.getTime())/1000);
				}
				bao.draw(g);
			}
			
			if(!plane.live){
				g.setColor(Color.red);
				Font f = new Font("宋体",Font.BOLD,50);
				g.setFont(f);
				g.drawString("时间："+period+"秒	", 140, 240);
			}
		}
	}
	
	class PaintThread extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
//				System.out.println("窗口重画一次！");
				repaint();//重画
				
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//定义键盘监听的内部类
	class KeyMonitor extends KeyAdapter {
		public void keyPressed(KeyEvent e){
			plane.addDirection(e);
		}
		
		public void keyReleased(KeyEvent e){
			plane.minusDirection(e);
		}
	}

	
	/*
	 * 初始化窗口
	 */
	
	public void launchFrame(){
		this.setTitle("吴的飞机游戏");//窗口名
		this.setVisible(true);//设置窗口可见
		this.setSize(Constant.Game_width,Constant.Game_height);//设置开启窗口的大小
		this.setLocation(300, 300);//设置窗口打开所在位置
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		new PaintThread().start();//启动重画窗口的线程
		addKeyListener(new KeyMonitor());//启动键盘监听
		
		//初始化50个炮弹
		for(int i=0;i<shells.length;i++){
			shells[i] = new Shell();
		}
	}
	public static void main(String[] args) {
		MyGameFrame f= new MyGameFrame();
		f.launchFrame();
	}
	
	//解决双缓冲
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(500,500);//这是游戏窗口的宽度和高度
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}  
}
