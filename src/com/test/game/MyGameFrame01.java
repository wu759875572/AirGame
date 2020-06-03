package com.test.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
/*
 * �ɻ���Ϸ��������
 */
public class MyGameFrame01 extends JFrame {
	
	Image air = GameUtil.getImage("images/air.png");
	
	@Override
	public void paint(Graphics g) {//�Զ������ã�g�൱��һֻ����
		// TODO Auto-generated method stub
		super.paint(g);
		Color c = g.getColor();//����ԭ������ɫ
		Font f = g.getFont();//����ԭ��������
		g.setColor(Color.BLUE);
		
		g.drawLine(100, 100, 300, 300);//����
		g.drawRect(100, 100, 300, 300);//������
		g.drawOval(100, 100, 300, 300);//����Բ
		g.fillRect(100, 100, 30, 30);//��ʵ�ľ���
		g.setColor(Color.red);
		g.setFont(new Font("����", Font.BOLD, 50));
		g.drawString("�ò˰�", 100, 200);//д��
		
		g.drawImage(air, 250, 250, null);
		
		g.setColor(c);
		g.setFont(f);
	}

	
	/*
	 * ��ʼ������
	 */
	
	public void launchFrame(){
		this.setTitle("��ķɻ���Ϸ");//������
		this.setVisible(true);//���ô��ڿɼ�
		this.setSize(500,500);//���ÿ������ڵĴ�С
		this.setLocation(300, 300);//���ô��ڴ�����λ��
		
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
