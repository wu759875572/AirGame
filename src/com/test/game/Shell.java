package com.test.game;

import java.awt.Color;
import java.awt.Graphics;

/*
 * 炮弹类
 */
public class Shell extends GameObject {
	
	double degree;
	
	boolean live = true;
	
	public Shell(){
		
			x = 200;
			y = 200;
			width = 10;
			height = 10;
			speed = 3;

			degree = Math.random() * Math.PI * 2;
		
		
	}
	
	public void draw(Graphics g){
		
		Color c =g.getColor();
		g.setColor(Color.YELLOW);
		
		g.fillOval((int)x, (int)y, width, height);
		
		//炮弹沿着任意的角度飞出
		x +=speed*Math.cos(degree);
		y +=speed*Math.sin(degree);
		
		if(x<0||x>Constant.Game_width-width){
			degree = Math.PI - degree;
		}
		if(y<30||y>Constant.Game_height-height){
			degree =  - degree;
		}
		
		g.getColor();
		
	}
}
