package com.leyidai.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leyidai.entity.RecordTypeViewNotice;
import com.leyidai.web.mapper.TypeLimitRecordMapper;
@Service
public class RecordTypeService {

	@Autowired
	private TypeLimitRecordMapper typeLimitRecordMapper;
	
	/**
	 * 根据日期，区县查询有用的RecordTypeViewNotice
	 * 
	 * @param openid
	 * @return
	 */
	public List<RecordTypeViewNotice>  getViewByDateAndArea(RecordTypeViewNotice recordTypeViewNotice)
	{
		return typeLimitRecordMapper.getViewByDateAndArea(recordTypeViewNotice);
	}
}
