package com.test.game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
/*
 * ����ͼƬ����
 */
	public class GameUtil {
	
	//��������õĽ�������˽�л�
	private  GameUtil() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static Image getImage(String path){
		BufferedImage bi = null;
		try{
			URL u = GameUtil.class.getClassLoader().getResource(path);
			bi = ImageIO.read(u);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return bi;
	}
	
}
