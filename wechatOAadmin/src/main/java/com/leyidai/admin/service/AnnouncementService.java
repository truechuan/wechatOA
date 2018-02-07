package com.leyidai.admin.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leyidai.admin.mapper.AnnouncementMapper;
import com.leyidai.admin.util.DateUtil;
import com.leyidai.admin.util.DateUtil.DateFormat;
import com.leyidai.entity.Announcement;
import com.leyidai.entity.CommonQueryCondition;

@Service
public class AnnouncementService {
	private static final Logger log = LoggerFactory.getLogger(AnnouncementService.class);
	
	@Autowired
	AnnouncementMapper announcementMapper;
	@Autowired
	private DateUtil dateUtil;
	
	/**
	 * 通过id查找公告
	 * @return
	 */
	@Transactional(readOnly = true)
	public Announcement getAnnouncementByAnnouncementId(int announcementId){
		return announcementMapper.getAnnouncementById(announcementId);
	}
	
	/**
	 * 添加公告记录
	 */
	@Transactional
	public void addAnnouncement(Announcement announcement){
		log.debug("add system announcement {} success.", announcement.getAnnouncementId());
		if(StringUtils.isEmpty(announcement.getCreateTime()))
		{
			announcement.setCreateTime(dateUtil.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));
		}
		announcementMapper.addAnnouncementRecord(announcement);
	}
	
	/**
	 * 查所有的公告
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Announcement> loadAnnouncement( int start, int size, String area){
		CommonQueryCondition condition = new CommonQueryCondition();
		if(start > 0){
			condition.setStart(start);
		}
		if(size > 0){
			condition.setPageSize(size);
		}
		condition.setArea(area);
		condition.setPreparePage(true);
		return announcementMapper.loadAll(condition);
	}
	
	/**
	 * 修改公告记录
	 * @param announcement
	 */
	@Transactional
	public void updateAnnouncement(Announcement announcement){
		if(StringUtils.isEmpty(announcement.getCreateTime()))
		{
			announcement.setCreateTime(dateUtil.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));
		}
		announcement.setUpdateTime(dateUtil.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));
		announcementMapper.updateAnnouncement(announcement);
	}
	
	/**
	 * 公告数量
	 */
	@Transactional(readOnly=true)
	public int getAnnouncementCount(String area){
		
		if(area.isEmpty()){
			return announcementMapper.getAnnouncementCount();
		}
		return announcementMapper.getAnnouncementCountByArea(area);
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@Transactional
	public void deleteAnnouncement(int id){
		announcementMapper.deleteAnnouncement(id);
	}
}
