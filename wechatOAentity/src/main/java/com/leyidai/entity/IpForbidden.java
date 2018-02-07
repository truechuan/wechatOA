package com.leyidai.entity;

import java.sql.Timestamp;

public class IpForbidden {

	private int forbiddenId;
	private String ip;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	
	public int getForbiddenId() {
		return forbiddenId;
	}
	public void setForbiddenId(int forbiddenId) {
		this.forbiddenId = forbiddenId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
