package com.leyidai.web.weChat;


/**
 * 微信常量
 * @author mikan
 * @version 1.0
 */
public class ConstantWeChat {
	
	
	/**
	 * 与接口配置信息中的Token要一致
	 */
	public static String TOKEN = "ttcool";
	
	/**
	 * 每次请求需要的ACCEESS_TOKEN
	 */
	public static String ACCEESS_TOKEN = "";
	
	/**
	 * 第三方用户唯一凭证
	 */
	public static String APPID = "wx3d29e8f5769f2b5d";
//	public static String APPID="wx92ffbd45af84ed3b";
//	public static String APPID = "wxc744f029ee477edf"; //生产
	
	/**
	 * 第三方用户唯一凭证密钥
	 */
	public static String APPSECRET = "869abb6967d50f39b2ccfdfcff7fc2f3";
//	public static String APPSECRET="004750a59dfcd4b55d9efeca83eda671";
//	public static String APPSECRET = "6e1f1d9835e3f55b1de0065961cc7ac6";  //生产
	
	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：图片
	 */
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
	
	/**
	 * 返回消息类型：语音
	 */
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
	
	/**
	 * 返回消息类型：视频
	 */
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
	
	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";
	
	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：事件
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(关注)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消关注)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
	
	/**
	 * OAUTH scope
	 */
	public static final String SCOPE_SNSAPI_BASE = "snsapi_base";
	public static final String SCOPE_SNSAPI_USERINFO = "snsapi_userinfo";
}
