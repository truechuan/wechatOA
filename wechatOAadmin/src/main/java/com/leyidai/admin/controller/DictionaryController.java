package com.leyidai.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leyidai.admin.service.DictionaryCategoryService;
import com.leyidai.admin.service.DictionaryService;
import com.leyidai.admin.util.PageUtils;
import com.leyidai.entity.Dictionary;
import com.leyidai.entity.DictionaryCategory;

/**
 * 字典管理
 * 
 * @author song
 *
 */
@Controller
public class DictionaryController extends BaseController {
	private static final Logger log = LoggerFactory
			.getLogger(DictionaryController.class);
	private final DictionaryService dictionaryService;

	@Autowired
	private PageUtils pageUtils;
	
	@Autowired
	private DictionaryCategoryService dictionaryCategoryService;
	@Autowired
	public DictionaryController(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
	
	@ModelAttribute("dictionaryCategorys")
	public List<DictionaryCategory> loadDictionaryCategorys(){
		
		List<DictionaryCategory> dictionaryCategorys = dictionaryCategoryService.getDictionaryCategorys();
		
		return dictionaryCategorys;
	}

	/**
	 * 初始化字典表单 - 新增字典值
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/dictionary/new", method = RequestMethod.GET)
	public String initCreationDictionaryForm(Model model) {
		Dictionary dictionary = new Dictionary();
		model.addAttribute(dictionary);

		return "dictionary/dictionaryForm";
	}

	/**
	 * 新增字典值
	 * 
	 * @param Dictionary
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/dictionary/new", method = RequestMethod.POST)
	public String createDictionary(@ModelAttribute Dictionary dictionary,
			Model model) {

		boolean isDictionaryExist = dictionaryService.isDictionaryExist(dictionary.getDictionaryValue(), dictionary.getDictionaryCategoryid());
		if (isDictionaryExist) {

			log.debug("dictionary name {} exists",dictionary.getDictionaryValue());
			model.addAttribute("result", "error");
			model.addAttribute("message", "字典值已存在！");
		} else {

			dictionaryService.addDictionary(dictionary);
			model.addAttribute("result", "success");
			log.debug("new dictionary {} sucess",dictionary.getDictionaryValue());
		}

		return "dictionary/dictionaryForm";
	}

	/**
	 * 初始更新字典表单 - 更新字典
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/dictionary/{id}/edit/{page}", method = RequestMethod.GET)
	public String initUpdateDictionaryForm(
			@PathVariable("id") int id,
			@PathVariable("page") int page, Model model) {
		log.debug("init update dictionary {} form", id);
		Dictionary dictionary = dictionaryService.getDictionaryById(id);
		model.addAttribute(dictionary);
		model.addAttribute(page);
		return "dictionary/dictionaryForm";
	}

	/**
	 * 更新字典值
	 * 
	 * @param dictionary
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/dictionary/{id}/edit/{page}", method = RequestMethod.POST)
	public String updateDictionary(@ModelAttribute Dictionary dictionary,
			@PathVariable("page") int page, Model model) {
		Dictionary oldDictionary = dictionaryService.getDictionaryById(dictionary.getId());
		Dictionary persistent = oldDictionary;
		persistent.setDictionaryCategoryid(dictionary.getDictionaryCategoryid());
		persistent.setDictionaryValue(dictionary.getDictionaryValue());
		dictionaryService.updateDictionary(persistent);
		model.addAttribute("result", "success");
		log.debug("update Dictionary {}-{} success", dictionary.getId());

		model.addAttribute(page);
		return "dictionary/dictionaryForm";
	}

	/**
	 * 字典值列表
	 * 
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/dictionarys/{page}")
	public String dictionarys(@PathVariable("page") int page, Model model) {

		pageUtils.setCurrentPage(page);
		pageUtils.setTotalRecord(dictionaryService.getTotalDictionaryRecord());

		int start = (pageUtils.getCurrentPage() - 1)
				* pageUtils.getPageRecord();
		List<Dictionary> dictionarys = dictionaryService.getDictionarys(start, pageUtils.getPageRecord());
		log.debug("dictionary list, page {} size {}",
				pageUtils.getCurrentPage(), dictionarys.size());
		model.addAttribute("dictionarys", dictionarys);
		model.addAttribute("pages", pageUtils);

		return "dictionary/dictionarys";
	}

	/**
	 * 删除字典值
	 * 
	 * @param id
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/dictionary/{id}/delete", method = RequestMethod.POST)
	public String deleteDictionary(
			@PathVariable("id") int id, int page) {
		log.debug("delete dictionary {}", id);

		dictionaryService.deleteDictionary(id);
		return "redirect:/dictionarys/" + page;
	}
}
