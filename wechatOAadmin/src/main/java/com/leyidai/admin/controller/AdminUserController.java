package com.leyidai.admin.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.leyidai.entity.BackstageAdminUser;
import com.leyidai.entity.Group;

/**
 * adminUser Controller
 * @author song
 *
 */
@Controller
public class AdminUserController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(AdminUserController.class);
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	@Autowired
	private BackstageAdminUserService adminUserService;
	@Autowired
	private AuthorizationService authorizationService;
	@Autowired
	private PageUtils pageUtils;
	@Value("#{systemConfigProperties[superAdminLoginName]}")
	private String superAdminLoginName;
	
	@ModelAttribute("groups")
	public Set<Group> loadAdminUserGroups(){
		
		BackstageAdminUser sessionAdminUser = this.getSessionAdminUser();
		Set<Group> groups = null;
		
		if(sessionAdminUser != null){
			
			if(StringUtils.equals(superAdminLoginName, sessionAdminUser.getLoginName())){
				groups = new HashSet<Group>(this.authorizationService.getGrups());
			} else{
				groups = sessionAdminUser.getGroups();
			}
			
		}
		
		return groups;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Group.class, new GroupEditor(authorizationService));
	}
	
	@RequestMapping(value = "/createAdminUser", method=RequestMethod.GET)
	public String initCreateAdminUser(Model model){
		
		model.addAttribute("areas", this.getAreas());
		model.addAttribute("adminUser", new BackstageAdminUser());
		return "adminUserForm";
	}
	
	@RequestMapping(value = "/createAdminUser", method=RequestMethod.POST)
	public String addAdminUser(@ModelAttribute("adminUser")BackstageAdminUser adminUser, Model model){
		
		BackstageAdminUser existAdminUser = adminUserService.getAdminUserByUsername(adminUser.getLoginName());
		if(existAdminUser != null && existAdminUser.getId() > 0){
			
			model.addAttribute("result", "error");
			model.addAttribute("message", "登录名已存在!");
		} else{
			
			adminUser.setPassWord(bcryptEncoder.encode(adminUser.getPassWord()));
			adminUser.setUserStatus("0");
			adminUserService.addAdminUser(adminUser);
			model.addAttribute("result", "success");
		}
		
		model.addAttribute("adminUser", adminUser);
		return "adminUserForm";
	}
	
	@RequestMapping(value="/password")
	public String updatePassword(String oldPassword, String newPassword, Model model){
		
		if(StringUtils.isNotEmpty(newPassword)){
			
			BackstageAdminUser sessionUser = this.getSessionAdminUser();
			
			if(bcryptEncoder.matches(oldPassword, sessionUser.getPassWord())){
				
				String newEncodedPassword = bcryptEncoder.encode(newPassword);
				sessionUser.setPassWord(newEncodedPassword);
				adminUserService.updateAdminUserPassword(sessionUser.getId(), newEncodedPassword);
				model.addAttribute("result", "success");
			} else{
				
				model.addAttribute("result", "error");
				model.addAttribute("message", "原密码输入错误！");
			}
		}
		
		return "passwordForm";
	}
	
	@RequestMapping(value="/adminUser/{adminUserId}/edit/{page}", method=RequestMethod.GET)
	public String initUpdateAdminUser(@PathVariable("adminUserId")int adminUserId, @PathVariable("page")int page, Model model){
		log.debug("init update form for admin user {}", adminUserId);
		
		BackstageAdminUser adminUser = adminUserService.getAdminUserById(adminUserId);
		int userGroup = authorizationService.getGrupsByAdminUserId(adminUserId);
		
		model.addAttribute("adminUser", adminUser);
		model.addAttribute(page);
		model.addAttribute("userGroup", userGroup);
		model.addAttribute("areas", this.getAreas());
		
		return "adminUserForm";
	}
	
	@RequestMapping(value="/adminUser/{adminUserId}/edit/{page}", method=RequestMethod.POST)
	public String updateAdminUser(@ModelAttribute("adminUser")BackstageAdminUser adminUser, @PathVariable("page")int page, Model model){
		log.debug("update admin user {} {}", adminUser.getLoginName(), adminUser.getId());
		
		boolean isUpdate = false;
		BackstageAdminUser persistAdminUser = adminUserService.getAdminUserById(adminUser.getId());
		
		if(!StringUtils.equals(adminUser.getLoginName(), persistAdminUser.getLoginName())){
			BackstageAdminUser existAdminUser = adminUserService.getAdminUserByUsername(adminUser.getLoginName());
			if(existAdminUser != null && existAdminUser.getId() > 0){
				
				model.addAttribute("result", "error");
				model.addAttribute("message", "登录名已存在!");
			} else{
				isUpdate = true;
			}
		} else{
			isUpdate = true;
		}
		
		if(isUpdate && persistAdminUser != null){
			
			persistAdminUser.setLoginName(adminUser.getLoginName());
			persistAdminUser.setRealName(adminUser.getRealName());
			
			if(!StringUtils.equals(adminUser.getPassWord(), persistAdminUser.getPassWord())){
				
				String newPassword = bcryptEncoder.encode(adminUser.getPassWord());
				log.debug("new password {}, old password {}", newPassword, persistAdminUser.getPassWord());
				persistAdminUser.setPassWord(newPassword);
			}
			persistAdminUser.setPhone(adminUser.getPhone());
			persistAdminUser.setComment(adminUser.getComment());
			persistAdminUser.setGroups(adminUser.getGroups());
			persistAdminUser.setArea(adminUser.getArea());
			adminUserService.updateAdminUser(persistAdminUser);
			model.addAttribute("adminUser", adminUser);
			model.addAttribute("result", "success");
		}
		
		int userGroup = authorizationService.getGrupsByAdminUserId(adminUser.getId());
		
		model.addAttribute(page);
		model.addAttribute("areas", this.getAreas());
		model.addAttribute("userGroup", userGroup);
		
		return "adminUserForm";
	}
	
	@RequestMapping(value="/adminUsers/{page}")
	public String adminUsers(@PathVariable("page")int page, Model model){
		
		pageUtils.setCurrentPage(page);
		pageUtils.setTotalRecord(adminUserService.totalAdminUserCount());
		
		int start = (pageUtils.getCurrentPage()-1)*pageUtils.getPageRecord();
		List<BackstageAdminUser> adminUsers = adminUserService.loadAllAdminUsers(start, pageUtils.getPageRecord());
		log.debug("adminUserList, page {} size {}", pageUtils.getCurrentPage(), adminUsers.size());
		
		Set<Group> groups = new HashSet<Group>();
		for(BackstageAdminUser adminUser: adminUsers){
			
			groups = authorizationService.getUserGroups(adminUser.getId());
			adminUser.setGroups(groups);
		}
		
		model.addAttribute("adminUses", adminUsers);
		model.addAttribute("pages", pageUtils);
		
		model.addAttribute("superAdminLoginName", superAdminLoginName);
		return "adminUsers";
	}
	

}
