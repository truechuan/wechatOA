package com.leyidai.entity;

import java.util.Set;

/**
 * 权限
 * @author Administrator
 *
 */
public class Role {
	private int roleId;
	private String name;
	private String description;
	private String createTime;
	private String updateTime;
	
	private Set<Resource> resources;
	
	@Override
	public int hashCode() {
		
		int hash = 7;  
	    hash = 13 * hash + this.roleId;  
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

		Role r = (Role) obj;

		if (r.getRoleId() != this.roleId) {
			return false;
		}

		boolean nameNotEqual = (this.name == null) ? (r.name != null) : !this.name.equals(r.name);
		if (nameNotEqual) {
			
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString(){
		
		return roleId + "-" + name + "-" + description;
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

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
