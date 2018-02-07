package com.leyidai.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.leyidai.admin.mapper.AreaMapper;
import com.leyidai.admin.service.DictionaryService;
import com.leyidai.admin.service.QuestionAnswerService;
import com.leyidai.admin.util.CategoryConstants;
import com.leyidai.admin.util.DateUtil;
import com.leyidai.admin.util.PageUtils;
import com.leyidai.entity.Answer;
import com.leyidai.entity.BackstageAdminUser;
import com.leyidai.entity.Dictionary;
import com.leyidai.entity.Question;
import com.leyidai.entity.Question.QuestionStatus;
import com.leyidai.entity.editor.QuestionStatusEditor;

@Controller
public class QuestionAnswerController extends BaseController {
	private static final Logger log = LoggerFactory
			.getLogger(QuestionAnswerController.class);

	@Autowired
	private PageUtils pageUtils;
	@Autowired
	private DateUtil dateUtil;
	@Autowired
	private AreaMapper areaMapper;
	@Value("#{systemConfigProperties[superAdminLoginName]}")
	private String superAdminLoginName;

	@Autowired
	private QuestionAnswerService questionAnswerService;
	@Autowired
	private DictionaryService dictionaryService;

	@ModelAttribute("questionStatus")
	public QuestionStatus[] questionStatus() {

		return QuestionStatus.values();
	}

	@ModelAttribute("questionTypes")
	public List<Dictionary> loadDepartmentsNew() {

		List<Dictionary> questionTypes = dictionaryService
				.getDictionarysByCatetory(CategoryConstants.QUESTION, 0);

		return questionTypes;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(QuestionStatus.class,
				new QuestionStatusEditor());
	}

