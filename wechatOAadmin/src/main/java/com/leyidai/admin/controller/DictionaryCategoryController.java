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
import com.leyidai.admin.util.PageUtils;
import com.leyidai.entity.DictionaryCategory;

/**
 * 字典分类管理
 * 
 * @author song
 *
 */
@Controller
public class DictionaryCategoryController extends BaseController {
	private static final Logger log = LoggerFactory
			.getLogger(DictionaryCategoryController.class);
	private final DictionaryCategoryService dictionaryCategoryService;

	@Autowired
	private PageUtils pageUtils;

	@Autowired
	public DictionaryCategoryController(DictionaryCategoryService dictionaryCategoryService) {
		this.dictionaryCategoryService = dictionaryCategoryService;
	}

	/**
	 * 初始化分类表单 - 新增分类
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/category/new", method = RequestMethod.GET)
	public String initCreationDictionaryCategoryForm(Model model) {

		DictionaryCategory dictionaryCategory = new DictionaryCategory();
		model.addAttribute(dictionaryCategory);
		model.addAttribute("areas", this.getAreas());

		return "dictionary/categoryForm";
	}

	/**
	 * 新增分类
	 * 
	 * @param DictionaryCategory
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/category/new", method = RequestMethod.POST)
	public String createDictionaryCategory(@ModelAttribute DictionaryCategory dictionaryCategory,
			Model model) {
		boolean isDictionaryCategoryExist = dictionaryCategoryService.isDictionaryCategoryExist
				(dictionaryCategory.getCategoryCode(), dictionaryCategory.getCategoryDesc(), dictionaryCategory.getHandleOrgId());
		if (isDictionaryCategoryExist) {

			log.debug("dictionaryCategory name {} exists",
					dictionaryCategory.getCategoryCode(), dictionaryCategory.getCategoryDesc());
			model.addAttribute("result", "error");
			model.addAttribute("message", "分类已存在！");
		} else {

			dictionaryCategoryService.addDictionaryCategory(dictionaryCategory);
			model.addAttribute("result", "success");
			log.debug("new dictionaryCategory {} sucess",
					dictionaryCategory.getCategoryCode(), dictionaryCategory.getCategoryDesc());
		}

		model.addAttribute("areas", this.getAreas());
		
		return "dictionary/categoryForm";
	}

	/**
	 * 初始更新分类表单 - 更新分类
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/category/{id}/edit/{page}", method = RequestMethod.GET)
	public String initUpdateDictionaryCategoryForm(
			@PathVariable("id") int id,
			@PathVariable("page") int page, Model model) {
		log.debug("init update dictionaryCategory {} form", id);

		DictionaryCategory dictionaryCategory = dictionaryCategoryService.getDictionaryCategoryById(id);
		model.addAttribute(dictionaryCategory);
		model.addAttribute(page);
		model.addAttribute("areas", this.getAreas());
		
		return "dictionary/categoryForm";
	}

	/**
	 * 更新分类
	 * 
	 * @param dictionaryCategory
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/category/{id}/edit/{page}", method = RequestMethod.POST)
	public String updateDictionaryCategory(@ModelAttribute DictionaryCategory dictionaryCategory,
			@PathVariable("page") int page, Model model) {
		DictionaryCategory oldDictionaryCategory = dictionaryCategoryService.getDictionaryCategoryById(dictionaryCategory.getId());
		DictionaryCategory persistent = oldDictionaryCategory;
		persistent.setCategoryCode(dictionaryCategory.getCategoryCode());
		persistent.setCategoryDesc(dictionaryCategory.getCategoryDesc());
		persistent.setHandleOrgId(dictionaryCategory.getHandleOrgId());
		dictionaryCategoryService.updateDictionaryCategory(persistent);
		model.addAttribute("result", "success");
		log.debug("update DictionaryCategory {}-{} success", dictionaryCategory.getId(), dictionaryCategory.getHandleOrgId());

		model.addAttribute(page);
		model.addAttribute("areas", this.getAreas());
		return "dictionary/categoryForm";
	}

	/**
	 * 分类列表
	 * 
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/categorys/{page}")
	public String dictionaryCategorys(@PathVariable("page") int page, Model model) {

		pageUtils.setCurrentPage(page);
		pageUtils.setTotalRecord(dictionaryCategoryService.getTotalDictionaryCategoryRecord());

		int start = (pageUtils.getCurrentPage() - 1)
				* pageUtils.getPageRecord();
		List<DictionaryCategory> dictionaryCategorys = dictionaryCategoryService.getDictionaryCategorys(start, pageUtils.getPageRecord());
		log.debug("dictionaryCategory list, page {} size {}",
				pageUtils.getCurrentPage(), dictionaryCategorys.size());
		model.addAttribute("dictionaryCategorys", dictionaryCategorys);
		model.addAttribute("pages", pageUtils);

		return "dictionary/categorys";
	}

	/**
	 * 删除分类
	 * 
	 * @param id
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/category/{id}/delete", method = RequestMethod.POST)
	public String deleteDictionaryCategory(
			@PathVariable("id") int id, int page) {
		log.debug("delete dictionaryCategory {}", id);

		dictionaryCategoryService.deleteDictionaryCategory(id);
		return "redirect:/categorys/" + page;
	}
}
