package com.leyidai.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.leyidai.entity.Answer;
import com.leyidai.entity.Question;

public interface QuestionAnswerMapper {

	@Insert("insert into p_question(`questionName`, `askUserId`, `status`, `questionTypeId`, `area`) values"
			+ "(#{questionName}, #{askUserId}, "
			+ "#{status, typeHandler=com.leyidai.entity.enumtype.handler.QuestionStatusHandler}, "
			+ "#{questionTypeId}, #{area})")
	@SelectKey(statement="SELECT last_insert_id() as id", keyProperty="id", before=false, resultType=Integer.class)
	public void addQuestion(Question question);
	
	@Update("update p_question set "
			+ "`status`=#{status, typeHandler=com.leyidai.entity.enumtype.handler.QuestionStatusHandler}, "
			+ "`questionName`=#{questionName}, `askUserId`=#{askUserId}, `updateTime`=#{updateTime}, "
			+ "`questionTypeId`=#{questionTypeId}, `area`=#{area} where id=#{id}")
	public void updateQuestion(Question question);
	
	@Insert("insert into p_answer(`theAnswer`, `questionId`, `answerUserId`, `status`) values"
			+ "(#{theAnswer}, #{questionId}, #{answerUserId}, #{status})")
	@SelectKey(statement="SELECT last_insert_id() as id", keyProperty="id", before=false, resultType=Integer.class)
	public void addAnswer(Answer answer);
	
	@Update("update p_answer set "
			+ "`theAnswer`=#{theAnswer}, `updateTime`=#{updateTime}, "
			+ "`answerUserId`=#{answerUserId}, `status`=#{status} where id=#{id}")
	public void updateAnswer(Answer answer);
	
	@Select("select * from p_question where questionTypeId=#{questionTypeId}")
	@Results({
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.QuestionStatusHandler.class)
	})
	public List<Question> getQuestionsByTypeId(int questionTypeId);
	
	@Select("select * from p_answer where questionId=#{questionId}")
	public List<Answer> getAnswersByQuestionId(int questionId);
	
	@Select("select * from p_question where questionName=#{questionName} and area=#{area}")
	@Results({
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.QuestionStatusHandler.class)
	})
	public Question isQuestionExist(@Param(value="questionName")String questionName,@Param(value="area")Integer area);
	
	@Select("select * from p_question where id=#{id}")
	@Results({
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.QuestionStatusHandler.class)
	})
	public Question getQuestionById(int id);
	
	@Select("select count(*) from p_question")
	public int getQuestionCount();
	
	@Select("select count(*) from p_question where area in (#{area})")
	public int getQuestionCountByArea(String area);
	
	@Select("select * from p_question limit #{start}, #{size}")
	@Results({
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.QuestionStatusHandler.class)
	})
	public List<Question> getQuestions(@Param("start")int start, @Param("size")int size);
	
	@Select("select * from p_question where area in (#{area}) limit #{start}, #{size}")
	@Results({
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.QuestionStatusHandler.class)
	})
	public List<Question> getQuestionsByArea(@Param("start")int start, @Param("size")int size, @Param("area")String area);
	
	@Select("select * from p_answer where id=#{id}")
	public Answer getAnswerById(int id);
	
	@Select("select count(*) from p_answer where questionId=#{questionId}")
	public int getQuestionAnswerCount(int questionId);
}
