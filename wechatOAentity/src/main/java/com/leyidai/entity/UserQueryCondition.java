package com.leyidai.entity;

public class UserQueryCondition extends CommonQueryCondition{
    private String name;
    private String mobile;
    private String openid;
    private String idcard;
    private int status = 99;
    private int isForbidden=99;
    
	public int getIsForbidden() {
		return isForbidden;
	}
	public void setIsForbidden(int isForbidden) {
		this.isForbidden = isForbidden;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
    
}
