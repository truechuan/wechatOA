package com.leyidai.entity.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leyidai.entity.Question.QuestionStatus;

public class QuestionStatusEditor extends PropertyEditorSupport {
	private static Logger log = LoggerFactory.getLogger(QuestionStatusEditor.class);
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		log.debug("question status editor for resource {}", text);
		
		if(StringUtils.isNotEmpty(text)){
			QuestionStatus[] questionStatuses = QuestionStatus.class.getEnumConstants();
			for(QuestionStatus questionStatus: questionStatuses){
				if(questionStatus.getValue() == Integer.valueOf(text)){
					setValue(questionStatus);
					break;
				}
			}
		} else{
			setValue(null);
		}
	}
	}
