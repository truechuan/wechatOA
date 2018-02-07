package com.leyidai.web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.leyidai.entity.User;
import com.leyidai.web.service.OrderRecordsService;
import com.leyidai.web.service.UserService;

@Controller
public class AccountController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(AccountController.class);
	  
	@Autowired
	private UserService userService;
	@Autowired
	private OrderRecordsService orderRecordsService;
	
	@RequestMapping(value="/static/myAccount")
	public String myAccount(HttpSession session, Model model){
		log.info("load myAccount start");

		String openid = (String)session.getAttribute("openid");
		User user = userService.getUserInfoByOpenId(openid);
		if(user == null){
			model.addAttribute("rtnMsg", "您是首次预约！请先完善个人信息");
			return "redirect:/static/register?rtnCode=9";
			//如果是黑名单用户，就提示他
		}else if (user.getIsForbidden()!=0) {
			model.addAttribute("user", user);
			model.addAttribute("forbidden","你已经被列为黑名单");
			return "/user/account";
		}
		else{
			model.addAttribute("user", user);
			return "/user/account";
		}
	}
	
}
