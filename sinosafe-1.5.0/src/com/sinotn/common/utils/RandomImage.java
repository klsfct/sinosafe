package com.sinotn.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;


/** */
/**
 * 生成随机数字或字母串，以图像方式显示，用于人工识别，使程序很难识别。 减小系统被程序自动攻击的可能性。
 * 由于数字的0，1，2易和字母的o，l,z混淆，使人眼难以识别，因此不生成数字 和字母的混合串。 生成的串字母统一用小写，串的最大长度为16。
 * 
 */

public class RandomImage {
	// 字符的高度和宽度，单位为像素
	private  static int h = 25;
	private  static int w = 60;
	// 字符间距
	private static  int wordWidth = 10;
	// 字符大小
	private static  int fontSize = 16;
	// 垂直方向起始位置
	private static int initypos = 18;
	// 要生成的字符个数
	private static  int charCount = 4;
	// 颜色数组，绘制字串时随机选择一个
	private static final Color[] CHAR_COLOR = {Color.MAGENTA,Color.RED, Color.BLUE,Color.MAGENTA,new Color(142,86,11),
		new Color(64,0,128),new Color(11,11,89),new Color(94,6,74),new Color(0,48,96),new Color(35,61,36),new Color(2,64,25),new Color(138,23,40)};
	private static int colorLen = 11;
	private  static  BufferedImage bi;
	private  static  Graphics2D g;
	
	/** */
	/**
	 * 生成图像的格式常量，JPEG格式,生成为文件时扩展名为.jpg； 输出到页面时需要设置MIME type 为image/jpeg
	 */
	public static  String GRAPHIC_JPEG = "JPEG";
	//private  static JPEGImageEncoder encoder ;
	private  static Font font = new Font(null, Font.BOLD, fontSize);
	private  static int xpos,ypos = initypos;
	private static  String c ;
	private  static Random rand = new Random();
	private static int x,y;
	/*******************************************************************
	 * 以图像方式绘制字符串，绘制结果输出到流out中
	 * 
	 * @param out  图像结果输出流
	 * @return 随机生成的串的值
	 ********************************************************************/
	public  String draw(OutputStream out){
		
		String charValue = StringUtils.randomNumeric(charCount);
		//String charValue = "1111";
		bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
		g = bi.createGraphics();
		g.setBackground(Color.WHITE);
		g.fillRect(0, 0, w, h);		
		g.setFont(font);// 设置font			
		for (int i = 0; i < charCount; i++) {// 绘制charValue,每个字符颜色随机
			c = charValue.substring(i, i + 1);
			Color color = CHAR_COLOR[rand.nextInt(colorLen)];
			g.setColor(color);	
			xpos = (i+1) * wordWidth;			
			g.drawString(c, xpos, ypos);	
		}
		//生成干扰点
		/*
		for (int k = 0; k < 6; k++) {						
			g.drawLine( rand.nextInt(10), rand.nextInt(20), rand.nextInt(200),rand.nextInt(100));
		}	
		*/
		try{
			ImageIO.write(bi, RandomImage.GRAPHIC_JPEG, out);
			//encoder = JPEGCodec.createJPEGEncoder(out);
			//encoder.encode(bi);
            out.flush();
		}catch(IOException ex){			
			ex.printStackTrace();
		}finally{
			try{
				if(null!=out) out.close();
			}catch(IOException exo){			
				exo.printStackTrace();
			}
		}
		return charValue;
	}

	/*********************************************
	 * 返回[from,to)之间的一个随机整数
	 * 
	 * @param from 起始值
	 * @param to  结束值
	 * @return [from,to)之间的一个随机整数
	 *********************************************/
	protected int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}

	/** */
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		RandomImage r = new RandomImage();
		System.out.println(r.draw(new FileOutputStream("c:/myimg.jpg")));
	}
}
