package com.leyidai.entity;

/**
 * 交互message
 */
public class InteractMessage {
	private long id;
	private boolean result; //状态 true false
	private String subject; //内容
	private String text;  //备注

	public InteractMessage() {
	}

	public InteractMessage(long id, boolean result, String subject, String text) {
		this.id = id;
		this.result = result;
		this.subject = subject;
		this.text = text;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String toString() {
		return "Id:[" + this.getId() + "] Subject:[" + this.getSubject()
				+ "] Text:[" + this.getText() + "]";
	}
}
