package com.leyidai.web.filter;

import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leyidai.web.util.CategoryConstants;
import com.leyidai.web.weChat.ConstantWeChat;
import com.leyidai.web.weChat.WeixinUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class WeChatInterceptor implements HandlerInterceptor {
	
	private static Logger log = LoggerFactory.getLogger(WeChatInterceptor.class);

	@Value("#{systemConfigProperties[debug]}")
	private String debug;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		log.debug("get openid start" + debug);
		if(StringUtils.isNotBlank(debug) || StringUtils.isNotBlank(CategoryConstants.RUNTYPE)){
			String myOpenid = WeixinUtil.getOpenIdByHtmlToken(null);
			request.getSession().setAttribute("openid", myOpenid);
			return true;
		}
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
		String redirectUrl = request.getRequestURL().toString();
		String openid = (String) request.getSession().getAttribute("openid");
		String code = request.getParameter("code");

		log.debug("get openid start" + code);
		if (StringUtils.isNotBlank(code)) {
			String newOpenid = WeixinUtil.getOpenIdByHtmlToken(code);
			request.getSession().setAttribute("openid", newOpenid);
		} else if(StringUtils.isBlank(openid)){
			String domain = redirectUrl;
			domain = URLEncoder.encode(domain, "UTF-8");
			String requestUrl = url.replace("APPID", ConstantWeChat.APPID)
					.replace("REDIRECT_URI", domain);
			redirectUrl = requestUrl;
			log.debug("redirectUrl[{}]", redirectUrl);
			response.sendRedirect(redirectUrl);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
