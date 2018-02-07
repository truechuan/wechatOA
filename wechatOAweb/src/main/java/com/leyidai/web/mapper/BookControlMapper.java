package com.leyidai.web.mapper;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.leyidai.entity.BookControl;
import com.leyidai.entity.SendDataTemp;

/**
 * 预约控制mapper
 * 
 * @author zst
 * 
 */
public interface BookControlMapper {

	@Update("update estatedb_book_control set bookMember = bookMember + 1 where members >= bookMember + 1"
			+ " and `date` = #{date} and timeId=#{timeId} and `areaId` = #{areaId}")
	public int update(@Param("date") String date,
			@Param("timeId") String timeId, @Param("areaId") int areaId);

	@Select("SELECT * from estatedb_book_control where `date` = #{date} and `areaId` = #{areaId}")
	public List<BookControl> find(BookControl bookControl);

	@Insert("INSERT INTO `estatedb`.`estatedb_book_control` "
			+ "(`date`, `timeId`, `time`, `areaId`, `areaName`, `members`, `bookMember`) VALUES "
			+ "(#{date}, #{timeId}, #{time}, #{areaId}, #{areaName}, #{members}, #{bookMember}")
	public int insert(BookControl bookControl);

	@Select("select date, sum(members-bookMember) as member from estatedb_book_control where areaId = #{areaId} and date > #{nowDate} group by date order by id asc limit 0, #{limit}")
	public List<Map<String, String>> findDate(@Param("areaId") String areaId,
			@Param("nowDate") Date nowDate, @Param("limit") Integer limit);

	@Update("update estatedb_book_control set bookMember = bookMember - 1 where bookMember >= 1"
			+ " and `date` = #{date} and timeId=#{timeId} and `areaId` = #{areaId}")
	public int updateCancel(@Param("date") String date,
			@Param("timeId") int timeId, @Param("areaId") int areaId);

	@Update("update estatedb_book_control set bookMember = bookMember - 1 where bookMember >= 1"
			+ " and `date` = #{date} and time=#{time} and `areaId` = #{areaId}")
	public int updateCancel2(@Param("date") String date,
			@Param("time") String time, @Param("areaId") int areaId);

	/******************* 2016-09-16 许云强 add start ************************/
	@Update("update estatedb_book_control set cancelbookMember = cancelbookMember + 1 where bookMember >= 1"
			+ " and `date` = #{date} and timeId=#{timeId} and `areaId` = #{areaId}")
	public int updateCancel1(@Param("date") String date,
			@Param("timeId") int timeId, @Param("areaId") int areaId);

	@Update("update estatedb_book_control set cancelbookMember = cancelbookMember + 1 where bookMember >= 1"
			+ " and `date` = #{date} and time=#{time} and `areaId` = #{areaId}")
	public int updateCancel12(@Param("date") String date,
			@Param("time") String time, @Param("areaId") int areaId);

	@Update("UPDATE estatedb_book_control SET bookMember=bookMember-cancelbookMember,cancelbookMember=0 WHERE date=#{date} AND cancelorderdNum!=0 and bookMember>=cancelbookMember")
	public int updateCancelNum(@Param("date") String date);

	/******************* 2016-09-16 许云强 add end ************************/

	// @Select("SELECT * from estatedb_book_control where date(date) = #{transactDate} and `noticeId` = #{noticeId}")
	// public BookControl getReservationNumberByNoticeId(@Param("noticeId")int
	// noticeId, @Param("transactDate")String transactDate);

	// 检查剩余数
	@Select("select members-bookMember from estatedb_book_control where `date` = #{date} and timeId=#{timeId} and `areaId` = #{areaId}")
	public int chenckMemberSurplus(@Param("date") String date,
			@Param("timeId") String timeId, @Param("areaId") int areaId);

	// 查询表中最大的日期
	@Select("select date from estatedb_book_control group by date order by date desc limit 0,1")
	public String selectDateTop1();

	// 查询表中第二大的日期
	@Select("select date from estatedb_book_control gr`oup by date order by date desc limit 1,1")
	public String selectDateTop2();

	@Select("select * from estatedb_sendDataTmp where `status`=0")
	public List<SendDataTemp> selectStatusDefault();
	
	
}
