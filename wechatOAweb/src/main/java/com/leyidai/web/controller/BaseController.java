package com.leyidai.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.leyidai.entity.User;
import com.leyidai.web.service.UserService;

/**
 * controller基类
 * @author song
 *
 */
public class BaseController {
	@Autowired
	private UserService userService;
	/**
	 * 获取session里的user
	 * @param request
	 * @return
	 */
	public User getUser(HttpServletRequest request){
		
		User user = null;
		Object scObj = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if(scObj != null){
			
//			SecurityContextImpl securityContextImpl = (SecurityContextImpl) scObj;
//			UserDetails userDetails = (UserDetails)securityContextImpl.getAuthentication().getPrincipal();
//			user = userDetails.getUser();
		}
		
		return user;
	}
	
	protected void initUser(HttpServletRequest request, Model model){
	}
	

}
