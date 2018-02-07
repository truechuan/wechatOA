package com.leyidai.admin.util;

public class excelOrder {
	private long id;
	private String transactDate;
	private String transactTime;
	private String name;
	private String transactTypeName;
	private String mobile;
	private String idcard;
	private String hourseNumber;
	private String hourseAddress;
	private int status;  //1:待审核，2：通过，3：取消预约，4：办结，5：爽约，6：补证，9：失败 
	public String getTransactDate() {
		return transactDate;
	}
	public void setTransactDate(String transactDate) {
		this.transactDate = transactDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTransactTime() {
		return transactTime;
	}
	public void setTransactTime(String transactTime) {
		this.transactTime = transactTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTransactTypeName() {
		return transactTypeName;
	}
	public void setTransactTypeName(String transactTypeName) {
		this.transactTypeName = transactTypeName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getHourseNumber() {
		return hourseNumber;
	}
	public void setHourseNumber(String hourseNumber) {
		this.hourseNumber = hourseNumber;
	}
	public String getHourseAddress() {
		return hourseAddress;
	}
	public void setHourseAddress(String hourseAddress) {
		this.hourseAddress = hourseAddress;
	}
}
