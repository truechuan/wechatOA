package com.leyidai.entity;

import java.util.List;

public class Dictionary {
	
	private int id;
	private int dictionaryCategoryid;
	private String dictionaryCategoryName;
	private String dictionaryValue;
	private List<?> entityList;
	
	private int handleOrgId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDictionaryCategoryid() {
		return dictionaryCategoryid;
	}
	public void setDictionaryCategoryid(int dictionaryCategoryid) {
		this.dictionaryCategoryid = dictionaryCategoryid;
	}
	public String getDictionaryValue() {
		return dictionaryValue;
	}
	public void setDictionaryValue(String dictionaryValue) {
		this.dictionaryValue = dictionaryValue;
	}
	public List<?> getEntityList() {
		return entityList;
	}
	public void setEntityList(List<?> entityList) {
		this.entityList = entityList;
	}
	public String getDictionaryCategoryName() {
		return dictionaryCategoryName;
	}
	public void setDictionaryCategoryName(String dictionaryCategoryName) {
		this.dictionaryCategoryName = dictionaryCategoryName;
	}
	public int getHandleOrgId() {
		return handleOrgId;
	}
	public void setHandleOrgId(int handleOrgId) {
		this.handleOrgId = handleOrgId;
	}
	
	
}
