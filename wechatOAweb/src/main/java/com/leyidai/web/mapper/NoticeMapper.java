package com.leyidai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.leyidai.entity.Notice;

public interface NoticeMapper {
	 
	@Select("select * from p_notice where `id`=#{id}")
	public Notice getNoticeById(int id);
	
	@Select("select * from p_notice where `id`=#{id} and area in(${area})")
	public Notice getNoticeByIdAndArea(@Param("id")int id, @Param("area")String area);
	
	@Select("select count(*) from p_notice")
	public int getNoticeCount();
	
	@Select("select * from p_notice limit #{start}, #{size}")
	public List<Notice> getNotices(@Param("start")int start, @Param("size")int size);
	
	@Select("select * from p_notice where noticeTypeId=#{noticeTypeId}")
	public List<Notice> getAllNoticesByType(int noticeTypeId);
	
	@Select("select * from p_notice where noticeTypeId=#{noticeTypeId} and isUseful=1")
	public List<Notice> getAllNoticesByTypeAndUseful(int noticeTypeId);

	@Select("select * from p_notice where noticeTypeId=#{noticeTypeId} and area in(${area})")
	public List<Notice> getAllNoticesByArea(@Param("noticeTypeId")int noticeTypeId, @Param("area")String area);
	@Select("select * from p_notice where area in(${area}) and isUseful=1 and noticeTypeId=#{noticeTypeId}")
	public List<Notice> getAllNoticesByAreaAndUseful(@Param("area")String area,@Param("noticeTypeId")int noticeTypeId);
	
	@Select("select noticeName,numLimit from p_notice where area in(${area}) and isUseful=1 and noticeTypeId=#{noticeTypeId}")
	public List<Notice> getNoticesByAreaAndUseful(@Param("area")String area,@Param("noticeTypeId")int noticeTypeId);
	
}
