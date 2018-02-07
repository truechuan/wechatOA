package com.leyidai.admin.controller;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leyidai.admin.service.AuthorizationService;
import com.leyidai.admin.util.SiteUtil.SpecialString;
import com.leyidai.entity.Resource;

public class ResourceEditor extends PropertyEditorSupport {
	private static Logger log = LoggerFactory.getLogger(ResourceEditor.class);
	private AuthorizationService authorizationService;
	
	public ResourceEditor(AuthorizationService authorizationService){
		this.authorizationService = authorizationService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		log.debug("resource editor for resource {}", text);
		
		if(StringUtils.isNotEmpty(text)){
			int id = Integer.valueOf(text.split(SpecialString.COMMA.getValue())[0]);
			Resource resource = authorizationService.getResourceById(id);
			setValue(resource);
		} else{
			setValue(null);
		}
	}

}
