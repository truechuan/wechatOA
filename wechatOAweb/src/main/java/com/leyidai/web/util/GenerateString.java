package com.leyidai.web.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

public class GenerateString {
	/**
     * 获取指定长度随机简体中文
     * @param len int
     * @return String
     */
    public static String getRandomJianHan(int len)
    {
        String ret="";
          for(int i=0;i<len;i++){
              String str = null;
              int hightPos, lowPos; // 定义高低位
              Random random = new Random();
              hightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
              lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
              byte[] b = new byte[2];
              b[0] = (new Integer(hightPos).byteValue());
              b[1] = (new Integer(lowPos).byteValue());
              try
              {
                  str = new String(b, "GBk"); //转成中文
              }
              catch (UnsupportedEncodingException ex)
              {
                  ex.printStackTrace();
              }
               ret+=str;
          }
      return ret;
    }

//	public static String get300Chinese() throws Exception {
//		GenerateString ch = new GenerateString();
//		String str = "";
//		for (int i = 1; i >= 0; i--) {
//			str = str + ch.getChinese(i);
//
//		}
//		System.out.println(str);
//		return str;
//	}
}