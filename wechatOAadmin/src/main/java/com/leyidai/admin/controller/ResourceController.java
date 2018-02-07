package com.leyidai.admin.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leyidai.admin.service.AuthorizationService;
import com.leyidai.admin.util.PageUtils;
import com.leyidai.entity.Group;
import com.leyidai.entity.Resource;

@Controller
public class ResourceController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(ResourceController.class);
	private final AuthorizationService authorizationService;
	
	@Autowired
	private PageUtils pageUtils;
	
	@Autowired
	public ResourceController(AuthorizationService authorizationService){
		this.authorizationService = authorizationService;
	}

	/**
	 * 初始化资源表单 - 新增资源
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/url/new", method=RequestMethod.GET)
	public String initCreationResourceForm(Model model){
		
		Resource resource = new Resource();
		model.addAttribute(resource);
		
		List<Group> groups = authorizationService.getGrups();
		model.addAttribute(groups);
		
		return "authority/resourceForm";
	}
	
	/**
	 * 新增资源
	 * @param resource
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/url/new", method=RequestMethod.POST)
	public String createResource(@ModelAttribute Resource resource, Model model){
		
		boolean isResourceExist = authorizationService.isResourceExist(resource.getUrl());
		if(isResourceExist){
			log.debug("resource {} exists", resource.getUrl());
			model.addAttribute("result", "error");
			model.addAttribute("message", "资源已存在！");
		} else{
			
			authorizationService.addResource(resource);
			model.addAttribute("result", "success");
			log.debug("create resource {}-{} success", resource.getResourceId(), resource.getUrl());
		}
		
		return "authority/resourceForm";
	}
	
	/**
	 * 初始化资源表单 - 更新资源
	 * @param resourceId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/url/{resourceId}/edit/{page}", method=RequestMethod.GET)
	public String initUpdateResourceForm(@PathVariable("resourceId")int resourceId, @PathVariable("page")int page, Model model){
		log.debug("init update resource {} form", resourceId);
		
		Resource resource = authorizationService.getResourceById(resourceId);
		model.addAttribute(resource);
		model.addAttribute(page);
		
		return "authority/resourceForm";
	}
	
	/**
	 * 更新资源
	 * @param resource
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/url/{resourceId}/edit/{page}", method=RequestMethod.POST)
	public String updateResource(@ModelAttribute Resource resource, @PathVariable("page")int page, Model model){
		
		Resource oldResource = authorizationService.getResourceById(resource.getResourceId());
		
		boolean isUpdate = false;
		if(StringUtils.equals(resource.getUrl(), oldResource.getUrl())){
			
			isUpdate = true;
		} else{
			
			boolean isResourceExist = authorizationService.isResourceExist(resource.getUrl());
			if(isResourceExist){
				
				log.debug("resource {} exists", resource.getUrl());
				model.addAttribute("result", "error");
				model.addAttribute("message", "资源已存在！");
			} else{
				isUpdate = true;
			}
		}
		
		if(isUpdate){
			oldResource.setDescription(resource.getDescription());
			oldResource.setMenu(resource.isMenu());
			oldResource.setUrl(resource.getUrl());
			authorizationService.updateResource(oldResource);
			model.addAttribute("result", "success");
			log.debug("update resource {} {} success", resource.getResourceId(), resource.getUrl());
		}
		
		model.addAttribute(page);
		return "authority/resourceForm";
	}
	
	/**
	 * 资源列表
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/urls/{page}")
	public String resources(@PathVariable("page")int page, Model model){
		
		pageUtils.setCurrentPage(page);
		pageUtils.setTotalRecord(authorizationService.totalResourceCount());
		
		int start = (pageUtils.getCurrentPage()-1)*pageUtils.getPageRecord();
		List<Resource> resources = authorizationService.getResources(start, pageUtils.getPageRecord());
		log.debug("resource list, page {} size {}", pageUtils.getCurrentPage(), resources.size());
		model.addAttribute("resources", resources);
		model.addAttribute("pages", pageUtils);
		
		return "authority/resources";
	}
	/**
	 * 删除资源
	 * @param resourceId
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/url/{resourceId}/delete", method=RequestMethod.POST)
	public String deleteResource(@PathVariable("resourceId")int resourceId, int page){
		log.debug("delete resource {}", resourceId);
		
		authorizationService.deleteResource(resourceId);
		return "forward:/urls/" + page;
	}
}
