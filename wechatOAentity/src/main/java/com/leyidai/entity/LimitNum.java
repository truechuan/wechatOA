package com.leyidai.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 * 2017-01-10 add
 * @author Smarty
 *
 */
public class LimitNum {

	private int id;
	private int areaCode;
	private int limittype;
	private int limitnum = 0;
	private int limittimeOnesDays = 0;
	private Timestamp createtime;
	private Timestamp updatetime;
	private int addUserid;
	
	private int isdel;
	
	private String areaDesc; //区域代码描述
	private List<Area> areaList;//区域列表
	
	
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public List<Area> getAreaList() {
		return areaList;
	}
	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}
	public String getAreaDesc() {
		return areaDesc;
	}
	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}
	public int getAddUserid() {
		return addUserid;
	}
	public void setAddUserid(int addUserid) {
		this.addUserid = addUserid;
	}
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
	public int getLimittype() {
		return limittype;
	}
	public void setLimittype(int limittype) {
		this.limittype = limittype;
	}
	public int getLimitnum() {
		return limitnum;
	}
	public void setLimitnum(int limitnum) {
		this.limitnum = limitnum;
	}
	public int getLimittimeOnesDays() {
		return limittimeOnesDays;
	}
	public void setLimittimeOnesDays(int limittimeOnesDays) {
		this.limittimeOnesDays = limittimeOnesDays;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	@Override
	public String toString() {
		return "LimitNum [id=" + id + ", areaCode=" + areaCode + ", limittype=" + limittype + ", limitnum=" + limitnum
				+ ", limittimeOnesDays=" + limittimeOnesDays + ", createtime=" + createtime + ", updatetime="
				+ updatetime + ", addUserid=" + addUserid + ", isdel=" + isdel + ", areaDesc=" + areaDesc
				+ ", areaList=" + areaList + "]";
	}

	
	
	
}
