package com.leyidai.admin.controller;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leyidai.admin.service.AuthorizationService;
import com.leyidai.admin.util.PageUtils;
import com.leyidai.entity.Resource;
import com.leyidai.entity.ResourceCategory;

@Controller
public class ResourceCategoryController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(ResourceController.class);
	private final AuthorizationService authorizationService;
	
	@Autowired
	private PageUtils pageUtils;
	
	@Autowired
	public ResourceCategoryController(AuthorizationService authorizationService){
		this.authorizationService = authorizationService;
	}
	
	@ModelAttribute("allResources")
	public Set<Resource> loadAllResources(){
		return new TreeSet<Resource>(this.authorizationService.loadAllResources());
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Resource.class, new ResourceEditor(authorizationService));
	}
	
	/**
	 * 初始化资源分类表单 - 新增资源分类
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/rcategory/new", method=RequestMethod.GET)
	public String initCreationRCategoryForm(Model model){
		
		ResourceCategory resourceCategory = new ResourceCategory();
		model.addAttribute(resourceCategory);
		
		return "authority/rcategoryForm";
	}
	
	/**
	 * 新增资源分类
	 * @param rcategory
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/rcategory/new", method=RequestMethod.POST)
	public String createRCategory(@ModelAttribute ResourceCategory resourceCategory, Model model){
		
		boolean isRCategoryExist = authorizationService.isResourceCategoryExist(resourceCategory.getName());
		if(isRCategoryExist){
			log.debug("resource category {} exists", resourceCategory.getName());
			model.addAttribute("result", "error");
			model.addAttribute("message", "URL分类已存在！");
		} else{
			
			authorizationService.addResourceCategory(resourceCategory);
			model.addAttribute("result", "success");
			log.debug("create resource category {}-{} success", resourceCategory.getRcategoryId(), resourceCategory.getName());
		}
		
		model.addAttribute(resourceCategory);
		return "authority/rcategoryForm";
	}
	
	/**
	 * 初始化资源分类表单 - 更新资源分类
	 * @param rcategoryId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/rcategory/{rcategoryId}/edit/{page}", method=RequestMethod.GET)
	public String initUpdateRCategoryForm(@PathVariable("rcategoryId")int rcategoryId, @PathVariable("page")int page, Model model){
		log.debug("init update resource category {} form", rcategoryId);
		
		ResourceCategory resourceCategory = authorizationService.getResourceCategory(rcategoryId);
		model.addAttribute(resourceCategory);
		model.addAttribute(page);
		
		return "authority/rcategoryForm";
	}
	
	/**
	 * 更新资源分类
	 * @param resourceCategory
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/rcategory/{rcategoryId}/edit/{page}", method=RequestMethod.POST)
	public String updateRCategory(@ModelAttribute ResourceCategory resourceCategory, @PathVariable("page")int page, Model model){
		
		ResourceCategory oldRCategory = authorizationService.getResourceCategory(resourceCategory.getRcategoryId());
		
		boolean isUpdate = false;
		if(StringUtils.equals(resourceCategory.getName(), oldRCategory.getName())){
			
			isUpdate = true;
		} else{
			
			boolean isRCategoryExist = authorizationService.isResourceCategoryExist(resourceCategory.getName());
			if(isRCategoryExist){
				
				log.debug("resource category {} exists", resourceCategory.getName());
				model.addAttribute("result", "error");
				model.addAttribute("message", "URL分类已存在！");
			} else{
				isUpdate = true;
			}
		}
		
		if(isUpdate){
			
			oldRCategory.setName(resourceCategory.getName());
			oldRCategory.setResources(resourceCategory.getResources());
			authorizationService.updateResourceCategory(oldRCategory);
			model.addAttribute("result", "success");
			log.debug("update resource category {} {} success", resourceCategory.getRcategoryId(), resourceCategory.getName());
		}
		
		model.addAttribute(resourceCategory);
		model.addAttribute(page);
		return "authority/rcategoryForm";
	}
	
	/**
	 * 资源分类列表
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/rcategories/{page}")
	public String rcategories(@PathVariable("page")int page, Model model){
		
		pageUtils.setCurrentPage(page);
		pageUtils.setTotalRecord(authorizationService.totalResourceCatgeoryCount());
		
		int start = (pageUtils.getCurrentPage()-1)*pageUtils.getPageRecord();
		List<ResourceCategory> rcs = authorizationService.getResourceCategorys(start, pageUtils.getPageRecord());
		log.debug("resource category list, page {} size {}", pageUtils.getCurrentPage(), rcs.size());
		model.addAttribute("rcategories", rcs);
		model.addAttribute("pages", pageUtils);
		
		return "authority/rcategories";
	}
	/**
	 * 删除资源分类
	 * @param rcategoryId
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/rcategory/{rcategoryId}/delete", method=RequestMethod.POST)
	public String deleteRCategory(@PathVariable("rcategoryId")int rcategoryId, int page){
		log.debug("delete resource category {}", rcategoryId);
		
		authorizationService.deleteResourceCategory(rcategoryId);
		return "forward:/rcategories/" + page;
	}

}
