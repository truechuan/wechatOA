package com.leyidai.admin.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.leyidai.admin.util.SiteUtil;


public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	private static final Logger log = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);
	@Autowired
	private SiteUtil siteUtil;
	
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		LYDAdminUserDetails userDetails = (LYDAdminUserDetails) authentication.getPrincipal();
		
		String redirectUrl = "/";
		if(userDetails != null){
			
			redirectUrl = (String)request.getSession().getAttribute("url");
			
			if(StringUtils.isEmpty(redirectUrl)){
				
				redirectUrl = "user/welcome.htm";
			}
		}
		
		log.info("redirect user to {}", redirectUrl);
		response.sendRedirect(redirectUrl);
	}
}
