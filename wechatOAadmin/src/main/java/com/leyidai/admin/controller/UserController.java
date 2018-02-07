package com.leyidai.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leyidai.admin.service.UserService;
import com.leyidai.admin.util.DateUtil;
import com.leyidai.admin.util.PageUtils;
import com.leyidai.entity.User;

/**
 * 用户处理
 * 
 * @author song
 * 
 */
@Controller
public class UserController extends BaseController {
	private static final Logger logs = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private DateUtil dateUtil;
	@Autowired
	private PageUtils pageUtil;

	@RequestMapping(value = "/user/welcome.htm")
	public String welcome() {
		logs.debug("load welcome page start");

		return "welcome";
	}

	/**
	 * 用户列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/users/{page}")
	public String loadUsers(@PathVariable("page") int page,
			@ModelAttribute("name") String name,
			@ModelAttribute("mobile") String mobile,
			@ModelAttribute("openid") String openid,
			@ModelAttribute("idcard") String idcard,
			@ModelAttribute("status") String status,
			@ModelAttribute("isForbidden") String isForbidden, Model model) {
		logs.debug("load users start");

		List<User> users = new ArrayList<User>();
		pageUtil.setCurrentPage(page);
		int c = userService.getUserCount(name, mobile,openid, idcard, status,
				isForbidden);
		logs.info(String.valueOf(c) + "这是总数");
		pageUtil.setTotalRecord(c);
		int a = pageUtil.getPageRecord();
		int b = pageUtil.getCurrentPage();
		int start = a * (b - 1);
		users = userService.getUsers(start, pageUtil.getPageRecord(), name,
				mobile,openid, idcard, status, isForbidden);
		logs.info(String.valueOf(c) + "这是总数");
		model.addAttribute("status", status);
		model.addAttribute("users", users);
		model.addAttribute("pages", pageUtil);
		return "/user/users";
	}

	/**
	 * 初始化用户
	 */
	@RequestMapping(value = "/updateUser/{id}")
	public String initUpdateUser(@PathVariable("id") int userId, Model model) {
		logs.debug("init User start");
		User user = userService.getUserByUserId(userId);
		model.addAttribute("user", user);
		return "/user/userForm";
	}

	/**
	 * 修改用户 userId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateUser/{id}/{userStatus}/{page}")
	public String updateUser(@ModelAttribute("id") int userId,
			@ModelAttribute("userStatus") int userStatus,
			@ModelAttribute("page") int page) {
		User user = userService.getUserByUserId(userId);
		user.setStatus(userStatus);
		user.setId(userId);
		userService.updateUser(user);
		return "forward:/users/" + page;
	}

	/**
	 * 拉黑用户 userId
	 * 
	 * @return
	 */
	@RequestMapping(value = "/forbiddenUser/{id}/{isForbidden}/{page}")
	public String forbiddenUser(@ModelAttribute("id") int userId,
			@ModelAttribute("isForbidden") int isForbidden,
			@ModelAttribute("page") int page) {
		User user = userService.getUserByUserId(userId);
		user.setIsForbidden(isForbidden);
		logs.info(String.valueOf(isForbidden));
		user.setId(userId);
		userService.updateUser(user);
		return "forward:/users/" + page;
	}

	/**
	 * 删除用户（真删）
	 * 
	 * @param userId
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/deleteUser/{userId}/{page}")
	public String deleteUser(@ModelAttribute("userId") int userId,
			@ModelAttribute("page") int page) {

		userService.deleteUser(userId);
		return "forward:/users/" + page;
	}
	
	/*********************2017-04-12 史晓昊 add start*********************/
	/**
	 * 解封用户
	 * @param userId
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/unLockedUser/{openid}/{page}")
	public String unLockedUser(@ModelAttribute("openid") int userId,
			@ModelAttribute("page") int page) {

		userService.unLockedByUserId(userId);
		return "forward:/users/" + page;
	}
	/*********************2017-04-12 史晓昊 add end*********************/
}
