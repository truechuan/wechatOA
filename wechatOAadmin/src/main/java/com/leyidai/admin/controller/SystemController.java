package com.leyidai.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leyidai.admin.security.LYDSecurityMetadataSource;

@Controller
public class SystemController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(SystemController.class);
	@Autowired
	private LYDSecurityMetadataSource lydSecurityMetadataSource;
	
	@RequestMapping("/system/reloadResourceDefine")
	public String reloadResourceDefine(Model model){
		
		log.debug("reload resource define");
		lydSecurityMetadataSource.reloadResourceDefine();
		model.addAttribute("result", "success");
		return "system/reloadResourceDefine";
	}

}
