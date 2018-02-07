package com.leyidai.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leyidai.admin.mapper.AreaMapper;
import com.leyidai.entity.Area;
import com.leyidai.entity.Dictionary;

@Service
@Transactional(readOnly = true)
public class AreaService {
	private static final Logger log = LoggerFactory.getLogger(AreaService.class);
	
	@Autowired
	private AreaMapper areaMapper;
	
	
	public List<Area> getAreas(){
		log.debug("load areas start");
		
		
		List<Area> areas = areaMapper.getAreas();
		for(Area a: areas){
			
			Dictionary di = areaMapper.getDictionaryByHandleOrgId(a.getHandleOrgId());
			if(di != null){
				
				a.setHandleorgName(di.getDictionaryValue());
			}
		}
		return areas;
	}
	
	public Area getAreaByCode(int areaCode){
		
		return areaMapper.getAreaByCode(areaCode);
	}
}
