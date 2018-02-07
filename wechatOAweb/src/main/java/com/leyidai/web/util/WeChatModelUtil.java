package com.leyidai.web.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.leyidai.web.weChat.ConstantWeChat;
import com.leyidai.web.weChat.WeixinUtil;

@Component
public class WeChatModelUtil {

	private static Logger log = LoggerFactory.getLogger(WeChatModelUtil.class);
	// private static String modelUrl =
	// "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	private static String modelUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	/**
	 * 业务办理通知模板
	 * 
	 * @param openId
	 *            用户微信ID
	 * @param rtnUrl
	 *            点击返回的地址
	 * @param userName
	 *            用户名
	 * @param date
	 *            预约时间段 eg：yyyy年MM月dd日 HH:mm-HH:mm
	 * @param address
	 *            办理地点
	 * @param remark
	 *            其他声明
	 * @return
	 */
	public static Map<String, String> pushMessage(String openId, String rtnUrl,
			String userName, String transactType, String date, String address,
			String remark) {
		log.debug("send wechat model message begin");
		String url = modelUrl.replace("ACCESS_TOKEN",
				ConstantWeChat.ACCEESS_TOKEN);
		// String templateId = "j1AIKMkdEiimTfPMSaman5W14rNX-vlUmLehoz7Yyos"; //
		// 测试
		// String templateId = "21fplndusSMEoZ8SXQKKohJvR0j3JhlCRup60_Gy3KI"; //
		// 正式
		String templateId = "Q2tbxVCNq7kCRx17-Vsk2ed7LhAmQgzooFCu73i8X6w";
		Map<String, Object> pushData = new HashMap<String, Object>();
		pushData.put("touser",  openId);
		pushData.put("template_id",  templateId);
		pushData.put("url", rtnUrl);

		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, String> chirldMap = new HashMap<String, String>();
		chirldMap.put("value", "您好，"+userName);
		data.put("first", chirldMap);

		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", date);
		data.put("keyword1", chirldMap);

		// 申请业务
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", transactType + "\n");
		data.put("keyword2", chirldMap);

		// 当前状态
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", "待审核");
		data.put("keyword3", chirldMap);

		// 备注
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", remark);
		data.put("remark", chirldMap);
		pushData.put("data", data);
		JSONObject jsonObj = new JSONObject();
		jsonObj.putAll(pushData);
		JSONObject rtnMsg = WeixinUtil.httpsRequest(url, "POST",
				jsonObj.toString());
		log.info("wechat return message [{}]", rtnMsg);
		@SuppressWarnings("unchecked")
		Map<String, String> rtnMap = (HashMap<String, String>) JSONObject
				.toBean(rtnMsg, java.util.Map.class);
		log.info("send wechat model message end");
		return rtnMap;
	}
	
	

	/**
	 * 预约失败通知模板
	 * 
	 * @param openId
	 *            用户微信ID
	 * @param url
	 *            点击返回的地址
	 * @param userName
	 *            用户名
	 * @param date
	 *            预约时间段 eg：yyyy年MM月dd日 HH:mm-HH:mm
	 * @param address
	 *            办理地点
	 * @param remark
	 *            其他声明
	 * @return
	 */
	public static Map<String, String> pushFailedMessage(String openId, String rtnUrl,
			String userName, String transactType, String date, String address,
			String remark) {
		log.debug("send wechat failed model message begin");
		String url = modelUrl.replace("ACCESS_TOKEN",
				ConstantWeChat.ACCEESS_TOKEN);
		// String templateId = "z0r1LY9OuMLh95NteDt86RdYvqX0uhkUMKwv_PBaI1g"; //
		// 测试
		String templateId = "UOHpvYTYFu82M6cJz48nKJg_UVCxGklkRzF6MfsvwHc"; // 正式
		Map<String, Object> pushData = new HashMap<String, Object>();
		pushData.put("touser",  openId);
		pushData.put("template_id",  templateId);
		pushData.put("url", rtnUrl);

		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, String> chirldMap = new HashMap<String, String>();
		chirldMap.put("value", "您好，"+userName);
		data.put("first", chirldMap);

		// 预约事项
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", transactType + "\n");
		data.put("keyword1", chirldMap);

		// 预约日期
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", date);
		data.put("keyword2", chirldMap);

		// 地点
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", address);
		data.put("keyword3", chirldMap);

		// 备注
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", remark);
		data.put("remark", chirldMap);
		pushData.put("data", data);
		JSONObject jsonObj = new JSONObject();
		jsonObj.putAll(pushData);
		JSONObject rtnMsg = WeixinUtil.httpsRequest(url, "POST",
				jsonObj.toString());
		log.debug("wechat return message [{}]", rtnMsg);
		@SuppressWarnings("unchecked")
		Map<String, String> rtnMap = (HashMap<String, String>) JSONObject
				.toBean(rtnMsg, java.util.Map.class);
		log.debug("send wechat failed model message end");
		return rtnMap;
	}

