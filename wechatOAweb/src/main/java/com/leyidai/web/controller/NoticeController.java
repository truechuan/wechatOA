package com.leyidai.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leyidai.entity.Dictionary;
import com.leyidai.entity.Notice;
import com.leyidai.web.service.DictionaryService;
import com.leyidai.web.service.NoticeService;
import com.leyidai.web.util.CategoryConstants;
import com.leyidai.web.util.ConstantVariable;
import com.leyidai.web.util.DateUtil;
import com.leyidai.web.util.PageUtil;

/**
 * 办件须知
 * @author Administrator
 *
 */
@Controller
public class NoticeController extends BaseController{
	@Autowired
	private PageUtil pageUtil;
	@Autowired
	private DateUtil dateUtil;
	
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private DictionaryService dictionaryService;
	

	/**
	 * 记录列表 load Notices
	 * 
	 * @return
	 */
	@RequestMapping(value = "/static/notices.html", method=RequestMethod.GET)
	public String getNotices(HttpSession session, Model model) {
		String handleOrg = ((String)session.getAttribute("area"));
		List<Dictionary> notices= dictionaryService.getDictionarysByCatetory(CategoryConstants.NOTICE, handleOrg);
		List<Notice> noticeTypes = new ArrayList<Notice>();
		for(Dictionary d: notices){
			noticeTypes = noticeService.getNoticesByType(d.getId());
			d.setEntityList(noticeTypes);
		}
		model.addAttribute("notices", notices);
		return "/notice/notices";
	}
	
	
	/**
	 * 显示在首页的登记种类列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/shiquNotices", method=RequestMethod.GET)
	public String getNoticesDefault( Model model) {
		String handleOrg = ConstantVariable.shiquOrgId;
		List<Dictionary> notices= dictionaryService.getDictionarysByCatetory(CategoryConstants.NOTICE, handleOrg);
		List<Notice> noticeTypes = new ArrayList<Notice>();
		for(Dictionary d: notices){
			noticeTypes = noticeService.getNoticesByType(d.getId());
			d.setEntityList(noticeTypes);
		}
		model.addAttribute("notices", notices);
		return "/notice/shiquNotices";
	}
	
	/**
	 * 显示在首页的登记种类详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/shiquNoticeInfo/{noticeId}", method=RequestMethod.GET)
	public String getNoticeInfo(@PathVariable("noticeId")int noticeId, Model model){
		String handleOrg = ConstantVariable.shiquOrgId;
		Notice notice = noticeService.getNoticeById(noticeId, handleOrg);
		model.addAttribute("notice", notice);
		return "/notice/noticeInfo";
	}
	
	@RequestMapping(value = "/static/{noticeId}/noticeInfo.html", method=RequestMethod.GET)
	public String getNoticeInfo(@PathVariable("noticeId")int noticeId, HttpSession session, Model model){
		String handleOrg = ((String)session.getAttribute("area")).replace("-", ",");
		Notice notice = noticeService.getNoticeById(noticeId, handleOrg);
		model.addAttribute("notice", notice);
		return "/notice/noticeInfo";
	}
	
	/**
	 * 办件须知入口页
	 * @return
	 */
	@RequestMapping(value = "/static/noticeEntry.html", method=RequestMethod.GET)
	public String noticeEntry(){
		
		return "/notice/noticeEntry";
	}
	
	/**
	 * 使用说明（办件指引 其中的一个分类）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/static/instructions.html", method=RequestMethod.GET)
	public String getInstructions(HttpSession session, Model model) {
		String handleOrg = ((String)session.getAttribute("area"));
		List<Dictionary> notices= dictionaryService.getDictionarysByCatetory(CategoryConstants.NOTICE, handleOrg);
		List<Notice> noticeTypes = new ArrayList<Notice>();
		for(Dictionary d: notices){
			if(d.getDictionaryValue().equals("使用说明")){
				noticeTypes = noticeService.getNoticesByType(d.getId());
				d.setEntityList(noticeTypes);
			}
		}
		
		
		model.addAttribute("notices", notices);
		return "/notice/instructions";
	}

}
