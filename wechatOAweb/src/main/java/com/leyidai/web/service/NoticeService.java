package com.leyidai.web.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leyidai.entity.Area;
import com.leyidai.entity.Notice;
import com.leyidai.web.mapper.AreaMapper;
import com.leyidai.web.mapper.NoticeMapper;
import com.leyidai.web.util.DateUtil;

@Service
public class NoticeService {
	private static final Logger log = LoggerFactory.getLogger(NoticeService.class);
	@Autowired
	private DateUtil dateUtil;
	@Autowired
	public NoticeMapper noticeMapper;
	@Autowired
	private AreaMapper areaMapper;
	
	@Transactional(readOnly=true)
	public Notice getNoticeById(int id, String handleOrgId){
		log.debug("start notice by id");
		List<Area> arrArea = areaMapper.getAreasByOrgId(handleOrgId);
		String arrAreaId = "";
		for(Area item : arrArea){
			arrAreaId += "," + item.getAreaCode();
		}
		if(StringUtils.isBlank(arrAreaId)){
			
			return noticeMapper.getNoticeById(id);
		}else{
			return noticeMapper.getNoticeByIdAndArea(id, arrAreaId.substring(1));
		}
	}
	
	@Transactional(readOnly=true)
	public List<Notice> getNotices(int start, int size){
		
		return noticeMapper.getNotices(start, size);
	}
	
	@Transactional(readOnly=true)
	public List<Notice> getNoticesByType(int handleOrgId){
		log.debug("start notice by id");

		return noticeMapper.getAllNoticesByType(handleOrgId);
	}
	
	@Transactional(readOnly=true)
	public int getNoticeCount(){
		
		return noticeMapper.getNoticeCount();
	}
	
}
