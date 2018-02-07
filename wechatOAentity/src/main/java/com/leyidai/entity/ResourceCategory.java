package com.leyidai.entity;

import java.util.Set;

public class ResourceCategory implements Comparable<ResourceCategory> {
	private int rcategoryId;
	private String name;
	
	private Set<Resource> resources;
	
	public int getRcategoryId() {
		return rcategoryId;
	}
	public void setRcategoryId(int rcategoryId) {
		this.rcategoryId = rcategoryId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Resource> getResources() {
		return resources;
	}
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	
	public int compareTo(ResourceCategory rc) {
		
		if(rc == null){
			return 1;
		}
		return this.name.compareTo(rc.getName());
	}

}
