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
import com.leyidai.admin.service.BackstageAdminUserService;
import com.leyidai.admin.util.PageUtils;
import com.leyidai.entity.Resource;
import com.leyidai.entity.Role;

@Controller
public class RoleController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(GroupController.class);
	private final AuthorizationService authorizationService;
	
	@Autowired
	private PageUtils pageUtils;
	
	@Autowired
	public RoleController(AuthorizationService authorizationService, BackstageAdminUserService adminUserService){
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
	 * 初始化权限表单 - 新增权限
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/role/new", method=RequestMethod.GET)
	public String initCreationRoleForm(Model model){
		
		Role role = new Role();
		model.addAttribute("role", role);
		
		return "authority/roleForm";
	}
	
	/**
	 * 新增权限
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/role/new", method=RequestMethod.POST)
	public String createRole(@ModelAttribute Role role, Model model){
		
		boolean isRoleExist = authorizationService.isRoleExist(role.getName());
		if(isRoleExist){
			
			log.debug("role name {} exists", role.getName());
			model.addAttribute("result", "error");
			model.addAttribute("message", "权限已存在！");
		} else{
			
			authorizationService.addRole(role);
			model.addAttribute("result", "success");
			log.debug("new role {} sucess", role.getName());
		}
		
		model.addAttribute("role", role);
		return "authority/roleForm";
	}
	
	/**
	 * 初始化权限表单 - 更新权限
	 * @param roleId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/role/{roleId}/edit/{page}", method=RequestMethod.GET)
	public String initUpdateRoleForm(@PathVariable("roleId")int roleId, @PathVariable("page")int page, Model model){
		log.debug("init update role {} form", roleId);
		
		Role role = authorizationService.getRoleById(roleId);
		model.addAttribute(role);
		model.addAttribute(page);
		
		return "authority/roleForm";
	}
	
	/**
	 * 更新权限
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/role/{roleId}/edit/{page}", method=RequestMethod.POST)
	public String updateRole(@ModelAttribute Role role, @PathVariable("page")int page, Model model){
		
		boolean isUpdate = false;
		Role oldRole = authorizationService.getRoleById(role.getRoleId());
		if(StringUtils.equals(oldRole.getName(), role.getName())){
			
			boolean isRoleExist = authorizationService.isGroupExist(role.getName());
			if(isRoleExist){
				log.debug("role name {} exists", role.getName());
				model.addAttribute("result", "error");
				model.addAttribute("message", "权限名称已存在！");
			} else{
				isUpdate = true;
			}
		} else{
			isUpdate = true;
		}
		
		if(isUpdate){
			
			Role persistent = oldRole;
			persistent.setName(role.getName());
			persistent.setDescription(role.getDescription());
			persistent.setResources(role.getResources());
			authorizationService.updateRole(persistent);
			model.addAttribute("result", "success");
			log.debug("update role {}-{} success", role.getRoleId(), role.getName());
		}
		
		model.addAttribute(page);
		
		return "authority/roleForm";
	}
	
	/**
	 * 权限列表
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/roles/{page}")
	public String roles(@PathVariable("page") int page, Model model){
		
		pageUtils.setCurrentPage(page);
		pageUtils.setTotalRecord(authorizationService.totalRoleCount());
		
		int start = (pageUtils.getCurrentPage()-1)*pageUtils.getPageRecord();
		List<Role> roles = authorizationService.getRoles(start, pageUtils.getPageRecord());
		log.debug("role list, page {} size {}", pageUtils.getCurrentPage(), roles.size());
		model.addAttribute("roles", roles);
		model.addAttribute("pages", pageUtils);
		
		return "authority/roles";
	}
	
	/**
	 * 删除权限
	 * @param groupId
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/role/{roleId}/delete", method=RequestMethod.POST)
	public String deleteRole(@PathVariable("roleId")int roleId, int page){
		log.debug("delete role {}", roleId);
		
		authorizationService.deleteRole(roleId);
		return "forward:/roles/" + page;
	}
}
