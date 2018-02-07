package com.leyidai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.leyidai.entity.Area;

public interface AreaMapper {

	@Select("select * from p_area")
	public List<Area> getAreas();

	@Select("select * from p_area where handleOrgId in( ${handleOrgId})")
	public List<Area> getAreasByOrgId(@Param("handleOrgId") String handleOrgId);
	
	@Select("select * from p_area where handleOrgId = ${handleOrgId}")
	public Area getAreasByHandleOrgId(@Param("handleOrgId") int handleOrgId);
}
