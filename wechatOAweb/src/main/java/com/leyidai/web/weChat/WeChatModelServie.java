package com.leyidai.web.weChat;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;


public class WeChatModelServie {
	
	private static String modelUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	/**
	 * 预约成功通知模板
	 * @param token 微信Access_token
	 * @param openId（template_id） 用户微信ID
	 * @param userName 用户名
	 * @param date 预约时间段 eg：yyyy年MM月dd日 HH:mm-HH:mm
	 * @param address 办理地点
	 * @param remark 其他声明
	 * @return 
	 */
	public static String pushMessage(String token, String openId, String userName, String date, String address, String remark){
		String url = modelUrl.replace("ACCESS_TOKEN", token);
		String touser = "oRg6ewdav8JNokjPfs23qomEtfoY";
		Map<String, Object> pushData = new HashMap<String, Object>();
		pushData.put("touser",  touser);
		pushData.put("template_id",  openId);
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, String> chirldMap = new HashMap<String, String>();
		chirldMap.put("value", userName);
		data.put("first", chirldMap);
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", "业务类型");
		data.put("businessType", chirldMap);
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", "不动产登记预约");
		data.put("business", chirldMap);
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", String.valueOf(System.currentTimeMillis()));
		data.put("order", chirldMap);
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", date);
		data.put("time", chirldMap);
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", address);
		data.put("address", chirldMap);
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", remark);
		data.put("remark", chirldMap);
		pushData.put("data",  data);
		JSONObject jsonObj = new JSONObject();
		jsonObj.putAll(pushData);
		System.out.println(jsonObj.toString());
		JSONObject rtnMsg = WeixinUtil.httpsRequest(url, "POST", jsonObj.toString());
		@SuppressWarnings("unchecked")
		Map<String, String> rtnMap = (HashMap<String, String>)JSONObject.toBean(rtnMsg, java.util.Map.class);
		return rtnMap.get("errCode");
	}
	
	public static void main(String[] args){
		String template_id = "j1AIKMkdEiimTfPMSaman5W14rNX-vlUmLehoz7Yyos";
		String token = "RgkQ2dmum_4tmtcqgKkL41A3Ae2bDqxI3tl1YWYS9BLz_5c1HXdwRkbOTFBp3OPUdF2jlfGguRTVKhFJkmet7hKYU9ppSp4681KS-0_VIVNnQibdT3mhSXGIAsS-Avo7AXKeAIAGDQ";
		WeChatModelServie.pushMessage(token, template_id, "张松涛", "2016-05-06 08:00-09:30", "北辰区不动产登记处", "请您持身份证及业务办理所需材料在预约办理时间段内完成取号，不能办理业务请及时取消。");
	}
}
