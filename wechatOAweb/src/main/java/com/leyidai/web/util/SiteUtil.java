package com.leyidai.web.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.leyidai.entity.OrderRecords;

/**
 * 网站工具
 * 
 * @author Administrator
 * 
 */
@Component
public class SiteUtil {
	private static final Logger log = LoggerFactory.getLogger(SiteUtil.class);

	public static final String CAPATCH = "capatch";
	public static final String SUBSCRIBE_DURATION = "duration";
	public static final String SUBSCRIBE_DURATION_TYPE = "durationType";
	public static final String TRANSFER_CHKINGACCT_MONEY = "transferCheckingAcctMoney";
	public static final String ACTIVITY_MONEY = "activityMoney";
	public static final String CHECK_USERNAME_LENGTH = "^[\u4E00-\u9FA5A-Za-z0-9_]{5,16}$";

	public enum SpecialString {
		PERCENT("%"), ASTERISK("*"), COMMA(",");

		private String value;

		SpecialString(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}
	
	
	/**
	 * 执行发送微信提示消息 处理status=1或2的情况，处理是否为队列的情况
	 * 
	 * @param orderModel
	 * @param flagQueue
	 */
	public static void invokePushMessage(OrderRecords orderModel, boolean flagQueue) {
		String rtnUrl = "http://www.tjsbdcdjzx.com/static/"
				+ orderModel.getTransactTypeId() + "/noticeInfo.html";
		String date = orderModel.getTransactDate() + " "
				+ orderModel.getTransactTime();
		Map<String, String> errmsg = null;
		if (orderModel.getStatus() == 2) {
			String remark = "\n备注：请您持身份证及业务办理所需材料在"
					+ orderModel.getTransactTime() + "完成取号，办理详情请点击查看";
			errmsg = WeChatModelUtil.pushSuccessMessage(orderModel.getOpenid(),
					rtnUrl, orderModel.getName(),
					orderModel.getTransactTypeName(), date,
					orderModel.getTransactOrgName(), remark);
		} else if (flagQueue) {
			String remark = "\n备注：预约信息已提交，预约排队中.......";
			errmsg = WeChatModelUtil.pushStatusMessage(orderModel.getOpenid(),
					rtnUrl, orderModel.getName(),
					orderModel.getTransactTypeName(), date,
					orderModel.getTransactOrgName(), remark, "排队中");
		} else {
			String remark = "\n备注：预约信息已提交审核，请耐心等待！";
			errmsg = WeChatModelUtil.pushMessage(orderModel.getOpenid(),
					rtnUrl, orderModel.getName(),
					orderModel.getTransactTypeName(), date,
					orderModel.getTransactOrgName(), remark);
		}
		log.info(String.valueOf(errmsg.toString()));
	}
	

	@SuppressWarnings("unused")
	private final String[] mobileAgents = { "iphone", "android", "phone",
			"mobile", "wap", "netfront", "java", "opera mobi", "opera mini",
			"ucweb", "windows ce", "symbian", "series", "webos", "sony",
			"blackberry", "dopod", "nokia", "samsung", "palmsource", "xda",
			"pieplus", "meizu", "midp", "cldc", "motorola", "foma", "docomo",
			"up.browser", "up.link", "blazer", "helio", "hosin", "huawei",
			"novarra", "webos", "techfaith", "palmsource", "alcatel", "amoi",
			"ktouch", "nexian", "ericsson", "philips", "sagem", "wellcom",
			"bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird",
			"zte-", "longcos", "pantech", "gionee", "portalmmm", "jig browser",
			"hiptop", "benq", "haier", "^lct", "320x320", "240x320", "176x220",
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
			"wapr", "webc", "winw", "winw", "xda", "xda-", "Googlebot-Mobile" };

	/**
	 * 判断是否访问来自于手机
	 * 
	 * @param request
	 * @return
	 */
	public boolean fromMoblie(HttpServletRequest request) {

		String serverName = request.getServerName();
		// 判断是否来自域名m.huifub.com
		log.info("server name {}", serverName);
		return serverName.startsWith("m.") || serverName.startsWith("M.");
	}

