package com.leyidai.admin.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 网站工具
 * 
 * @author Administrator
 * 
 */
@Component
public class SiteUtil {
	private static final Logger log = LoggerFactory.getLogger(SiteUtil.class);
	
	private final String[] mobileAgents = { "iphone", "android", "phone", "mobile",
			"wap", "netfront", "java", "opera mobi", "opera mini", "ucweb",
			"windows ce", "symbian", "series", "webos", "sony",
			"blackberry", "dopod", "nokia", "samsung", "palmsource", "xda",
			"pieplus", "meizu", "midp", "cldc", "motorola", "foma",
			"docomo", "up.browser", "up.link", "blazer", "helio", "hosin",
			"huawei", "novarra", "coolpad", "webos", "techfaith",
			"palmsource", "alcatel", "amoi", "ktouch", "nexian",
			"ericsson", "philips", "sagem", "wellcom", "bunjalloo", "maui",
			"smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
			"pantech", "gionee", "portalmmm", "jig browser", "hiptop",
			"benq", "haier", "^lct", "320x320", "240x320", "176x220",
			"w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq",
			"bird", "blac", "blaz", "brew", "cell", "cldc", "cmd-", "dang",
			"doco", "eric", "hipt", "inno", "ipaq", "java", "jigs", "kddi",
			"keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo",
			"midp", "mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-",
			"newt", "noki", "oper", "palm", "pana", "pant", "phil", "play",
			"port", "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-",
			"send", "seri", "sgh-", "shar", "sie-", "siem", "smal", "smar",
			"sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-",
			"upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi", "wapp",
			"wapr", "webc", "winw", "winw", "xda", "xda-",
			"Googlebot-Mobile" };

	public enum SpecialString{
		PERCENT("%"), ASTERISK("*"), COMMA(",");
		
		private String value;
		SpecialString(String value){
			this.value = value;
		}
		
		public String getValue(){
			return this.value;
		}
	}
	
	public static String filterUrlNumber(String url){
		
		if(org.apache.commons.lang.StringUtils.isNotEmpty(url)){
			url = url.replaceAll("\\d+", SpecialString.ASTERISK.getValue());
		}
		
		return url;
	}
	
	/**
	 * 判断是否访问来自于手机
	 * @param request
	 * @return
	 */
	public boolean fromMoblie(HttpServletRequest request) {
		boolean isMoblie = false;
		
		log.info("user agent {}", request.getHeader("User-Agent"));
		if (request.getHeader("User-Agent") != null) {
			for (String mobileAgent : mobileAgents) {
				if (request.getHeader("User-Agent").toLowerCase()
						.indexOf(mobileAgent) >= 0) {
					isMoblie = true;
					break;
				}
			}
		}
		
		//判断是否来自域名m.huifub.com
		log.info("server name {}", request.getServerName());
		boolean isMobileServerName = request.getServerName().contains("m.huifub.com");
		
		return isMoblie || isMobileServerName;
	}

	/**
	 * 获取用户真实ip
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		
	       String ip = request.getHeader("x-forwarded-for"); 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("WL-Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getRemoteAddr(); 
	       } 
	       
	       return ip; 
	 }
}
