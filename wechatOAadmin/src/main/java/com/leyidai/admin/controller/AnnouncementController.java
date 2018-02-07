package com.leyidai.admin.controller;

import java.io.Console;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leyidai.admin.service.AnnouncementService;
import com.leyidai.admin.util.DateUtil;
import com.leyidai.admin.util.DateUtil.DateFormat;
import com.leyidai.admin.util.PageUtils;
import com.leyidai.entity.Announcement;
import com.leyidai.entity.BackstageAdminUser;
import com.leyidai.entity.Question;
import com.leyidai.entity.Announcement.AnnouncementLanmu;
import com.leyidai.entity.Announcement.AnnouncementStatus;
import com.leyidai.entity.editor.AnnouncementLanmuEditor;
import com.leyidai.entity.editor.AnnouncementStatusEditor;
import com.leyidai.admin.mapper.AreaMapper;

@Controller
public class AnnouncementController extends BaseController {
	@Autowired
	private PageUtils pageUtil;
	@Autowired
	private DateUtil dateUtil;

	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private AreaMapper areaMapper;

	@ModelAttribute("announcementLanmu")
	public AnnouncementLanmu[] announcementLanmu() {

		return AnnouncementLanmu.values();
	}

	@ModelAttribute("announcementStatus")
	public AnnouncementStatus[] announcementStatus() {

		return AnnouncementStatus.values();
	}

	@Value("#{systemConfigProperties[superAdminLoginName]}")
	private String superAdminLoginName;

