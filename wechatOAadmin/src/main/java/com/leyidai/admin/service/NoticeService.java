package com.leyidai.admin.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leyidai.admin.mapper.NoticeMapper;
import com.leyidai.admin.util.DateUtil;
import com.leyidai.entity.Notice;

@Service
public class NoticeService {
	private static final Logger log = LoggerFactory.getLogger(NoticeService.class);
	@Autowired
	private DateUtil dateUtil;
	@Autowired
	public NoticeMapper noticeMapper;
	
	@Transactional
	public void addNotice(Notice Notice){
		log.debug("add Notice start");
		
		noticeMapper.addNotice(Notice);
	}
	
	@Transactional
	public void deleteNotice(int id){
		log.debug("delete Notice start");
		
		noticeMapper.deleteNoticeById(id);
	}
	
	@Transactional
	public void updateNotice(Notice Notice){
		noticeMapper.updateNotice(Notice);
	}
	
	@Transactional(readOnly=true)
	public Notice getNoticeById(int id){
		
		return noticeMapper.getNoticeById(id);
	}

//	@Transactional(readOnly=true)
//	public List<Notice> geNoticesByAreaId(int id){
//		
//		return noticeMapper.geNoticesByAreaId(id);
//	}
	
	@Transactional(readOnly=true)
	public List<Notice> getNotices(int start, int size, String area){
		
		if(start < 0){
			start = 0;
		}
		
		if(StringUtils.isEmpty(area)){
			return noticeMapper.getNotices(start, size);
		}
		
		return noticeMapper.getNoticesByArea(area, start, size);
	}
	
	@Transactional(readOnly=true)
	public List<Notice> getNotices(){
		
		return noticeMapper.getAllNotices();
	}
	
	@Transactional(readOnly=true)
	public int getNoticeCount(String area){
		
		if(StringUtils.isEmpty(area)){
			return noticeMapper.getNoticeCount();
		}
		
		return noticeMapper.getNoticeCountByArea(area);
	}
	
}
