package com.leyidai.entity;

import org.apache.commons.lang.StringUtils;

/**
 * 记录查询条件
 * @author Administrator
 *
 */
public class CommonQueryCondition {
	
	private int id;
	private int userId;
	private String startDate;
	private String endDate;
	private boolean preparePage;//是否分页
	private int start;//起始条数
	private int pageSize = 10;//每页数量
	
	private String name;
	private String openid;
	private String mobile;
	private String idcard;
	private int status;
	private String transactOrgName; //办理机构
	private String hourseNumber; //房产登记字号
	private String area; //所属区县
	private int isForbidden;
	private String transactTypeName;
	private int examineStatus;
	
	public int getIsForbidden() {
		return isForbidden;
	}

	public void setIsForbidden(int isForbidden) {
		this.isForbidden = isForbidden;
	}

	public void buildCondition(int userId, String startDate, String endDate, int start, int size,
			String name, String mobile, String idcard, int status, String area,String transactTypeName){
		
		if(userId > 0){
			setUserId(userId);
		}
		if(StringUtils.isNotEmpty(startDate)){
			setStartDate(startDate);
		}
		if(StringUtils.isNotEmpty(endDate)){
			setEndDate(endDate);
		}
		if(StringUtils.isNotEmpty(name)){
			setName(name);
		}
		if(StringUtils.isNotEmpty(mobile)){
			setMobile(mobile);
		}
		
		if(StringUtils.isNotEmpty(idcard)){
			setIdcard(idcard);
		}
		if(StringUtils.isNotEmpty(transactTypeName)){
			setTransactTypeName(transactTypeName);
		}
		if(status > 0){
			setStatus(status);
		}
		if(start >= 0){
			setStart(start);
			setPreparePage(true);
		}
		
		if(size > 0){
			setPageSize(size);
		}
		
		if(StringUtils.isNotEmpty(area)){
			setArea(area);
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public boolean isPreparePage() {
		return preparePage;
	}
	public void setPreparePage(boolean preparePage) {
		this.preparePage = preparePage;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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

	public String getTransactOrgName() {
		return transactOrgName;
	}

	public void setTransactOrgName(String transactOrgName) {
		this.transactOrgName = transactOrgName;
	}

	public String getHourseNumber() {
		return hourseNumber;
	}

	public void setHourseNumber(String hourseNumber) {
		this.hourseNumber = hourseNumber;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getTransactTypeName() {
		return transactTypeName;
	}

	public void setTransactTypeName(String transactTypeName) {
		this.transactTypeName = transactTypeName;
	}

	public int getExamineStatus() {
		return examineStatus;
	}

	public void setExamineStatus(int examineStatus) {
		this.examineStatus = examineStatus;
	}

}
