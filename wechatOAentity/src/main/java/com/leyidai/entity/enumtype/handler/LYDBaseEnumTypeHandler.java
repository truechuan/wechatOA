package com.leyidai.entity.enumtype.handler;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
/**
 * enum转换handler
 * @author Administrator
 *
 * @param <T> 枚举类型
 */
public abstract class LYDBaseEnumTypeHandler<T> extends BaseTypeHandler<T> {

	@Override
	public T getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		
		int value = rs.getInt(columnName);
		
		if(rs.wasNull()){
			
			return null;
		}
		return this.getEnumByValue(value);
	}

	@Override
	public T getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		int value = rs.getInt(columnIndex);
		if(rs.wasNull()){
			
			return null;
		}
		return this.getEnumByValue(value);
	}

	@Override
	public T getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		int value = cs.getInt(columnIndex);
		if(cs.wasNull()){
			return null;
		}
		return this.getEnumByValue(value);
	}
	
	public abstract T getEnumByValue(int value);
}
