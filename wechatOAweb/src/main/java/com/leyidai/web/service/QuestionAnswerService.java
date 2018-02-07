package com.leyidai.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leyidai.entity.Answer;
import com.leyidai.entity.Area;
import com.leyidai.entity.Question;
import com.leyidai.web.mapper.AreaMapper;
import com.leyidai.web.mapper.QuestionAnswerMapper;
import com.leyidai.web.util.DateUtil;
import com.leyidai.web.util.DateUtil.DateFormat;

@Service
public class QuestionAnswerService {

	@Autowired
	private QuestionAnswerMapper questionAnswerMapper;
	@Autowired
	private DateUtil dateUtil;
	@Autowired
	private AreaMapper areaMapper;
	
	@Transactional
	public void addQuestion(Question question){
		questionAnswerMapper.addQuestion(question);
	}
	
	@Transactional(readOnly=true)
	public List<Answer> getAnswersByQuestionId(int questionId){
		return questionAnswerMapper.getAnswersByQuestionId(questionId);
	}
	
	@Transactional(readOnly=true)
	public boolean isQuestionExist(String questionName){
		return questionAnswerMapper.isQuestionExist(questionName)!=null;
	}
	
	@Transactional(readOnly=true)
	public int getQuestionCount(){
		return questionAnswerMapper.getQuestionCount();
	}
	
	@Transactional(readOnly=true)
	public List<Question> getQuestions(int questionTypeId, String handleOrgId){
		
		List<Area> arrArea = areaMapper.getAreasByOrgId(handleOrgId);
		String arrAreaId = "";
		for(Area item : arrArea){
			arrAreaId += "," + item.getAreaCode();
		}
		
		List<Question> questions = new ArrayList<Question>();
		if(StringUtils.isBlank(arrAreaId)){
			if(questionTypeId == 0){
				questions = questionAnswerMapper.getQuestionsBySize(100);
			}else{
				questions = questionAnswerMapper.getQuestions(questionTypeId);
			}
		}else{
			if(questionTypeId == 0){
				questions = questionAnswerMapper.getQuestionsBySizeAndAreaId(100, arrAreaId.substring(1));
			}else{
				questions = questionAnswerMapper.getQuestionsAndAreaId(questionTypeId, arrAreaId.substring(1));
			}
		}
		
		for(Question q: questions){
			q.setAnswerCount(questionAnswerMapper.getQuestionAnswerCount(q.getId()));
			if(q.getAnswerCount() > 0){
				List<Answer> answer = questionAnswerMapper.getAnswersByQuestionId(q.getId());
				q.setAnswerId(answer.get(0).getId());
			}
			q.setCreateTime(dateUtil.getFormatTime(q.getCreateTime(), DateFormat.YYYY_MM_DD));
			
        	if(q.getQuestionName() != null && q.getQuestionName().length() > 11){
        		q.setQuestionName(q.getQuestionName().trim().substring(0, 11)+"...");
        	}
		}
		return questions;
	}
	
	@Transactional(readOnly=true)
	public Question getQuestionById(int questionId){
		return questionAnswerMapper.getQuestionById(questionId);
	}
	
	@Transactional(readOnly=true)
	public Answer getAnswerById(int answerId){
		return questionAnswerMapper.getAnswerById(answerId);
	}
}
