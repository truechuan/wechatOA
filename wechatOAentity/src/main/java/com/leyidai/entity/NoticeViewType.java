package com.leyidai.entity;


public class NoticeViewType {
	private int noticeViewTimeId;
	private int typeId;
	private int workTimeId;
	private int limitNum;
	public int getNoticeViewTimeId() {
		return noticeViewTimeId;
	}
	public void setNoticeViewTimeId(int noticeViewTimeId) {
		this.noticeViewTimeId = noticeViewTimeId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getWorkTimeId() {
		return workTimeId;
	}
	public void setWorkTimeId(int workTimeId) {
		this.workTimeId = workTimeId;
	}
	public int getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}
}