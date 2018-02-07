package com.leyidai.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leyidai.entity.Announcement;
import com.leyidai.entity.Dictionary;
import com.leyidai.entity.Notice;
import com.leyidai.entity.Question;
import com.leyidai.entity.Announcement.AnnouncementLanmu;
import com.leyidai.web.mapper.AnnouncementMapper;
import com.leyidai.web.mapper.QuestionAnswerMapper;
import com.leyidai.web.service.AnnouncementService;
import com.leyidai.web.service.DictionaryService;
import com.leyidai.web.service.NoticeService;
import com.leyidai.web.service.QuestionAnswerService;
import com.leyidai.web.util.CategoryConstants;
import com.leyidai.web.util.DateUtil;
import com.leyidai.web.util.DateUtil.DateFormat;

/**
 * 微信菜单链接内容
 * 
 * @author FTY First Time Edit 2017.09.19
 * 
 */
@Controller
public class MPController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(AnnouncementController.class);
	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private DateUtil dateUtil;
	// 微信菜单使用默认区县
	private String defaultOrg = "999";
	@Autowired
	private QuestionAnswerService questionAnswerService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private AnnouncementMapper announcementMapper;
	@Autowired
	private QuestionAnswerMapper questionAnswerMapper;
	
	/**
	 * 总的通知公告列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/MP/announcements.html")
	public String announcements(Model model) {
		List<Announcement> announcements=announcementMapper.loadAllByDefaultArea(AnnouncementLanmu.THE_ANNOUNCEMENT, defaultOrg);
		for (Announcement annmt : announcements) {
			annmt.setCreateTime(dateUtil.getFormatTime(annmt.getCreateTime(),
					DateFormat.YYYY_MM_DD));
		}
		model.addAttribute("announcements", announcements);
		return "announcements";
	}

	/**
	 * 总的问题列表
	 * 
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/MP/questions/{questionTypeId}.html")
	public String questions(Model model, HttpSession session,
			@PathVariable("questionTypeId") int questionTypeId) {
		List<Question> questions = questionAnswerMapper.getQuestionsByDefaultAreaId(defaultOrg);
		model.addAttribute("questions", questions);
		return "/questionAnswer/questions";
	}

	/**
	 * 总的登记要件列表 load Notices
	 * 
	 * @return
	 */
	@RequestMapping(value = "/MP/notices.html", method = RequestMethod.GET)
	public String getNotices(HttpSession session, Model model) {
		List<Dictionary> notices = dictionaryService.getDictionarysByCatetory(
				CategoryConstants.NOTICE, defaultOrg);
		List<Notice> noticeTypes = new ArrayList<Notice>();
		for (Dictionary d : notices) {
			noticeTypes = noticeService.getNoticesByType(d.getId());
			d.setEntityList(noticeTypes);
		}
		model.addAttribute("notices", notices);
		return "/notice/notices";
	}

	/**
	 * 总的使用说明（办件指引 其中的一个分类）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/MP/instructions.html", method = RequestMethod.GET)
	public String getInstructions(HttpSession session, Model model) {
		List<Dictionary> notices = dictionaryService.getDictionarysByCatetory(
				CategoryConstants.NOTICE, defaultOrg);
		List<Notice> noticeTypes = new ArrayList<Notice>();
		for (Dictionary d : notices) {
			if (d.getDictionaryValue().equals("使用说明")) {
				noticeTypes = noticeService.getNoticesByType(d.getId());
				d.setEntityList(noticeTypes);
			}
		}
		model.addAttribute("notices", notices);
		return "/notice/instructions";
	}

}