	/**
	 * 获取用户真实ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}

		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	public String getBaseUrl(HttpServletRequest request) {

		return request.getScheme() + "://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort()// 端口号
				+ request.getContextPath();// 项目名称

	}

	public static String filterUrlNumber(String url) {

		if (org.apache.commons.lang.StringUtils.isNotEmpty(url)) {
			url = url.replaceAll("\\d+", SpecialString.ASTERISK.getValue());
		}

		return url;
	}

	/**
	 * 向指定URL发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是name1=value1&name2=value2的形式
	 * @return URL所代表远程资源的响应
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");

			conn.setDoOutput(true);// 发送POST请求必须设置如下两行
			conn.setDoInput(true);

			out = new PrintWriter(conn.getOutputStream());// 获取URLConnection对象对应的输出流s
			out.print(param);// 发送请求参数
			out.flush();// flush输出流的缓冲
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));// 定义BufferedReader输入流来读取URL的响应
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	
	
    /** 
     *  
     * @param uri 
     * @param param 
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。 
     * @param charset 
     * @return 
     */  
    public static String sendPost(String uri, String param, String charset) {  
        String result = null;  
        PrintWriter out = null;  
        InputStream in = null;  
        try {  
            URL url = new URL(uri);  
            HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();  
            urlcon.setDoInput(true);  
            urlcon.setDoOutput(true);  
            urlcon.setUseCaches(false);  
            urlcon.setRequestMethod("POST");  
            urlcon.connect();// 获取连接  
            out = new PrintWriter(urlcon.getOutputStream());  
            out.print(param);  
            out.flush();  
            in = urlcon.getInputStream();  
            BufferedReader buffer = new BufferedReader(new InputStreamReader(  
                    in, charset));  
            StringBuffer bs = new StringBuffer();  
            String line = null;  
            while ((line = buffer.readLine()) != null) {  
                bs.append(line);  
            }  
            result = bs.toString();  
        } catch (Exception e) {  
            System.out.println("[请求异常][地址：" + uri + "][参数：" + param + "][错误信息："  
                    + e.getMessage() + "]");  
        } finally {  
            try {  
                if (null != in)  
                    in.close();  
                if (null != out)  
                    out.close();  
            } catch (Exception e2) {  
                System.out.println("[关闭流异常][错误信息：" + e2.getMessage() + "]");  
            }  
        }  
        return result;  
    }  
	
    
    public static String sendPost2(String url,String param){  
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
            //e.printStackTrace();  
        	log.info("请求出错"+e.getMessage());
            return "false";
        }  
        return result;  
    }  
    
    
    /**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求Map参数，请求参数应该是 {"name1":"value1","name2":"value2"}的形式。
	 * @param charset
	 *            发送和接收的格式
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, Map<String, Object> map,
			String charset) {
		StringBuffer sb = new StringBuffer();
		// 构建请求参数
		if (map != null && map.size() > 0) {
			Iterator it = map.entrySet().iterator(); // 定义迭代器
			while (it.hasNext()) {
				Map.Entry er = (Entry) it.next();
				sb.append(er.getKey());
				sb.append("=");
				sb.append(er.getValue());
				sb.append("&");
			}
		}
		return sendGet(url, sb.toString());
	}
    

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "application/json");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			log.info("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title : filterNumber
	 * @Type : FilterStr
	 * @date : 2014年3月12日 下午7:23:03
	 * @Description : 过滤出数字
	 * @param str
	 * @return
	 */
	public static String filterNumber(String number) {
		number = number.replaceAll("[^(0-9)]", "");
		return number;
	}

	/**
	 * 
	 * @Title : filterNumber
	 * @Type : FilterStr
	 * @date : 2014年3月12日 下午7:23:03
	 * @Description : 过滤出数字和短横线
	 * @param str
	 * @return
	 */
	public static String filterNumberM(String number) {
		number = number.replaceAll("[^\\d-\\d]", "");
		log.info(number);
		return number;
	}

	//
	// public static doGet()
	// {}

	/**
	 * 随机生成字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

}
