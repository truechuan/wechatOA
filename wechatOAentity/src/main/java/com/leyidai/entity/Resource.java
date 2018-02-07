package com.leyidai.entity;

import java.util.Set;

/**
 * 资源
 * 
 * @author Administrator
 * 
 */
public class Resource implements Comparable<Resource> {
	private int resourceId;
	private String url;
	private boolean menu;

	private String description;
	private ResourceCategory resourceCategory;

	private Set<Role> roles;

	@Override
	public int hashCode() {

		int hash = 7;
		hash = 13 * hash + this.resourceId;
		hash = 13 * hash + (this.url != null ? this.url.hashCode() : 0);
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

		Resource r = (Resource) obj;

		if (r.getResourceId() != this.resourceId) {
			return false;
		}

		boolean urlNotEqual = (this.url == null) ? (r.url != null) : !this.url.equals(r.url);
		if (urlNotEqual) {
			
			return false;
		}
		
		return true;
	}

	@Override
	public String toString(){
		
		return resourceId + "-" + url + "-" + description + "-" + menu;
	}
	
	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isMenu() {
		return menu;
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public ResourceCategory getResourceCategory() {
		return resourceCategory;
	}

	public void setResourceCategory(ResourceCategory resourceCategory) {
		this.resourceCategory = resourceCategory;
	}

	public int compareTo(Resource o) {
		
		if(o == null){
			return 1;
		}
		return this.url.compareToIgnoreCase(o.getUrl());
	}
}
