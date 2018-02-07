package com.leyidai.web.security;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leyidai.web.service.UserService;
import com.leyidai.web.util.SiteUtil;
import com.leyidai.web.weChat.ConstantWeChat;
import com.leyidai.web.weChat.WeixinUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	private static final Logger log = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);
	@Autowired
	private SiteUtil siteUtil;
	@Autowired
	private UserService userService;
	
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		log.debug("onAuthenticationSuccess start");
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		String redirectUrl = request.getRequestURL().toString();
		if(userDetails != null){
			String openid = (String)request.getSession().getAttribute("openid");
			String code = request.getParameter("code");
			if(StringUtils.isNotBlank(code)){
				String newOpenid = WeixinUtil.getOpenIdByHtmlToken(code);
				request.getSession().setAttribute("openid", newOpenid);
			}else{
				String domain = "1503452il4.51mypc.cn/" + redirectUrl;
				domain = URLEncoder.encode(domain, "UTF-8");
				String requestUrl = url.replace("APPID", ConstantWeChat.APPID).replace("REDIRECT_URI", domain);
				redirectUrl = requestUrl;
			}
		}
		
		log.info("redirect user to {}", redirectUrl);
		response.sendRedirect(redirectUrl);
	}
}