	/**
	 * 预约完结通知模板
	 * 
	 * @param openId
	 *            用户微信ID
	 * @param url
	 *            点击返回的地址
	 * @param userName
	 *            用户名
	 * @param date
	 *            预约时间段 eg：yyyy年MM月dd日 HH:mm-HH:mm
	 * @param address
	 *            办理地点
	 * @param remark
	 *            其他声明
	 * @return
	 */
	public static String pushFinishMessage(String openId, String rtnUrl,
			String userName, String transactType, String date, String address,
			String remark) {
		log.debug("send wechat finish model message begin");
		date = date.split(" ")[0];
		String url = modelUrl.replace("ACCESS_TOKEN",
				ConstantWeChat.ACCEESS_TOKEN);
		// String templateId = "z0r1LY9OuMLh95NteDt86RdYvqX0uhkUMKwv_PBaI1g"; //
		// 测试
		// String templateId = "Yp9W4AP5aYVdDxSpC80EZwcmrZ0BSdxlfRo98MMGJ8w"; //
		// 正式 办结
		String templateId = "Yp9W4AP5aYVdDxSpC80EZwcmrZ0BSdxlfRo98MMGJ8w"; // 正式
		Map<String, Object> pushData = new HashMap<String, Object>();
		pushData.put("touser",  openId);
		pushData.put("template_id",  templateId);
		pushData.put("url", rtnUrl);

		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, String> chirldMap = new HashMap<String, String>();
		chirldMap.put("value", "您好，"+userName);
		data.put("first", chirldMap);

		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", date);
		data.put("keyword1", chirldMap);

		// 申请业务
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", transactType + "\n");
		data.put("keyword2", chirldMap);

		// 当前状态
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", "办结");
		data.put("keyword3", chirldMap);

		// 备注
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", remark);
		data.put("remark", chirldMap);
		pushData.put("data", data);
		JSONObject jsonObj = new JSONObject();
		jsonObj.putAll(pushData);
		JSONObject rtnMsg = WeixinUtil.httpsRequest(url, "POST",
				jsonObj.toString());
		log.debug("wechat return message [{}]", rtnMsg);
		@SuppressWarnings("unchecked")
		Map<String, String> rtnMap = (HashMap<String, String>) JSONObject
				.toBean(rtnMsg, java.util.Map.class);
		log.debug("send wechat finish model message end");
		return rtnMap.get("errCode");
	}

	public static void main(String[] args) {
		String template_id = "j1AIKMkdEiimTfPMSaman5W14rNX-vlUmLehoz7Yyos";
		// String token =
		// "RgkQ2dmum_4tmtcqgKkL41A3Ae2bDqxI3tl1YWYS9BLz_5c1HXdwRkbOTFBp3OPUdF2jlfGguRTVKhFJkmet7hKYU9ppSp4681KS-0_VIVNnQibdT3mhSXGIAsS-Avo7AXKeAIAGDQ";
		WeChatModelUtil.pushMessage(template_id, "", "张松涛", "",
				"2016-05-06 08:00-09:30", "北辰区不动产登记处",
				"请您持身份证及业务办理所需材料在预约办理时间段内完成取号，不能办理业务请及时取消。");
	}
	
