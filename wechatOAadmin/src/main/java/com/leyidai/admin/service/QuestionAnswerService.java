package com.leyidai.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.leyidai.admin.mapper.QuestionAnswerMapper;
import com.leyidai.entity.Answer;
import com.leyidai.entity.Question;

@Service
public class QuestionAnswerService {

	@Autowired
	private QuestionAnswerMapper questionAnswerMapper;
	
	@Transactional
	public void addQuestion(Question question){
		questionAnswerMapper.addQuestion(question);
	}
	
	@Transactional
	public void updateQuestion(Question question){
		questionAnswerMapper.updateQuestion(question);
	}
	
	@Transactional
	public void addAnswer(Answer answer){
		questionAnswerMapper.addAnswer(answer);
	}
	
	@Transactional
	public void updateAnswer(Answer answer){
		questionAnswerMapper.updateAnswer(answer);
	}
	
	/**
	 * 根据typeId查询所有问题
	 * @param questionTypeId
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Question> getQuestionsByTypeId(int questionTypeId){
		return questionAnswerMapper.getQuestionsByTypeId(questionTypeId);
	}
	
	@Transactional(readOnly=true)
	public Question getQuestionById(int questionId){
		return questionAnswerMapper.getQuestionById(questionId);
	}
	
	@Transactional(readOnly=true)
	public List<Answer> getAnswersByQuestionId(int questionId){
		return questionAnswerMapper.getAnswersByQuestionId(questionId);
	}
	
	@Transactional(readOnly=true)
	public boolean isQuestionExist(String questionName,Integer area){
		return questionAnswerMapper.isQuestionExist(questionName,area)!=null;
	}
	
	@Transactional(readOnly=true)
	public int getQuestionCount(String area){
		
		if(StringUtils.isEmpty(area)){
			return questionAnswerMapper.getQuestionCount();
		}
		
		return questionAnswerMapper.getQuestionCountByArea(area);
	}
	
	@Transactional(readOnly=true)
	public List<Question> getQuestions(int start, int size, String area){
		
		if(start < 0){
			start = 0;
		}
		
		List<Question> questions = new ArrayList<Question>();
		if(StringUtils.isEmpty(area)){
			questions = questionAnswerMapper.getQuestions(start, size);
		}else{
			questions = questionAnswerMapper.getQuestionsByArea(start, size, area);
		}
		
		for(Question q: questions){
			q.setAnswerCount(questionAnswerMapper.getQuestionAnswerCount(q.getId()));
			if(q.getAnswerCount() > 0){
				List<Answer> answer = questionAnswerMapper.getAnswersByQuestionId(q.getId());
				q.setAnswerId(answer.get(0).getId());
			}
		}
		return questions;
	}
	
	@Transactional(readOnly=true)
	public Answer getAnswerById(int answerId){
		return questionAnswerMapper.getAnswerById(answerId);
	}
	
}
