package com.leyidai.entity;

import java.util.Set;

/**
 * 角色
 * @author Administrator
 *
 */
public class Group {
	private int groupId;
	private String name;
	private String description;
	private boolean enable=true;
	private String createTime;
	private String updateTime;
	
	private Set<Role> roles;
	
	@Override
	public int hashCode() {
		
		int hash = 7;  
	    hash = 13 * hash + this.groupId;  
	    hash = 13 * hash + (this.name != null ? this.name.hashCode() : 0);  
	    return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
			return false;
		}

		if (this.getClass() != obj.getClass()) {
			return false;
		}

		Group g = (Group) obj;

		if (g.getGroupId() != this.groupId) {
			return false;
		}

		boolean nameNotEqual = (this.name == null) ? (g.name != null) : !this.name.equals(g.name);
		if (nameNotEqual) {
			
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString(){
		
		return groupId + "-" + name + "-" + description + "-" + enable;
	}
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
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
	public void setUpdateTime(String updatetime) {
		this.updateTime = updatetime;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
