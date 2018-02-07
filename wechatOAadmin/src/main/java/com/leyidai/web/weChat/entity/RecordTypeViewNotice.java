package com.leyidai.web.weChat.entity;

public class RecordTypeViewNotice {
	private Integer numlimitid;
	private Integer area;
	private Integer transacttypeid;
	private String date;
	private Integer orderlimit;
	private Integer orderednum;
	private String noticename;
	private String time;
	private Integer cancelorderdNum;
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
	public Integer getTransacttypeid() {
		return transacttypeid;
	}
	public void setTransacttypeid(Integer transacttypeid) {
		this.transacttypeid = transacttypeid;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNoticename() {
		return noticename;
	}
	public void setNoticename(String noticename) {
		this.noticename = noticename;
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
}
