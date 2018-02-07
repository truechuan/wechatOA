package com.leyidai.entity;

public class RecordTypeViewNotice {

	private String date;
	private Integer orderlimit;
	private Integer orderednum;
	private String time;
	private Integer cancelorderdNum;
	private int isUseful;
	private String noticeName;
	private Integer numlimitid;
	private Integer area;
	private Integer transacttypeid;
	public Integer getTransacttypeid() {
		return transacttypeid;
	}
	public void setTransacttypeid(Integer transacttypeid) {
		this.transacttypeid = transacttypeid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getOrderlimit() {
		return orderlimit;
	}
	public void setOrderlimit(Integer orderlimit) {
		this.orderlimit = orderlimit;
	}
	public Integer getOrderednum() {
		return orderednum;
	}
	public void setOrderednum(Integer orderednum) {
		this.orderednum = orderednum;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getCancelorderdNum() {
		return cancelorderdNum;
	}
	public void setCancelorderdNum(Integer cancelorderdNum) {
		this.cancelorderdNum = cancelorderdNum;
	}
	public int getIsUseful() {
		return isUseful;
	}
	public void setIsUseful(int isUseful) {
		this.isUseful = isUseful;
	}
	public String getNoticeName() {
		return noticeName;
	}
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	
	public Integer getNumlimitid() {
		return numlimitid;
	}
	public void setNumlimitid(Integer numlimitid) {
		this.numlimitid = numlimitid;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
}
