package com.leyidai.entity;

public class Answer {
	
	 private int id;
	 private String theAnswer;
	 private int questionId;
	 private int answerUserId;
	 private int status; //暂时没用
	 private String createTime;
	 private String updateTime;
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTheAnswer() {
		return theAnswer;
	}
	public void setTheAnswer(String theAnswer) {
		this.theAnswer = theAnswer;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getAnswerUserId() {
		return answerUserId;
	}
	public void setAnswerUserId(int answerUserId) {
		this.answerUserId = answerUserId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	 
	 
}
