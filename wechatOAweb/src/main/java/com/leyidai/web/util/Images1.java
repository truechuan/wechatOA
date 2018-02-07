package com.leyidai.web.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Images1 {
    // 图片的宽度。
    private int width = 160;
    // 图片的高度。
    private int height = 40;
    // 验证码字符个数
    private int codeCount = 4;
    // 验证码干扰线数
    private int lineCount = 20;
    // 验证码
    private String code = null;
    // 验证码图片Buffer
    private BufferedImage buffImg = null;
    Random random = new Random();

    public Images1() {
        creatImage();
    }

    public Images1(int width, int height) {
        this.width = width;
        this.height = height;
        creatImage();
    }

    public Images1(int width, int height, int codeCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        creatImage();
    }

    public Images1(int width, int height, int codeCount, int lineCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        creatImage();
    }

    // 生成图片
    private void creatImage() {
        int fontWidth = width / codeCount;// 字体的宽度
        int fontHeight = height - 5;// 字体的高度
        int codeY = height - 8;

        // 图像buffer
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = buffImg.getGraphics();
        //Graphics2D g = buffImg.createGraphics();
        // 设置背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        
        
        
        // 设置字体
        //Font font1 = getFont(fontHeight);
//        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        Font font = new Font("Fixedsys", Font.ITALIC, fontHeight);  
        g.setFont(font);

        // 设置干扰线
        for (int i = 0; i < lineCount; i++) {
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width);
            int ye = ys + random.nextInt(height);
            g.setColor(getRandColor(1, 255));
            g.drawLine(xs, ys, xe, ye);
        }
        Color c = getRandColor(200, 250);  
        shear(g, width, height, c);// 使图片扭曲  
        
        // 添加噪点
        float yawpRate = 0.02f;// 噪声率
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);

            buffImg.setRGB(x, y, random.nextInt(255));
        }


        String str1 = randomStr(codeCount);// 得到随机字符
        //this.code = str1;
        for (int i = 0; i < codeCount; i++) {
            String strRand = str1.substring(i, i + 1);
            g.setColor(getRandColor(1, 255));
            // g.drawString(a,x,y);
            // a为要画出来的东西，x和y表示要画的东西最左侧字符的基线位于此图形上下文坐标系的 (x, y) 位置处
            
            g.drawString(strRand, i*fontWidth+3, codeY);
        }
        

    }

    // 得到随机字符
    private String randomStr(int n) {
    	int flag = 0;
		String str1 = "";
		String str3 = "";
    	double a = Math.random()*100;
//		System.out.println((int)a);
		if((int)a % 2 == 0){ //偶数
			flag = 1;
			str1 = "一二三四五六七八九十";
			str3 = "加乘";
		}else{ //基数
			flag = 0;
			str1 = "1234567890";
			str3 = "+*";
		}
        
//        String str3 = "+*";
        String str2 = "";
        int len = str1.length() - 1;
        int len3 = str3.length();
        double r,r3;
        
        String s1 = "";
        String s2 = "";
        String s3 = "";
        
        r = (Math.random()) * len;
        s1 = str1.charAt((int) r)+"";
        
        r3 = (Math.random()) * len3;
        s2 = str3.charAt((int) r3)+"";
        
        r = (Math.random()) * len;
        s3 = str1.charAt((int) r)+"";
        
        str2= s1 + s2+ s3 + "=?";
//        for (int i = 0; i < n; i++) {
//            r = (Math.random()) * len;
//            str2 = str2 + str1.charAt((int) r);
//        }
        if(flag == 1){
        	s1 = oneToOne(s1);
        	s3 = oneToOne(s3);
//        	System.out.println("s1="+s1);
//        	System.out.println("s3="+s3);
        }
        
        switch(str3.charAt((int) r3)){
        case '加': 
        	this.code = (Integer.parseInt(s1) + Integer.parseInt(s3))+"";
        	break;
        case '乘': 
        	this.code = (Integer.parseInt(s1) * Integer.parseInt(s3))+"";
        	break;
        case '+': 
        	this.code = (Integer.parseInt(s1) + Integer.parseInt(s3))+"";
        	break;
        case '-': 
        	this.code = (Integer.parseInt(s1) - Integer.parseInt(s3))+"";
        	break;
        case '*': 
        	this.code = (Integer.parseInt(s1) * Integer.parseInt(s3))+"";
        	break;
        case '/': 
        	this.code = (Integer.parseInt(s1) / Integer.parseInt(s3))+"";
        	break;
        }
        
        return str2;
    }

    public String oneToOne(String code){
		
		String[] str1 = {"一","二","三","四","五","六","七","八","九","十"};
		String[] str2 = {"1","2","3","4","5","6","7","8","9","10"};
		
		int posistion = 0;
		for(int i=0; i<str1.length;i++){
			if(code.equals(str1[i]) ){
				posistion = i;
				break;
			}
		}
//		System.out.println("posistion="+posistion);
		return str2[posistion];
	}

    // 得到随机颜色
    private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    
    /**
     * 产生随机字体
     */
    private Font getFont(int size) {
        Random random = new Random();
        Font font[] = new Font[5];
        font[0] = new Font("Ravie", Font.PLAIN, size);
        font[1] = new Font("Antique Olive Compact", Font.PLAIN, size);
        font[2] = new Font("Fixedsys", Font.PLAIN, size);
        font[3] = new Font("Wide Latin", Font.PLAIN, size);
        font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, size);
        return font[random.nextInt(5)];
    }
    
    // 扭曲方法
        private void shear(Graphics g, int w1, int h1, Color color) {
            shearX(g, w1, h1, color);
            shearY(g, w1, h1, color);
        }

        private void shearX(Graphics g, int w1, int h1, Color color) {

            int period = random.nextInt(2);

            boolean borderGap = true;
            int frames = 1;
            int phase = random.nextInt(2);

            for (int i = 0; i < h1; i++) {
                double d = (double) (period >> 1)
                        * Math.sin((double) i / (double) period
                                + (6.2831853071795862D * (double) phase)
                                / (double) frames);
                g.copyArea(0, i, w1, 1, (int) d, 0);
                if (borderGap) {
                    g.setColor(color);
                    g.drawLine((int) d, i, 0, i);
                    g.drawLine((int) d + w1, i, w1, i);
                }
            }

        }

        private void shearY(Graphics g, int w1, int h1, Color color) {

            int period = random.nextInt(40) + 10; // 50;

            boolean borderGap = true;
            int frames = 20;
            int phase = 7;
            for (int i = 0; i < w1; i++) {
                double d = (double) (period >> 1)
                        * Math.sin((double) i / (double) period
                                + (6.2831853071795862D * (double) phase)
                                / (double) frames);
                g.copyArea(i, 0, 1, h1, 0, (int) d);
                if (borderGap) {
                    g.setColor(color);
                    g.drawLine(i, (int) d, i, 0);
                    g.drawLine(i, (int) d + h1, i, h1);
                }

            }

        }


    
    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public String getCode() {
        return code.toLowerCase();
    }
    
    //使用方法
 /*public void getCode3(HttpServletRequest req, HttpServletResponse response,HttpSession session) throws IOException{
        // 设置响应的类型格式为图片格式
            response.setContentType("image/jpeg");
            //禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            
            
            CreateImageCode vCode = new CreateImageCode(100,30,5,10);
            session.setAttribute("code", vCode.getCode());
            vCode.write(response.getOutputStream());
     }*/

}