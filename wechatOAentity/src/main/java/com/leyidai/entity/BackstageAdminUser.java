package com.leyidai.entity;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 后台管理员
 * @author song
 *
 */
public class BackstageAdminUser {
	
	private int id;
	private String loginName;
	private String realName;
	private String phone;
	private String passWord;
	private String loginTimes;
	private String comment;
	private String userStatus;
	private String createTime;
	private String updateTime;
	private String area;
	
	private Set<Group> groups;
	
	private Map<ResourceCategory, Collection<Resource>> menues;

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(String loginTimes) {
		this.loginTimes = loginTimes;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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

	public Map<ResourceCategory, Collection<Resource>> getMenues() {
		return menues;
	}

	public void setMenues(Map<ResourceCategory, Collection<Resource>> menues) {
		this.menues = menues;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	
}
