package com.leyidai.entity.enumtype.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;

import com.leyidai.entity.Question.QuestionStatus;

public class QuestionStatusHandler extends LYDBaseEnumTypeHandler<QuestionStatus>{
	
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			QuestionStatus questionStatus, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, questionStatus.getValue());
	}

	public QuestionStatus getEnumByValue(int value){
		QuestionStatus[] questionStatuses = QuestionStatus.class.getEnumConstants();
		for(QuestionStatus questionStatus: questionStatuses){
			if(questionStatus.getValue() == value){
				return questionStatus;
			}
		}
		
		throw new IllegalArgumentException("unknow enum value {" + value +"} for QuestionStatus.");
	}
	
}
