package com.leyidai.entity;
/**
 * 用户安全信息
 * @author Administrator
 *
 */
public class UserInfo {
	private int userId;
	private String email;
	private String realName;
	private String identification;
	private String mobile;
	private String tranPassword;
	private String createTime;
	private String updateTime;
	private int checkIdentityNum;
	private int checkIdentityTodayNum;
	private String checkIdentityLastTime;
	
	public UserInfo(){
		
	}
	
	public UserInfo(int userId){
		this.userId = userId;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTranPassword() {
		return tranPassword;
	}
	public void setTranPassword(String tranPassword) {
		this.tranPassword = tranPassword;
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

	public int getCheckIdentityNum() {
		return checkIdentityNum;
	}

	public void setCheckIdentityNum(int checkIdentityNum) {
		this.checkIdentityNum = checkIdentityNum;
	}

	public int getCheckIdentityTodayNum() {
		return checkIdentityTodayNum;
	}

	public void setCheckIdentityTodayNum(int checkIdentityTodayNum) {
		this.checkIdentityTodayNum = checkIdentityTodayNum;
	}

	public String getCheckIdentityLastTime() {
		return checkIdentityLastTime;
	}

	public void setCheckIdentityLastTime(String checkIdentityLastTime) {
		this.checkIdentityLastTime = checkIdentityLastTime;
	}
	
	
}
