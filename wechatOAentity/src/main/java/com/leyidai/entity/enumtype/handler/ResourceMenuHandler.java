package com.leyidai.entity.enumtype.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
/**
 * 资源菜单enum handler
 * @author Administrator
 *
 */
public class ResourceMenuHandler extends LYDBaseEnumTypeHandler<Boolean> {

	@Override
	public Boolean getEnumByValue(int value) {
		
		return value == 1 ? true : false;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			Boolean parameter, JdbcType jdbcType) throws SQLException {
		
		ps.setInt(i, parameter ? 1 : 0);
	}

}
