package com.leyidai.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.leyidai.entity.Announcement;
import com.leyidai.entity.CommonQueryCondition;




public interface AnnouncementMapper {
	@Select("select * from lyd_announcement where `announcementId`=#{announcementId} order by createTime desc")
	@Results({
		@Result(property="lanmu", column="lanmu", typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementLanmuHandler.class),
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementStatusHandler.class)
	})
	public Announcement getAnnouncementById(int id);
	
	@Insert("insert into lyd_announcement(`title`, `summary`, `keyWord`, `description`, `status`,"
			+ "`ralateWord`, `imageUrl`,`lanmu`,`detailInfo`,`createTime`, `area`) values"
			+ "(#{title}, #{summary}, #{keyWord}, "
			+"#{description},#{status,typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementStatusHandler},#{ralateWord},#{imageUrl},"
			+ "#{lanmu, typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementLanmuHandler},"
			+ "#{detailInfo}, #{createTime}, #{area})")
	@SelectKey(statement="SELECT last_insert_id() as announcementId", keyProperty="announcementId", before=false, resultType=Integer.class)
	public void addAnnouncementRecord(Announcement announcement);
	
	@SelectProvider(type=AnnouncementSqlProvider.class, method="selectAnnouncement")
	@Results({
		@Result(property="lanmu", column="lanmu", typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementLanmuHandler.class),
		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementStatusHandler.class)
	})
	public List<Announcement> loadAll(@Param("condition")CommonQueryCondition condition);
	
	@Update("update lyd_announcement set"
			+ "`title`=#{title}, `summary`=#{summary}, `keyWord`=#{keyWord}, "
			+"`description`=#{description},`status`=#{status,typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementStatusHandler},`ralateWord`=#{ralateWord},`imageUrl`=#{imageUrl},"
			+" `detailInfo`=#{detailInfo}, `ralateWord`=#{ralateWord},`imageUrl`=#{imageUrl}, `area`=#{area},"
			+ "`lanmu`=#{lanmu, typeHandler=com.leyidai.entity.enumtype.handler.AnnouncementLanmuHandler}, "
			+ "`createTime`=#{createTime},`updateTime`=#{updateTime} where `announcementId`=#{announcementId}")
	public void updateAnnouncement(Announcement announcement);
	
	@Select("select count(*) from lyd_announcement where area in (#{area})")
	public int getAnnouncementCountByArea(String area);
	
	@Select("select count(*) from lyd_announcement")
	public int getAnnouncementCount();
	
	@Delete("delete from lyd_announcement where `announcementId`=#{announcementId}")
	public void deleteAnnouncement(int id);
	
}
