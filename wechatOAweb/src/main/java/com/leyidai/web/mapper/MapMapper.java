package com.leyidai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.leyidai.entity.Map;

public interface MapMapper {

	@Select("select * from p_map")
	public List<Map> getMap();

	@Select("select * from p_map where handleOrgId in( ${handleOrgId})")
	public List<Map> getUrlByOrgId(@Param("handleOrgId") String handleOrgId);
	
	@Select("select * from p_map where handleOrgId = ${handleOrgId}")
	public Map getUrlByHandleOrgId(@Param("handleOrgId") String handleOrgId);
}
