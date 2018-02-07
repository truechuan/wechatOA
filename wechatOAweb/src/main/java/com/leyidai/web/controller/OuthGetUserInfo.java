package com.leyidai.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leyidai.entity.Dictionary;
import com.leyidai.entity.User;
import com.leyidai.web.outh.ConstantOuth;
import com.leyidai.web.service.DictionaryService;
import com.leyidai.web.util.CategoryConstants;
import com.leyidai.web.util.MyHttpRequest;
import com.leyidai.web.util.SiteUtil;
import com.leyidai.web.weChat.StringUtil;

@Controller
public class OuthGetUserInfo {
	@Autowired
	private DictionaryService dictionaryService;
	private static Logger log = org.slf4j.LoggerFactory
			.getLogger(OrderRecordsController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(String spid, HttpServletRequest request) {
		// 如果能够查询到session值，说明用户不必再次获取openid和userinfo
		if (request.getSession().getAttribute("isAuthTrue") != null) {
			if (request.getSession().getAttribute("isAuthTrue").equals("1")) {
				System.out.println("进去了分支");
				return "/areaSelectForAuth";
			}
		}
		String redirectUri = ConstantOuth.redirect_uri;
		try {
			redirectUri = redirectUri + "?spid=" + spid;// 审批id，此处可添加返回时需要的参数
			redirectUri = URLEncoder.encode(redirectUri, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// scope为auth_user(可获取隐私信息：真实姓名、证件号、手机号) 或 auth_base
		String url = ConstantOuth.sso_url
				+ "?scope=auth_user&state=11&client_id="
				+ ConstantOuth.client_id + "&redirect_uri=" + redirectUri;
		return "redirect:" + url;
	}

	@RequestMapping(value = "/outh/autoLogin")
	public String autoLogin(HttpServletRequest request, String code,
			String state, String spid, Model model) {
		if (StringUtil.isEmpty(code) || StringUtil.isEmpty(state)) {
			throw new RuntimeException("error");
		}
		log.info(code);
		String clientId = ConstantOuth.client_id;
		String clientSecret = ConstantOuth.client_secret;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("client_id", clientId);
		params.put("client_secret", clientSecret);
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		// 获取token
		String result = SiteUtil.sendGet(ConstantOuth.token_url, params,
				"utf-8");
		log.info(result);
		JSONObject data = JSONObject.fromObject(result);
		request.getSession().setAttribute("openid",
				data.get("openid").toString());
		Map<String, Object> userinfoParams = new HashMap<String, Object>();
		userinfoParams.put("access_token", data.get("access_token").toString());
		userinfoParams.put("openid", data.get("openid").toString());
		// 获取用户信息
		String userInfo = SiteUtil.sendGet(ConstantOuth.userinfo_url,
				userinfoParams, "utf-8");
		System.out.println(userInfo);
		log.info(userInfo);
		JSONObject dataUserInfo = JSONObject.fromObject(userInfo);
		// 用户必须实名认证
		if (dataUserInfo.get("rank").toString()
				.equals("ADVANCED_AUTHENTICATION")) {
			// 用户的身份证号，手机号，用户真实姓名都不为空
			if (dataUserInfo.get("idCard").toString() != null
					&& dataUserInfo.get("msisdn").toString() != null
					&& dataUserInfo.get("realName").toString() != null) {
				// 认为用户合法，可以正常预约,进入选区页面
				log.info("进入调去info分支");
				User user = new User();
				user.setName(dataUserInfo.get("realName").toString());
				user.setIdcard(dataUserInfo.get("idCard").toString());
				user.setMobile(Long.valueOf(dataUserInfo.get("msisdn")
						.toString()));
				user.setOpenid(data.get("openid").toString());
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("isAuthTrue", "1");
				return "redirect:/areaSelectForAuth";
			} else {
				request.getSession().setAttribute("isAuthTrue", "0");

				log.info("进去了else分支1");
				return "redirect:" + ConstantOuth.authIndex;
			}
		} else {
			request.getSession().setAttribute("isAuthTrue", "0");

			log.info("进去了else分支2");
			return "redirect:" + ConstantOuth.authIndex;
		}
	}

	@RequestMapping(value = "/bookAuth/{area}")
	public String bookInit(HttpSession session, Model model,
			@PathVariable("area") String area) {

		if (StringUtils.isEmpty(area)) {
			return "/areaSelectForAuth";
		}
		String openid = (String) session.getAttribute("openid");
		// 办理机构
		List<Dictionary> arrhandleOrg = dictionaryService
				.getDictionarysByCatetory(CategoryConstants.HANDLEORG, null);
		List<Dictionary> handleOrg = new ArrayList<Dictionary>();
		for (int i = 0; i < arrhandleOrg.size(); i++) {
			Dictionary item = arrhandleOrg.get(i);
			if (area.indexOf(String.valueOf(item.getId())) != -1) {
				handleOrg.add(item);
			}
		}
		model.addAttribute("openid", openid);
		// model.addAttribute("arrDays", arrDays);
		model.addAttribute("handleOrg", handleOrg);
		// model.addAttribute("businessType", businessType);
		model.addAttribute("userItem", (User) session.getAttribute("user"));
		return "book";
	}

	/**
	 * 刷新token
	 * 
	 * @param request
	 * @param code
	 * @param state
	 * @param spid
	 * @return
	 */
	@RequestMapping(value = "/outh/refreshToken")
	public String autoLoginRefresh(HttpServletRequest request, String code,
			String state, String spid) {

		String clientId = ConstantOuth.client_id;
		String clientSecret = ConstantOuth.client_secret;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("client_id", clientId);
		params.put("client_secret", clientSecret);
		// params.put("refresh_token",refresh_token);
		params.put("grant_type", "refresh_token");
		// 获取token
		String result = MyHttpRequest.sendGet(ConstantOuth.token_url, params,
				"utf-8");
		System.out.println(result);
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map data = mapper.readValue(result, Map.class);
			Map<String, Object> userinfoParams = new HashMap<String, Object>();
			userinfoParams.put("access_token", data.get("access_token")
					.toString());
			userinfoParams.put("openid", data.get("openid").toString());
			// 获取用户信息
			String userInfo = MyHttpRequest.sendGet(ConstantOuth.userinfo_url,
					userinfoParams, "utf-8");
			System.out.println(userInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 业务处理页面
		return "redirect:/";
	}

}
