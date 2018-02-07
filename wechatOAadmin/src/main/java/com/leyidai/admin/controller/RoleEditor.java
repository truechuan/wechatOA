package com.leyidai.admin.controller;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;

import com.leyidai.admin.service.AuthorizationService;
import com.leyidai.admin.util.SiteUtil.SpecialString;
import com.leyidai.entity.Role;

public class RoleEditor extends PropertyEditorSupport {
	private AuthorizationService authorizationService;

	public RoleEditor(AuthorizationService authorizationService){
		this.authorizationService = authorizationService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		if(StringUtils.isNotEmpty(text)){
			int id = Integer.valueOf(text.split(SpecialString.COMMA.getValue())[0]);
			Role role = authorizationService.getRoleById(id);
			setValue(role);
		} else{
			setValue(null);
		}
		
	}

}