	/**
	 * 使用业务办理模版推送消息  可以发送业务办理状态
	 * @param openId
	 * @param rtnUrl   点击模版进入的页面
	 * @param userName
	 * @param transactType
	 * @param date
	 * @param address
	 * @param remark
	 * @param status  业务办理状态
	 * @return 
	 */
	public static Map<String, String> pushStatusMessage(String openId, String rtnUrl,
			String userName, String transactType, String date, String address,
			String remark,String status) {
		log.debug("send wechat model message begin");
		String url = modelUrl.replace("ACCESS_TOKEN",
				ConstantWeChat.ACCEESS_TOKEN);
		// String templateId = "j1AIKMkdEiimTfPMSaman5W14rNX-vlUmLehoz7Yyos"; //
		// 测试
		// String templateId = "21fplndusSMEoZ8SXQKKohJvR0j3JhlCRup60_Gy3KI"; //
		// 正式
		String templateId = "Q2tbxVCNq7kCRx17-Vsk2ed7LhAmQgzooFCu73i8X6w";
		Map<String, Object> pushData = new HashMap<String, Object>();
		pushData.put("touser",  openId);
		pushData.put("template_id",  templateId);
		pushData.put("url", rtnUrl);

		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, String> chirldMap = new HashMap<String, String>();
		chirldMap.put("value", "您好，"+userName);
		data.put("first", chirldMap);

		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", date);
		data.put("keyword1", chirldMap);

		// 申请业务
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", transactType + "\n");
		data.put("keyword2", chirldMap);

		// 当前状态
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", status);
		data.put("keyword3", chirldMap);

		// 备注
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", remark);
		data.put("remark", chirldMap);
		pushData.put("data", data);
		JSONObject jsonObj = new JSONObject();
		jsonObj.putAll(pushData);
		JSONObject rtnMsg = WeixinUtil.httpsRequest(url, "POST",
				jsonObj.toString());
		log.info("wechat return message [{}]", rtnMsg);
		@SuppressWarnings("unchecked")
		Map<String, String> rtnMap = (HashMap<String, String>) JSONObject
				.toBean(rtnMsg, java.util.Map.class);
		log.info("send wechat model message end");
		return rtnMap;
	}
	
	/**
	 * 预约成功通知模板
	 * 
	 * @param openId
	 *            用户微信ID
	 * @param rtnUrl
	 *            点击返回的地址
	 * @param userName
	 *            用户名
	 * @param date
	 *            预约时间段 eg：yyyy年MM月dd日 HH:mm-HH:mm
	 * @param address
	 *            办理地点
	 * @param remark
	 *            其他声明
	 * @return
	 */
	public static Map<String, String> pushSuccessMessage(String openId, String rtnUrl,
			String userName, String transactType, String date, String address,
			String remark) {
		log.debug("send wechat model message begin");
		String url = modelUrl.replace("ACCESS_TOKEN",
				ConstantWeChat.ACCEESS_TOKEN);
		// String templateId = "j1AIKMkdEiimTfPMSaman5W14rNX-vlUmLehoz7Yyos"; //
		// 测试
		// String templateId = "21fplndusSMEoZ8SXQKKohJvR0j3JhlCRup60_Gy3KI"; //
		// 正式
		String templateId = "21fplndusSMEoZ8SXQKKohJvR0j3JhlCRup60_Gy3KI";
		Map<String, Object> pushData = new HashMap<String, Object>();
		pushData.put("touser",  openId);
		pushData.put("template_id",  templateId);
		pushData.put("url", rtnUrl);

		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, String> chirldMap = new HashMap<String, String>();
		chirldMap.put("value", userName);
		data.put("first", chirldMap);
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", "业务类型");
		data.put("businessType", chirldMap);
		chirldMap = new HashMap<String, String>();
		chirldMap.put("value", transactType+"\n");
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
		pushData.put("data", data);
		JSONObject jsonObj = new JSONObject();
		jsonObj.putAll(pushData);
		JSONObject rtnMsg = WeixinUtil.httpsRequest(url, "POST",
				jsonObj.toString());
		log.info("wechat return message [{}]", rtnMsg);
		@SuppressWarnings("unchecked")
		Map<String, String> rtnMap = (HashMap<String, String>) JSONObject
				.toBean(rtnMsg, java.util.Map.class);
		log.info("send wechat model message end");
		return rtnMap;
	}
	
}
