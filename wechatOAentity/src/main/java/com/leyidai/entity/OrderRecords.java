package com.leyidai.entity;

import java.sql.Timestamp;

public class OrderRecords {

	private String vCode; //验证码
	/***************** 2017-01-10 add  许云强   start*********************/
	  private int no_promise_num;   //爽约次数
	  private int checked_cancel_num; //审核后取消次数
	  
	  
	public int getNo_promise_num() {
		return no_promise_num;
	}

	public void setNo_promise_num(int no_promise_num) {
		this.no_promise_num = no_promise_num;
	}

	public int getChecked_cancel_num() {
		return checked_cancel_num;
	}

	public void setChecked_cancel_num(int checked_cancel_num) {
		this.checked_cancel_num = checked_cancel_num;
	}

	/***************** 2017-01-10 add  许云强   start*********************/
	
	private long id;
	private long uid;
	private String openid;
	private int transactOrgId;
	private String transactOrgName;
	private int transactTypeId;
	private String transactTypeName;
	private String transactDate;
	private int transactTimeId;
	private String transactTime;
	private String deadTime;
	private String name;
	private String idcard;
	private String mobile;
	private int noticeFlg;
	private String area;
	private String hourseNumber;
	private String hourseAddress;
	private Timestamp createTime;
	private int status;  //1:待审核，2：通过，3：审核前取消预约，4：办结，5：爽约，6：补证，8：审核后取消，9：失败 
	private int del;
	private String failReason;
	private int orderTaking; //取号顺序，硬件返回的
	private int numberOfTake; //取号次数
	
	private int usertype;//用户类型：0:个人; 1:企业
	private String obligeename;//利权人姓名
	private String obligeeidcard;//权利人身份证号
	private int isHaveHasagent;//否是有代理人: 0:是; 1:否

	private String IPAddress;
	
	private int examineStatus;
	
	public String getvCode() {
		return vCode;
	}

	public void setvCode(String vCode) {
		this.vCode = vCode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getTransactOrgId() {
		return transactOrgId;
	}

	public void setTransactOrgId(int transactOrgId) {
		this.transactOrgId = transactOrgId;
	}

	public String getTransactOrgName() {
		return transactOrgName;
	}

	public void setTransactOrgName(String transactOrgName) {
		this.transactOrgName = transactOrgName;
	}

	public int getTransactTypeId() {
		return transactTypeId;
	}

	public void setTransactTypeId(int transactTypeId) {
		this.transactTypeId = transactTypeId;
	}

	public String getTransactTypeName() {
		return transactTypeName;
	}

	public void setTransactTypeName(String transactTypeName) {
		this.transactTypeName = transactTypeName;
	}

	public String getTransactDate() {
		return transactDate;
	}

	public void setTransactDate(String transactDate) {
		this.transactDate = transactDate;
	}

	public int getTransactTimeId() {
		return transactTimeId;
	}

	public void setTransactTimeId(int transactTimeId) {
		this.transactTimeId = transactTimeId;
	}

	public String getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(String transactTime) {
		this.transactTime = transactTime;
	}

	public String getDeadTime() {
		return deadTime;
	}

	public void setDeadTime(String deadTime) {
		this.deadTime = deadTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getNoticeFlg() {
		return noticeFlg;
	}

	public void setNoticeFlg(int noticeFlg) {
		this.noticeFlg = noticeFlg;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public int getOrderTaking() {
		return orderTaking;
	}

	public void setOrderTaking(int orderTaking) {
		this.orderTaking = orderTaking;
	}

	public int getNumberOfTake() {
		return numberOfTake;
	}

	public void setNumberOfTake(int numberOfTake) {
		this.numberOfTake = numberOfTake;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public String getObligeename() {
		return obligeename;
	}

	public void setObligeename(String obligeename) {
		this.obligeename = obligeename;
	}

	public String getObligeeidcard() {
		return obligeeidcard;
	}

	public void setObligeeidcard(String obligeeidcard) {
		this.obligeeidcard = obligeeidcard;
	}

	public int getIsHaveHasagent() {
		return isHaveHasagent;
	}

	public void setIsHaveHasagent(int isHaveHasagent) {
		this.isHaveHasagent = isHaveHasagent;
	}

	public String getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getExamineStatus() {
		return examineStatus;
	}

	public void setExamineStatus(int examineStatus) {
		this.examineStatus = examineStatus;
	}



}
