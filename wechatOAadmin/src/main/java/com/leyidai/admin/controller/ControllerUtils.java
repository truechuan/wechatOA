package com.leyidai.admin.controller;

import org.springframework.beans.factory.annotation.Value;

import com.leyidai.entity.BackstageAdminUser;

public class ControllerUtils {


	@Value("#{systemConfigProperties[superAdminLoginName]}")
	private static String superAdminLoginName;
	
	/**
	 * 从baseController中获得区县areaId
	 * @param sessionAdminUser
	 * @return
	 */
	public static String GetAdminAreaFromBaseController(BackstageAdminUser sessionAdminUser)
	{
		String area = "";
		//不为空，且不是超级管理员
		if (sessionAdminUser != null
				&& !sessionAdminUser.getLoginName().equals(superAdminLoginName)) {
			area = sessionAdminUser.getArea();
			return area;
		}
		else {
			return null;
		}
		
	}
}
