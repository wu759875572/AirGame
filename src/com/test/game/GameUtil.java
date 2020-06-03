package com.test.game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
/*
 * 加载图片功能
 */
	public class GameUtil {
	
	//工具类最好的将构造器私有化
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
