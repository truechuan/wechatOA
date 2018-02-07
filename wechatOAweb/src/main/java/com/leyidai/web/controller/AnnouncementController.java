package com.leyidai.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leyidai.entity.Announcement;
import com.leyidai.entity.Announcement.AnnouncementLanmu;
import com.leyidai.web.mapper.AnnouncementMapper;
import com.leyidai.web.service.AnnouncementService;
import com.leyidai.web.util.DateUtil;
import com.leyidai.web.util.DateUtil.DateFormat;
import com.leyidai.web.util.PageUtil;

/**
 * 通知公告
 * @author Administrator
 *
 */
@Controller
public class AnnouncementController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(AnnouncementController.class);
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private AnnouncementMapper announcementMapper;
	@Autowired
	private PageUtil pageUtil;
	@Autowired
	private DateUtil dateUtil;
	
	/**
	 * 通知公告列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/static/{page}/announcements.html")
	public String announcements(HttpSession session, Model model,
			@PathVariable("page")int page) {
		log.info("load announcements");
		String handleOrg = ((String)session.getAttribute("area"));
		
		if(StringUtils.isEmpty(handleOrg)){
			return "/areaSelect";
		}
		
		int announcementCount = announcementService.getAnnouncementCount(AnnouncementLanmu.THE_ANNOUNCEMENT, handleOrg);
		pageUtil.setTotalRecord(announcementCount);
		pageUtil.setCurrentPage(page);
		int start = pageUtil.getPageRecord() * (pageUtil.getCurrentPage()-1);
		List<Announcement> announcements = announcementService.loadAnnouncement(start, pageUtil.getPageRecord(), AnnouncementLanmu.THE_ANNOUNCEMENT, handleOrg);
		
		for(Announcement annmt: announcements){
			annmt.setCreateTime(dateUtil.getFormatTime(annmt.getCreateTime(), DateFormat.YYYY_MM_DD));
        }
        model.addAttribute("totalPage", pageUtil.getTotalPage());
		model.addAttribute("currentPage", page);
        
        model.addAttribute("announcements", announcements);
		return "announcements";
	}
	
	/**
	 * 首页通知公告聚合页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/announcementList")
	public String announcementList(Model model) {
        model.addAttribute("announcements", announcementMapper.getAnnouncementAtIndexs());
		return "announcementList";
	}
	
	
	/**
	 * 通知公告详情
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/static/{announcementId}/announcement.html")
	public String announcementInfo(HttpServletRequest request, Model model,
			@PathVariable("announcementId")int announcementId) {
		log.info("load announcementInfo by announcementId");
		Announcement annmt = announcementService.selectAnnouncement(announcementId);
		annmt.setCreateTime(dateUtil.getFormatTime(annmt.getCreateTime(), DateFormat.YYYY_MM_DD));
        model.addAttribute("announcementInfo", annmt);
		return "announcementInfo";
	}
}
