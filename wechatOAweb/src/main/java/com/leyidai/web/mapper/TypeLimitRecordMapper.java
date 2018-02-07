package com.leyidai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.leyidai.entity.RecordType;
import com.leyidai.entity.RecordTypeViewNotice;

public interface TypeLimitRecordMapper {

	@Select("SELECT * from estatedb_record_type where `date` = #{date} and `area` = #{area} and `transactTypeId`=#{transacttypeid} and `time`=#{time}")
	public List<RecordType> find(RecordType recordType);
	
	//获取到队列区县的类型限制信息
	@Select("select numLimitId,area,transactTypeId,date,orderLimit,orderedNum,time,cancelorderdNum from ordertypeviewnotice where `date`=#{date} and `area` in (${area}) and isUseful=1")
	public List<RecordType> getQueueListTypes(@Param("date")String date,@Param("area")String area);
	
//	@Select("SELECT * from estatedb_record_type  where `date` = #{date} and `area` = #{area} and `transactTypeId`=#{transacttypeid} and `time`=#{time}")
//	public List<RecordType> find(RecordType recordType);
	
	@Insert("insert into estatedb_record_type (`area`,`transactTypeId`,`date`,`orderLimit`,`orderedNum`,`time`) values (#{area},#{transacttypeid},#{date},#{orderlimit},#{orderednum},#{time})")
	public int insert(RecordType recordType);
	
	//让用户选定的类型已预约数目加一
	@Update("update estatedb_record_type set orderedNum=orderedNum+1 where orderedNum+1<=orderLimit and `date` = #{date} and `area` = #{area} and `transactTypeId`=#{transacttypeid} and `time`=#{time}")
	public int updateOrderednum(RecordType recordType);
	
	//让用户选定的类型已预约数目减一
	@Update("update estatedb_record_type set orderedNum=orderedNum-1 where orderedNum >= 1 and  `date` = #{date} and `area` = #{area} and `transactTypeId`=#{transacttypeid} and `time`=#{time}")
	public int updateOrderednumminurs(RecordType recordType);
	
	/******************* 2016-09-16 许云强 add start  ************************/
	@Update("update estatedb_record_type set cancelorderdNum=cancelorderdNum+1 where `date` = #{date} and `area` = #{area} and `transactTypeId`=#{transacttypeid} and `time`=#{time}")
	public int updateCancelOrderednum(RecordType recordType);
	
	@Select("SELECT * from estatedb_record_type where `date` = #{date} ")
	public List<RecordType> findByDate(RecordType recordType);
	
	@Update("UPDATE estatedb_record_type SET orderedNum=orderedNum-cancelorderdNum,cancelorderdNum=0 WHERE date=#{date} and orderedNum>=cancelorderdNum AND cancelorderdNum!=0")
	public int updateCancelNum(@Param("date") String date);
	
	/******************* 2016-09-16 许云强 add end   ************************/
	@Select("SELECT * from ordertypeviewnotice where `date` = #{date} and `area` = #{area} and isUseful=1")
	public List<RecordTypeViewNotice> getViewByDateAndArea(RecordTypeViewNotice recordTypeViewNotice);
}
