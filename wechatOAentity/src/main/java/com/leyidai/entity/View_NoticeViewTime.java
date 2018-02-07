package com.leyidai.entity;

/**
 * 视图  notice和worktime表以及类型限制时间关联表的视图
 * @author fty
 *
 */
public class View_NoticeViewTime{
	
    private int noticeViewTimeId;
    private String noticeName;
    private String startTime;
    private String endTime;
    private int areaId;
    private int limitNum;
    private int typeId;
    private int workTimeId;
    private String areaName;
    public int getNoticeViewTimeId() {
		return noticeViewTimeId;
	}
	public void setNoticeViewTimeId(int noticeViewTimeId) {
		this.noticeViewTimeId = noticeViewTimeId;
	}
	public String getNoticeName() {
		return noticeName;
	}
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public int getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
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
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	private int isDel;
	
    
}