package com.leyidai.admin.util;

import java.io.IOException;
import java.util.Random;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 短信工具类
 * 所有短信间相关的工具集合
 * @author 
 *
 */
@Component
public class MessageUtil {
	private static final Logger log = LoggerFactory.getLogger(MessageUtil.class);
	
	@Value("#{systemConfigProperties[codeAffectTime]}")
	private String codeAffectTime;
	
	@Value("#{systemConfigProperties[sendMessageUrl]}")
	private String sendMessageUrl;
	
	@Value("#{systemConfigProperties[sendMessageUserName2]}")
	private String sendMessageUserName2;
	
	@Value("#{systemConfigProperties[sendMessageCode2]}")
	private String sendMessageCode2;
	
	/**
	 * 发送手机短信
	 * @param mobile
	 * @param contentText
	 */
	public void sendMessage(String mobile, String contentText){
		
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(""+sendMessageUrl+""); 
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		NameValuePair[] data ={
					new NameValuePair("userName", ""+sendMessageUserName2+""),
					new NameValuePair("code", ""+sendMessageCode2+""),
					new NameValuePair("phoneNum", mobile),
					new NameValuePair("text",contentText),
					new NameValuePair("sendDate","")
					};
		post.setRequestBody(data); 
		try {
			client.executeMethod(post);
			log.info(mobile+"="+new String(post.getResponseBodyAsString().getBytes("UTF-8")));
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 生成随机数
	 * @param length
	 * @return
	 */
	public static String randomCode(int length){
		
		Random random = new Random();
		String sRand = "";
		for (int i = 0; i < length; i++) {
			sRand += String.valueOf(random.nextInt(10));
		}
		return sRand;
	}
	
	/**
	 * 发送验证类型短息
	 * @param mobile
	 * @param contentText
	 * @return
	 */
	public String sendVerifyMessage(String mobile, String contentText){
		String code = randomCode(6);
		contentText = contentText + "，验证码:"+code+"("+codeAffectTime+"分钟内有效，请尽快完成验证)【天津市不动产】";
		sendMessage(mobile, contentText);
		return code;
	}
}