	private static Logger log = org.slf4j.LoggerFactory
			.getLogger(AnnouncementController.class);

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(AnnouncementLanmu.class,
				new AnnouncementLanmuEditor());
		binder.registerCustomEditor(AnnouncementStatus.class,
				new AnnouncementStatusEditor());
	}

	/**
	 * 初始化公告添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/createAnnouncement", method = RequestMethod.GET)
	public String initCreateAnnouncement(
			@ModelAttribute("announcement") Announcement announcement,
			Model model) {
		announcement.setLanmu(AnnouncementLanmu.THE_ANNOUNCEMENT);
		announcement.setStatus(AnnouncementStatus.VALID);
		announcement.setCreateTime(dateUtil
				.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));

		BackstageAdminUser sessionAdminUser = this.getSessionAdminUser();
		String flag = "true";
		if (sessionAdminUser != null
				&& !sessionAdminUser.getLoginName().equals(superAdminLoginName)) {
			flag = "false";
		}
		model.addAttribute("announcement", announcement);
		model.addAttribute("areas", this.getAreas());
		model.addAttribute("isMoreManger", flag);
		model.addAttribute("question", new Question());

		return "announcementForm";
	}

	/**
	 * 初始化公告修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateAnnouncement/{announcementId}",
			method = RequestMethod.GET)
	public String initUpdateAnnouncement(
			@PathVariable("announcementId") int announcementId, Model model) {
		Announcement announcement = announcementService
				.getAnnouncementByAnnouncementId(announcementId);

		announcement.setCreateTime(announcement.getCreateTime().substring(0,
				announcement.getCreateTime().indexOf(".")));

		BackstageAdminUser sessionAdminUser = this.getSessionAdminUser();
		String flag = "true";
		if (sessionAdminUser != null
				&& !sessionAdminUser.getLoginName().equals(superAdminLoginName)) {
			flag = "false";
		}

		model.addAttribute("announcement", announcement);
		model.addAttribute("areas", this.getAreas());
		model.addAttribute("isMoreManger", flag);
		model.addAttribute("question", new Question());

		return "announcementForm";
	}

	/**
	 * 添加公告记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/createAnnouncement", method = RequestMethod.POST)
	public String addAnnouncement(
			@ModelAttribute("announcement") Announcement announcement,
			Model model,String areaList) {

		BackstageAdminUser sessionAdminUser = this.getSessionAdminUser();
		log.info(sessionAdminUser.getArea());
		// 如果用户不是admin
		if (sessionAdminUser != null
				&& !sessionAdminUser.getLoginName().equals(superAdminLoginName)) {
			announcement.setArea(Integer.valueOf(sessionAdminUser.getArea()));
			announcementService.addAnnouncement(announcement);
			model.addAttribute("announcement", announcement);
			model.addAttribute("result", "success");
			return "announcementForm";
		}
		else {
			if(areaList!=null&&!areaList.equals("")&&areaList.indexOf(",")>0)
			{
				for(String item :areaList.split(","))
				{
					announcement.setArea(Integer.valueOf(item));
					announcementService.addAnnouncement(announcement);
				}
				model.addAttribute("announcement", announcement);
				model.addAttribute("result", "success");
				return "announcementForm";
			}else if(areaList!=null&&!areaList.equals("")){
				announcement.setArea(Integer.valueOf(areaList));
				announcementService.addAnnouncement(announcement);
				model.addAttribute("announcement", announcement);
				model.addAttribute("result", "success");
				return "announcementForm";
			}
			else {
				model.addAttribute("result", "failure");
				return "announcementForm";
			}
		}
	}

	/**
	 * 修改公告记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateAnnouncement/{announcementId}",
			method = RequestMethod.POST)
	public String updateAnnouncement(
			@PathVariable("announcementId") int announcementId, Model model,
			@ModelAttribute("announcement") Announcement announcement) {

		BackstageAdminUser sessionAdminUser = this.getSessionAdminUser();

		// 如果用户不是admin
		if (sessionAdminUser != null
				&& !sessionAdminUser.getLoginName().equals(superAdminLoginName)) {
			announcement.setArea(Integer.valueOf(sessionAdminUser.getArea()));
		}
		// 如果用户为admin，什么也不做
		announcementService.updateAnnouncement(announcement);
		model.addAttribute("announcement", announcement);
		model.addAttribute("result", "success");
		model.addAttribute("areas", this.getAreas());
		return "announcementForm";
	}

	/**
	 * 公告列表 load announcements
	 * 
	 * @return
	 */
	@RequestMapping(value = "/announcements/{page}/{areaId}")
	public String announcement(@PathVariable("page") int page, Model model,
			@PathVariable("areaId") int areaId) {

		if (areaId == 1) {
			areaId = 1034;
		}

		BackstageAdminUser sessionAdminUser = this.getSessionAdminUser();
		String area = "";
		if (sessionAdminUser != null
				&& !sessionAdminUser.getLoginName().equals(superAdminLoginName)) {
			area = sessionAdminUser.getArea();
		} else {
			area = String.valueOf(areaMapper.getAreaByHandleOrgId(areaId)
					.getAreaCode());
		}
		pageUtil.setCurrentPage(page);
		pageUtil.setTotalRecord(announcementService.getAnnouncementCount(area));
		int start = pageUtil.getPageRecord() * (pageUtil.getCurrentPage() - 1);
		List<Announcement> announcements = announcementService
				.loadAnnouncement(start, pageUtil.getPageRecord(), area);

		for (Announcement annmt : announcements) {
			String content = annmt.getDetailInfo().replaceAll("<[^>]+>", "")
					.replace("　", "").replaceAll("\\s*", "").trim();
			if (content != null && content.length() > 20) {
				content = content.trim().substring(0, 20) + "...";
			}

			annmt.setDetailInfo(content);
		}

		if (sessionAdminUser.getLoginName().equals(superAdminLoginName)) {
			model.addAttribute("areas", this.getAreas());// 增加参数
		} else {
			model.addAttribute("areas", "");
		}
		model.addAttribute("nowArea",areaId );
		model.addAttribute("pages", pageUtil);
		model.addAttribute("announcements", announcements);
		return "announcements";
	}

	@RequestMapping(value = "/deleteAnnouncement/{page}/{id}")
	public String deleteAnnouncement(@PathVariable("page") int page,
			@PathVariable("id") int id) {

		announcementService.deleteAnnouncement(id);
		return "forward:/announcements/1/" + page;
	}

}
