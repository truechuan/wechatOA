package com.leyidai.admin.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.leyidai.admin.security.LYDAdminUserDetails;
import com.leyidai.admin.service.AreaService;
import com.leyidai.entity.Area;
import com.leyidai.entity.BackstageAdminUser;
import com.leyidai.entity.Resource;
import com.leyidai.entity.ResourceCategory;
@Controller
public class BaseController {
	private static final Logger log = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	private AreaService areaService;
	
	public BackstageAdminUser getSessionAdminUser(){
		BackstageAdminUser sessionAdminUser = null;
		
		try{
			
			if(SecurityContextHolder.getContext().getAuthentication() != null){
				
				LYDAdminUserDetails userDetails = (LYDAdminUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if(userDetails != null){
					
					sessionAdminUser = userDetails.getAdminUser();
				}
			}
		} catch(Exception e){
			System.out.println(e.fillInStackTrace());
			log.error(e.getMessage());
			log.debug("user details had invalid.");
		}
		
		
		return sessionAdminUser;
	}
	
	@ModelAttribute("menues")
	public Map<ResourceCategory, Collection<Resource>> loadMenus(){
		
		Map<ResourceCategory, Collection<Resource>> menues = null;
		BackstageAdminUser adminUser = this.getSessionAdminUser();
		
		if(adminUser != null){
			
			menues = adminUser.getMenues();
		}
		
		return menues;
	}

	/**
	 * 获取区县
	 * @return
	 */
	public List<Area> getAreas(){
		return areaService.getAreas();
	}
	
	/**
	 * 是否有更多区县控制权
	 * @return
	 */
	public boolean isMoreManger(){
		
		boolean returnValue = false;
		BackstageAdminUser sessionAdminUser = this.getSessionAdminUser();
		if(sessionAdminUser != null){
			String area = sessionAdminUser.getArea();
			if(area.length() > 2)
			returnValue = true;
		}
		
		return returnValue;
	}
	
	
}
