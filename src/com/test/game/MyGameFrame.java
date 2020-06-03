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
 * �ɻ���Ϸ��������
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
	int period; //��Ϸ�ĳ���ʱ��
	
	@Override
	public void paint(Graphics g) {//�Զ������ã�g�൱��һֻ����
		// TODO Auto-generated method stub
		g.drawImage(bg, 0, 0, null);
		
		plane.drawSelf(g);//���ɻ�
		for(int i=0;i<shells.length;i++){
			shells[i].draw(g);
			
			//����ӵ��Ƿ���ɻ���ײ
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
				Font f = new Font("����",Font.BOLD,50);
				g.setFont(f);
				g.drawString("ʱ�䣺"+period+"��	", 140, 240);
			}
		}
	}
	
	class PaintThread extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
//				System.out.println("�����ػ�һ�Σ�");
				repaint();//�ػ�
				
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//������̼������ڲ���
	class KeyMonitor extends KeyAdapter {
		public void keyPressed(KeyEvent e){
			plane.addDirection(e);
		}
		
		public void keyReleased(KeyEvent e){
			plane.minusDirection(e);
		}
	}

	
	/*
	 * ��ʼ������
	 */
	
	public void launchFrame(){
		this.setTitle("��ķɻ���Ϸ");//������
		this.setVisible(true);//���ô��ڿɼ�
		this.setSize(Constant.Game_width,Constant.Game_height);//���ÿ������ڵĴ�С
		this.setLocation(300, 300);//���ô��ڴ�����λ��
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		new PaintThread().start();//�����ػ����ڵ��߳�
		addKeyListener(new KeyMonitor());//�������̼���
		
		//��ʼ��50���ڵ�
		for(int i=0;i<shells.length;i++){
			shells[i] = new Shell();
		}
	}
	public static void main(String[] args) {
		MyGameFrame f= new MyGameFrame();
		f.launchFrame();
	}
	
	//���˫����
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(500,500);//������Ϸ���ڵĿ�Ⱥ͸߶�
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}  
}
