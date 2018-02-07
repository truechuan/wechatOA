package com.leyidai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.leyidai.entity.Answer;
import com.leyidai.entity.Question;

public interface QuestionAnswerMapper {

	@Insert("insert into p_question(`questionName`, `askUserId`, `status`, `questionTypeId`) values"
			+ "(#{questionName}, #{askUserId}, "
			+ "#{status, typeHandler=com.leyidai.entity.enumtype.handler.QuestionStatusHandler}, "
			+ "#{questionTypeId})")
	@SelectKey(statement="SELECT last_insert_id() as id", keyProperty="id", before=false, resultType=Integer.class)
	public void addQuestion(Question question);
	
	@Select("select * from p_question where questionName=#{questionName} and status=0")
	@Results({
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.QuestionStatusHandler.class)
	})
	public Question isQuestionExist(String questionName);
	
	@Select("select count(*) from p_question")
	public int getQuestionCount();
	
	@Select("select * from p_question where questionTypeId=#{questionTypeId} and status=0")
	@Results({
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.QuestionStatusHandler.class)
	})
	public List<Question> getQuestions(@Param("questionTypeId")int questionTypeId);
	
	@Select("select * from p_question where status=0 limit #{size}")
	@Results({
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.QuestionStatusHandler.class)
	})
	public List<Question> getQuestionsBySize(@Param("size")int size);
	
	@Select("select * from p_answer where questionId=#{questionId}")
	public List<Answer> getAnswersByQuestionId(int questionId);
	
	@Select("select count(*) from p_answer where questionId=#{questionId}")
	public int getQuestionAnswerCount(int questionId);
	
	@Select("select * from p_question where id=#{id} and status=0")
	@Results({
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.QuestionStatusHandler.class)
	})
	public Question getQuestionById(int id);
	
	@Select("select * from p_answer where id=#{id}")
	public Answer getAnswerById(int id);
	

	
	@Select("select * from p_question where questionTypeId=#{questionTypeId} and status=0 and area in (${area})")
	@Results({
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.QuestionStatusHandler.class)
	})
	public List<Question> getQuestionsAndAreaId(@Param("questionTypeId")int questionTypeId, @Param("area")String area);
	
	@Select("select * from p_question where area in (${area}) and status=0 limit #{size}")
	@Results({
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.QuestionStatusHandler.class)
	})
	public List<Question> getQuestionsBySizeAndAreaId(@Param("size")int size, @Param("area")String area);
	
	@Select("select * from p_question where area in (${area}) and status=0")
	@Results({
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.QuestionStatusHandler.class)
	})
	public List<Question> getQuestionsByDefaultAreaId(@Param("area")String area);
}
