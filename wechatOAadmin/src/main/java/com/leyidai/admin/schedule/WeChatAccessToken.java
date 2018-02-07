package com.leyidai.admin.schedule;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.leyidai.web.weChat.ConstantWeChat;
import com.leyidai.web.weChat.WeixinUtil;

@Component
public class WeChatAccessToken {
	private static Logger log = org.slf4j.LoggerFactory
			.getLogger(WeChatAccessToken.class);

	public WeChatAccessToken() {
		log.debug("WeChatAccessToken start");
		ConstantWeChat.ACCEESS_TOKEN = WeixinUtil.getAccessToken(
				ConstantWeChat.APPID, ConstantWeChat.APPSECRET).getToken();
		log.debug("WeChatAccessToken ACCEESS_TOKEN[{}]",
				ConstantWeChat.ACCEESS_TOKEN);
		log.debug("WeChatAccessToken end");
	}

//	@Scheduled(cron = "0 0 0/1 ? * *")
//	public void freshToken() {
//		log.debug("WeChatAccessToken start");
//		ConstantWeChat.ACCEESS_TOKEN = WeixinUtil.getAccessToken(
//				ConstantWeChat.APPID, ConstantWeChat.APPSECRET).getToken();
//		
//		//================================写入txt文件供前台调用===================================================
//		FileOutputStream fop = null;
//		File file;
//		String content = ConstantWeChat.ACCEESS_TOKEN;
//
//		try {
//
//			file = new File("C:/ACCEESS_TOKEN.txt");
//			fop = new FileOutputStream(file);
//
//			// if file doesnt exists, then create it
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//
//			// get the content in bytes
//			byte[] contentInBytes = content.getBytes();
//
//			fop.write(contentInBytes);
//			fop.flush();
//			fop.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (fop != null) {
//					fop.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		log.debug("WeChatAccessToken ACCEESS_TOKEN[{}]",
//				ConstantWeChat.ACCEESS_TOKEN);
//		log.debug("WeChatAccessToken end");
//	}

}