	/**
	 * 初始化添加问题
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/question/new", method = RequestMethod.GET)
	public String initQuestion(Model model) {
		BackstageAdminUser sessionAdminUser = this.getSessionAdminUser();
		String flag = "true";
		if (sessionAdminUser != null
				&& !sessionAdminUser.getLoginName().equals(superAdminLoginName)) {
			flag = "false";
		}
		model.addAttribute("isMoreManger", flag);
		model.addAttribute("areas", this.getAreas());
		model.addAttribute("question", new Question());
		return "/questionAnswer/questionForm";
	}

	/**
	 * 新增问题
	 * 
	 * @param question
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/question/new", method = RequestMethod.POST)
	public String createDictionary(@ModelAttribute Question question,
			Model model) {

		BackstageAdminUser sessionAdminUser = this.getSessionAdminUser();
		// 判断是否是admin
		boolean isNotAdmin = sessionAdminUser != null
				&& !sessionAdminUser.getLoginName().equals(superAdminLoginName);
		if (isNotAdmin) {
			// 如果用户不是admin，就填入Session的area  // 如果是admin，就直接使用model中的area，不再另行设置
			question.setArea(Integer.valueOf(sessionAdminUser.getArea()));
		}
		
		boolean isQuestionExist = questionAnswerService.isQuestionExist(
				question.getQuestionName(), question.getArea());
		if (isQuestionExist) {

			log.debug("question name {} exists", question.getQuestionName());
			model.addAttribute("result", "error");
			model.addAttribute("message", "该问题已存在！");
		} else {
			questionAnswerService.addQuestion(question);
			model.addAttribute("result", "success");
			log.debug("new question {} sucess", question.getQuestionName());
		}

		return "/questionAnswer/questionForm";
	}

	/**
	 * 初始化更新问题
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/question/{questionId}/edit/{page}",
			method = RequestMethod.GET)
	public String initUpdateQuestionyForm(
			@PathVariable("questionId") int questionId,
			@PathVariable("page") int page, Model model) {
		log.debug("init update question {} form", questionId);

		BackstageAdminUser sessionAdminUser = this.getSessionAdminUser();
		String flag = "true";
		if (sessionAdminUser != null
				&& !sessionAdminUser.getLoginName().equals(superAdminLoginName)) {
			flag = "false";
		}
		model.addAttribute("isMoreManger", flag);
		model.addAttribute("areas", this.getAreas());
		Question question = questionAnswerService.getQuestionById(questionId);
		model.addAttribute(question);
		model.addAttribute(page);
		return "/questionAnswer/questionForm";
	}

	/**
	 * 修改问题
	 * 
	 * @param question
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/question/{id}/edit/{page}",
			method = RequestMethod.POST)
	public String updateQuestion(@ModelAttribute Question question,
			@PathVariable("page") int page, Model model) {

		BackstageAdminUser sessionAdminUser = this.getSessionAdminUser();
		// 如果用户不是admin，如果用户为admin，什么也不做
		if (sessionAdminUser != null
				&& !sessionAdminUser.getLoginName().equals(superAdminLoginName)) {
			question.setArea(Integer.valueOf(sessionAdminUser.getArea()));
		}
		questionAnswerService.updateQuestion(question);
		model.addAttribute("result", "success");
		log.debug("update question {}-{} success", question.getId());

		model.addAttribute(page);
		return "/questionAnswer/questionForm";
	}

	/**
	 * 问题列表
	 * 
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/questions/{page}/{areaId}")
	public String questions(@PathVariable("page") int page, Model model,@PathVariable("areaId") int areaId) {

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

		pageUtils.setCurrentPage(page);
		pageUtils.setTotalRecord(questionAnswerService.getQuestionCount(area));

		int start = (pageUtils.getCurrentPage() - 1)
				* pageUtils.getPageRecord();
		List<Question> questions = questionAnswerService.getQuestions(start,
				pageUtils.getPageRecord(), area);
		log.debug("questions list, page {} size {}",
				pageUtils.getCurrentPage(), questions.size());
		if (sessionAdminUser.getLoginName().equals(superAdminLoginName)) {
			model.addAttribute("areas", this.getAreas());// 增加参数
		} else {
			model.addAttribute("areas", "");
		}
		model.addAttribute("questions", questions);
		model.addAttribute("nowArea",areaId );
		model.addAttribute("pages", pageUtils);

		return "/questionAnswer/questions";
	}

	/**
	 * 初始化添加回答
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/answer/new/{questionId}",
			method = RequestMethod.GET)
	public String initAnswer(Model model,
			@PathVariable("questionId") int questionId) {

		Question question = questionAnswerService.getQuestionById(questionId);

		model.addAttribute("answer", new Answer());
		model.addAttribute("question", question);
		return "/questionAnswer/answerForm";
	}

	/**
	 * 新增回答
	 * 
	 * @param answer
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/answer/new/{questionId}",
			method = RequestMethod.POST)
	public String createAnswer(@ModelAttribute Answer answer,
			@PathVariable("questionId") int questionId, Model model) {

		questionAnswerService.addAnswer(answer);
		model.addAttribute("result", "success");
		log.debug("new answer {} sucess", answer.getId());

		return "/questionAnswer/answerForm";
	}

	/**
	 * 初始化更新回答
	 * 
	 * @param answerId
	 * @param questionId
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/answer/{answerId}/{questionId}/edit/{page}",
			method = RequestMethod.GET)
	public String initUpdateAnaswerForm(@PathVariable("answerId") int answerId,
			@PathVariable("questionId") int questionId,
			@PathVariable("page") int page, Model model) {
		log.debug("init update answer {} form", answerId);

		Question question = questionAnswerService.getQuestionById(questionId);
		Answer answer = questionAnswerService.getAnswerById(answerId);
		model.addAttribute(question);
		model.addAttribute(answer);
		model.addAttribute(page);
		return "/questionAnswer/answerForm";
	}

	/**
	 * 修改回答
	 * 
	 * @param question
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/answer/{answerId}/{questionId}/edit/{page}",
			method = RequestMethod.POST)
	public String updateAnswer(@ModelAttribute Answer answer,
			@PathVariable("questionId") int questionId,
			@PathVariable("page") int page, Model model) {

		Question question = questionAnswerService.getQuestionById(questionId);
		questionAnswerService.updateAnswer(answer);
		model.addAttribute("result", "success");
		log.debug("update answer {} success", answer.getId());

		model.addAttribute("isMoreManger", this.isMoreManger());
		model.addAttribute(page);
		model.addAttribute(question);

		return "/questionAnswer/answerForm";
	}

}
