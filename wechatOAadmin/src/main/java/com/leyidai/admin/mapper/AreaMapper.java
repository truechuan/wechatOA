package com.leyidai.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.leyidai.entity.Area;
import com.leyidai.entity.Dictionary;

public interface AreaMapper {

	@Select("select * from p_area")
	public List<Area> getAreas();
	
	@Select("select * from p_area where areaCode=#{areaCode}")
	public Area getAreaByCode(int areaCode);
	
	@Select("select * from p_area where handleOrgId=#{handleOrgId}")
	public Area getAreaByHandleOrgId(int handleOrgId);
	
	@Select("select * from p_system_dictionary where id=#{handleOrgId}")
	public Dictionary getDictionaryByHandleOrgId(int handleOrgId);
}
