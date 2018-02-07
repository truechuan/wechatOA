package com.leyidai.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.leyidai.entity.Notice;

public interface NoticeMapper {
	 
	@Insert("insert into p_notice(`noticeName`, `noticeDescription`, `noticeTypeId`, `area`,`isUseful`,`numLimit`) values (#{noticeName}, #{noticeDescription}, #{noticeTypeId }, #{area },#{isUseful},#{numLimit})")
	@SelectKey(statement="SELECT last_insert_id() as id", keyProperty="id", before=false, resultType=Integer.class)
	public void addNotice(Notice notice);

	@Update("update p_notice set `noticeName`=#{noticeName}, `noticeDescription` =#{noticeDescription},`numLimit`=#{numLimit},`isUseful`=#{isUseful}, `area` =#{area},"
			+ "`noticeTypeId` =#{noticeTypeId} where `id`=#{id}")
	public void updateNotice(Notice notice); 
	
	@Select("select * from p_notice where `id`=#{id}")
	public Notice getNoticeById(int id);
	
	@Select("select count(*) from p_notice")
	public int getNoticeCount();
	
	@Select("select count(*) from p_notice where area in (#{area})")
	public int getNoticeCountByArea(String area);
	
	@Select("select * from p_notice order by createTime desc limit #{start}, #{size}")
	public List<Notice> getNotices(@Param("start")int start, @Param("size")int size);
	
	@Select("select * from p_notice where area in (#{area}) order by createTime desc limit #{start}, #{size} ")
	public List<Notice> getNoticesByArea(@Param("area")String area, @Param("start")int start, @Param("size")int size);
	
	@Delete("delete from p_notice where `id`=#{id}")
	public void deleteNoticeById(int id);
	
	@Select("select * from p_notice")
	public List<Notice> getAllNotices();
	
	@Select("select * from p_notice where `area`=#{areaId}")
	public List<Notice> geNoticesByAreaId(int areaId);
	
	
	@Select("select max(id) from p_notice ")
	public int getMaxid();
	
	
}
