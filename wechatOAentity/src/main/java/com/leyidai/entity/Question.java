package com.leyidai.entity;

public class Question {

	/**
	 * 状态
	 *
	 */
	public enum QuestionStatus{
		INVAID(0,"显示"),
		VALID(1,"不显示");
		
		public void setValue(int value) {
			this.value = value;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		private int value;
		private String description;
		QuestionStatus(int value, String description){
			this.value = value;
			this.description = description;
		}
		public int getValue(){
			return this.value;
		}
		public String getDescription(){
			return this.description;
		}
	}
	
	  private int id;
	  private String questionName;
	  private int askUserId;
	  private QuestionStatus status;
	  private int questionTypeId;
	  private String createTime;
	  private String updateTime;
	  private int answerCount = 0;  //答案条数
	  private int answerId;
	  private int area;
	  
	  
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public int getAskUserId() {
		return askUserId;
	}
	public void setAskUserId(int askUserId) {
		this.askUserId = askUserId;
	}
	public QuestionStatus getStatus() {
		return status;
	}
	public void setStatus(QuestionStatus status) {
		this.status = status;
	}
	public int getQuestionTypeId() {
		return questionTypeId;
	}
	public void setQuestionTypeId(int questionTypeId) {
		this.questionTypeId = questionTypeId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public int getAnswerCount() {
		return answerCount;
	}
	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	
}
