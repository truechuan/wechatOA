package com.leyidai.admin.controller;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;

import com.leyidai.admin.service.AuthorizationService;
import com.leyidai.admin.util.SiteUtil.SpecialString;
import com.leyidai.entity.Group;

public class GroupEditor extends PropertyEditorSupport {
	private AuthorizationService authorizationService;

	public GroupEditor(AuthorizationService authorizationService){
		this.authorizationService = authorizationService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		if(StringUtils.isNotEmpty(text)){
			int id = Integer.valueOf(text.split(SpecialString.COMMA.getValue())[0]);
			Group group = authorizationService.getGroupById(id);
			setValue(group);
		} else{
			setValue(null);
		}
		
	}
}
