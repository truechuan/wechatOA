package com.leyidai.admin.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.leyidai.entity.Group;
import com.leyidai.entity.Role;

/**
 * 权限管理
 * @author Administrator
 *
 */
@Controller
public class GroupController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(GroupController.class);
	private final AuthorizationService authorizationService;
	
	@Autowired
	private PageUtils pageUtils;
	
	@Autowired
	public GroupController(AuthorizationService authorizationService){
		this.authorizationService = authorizationService;
	}
	
	@ModelAttribute("allRoles")
	public Set<Role> loadAllRoles(){
		return new HashSet<Role>(this.authorizationService.getRoles());
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Role.class, new RoleEditor(this.authorizationService));
	}
	
	/**
	 * 初始化角色表单 - 新增角色
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/group/new", method=RequestMethod.GET)
	public String initCreationGroupForm(Model model){
		
		Group group = new Group();
		model.addAttribute(group);
		
		return "authority/groupForm";
	}
	
	/**
	 * 新增角色
	 * @param group
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/group/new", method=RequestMethod.POST)
	public String createGroup(@ModelAttribute Group group, Model model){
		
		boolean isGroupNameExist = authorizationService.isGroupExist(group.getName());
		if(isGroupNameExist){
			
			log.debug("group name {} exists", group.getName());
			model.addAttribute("result", "error");
			model.addAttribute("message", "角色名称已存在！");
		} else{
			
			authorizationService.addGroup(group);
			model.addAttribute("result", "success");
			log.debug("new group {} sucess", group.getName());
		}
		
		return "authority/groupForm";
	}
	
	/**
	 * 初始化角色表单 - 更新角色
	 * @param groupId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/group/{groupId}/edit/{page}", method=RequestMethod.GET)
	public String initUpdateGroupForm(@PathVariable("groupId")int groupId, @PathVariable("page")int page, Model model){
		log.debug("init update group {} form", groupId);
		
		Group group = authorizationService.getGroupById(groupId);
		model.addAttribute(group);
		model.addAttribute(page);
		return "authority/groupForm";
	}
	
	/**
	 * 更新角色
	 * @param group
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/group/{groupId}/edit/{page}", method=RequestMethod.POST)
	public String updateGroup(@ModelAttribute Group group, @PathVariable("page")int page, Model model){
		
		boolean isUpdate = false;
		Group oldGroup = authorizationService.getGroupById(group.getGroupId());
		if(!StringUtils.equals(oldGroup.getName(), group.getName())){
			
			boolean isGroupNameExist = authorizationService.isGroupExist(group.getName());
			if(isGroupNameExist){
				log.debug("group name {} exists", group.getName());
				model.addAttribute("result", "error");
				model.addAttribute("message", "角色名称已存在！");
			} else{
				isUpdate = true;
			}
		} else{
			isUpdate = true;
		}
		
		if(isUpdate){
			Group persistent = oldGroup;
			persistent.setName(group.getName());
			persistent.setDescription(group.getDescription());
			persistent.setRoles(group.getRoles());
			authorizationService.updateGroup(persistent);
			model.addAttribute("result", "success");
			log.debug("update group {}-{} success", group.getGroupId(), group.getName());
		}
		
		model.addAttribute(page);
		return "authority/groupForm";
	}
	
	/**
	 * 角色列表
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/groups/{page}")
	public String groups(@PathVariable("page") int page, Model model){
		
		pageUtils.setCurrentPage(page);
		pageUtils.setTotalRecord(authorizationService.totalGroupCount());
		
		int start = (pageUtils.getCurrentPage() - 1)*pageUtils.getPageRecord();
		List<Group> groups = authorizationService.getGroups(start, pageUtils.getPageRecord());
		log.debug("group list, page {} size {}", pageUtils.getCurrentPage(), groups.size());
		model.addAttribute("groups", groups);
		model.addAttribute("pages", pageUtils);
		
		return "authority/groups";
	}
	
	/**
	 * 删除角色
	 * @param groupId
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/group/{groupId}/delete", method=RequestMethod.POST)
	public String deleteGroup(@PathVariable("groupId")int groupId, int page){
		log.debug("delete group {}", groupId);
		
		authorizationService.deleteGroup(groupId);
		return "forward:/groups/" + page;
	}
}
