package com.leyidai.entity;

import java.sql.Timestamp;

public class User {
	  private long id ;
	  private String openid ;
	  private String name ;
	  private long mobile ;
	  private int idcardType;
	  private String idcard ;
	  private String idcardmd5;
	  private String idcardImgUrl ;
	  private String idcardImgUrl2 ;
	  private Timestamp createTime ;
	  private int type; //1：个人； 2：企业
	  private int status;   // 0：待审核； 1：审核通过； 2：未通过
	  private String reason; //失败原因
	  private Timestamp lastLoginTime;
	  private long loginTimes;
	  private String updateTime;
	  private int isForbidden;//是否是黑名单
	  
	  /***************** 2017-01-09 add  许云强   start*********************/
	  private int no_promise_num;   //爽约次数
	  private int checked_cancel_num; //审核后取消次数
	  private Timestamp stoptime_start;     //封停时间
	  private Timestamp stoptime_end;		  //解封时间
	  private int stoptime_status; //封停状态    0：正常， 1：封停
	  
	  
	  
	public int getStoptime_status() {
		return stoptime_status;
	}
	public void setStoptime_status(int stoptime_status) {
		this.stoptime_status = stoptime_status;
	}
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
	public Timestamp getStoptime_start() {
		return stoptime_start;
	}
	public void setStoptime_start(Timestamp stoptime_start) {
		this.stoptime_start = stoptime_start;
	}
	public Timestamp getStoptime_end() {
		return stoptime_end;
	}
	public void setStoptime_end(Timestamp stoptime_end) {
		this.stoptime_end = stoptime_end;
	}
	/***************** 2017-01-09 add  许云强   end  *********************/
	  
	  
	  
	  
	  
	public int getIsForbidden() {
		return isForbidden;
	}
	public void setIsForbidden(int isForbidden) {
		this.isForbidden = isForbidden;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public int getIdcardType() {
		return idcardType;
	}
	public void setIdcardType(int idcardType) {
		this.idcardType = idcardType;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getIdcardImgUrl() {
		return idcardImgUrl;
	}
	public void setIdcardImgUrl(String idcardImgUrl) {
		this.idcardImgUrl = idcardImgUrl;
	}
	public String getIdcardImgUrl2() {
		return idcardImgUrl2;
	}
	public void setIdcardImgUrl2(String idcardImgUrl2) {
		this.idcardImgUrl2 = idcardImgUrl2;
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
	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public long getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(long loginTimes) {
		this.loginTimes = loginTimes;
	}
	public String getIdcardmd5() {
		return idcardmd5;
	}
	public void setIdcardmd5(String idcardmd5) {
		this.idcardmd5 = idcardmd5;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
