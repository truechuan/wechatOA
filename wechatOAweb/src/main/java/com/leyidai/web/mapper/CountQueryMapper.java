package com.leyidai.web.mapper;

import org.apache.ibatis.annotations.Update;

public interface CountQueryMapper {

	@Update("update queryCount set countQuery = countQuery + 1 where id =1")
	public int updateCount();
}
