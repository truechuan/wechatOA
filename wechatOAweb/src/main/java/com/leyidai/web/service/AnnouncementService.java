package com.leyidai.web.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leyidai.entity.Announcement;
import com.leyidai.entity.Area;
import com.leyidai.entity.Announcement.AnnouncementLanmu;
import com.leyidai.web.mapper.AnnouncementMapper;
import com.leyidai.web.mapper.AreaMapper;
import com.leyidai.web.util.DateUtil;
@Service
public class AnnouncementService {
private static final Logger log = LoggerFactory.getLogger(AnnouncementService.class);
	
	@Autowired
	AnnouncementMapper announcementMapper;
	@Autowired
	private DateUtil dateUtil;
	@Autowired
	private AreaMapper areaMapper;
	/**
	 * 查所有的公告
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Announcement> loadAnnouncement(int start,int size,AnnouncementLanmu lanmu, String handleOrgId){
		log.debug("load announcement");
		
		List<Area> arrArea = areaMapper.getAreasByOrgId(handleOrgId);
		String arrAreaId = "";
		for(Area item : arrArea){
			arrAreaId += "," + item.getAreaCode();
		}
		
		if(StringUtils.isBlank(arrAreaId)){

			return announcementMapper.loadAll(start,size,lanmu);
		}else{

			return announcementMapper.loadAllByArea(start,size,lanmu, arrAreaId.substring(1));
		}
		
	}
	
	/**
	 * 查一条公告
	 * @return
	 */
	@Transactional(readOnly = true)
	public Announcement selectAnnouncement(int announcementId){
		log.debug("select announcement");
		return announcementMapper.selectAnnouncement(announcementId);
	}
	
	/**
	 * 得到所有的公告数量
	 * @return
	 */
	@Transactional(readOnly = true)
	public int getAnnouncementCount(AnnouncementLanmu lanmu, String handleOrgId){
		log.debug("load announcement");
		
		List<Area> arrArea = areaMapper.getAreasByOrgId(handleOrgId);
		String arrAreaId = "";
		for(Area item : arrArea){
			arrAreaId += "," + item.getAreaCode();
		}
		
		if(StringUtils.isBlank(arrAreaId)){
			return announcementMapper.getAnnouncementCount(lanmu);
		}else{
			return announcementMapper.getCountByArea(lanmu, arrAreaId.substring(1));
		}
		
	}
	
	
}
