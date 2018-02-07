package com.leyidai.web.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leyidai.entity.Answer;
import com.leyidai.entity.Dictionary;
import com.leyidai.entity.Question;
import com.leyidai.entity.Question.QuestionStatus;
import com.leyidai.entity.editor.QuestionStatusEditor;
import com.leyidai.web.service.DictionaryService;
import com.leyidai.web.service.QuestionAnswerService;
import com.leyidai.web.util.CategoryConstants;
import com.leyidai.web.util.DateUtil;
import com.leyidai.web.util.PageUtil;

@Controller
public class QuestionAnswerController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(QuestionAnswerController.class);
	
	@Autowired
	private PageUtil pageUtils;
	@Autowired
	private DateUtil dateUtil;
	
	@Autowired
	private QuestionAnswerService questionAnswerService;
	@Autowired
	private DictionaryService dictionaryService;

	@ModelAttribute("questionStatus")
	public QuestionStatus[] questionStatus() {

		return QuestionStatus.values();
	}
	
	@ModelAttribute("questionTypes")
	public List<Dictionary> loadDepartmentsNew(){
		
		List<Dictionary> questionTypes= dictionaryService.getDictionarysByCatetory(CategoryConstants.QUESTION, null);
		
		return questionTypes;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(QuestionStatus.class,new QuestionStatusEditor());
	}
	
	/**
	 * 问题列表
	 * 
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/static/questions/{questionTypeId}.html")
	public String questions(Model model, HttpSession session, 
			@PathVariable("questionTypeId") int questionTypeId) {
		String handleOrg = ((String)session.getAttribute("area")).replace("-", ",");
		List<Question> questions = questionAnswerService.getQuestions(questionTypeId, handleOrg);
		log.debug("questions list {}",  questions.size());
		model.addAttribute("questions", questions);

		return "/questionAnswer/questions";
	}
	
	/**
	 * 新增问题
	 * @param question
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/question/new", method = RequestMethod.POST)
	public String createDictionary(@ModelAttribute Question question,
			Model model) {

		boolean isQuestionExist = questionAnswerService.isQuestionExist(question.getQuestionName());
		if (isQuestionExist) {

			log.debug("question name {} exists",question.getQuestionName());
			model.addAttribute("result", "error");
			model.addAttribute("message", "该问题已存在！");
		} else {

			questionAnswerService.addQuestion(question);
			model.addAttribute("result", "success");
			log.debug("new question {} sucess", question.getQuestionName());
		}

		return "/questionAnswer/questions";
	}

	/**
	 * 回答详情
	 * @param answerId
	 * @param questionId
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/static/answer/{answerId}/{questionId}", method = RequestMethod.GET)
	public String anaswerInfo(
			@PathVariable("answerId") int answerId, Model model,
			@PathVariable("questionId")int questionId) {
		log.debug("load answer info {}", answerId);
		
		Question question = questionAnswerService.getQuestionById(questionId);
		Answer answer = questionAnswerService.getAnswerById(answerId);
		model.addAttribute("question", question);
		model.addAttribute("answer", answer);
		return "/questionAnswer/answerInfo";
	}

}
