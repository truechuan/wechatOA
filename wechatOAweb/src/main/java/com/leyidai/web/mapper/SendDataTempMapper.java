package com.leyidai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.leyidai.entity.SendDataTemp;

public interface SendDataTempMapper {

	@Insert("insert into estatedb_sendDataTmp(`name`, `idcard`, `hourseNumber`, `transactType`,`area`,`orderId`,`status`) values (#{name}, #{idcard},#{hourseNumber},#{transactType},#{area},#{orderId},#{status})")
	@SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id",
			before = false, resultType = Integer.class)
	public void addSendDataTemp(SendDataTemp sendDataTemp);

	@Select("select * from estatedb_sendDataTmp where `status`=0")
	public List<SendDataTemp> selectStatusDefault();
	
	@Select("select * from estatedb_sendDataTmp limit 1")
	public SendDataTemp selectStatusDefault1();

	
	
	// 更新order表条目的状态，是否校验通过
	@Update("update estatedb_sendDataTmp set `status`=#{status} where id=#{id}")
	public void updateExamineStatus(SendDataTemp sendDataTemp);
}
