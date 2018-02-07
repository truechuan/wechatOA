package com.leyidai.entity;

public class Area {
	
	private int id;
	private int areaCode;
	private String areaDesc;
	private int handleOrgId;
	private String typeOrTime;
	private String OpenOrderTime;
	
	private String handleorgName; //办理机构名称
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaDesc() {
		return areaDesc;
	}
	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}
	public int getHandleOrgId() {
		return handleOrgId;
	}
	public void setHandleOrgId(int handleOrgId) {
		this.handleOrgId = handleOrgId;
	}
	public String getTypeOrTime() {
		return typeOrTime;
	}
	public void setTypeOrTime(String typeOrTime) {
		this.typeOrTime = typeOrTime;
	}
	public String getHandleorgName() {
		return handleorgName;
	}
	public void setHandleorgName(String handleorgName) {
		this.handleorgName = handleorgName;
	}
	public String getOpenOrderTime() {
		return OpenOrderTime;
	}
	public void setOpenOrderTime(String openOrderTime) {
		OpenOrderTime = openOrderTime;
	}
	
}
