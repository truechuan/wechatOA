package com.leyidai.web.outh;

public class ConstantOuth {

	// 授权获取code地址
	public static String sso_url = "http://zwfw.tj.gov.cn/oauth/authorize";
	// 获取token地址
	public static String token_url = "http://zwfw.tj.gov.cn/oauth/token";
	// 获取用户信息地址
	public static String userinfo_url = "http://zwfw.tj.gov.cn/oauth/userinfo";
	// 回调地址，接入方提供(调试时可以用本地项目的本地地址，例如：http://192.168.1.100:8080/demo/redirect)
//	public static String redirect_uri = "http://www.tjsbdcdjzx.com/outh/autoLogin";//正式
	public static String redirect_uri = "http://127.0.0.1:8080/projectmasterweb/outh/autoLogin";//测试
	// 天津政务服务平台提供(请联系管理员获取)
//	public static String client_id = "60AE18CDA57A5B81E05044DA78FA8D57";//正式
	public static String client_id = "5F3F2BAA14DC76BFE05044DA78FA82B4";//测试
	// 天津政务服务平台提供(请联系管理员获取)
//	public static String client_secret = "60AE2399381A1ADFE05044DA78FA8E74";//正式
	public static String client_secret = "5F3F2BAA14DD76BFE05044DA78FA82B4";//测试

	// 登出地址
	public static String logout_url = "http://zwfw.tj.gov.cn/oauth/logout";
	//天津政务服务平台首页
	public static String authIndex="http://zwfw.tj.gov.cn";
	
}
