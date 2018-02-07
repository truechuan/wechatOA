package com.leyidai.web.mapper;

import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Select;

public interface IpForbiddenMapper {
	/**
	 * 查所有的公告
	 * 
	 * @return
	 */
	@Select("select count(*) from estatedb_IpForbidden where ip=#{ip}")
	public int IsForbiddenIp(@Param("ip") String ip);

	@Select("select count(*) from forbiddenopenid where openid=#{openid}")
	public int IsForbiddenOpenId(@Param("openid") String openid);	
}
