package com.leyidai.web.filter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.IpAddressMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.leyidai.web.service.IpForbiddenService;
import com.leyidai.web.util.SiteUtil;

public class SystemMaintenanceInterceptor implements HandlerInterceptor {

	@Autowired
	IpForbiddenService ipService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Date today = new Date();
		String servletPath = request.getServletPath();//提取网址路径，防止进入死循环
		
		String ip=SiteUtil.getIpAddr(request);
		
		
		
		if (today.getHours() >= 22 || today.getHours() < 6) {
			if (servletPath.equals("/systemMaintenance")) {
				return true;
			}
			request.getRequestDispatcher("/systemMaintenance").forward(request,
					response);

			return false;
		}
		else if(ipService.IsIpForbidden(ip)){
			//如果ip是被封禁的
			if (servletPath.equals("/ipForbidden")) {
				return true;
			}
			request.getRequestDispatcher("/ipForbidden").forward(request,
					response);
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
