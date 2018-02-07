package com.leyidai.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leyidai.admin.mapper.NoticeViewTypeMapper;
import com.leyidai.entity.NoticeViewType;

@Service
public class NoticeViewTypeService {
	/********************* 2017-3-9 史晓昊 add ******************************/
	@Autowired
	NoticeViewTypeMapper noticeViewTypeMapper;

	public NoticeViewType getLimitNumByNoticeViewTimeId(int noticeViewTimeId) {
		return noticeViewTypeMapper
				.getLimitNumByNoticeViewTimeId(noticeViewTimeId);
	}

	public int updateLimitNumBynoticeViewTimeId(NoticeViewType noticeViewType) {
		return noticeViewTypeMapper
				.updateLimitNumBynoticeViewTimeId(noticeViewType);
	}

}
