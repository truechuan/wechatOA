package com.leyidai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.leyidai.entity.Announcement;
import com.leyidai.entity.AnnouncementAtIndex;
import com.leyidai.entity.Area;
import com.leyidai.entity.Announcement.AnnouncementLanmu;

public interface AnnouncementMapper {
	/**
	 * 查所有的公告
	 * @return
	 */
	@Select("select * from lyd_announcement where createTime<now() and lanmu=#{lanmu, "
			+ "typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementLanmuHandler} and `status`=1 order by createTime desc limit #{start},#{size}")
	@Results({
		@Result(property="lanmu", column="lanmu", typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementLanmuHandler.class),
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementStatusHandler.class)
	})
	public List<Announcement> loadAll(@Param("start")int start,@Param("size")int size,@Param("lanmu")AnnouncementLanmu lanmu);
	
	/*
	 * 查一条公告
	 * @return
	 */
	@Select("select * from lyd_announcement where `announcementId` = #{announcementId}")
	@Results({
		@Result(property="lanmu", column="lanmu", typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementLanmuHandler.class),
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementStatusHandler.class)
	})
	public Announcement selectAnnouncement(int announcementId);
	
	/*
	 * 查所有的新闻
	 */	
	@Select("select * from lyd_announcement where lanmu=#{lanmu, "
			+ "typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementLanmuHandler}")
	@Results({
		@Result(property="lanmu", column="lanmu", typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementLanmuHandler.class),
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementStatusHandler.class)
	})
	public List<Announcement> loadAllNews(AnnouncementLanmu lanmu);
	
	/**
	 * 得到所有的公告数量
	 * @return
	 */
	@Select("select count(*) from lyd_announcement where createTime<now() and lanmu=#{lanmu, "
			+ "typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementLanmuHandler} and `status`=1")
	public int getAnnouncementCount(AnnouncementLanmu lanmu);
	
	@Select("select count(*) from lyd_announcement where createTime<now() and lanmu=#{lanmu, "
			+ "typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementLanmuHandler} and `status`=1 and `area` in (${area})")
	public int getCountByArea(@Param("lanmu")AnnouncementLanmu lanmu, @Param("area")String area);
	
	@Select("select * from lyd_announcement where createTime<now() and lanmu=#{lanmu, "
			+ "typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementLanmuHandler} and `status`=1 and `area` in (${area}) order by createTime desc limit #{start},#{size}")
	@Results({
		@Result(property="lanmu", column="lanmu", typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementLanmuHandler.class),
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementStatusHandler.class)
	})
	public List<Announcement> loadAllByArea(@Param("start")int start,@Param("size")int size,@Param("lanmu")AnnouncementLanmu lanmu, @Param("area")String area);
	
	@Select("select * from lyd_announcement where createTime<now() and lanmu=#{lanmu, "
			+ "typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementLanmuHandler} and `status`=1 and `area` in (${area}) order by createTime desc")
	@Results({
		@Result(property="lanmu", column="lanmu", typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementLanmuHandler.class),
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementStatusHandler.class)
	})
	public List<Announcement> loadAllByDefaultArea(@Param("lanmu")AnnouncementLanmu lanmu, @Param("area")String area);

	
	@Select("select * from p_announcementAtIndex order by listNum")
	public List<AnnouncementAtIndex> getAnnouncementAtIndexs();
	
	@Select("select * from p_announcementAtIndex order by id desc")
	public List<AnnouncementAtIndex> getAnnouncementAtIndexsOrderById();
	
}

