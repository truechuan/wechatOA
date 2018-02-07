package com.leyidai.web.schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

import com.leyidai.web.util.SiteUtil;

public class SendTimeOut implements Callable<String> {

	private String url;
	private String param;

	public SendTimeOut(String urlStr, String paramStr){  
        url=urlStr;
        param=paramStr;
    }  
	
	@Override
	public String call() throws Exception {
		String result = "";  
        try {  
            URL realUrl = new URL(url);  
            URLConnection conn = realUrl.openConnection();  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");  
            //post设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            PrintWriter out = new PrintWriter(conn.getOutputStream());  
            out.print(param);  
            out.flush();  
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));  
            String line;  
            while((line = in.readLine()) != null){  
                result +="\n" + line;  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return result;  
	}
}
