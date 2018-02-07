package com.leyidai.web.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leyidai.entity.Map;
import com.leyidai.web.mapper.AnnouncementMapper;
import com.leyidai.web.mapper.MapMapper;
import com.leyidai.web.util.CategoryConstants;
import com.leyidai.web.util.SiteUtil;

import cxf.AppointmentSvc;
import cxf.AppointmentSvcSoap;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//import org.codehaus.xfire.client.Client;

/**
 * 前端Controller
 * 
 * @author Administrator
 * 
 */
@Controller
public class IndexController extends BaseController {

	@Autowired
	private MapMapper mapMapper;
	@Value("#{systemConfigProperties[defaultArea]}")
	private String defaultArea;
	@Autowired
	private AnnouncementMapper announcementMapper;
	private static Logger log = Logger.getLogger(OrderRecordsController.class);

	/* 2017-1-9 add start */
	/**
	 * 系统维护页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/systemMaintenance")
	public String systemMaintenance() {
		return "/systemMaintenance";
	}

	/* 2017-1-9 add end */
	/**
	 * ip封禁页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ipForbidden")
	public String ipForbidden() {
		return "/ipForbidden";
	}

	/**
	 * 选择区县入口页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/")
	public String selectArea() {

		// if(session.getAttribute("area") != null &&
		// StringUtils.isBlank(area)){
		//
		// return "index";
		// } else if (StringUtils.isBlank(area)){
		//
		// area = defaultArea;
		// session.setAttribute("area", area);
		// return "index";
		// }

		return "/areaSelect";
	}

	// /**
	// * 选择区县入口页
	// *
	// * @return
	// */
	// @ResponseBody
	// @RequestMapping(value = "/secret/MP_verify_4yPz8U3x9PXYH6lQ.txt")
	// public String txt() {
	//
	// // if(session.getAttribute("area") != null &&
	// // StringUtils.isBlank(area)){
	// //
	// // return "index";
	// // } else if (StringUtils.isBlank(area)){
	// //
	// // area = defaultArea;
	// // session.setAttribute("area", area);
	// // return "index";
	// // }
	//
	// return "4yPz8U3x9PXYH6lQ";
	// }

	/**
	 * 选择区县入口页给腾讯城市服务
	 * 
	 * @return
	 */
	@RequestMapping(value = "/areaSelectForTencent")
	public String selectAreaForTencent() {
		return "/areaSelectForTencent";
	}

	@RequestMapping(value = "/newarea/{area}")
	public String index(HttpSession session, @PathVariable("area") String area,
			Model model) {
		if (StringUtils.isBlank(area)) {
			area = defaultArea;
		}
		// 9-21 start 添加地图
		Map MapUrl = mapMapper.getUrlByHandleOrgId(area);
		session.setAttribute("area", area);
		model.addAttribute("MapUrl", MapUrl.getMapUrl());
		// 9-21 end
		
		model.addAttribute("announcements", announcementMapper.getAnnouncementAtIndexsOrderById().subList(0, 8));
		
		return "index";
	}

	@RequestMapping(value = "/areaForTencent/{area}")
	public String indexForTencent(HttpSession session,
			@PathVariable("area") String area) {

		if (StringUtils.isBlank(area)) {
			area = defaultArea;
		}
		session.setAttribute("area", area);
		session.setAttribute("isTencent", "true");
		return "/agreementForTencent";
	}

	@RequestMapping(value = "/static/agreementForTencent")
	public String registerAgreementForTencent() {
		return "/agreementForTencent";
	}

	@RequestMapping(value = "/static/agreement")
	public String registerAgreement() {
		return "/agreement";
	}
	
	@RequestMapping(value = "/areaSelectForAuth")
	public String areaSelectForAuth() {
		return "/areaSelectForAuth";
	}
	
	
}
