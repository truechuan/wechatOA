package com.leyidai.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.Element;
import com.leyidai.entity.OrderRecords;
import com.leyidai.entity.User;
import com.leyidai.web.service.OrderRecordsService;
import com.leyidai.web.service.UserService;
import com.leyidai.web.util.DateUtil;
import com.leyidai.web.util.MessageUtil;
import com.leyidai.web.util.SiteUtil;

@Controller
public class UserController extends BaseController {
	private static final Logger log = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private OrderRecordsService orderRecordsService;
	@Autowired
	private DateUtil dateUtil;
	@Autowired
	private SiteUtil siteUtil;
	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/static/register")
	public String register(HttpSession session, Model model) {
		log.info("load register start");

		String openid = (String) session.getAttribute("openid");
		User user = userService.getUserInfoByOpenId(openid);
		List<OrderRecords> orderRecords = orderRecordsService
				.selectByOpenid(openid);
		boolean flag = false;
		for (OrderRecords order : orderRecords) {
			if (order.getStatus() == 1 || order.getStatus() == 2) {
				if (order.getTransactOrgId() == 1037) {
					break;
				} else {
					flag = true;
					break;
				}
			}
		}
		if (user != null) {
			// 如果是黑名单的，就不让他修改身份信息
			if (user.getIsForbidden() != 0) {
				model.addAttribute("user", user);
				model.addAttribute("forbidden", "你因违规或爽约被列入黑名单");
				return "/user/account";
			} else if (!orderRecords.isEmpty() && flag) {
				model.addAttribute("user", user);
				model.addAttribute("forbidden", "你已经有未处理的预约，请不要修改个人身份信息");
				return "/user/account";
			} else {
				model.addAttribute("user", user);
				return "register";
			}
		} else {
			// 获取用户openid
			model.addAttribute("openid", openid);
			return "register";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/static/register/submit")
	public Map<String, String> register(User user) throws UnsupportedEncodingException {
		user.setName(URLDecoder.decode(user.getName(),"UTF-8"));
		return userService.insertUser(user);
	}

	@ResponseBody
	@RequestMapping(value = "/static/register/update")
	public Map<String, String> registerUpdate(User user,HttpSession session) throws UnsupportedEncodingException {
		user.setName(URLDecoder.decode(user.getName(),"UTF-8"));
		String openid = (String) session.getAttribute("openid");
		user.setOpenid(openid);
		return userService.updateUser(user);
//		return null;
	}

	@RequestMapping(value = "/static/ordered")
	public String ordered(Model model) {
		// 获取用户openid
		model.addAttribute("openid",
				"j1AIKMkdEiimTfPMSaman5W14rNX-vlUmLehoz7Yyos");
		return "register";
	}

	
	
	
	
	
}
