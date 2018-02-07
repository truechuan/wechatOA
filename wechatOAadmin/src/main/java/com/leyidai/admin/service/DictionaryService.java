package com.leyidai.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leyidai.admin.mapper.DictionaryMapper;
import com.leyidai.admin.util.DateUtil;
import com.leyidai.entity.Dictionary;

@Service
public class DictionaryService {
	private static final Logger log = LoggerFactory.getLogger(DictionaryService.class);
	@Autowired
	private DateUtil dateUtil;
	@Autowired
	public DictionaryMapper dictionaryMapper;
	
	@Transactional
	public void addDictionary(Dictionary dictionary){
		log.debug("add dictionary start");
		
		dictionaryMapper.addDictionary(dictionary);
	}
	
	@Transactional
	public void deleteDictionary(int id){
		log.debug("delete dictionary start");
		
		dictionaryMapper.deleteDictionaryById(id);
	}
	
	@Transactional
	public void updateDictionary(Dictionary dictionary){
		dictionaryMapper.updateDictionary(dictionary);
	}
	
	@Transactional(readOnly=true)
	public Dictionary getDictionaryById(int id){
		
		return dictionaryMapper.getDictionaryById(id);
	}
	
	@Transactional(readOnly=true)
	public List<Dictionary> getDictionarys(int start, int size){
		
		return dictionaryMapper.getDictionarys(start, size);
	}
	
	@Transactional(readOnly=true)
	public List<Dictionary> getDictionarys(){
		
		return dictionaryMapper.getAllDictionarys();
	}
	
	@Transactional(readOnly=true)
	public boolean isDictionaryExist(String dictionaryValue, int dictionaryCategoryid){
		
		return dictionaryMapper.getDictionaryByDictionaryValue(dictionaryValue, dictionaryCategoryid)!=null;
	}
	
	@Transactional(readOnly=true)
	public int getTotalDictionaryRecord(){
		
		return dictionaryMapper.getDictionaryCount();
	}
	
	@Transactional(readOnly=true)
	public List<Dictionary> getDictionarysByCatetory(String categoryDesc, int handleOrgId){
		
		if(handleOrgId > 0){
			return dictionaryMapper.getDictionarysByCategoryAndHandId(categoryDesc, handleOrgId);
		}
		return dictionaryMapper.getDictionarysByCategory(categoryDesc);
	}
	
	/***************2017-4-12 史晓昊add start *************/
	//通过查询字典返回区县
	@Transactional(readOnly=true)
	public List<Dictionary> getDictionarysAreaByCatetory(String categoryDesc, int handleOrgId){

		if(handleOrgId > 0){
			return dictionaryMapper.getDictionarysByHandId(handleOrgId); 
		}
		return dictionaryMapper.getDictionarysByCategory(categoryDesc);
	}
	/***************2017-4-12 史晓昊 add start *************/
}
