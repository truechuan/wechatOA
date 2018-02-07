package com.leyidai.entity;

public class BookControl {
	private int id;
	private String date;
	private int timeId;
	private String time;
	private int areaId;
	private String areaName;
	private int members;
	private int bookMember;  //剩余预约数
	private Integer cancelbookMember;
	private String noticeName;  //子业务类型
	private int noticeId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getTimeId() {
		return timeId;
	}
	public void setTimeId(int timeId) {
		this.timeId = timeId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public int getMembers() {
		return members;
	}
	public void setMembers(int members) {
		this.members = members;
	}
	public int getBookMember() {
		return bookMember;
	}
	public void setBookMember(int bookMember) {
		this.bookMember = bookMember;
	}
	public String getNoticeName() {
		return noticeName;
	}
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public Integer getCancelbookMember() {
		return cancelbookMember;
	}
	public void setCancelbookMember(Integer cancelbookMember) {
		this.cancelbookMember = cancelbookMember;
	}

}
