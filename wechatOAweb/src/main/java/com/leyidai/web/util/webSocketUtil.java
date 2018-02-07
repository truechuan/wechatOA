package com.leyidai.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;

import com.leyidai.entity.SendDataTemp;
import com.leyidai.web.schedule.SendTimeOut;
import com.leyidai.web.schedule.WorkerScheduler;  

public class webSocketUtil {

	private static Logger log = org.slf4j.LoggerFactory
			.getLogger(WorkerScheduler.class);

	
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	
	
	public static List<String> getContext(String html) {  
        List<String> resultList=new ArrayList<String>();
        Pattern p = Pattern.compile(">([^</]+)</");//正则表达式 commend by danielinbiti  
        Matcher m = p.matcher(html );//  
        while (m.find()) {  
            resultList.add(m.group(1));//  
        }  
        return resultList;  
    }  
	
	//
	// 接口地址http://162.16.160.50/webservice/Real/AppointmentSvc.asmx/GetAppointmentResult?strJson=string
	/**
	 * 将拼装好的json格式数据通过get方式提交给webservice接口以便调用，获取其返回值
	 * 
	 * @param orderModel
	 * @return
	 */
	public static String webSockte(SendDataTemp orderModel) {

		String defaultReturn = "{\"id\":0,\"returnMsg\":3}";
		String jsonString = assemblyJsonString(orderModel);

		String param = "strJson=[" + jsonString + "]";
		log.info(param);

		// 将字符串转为XML
		try {
			log.info("我进来了");
			// log.info(SiteUtil.sendPost2(ConstantVariable.webSocket, param));
			String rtnString=sendPostTimeOutFuture(ConstantVariable.webSocket, param);
			log.info(replaceBlank(rtnString));
			Document doc = DocumentHelper.parseText(replaceBlank(rtnString));
//			 Document doc = DocumentHelper
//			 .parseText("<?xml version=\"1.0\" encoding=\"utf-8\"?><string>{\"id\":9,\"returnMsg\":1}</string>");
			 log.info(doc.selectSingleNode("//string").getText());
			if (doc.selectNodes("//string").size() > 0) {
				// 如果能找到returnMsg，说明没有报错
				if (doc.selectSingleNode("//string").getText()
						.indexOf("returnMsg") >= 0) {
					log.info(doc.selectSingleNode("//string").getText()
							+ "找到string");
					return doc.selectSingleNode("//string").getText();
				} else {
					log.info("找不到returnMsg");
					return defaultReturn;
				}
			} else {
				return defaultReturn;
			}
		} catch (DocumentException e) {
			return defaultReturn;
			// TODO Auto-generated catch block
//			throw e.toString();
			//e.printStackTrace();
		}
		//return defaultReturn;
	}

	
	public static String sendPostTimeOutFuture(String url,String param)
	{
		String result="<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        ExecutorService executor = Executors.newSingleThreadExecutor();  
        FutureTask<String> future = new FutureTask<String>(new SendTimeOut(url, param));  
        executor.execute(future);  
        try {  
            result = future.get(5000, TimeUnit.MILLISECONDS);  
            return result;
        } catch (InterruptedException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (ExecutionException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (TimeoutException e) {  
            // TODO Auto-generated catch block  
            log.info("超时");
        }finally{  
            future.cancel(true);  
            executor.shutdown();     
        }  
        return result;
	}
	
	
	/**
	 * 拼装json格式数据，用于调用远程webservice接口时使用
	 * 
	 * @param orderModel
	 * @return
	 */
	public static String assemblyJsonString(SendDataTemp orderModel) {

		JSONObject jsonObject = new JSONObject();
		int type = orderModel.getTransactType();

		jsonObject.put("id", orderModel.getId());
		jsonObject.put("name", orderModel.getName());
		jsonObject.put("idcard", orderModel.getIdcard());
		// type为1代表是新房业务，需要包含短横线
		if (type == 1) {
			jsonObject.put("hourseNumber",
					SiteUtil.filterNumberM(orderModel.getHourseNumber()));
		} else {
			jsonObject.put("hourseNumber",
					SiteUtil.filterNumber(orderModel.getHourseNumber()));
		}
		jsonObject.put("area", orderModel.getArea());

		jsonObject.put("transactType", type);
		return jsonObject.toString();
	}

	/**
	 * 选择结构，用于判定业务类型请求接口时的类型id 根据名称中文内的字符判断
	 * 
	 * @param typeName
	 *            业务类型名称
	 * @return
	 */
	public static int switchType(String typeName) {
		int transactType = 0;
		// 如果包含新字，认为是新房业务，业务类型为1
		if (StringUtils.indexOfIgnoreCase(typeName, "新") != -1|| StringUtils.indexOfIgnoreCase(typeName, "商品房办证") != -1) {
			return transactType = 1;
		}
		// 如果包含过户或核税，业务类型为2
		else if (StringUtils.indexOfIgnoreCase(typeName, "过户") != -1
				|| StringUtils.indexOfIgnoreCase(typeName, "税") != -1) {
			return transactType = 2;
		}
		// 如果包含抵押同时包含注销且不包含预告，业务类型为3
		else if (StringUtils.indexOfIgnoreCase(typeName, "抵押") != -1
				&& StringUtils.indexOfIgnoreCase(typeName, "注销") != -1
				&& StringUtils.indexOfIgnoreCase(typeName, "预告") == -1) {
			return transactType = 3;
		}
		// 剩下的业务类型为4
		else {
			return transactType = 4;
		}
	}
}
