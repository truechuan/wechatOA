package com.leyidai.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leyidai.admin.mapper.DictionaryCategoryMapper;
import com.leyidai.admin.util.DateUtil;
import com.leyidai.entity.DictionaryCategory;

@Service
public class DictionaryCategoryService {
	private static final Logger log = LoggerFactory.getLogger(DictionaryCategoryService.class);
	@Autowired
	private DateUtil dateUtil;
	@Autowired
	public DictionaryCategoryMapper dictionaryCategoryMapper;
	
	@Transactional
	public void addDictionaryCategory(DictionaryCategory dictionaryCategory){
		log.debug("add dictionaryCategory start");
		
		dictionaryCategoryMapper.addDictionaryCategory(dictionaryCategory);
	}
	
	@Transactional
	public void deleteDictionaryCategory(int id){
		log.debug("delete dictionaryCategory start");
		
		dictionaryCategoryMapper.deleteDictionaryCategoryById(id);
	}
	
	@Transactional
	public void updateDictionaryCategory(DictionaryCategory dictionaryCategory){
		dictionaryCategoryMapper.updateDictionaryCategory(dictionaryCategory);
	}
	
	@Transactional(readOnly=true)
	public DictionaryCategory getDictionaryCategoryById(int id){
		
		return dictionaryCategoryMapper.getDictionaryCategoryById(id);
	}
	
	@Transactional(readOnly=true)
	public List<DictionaryCategory> getDictionaryCategorys(int start, int size){
		
		return dictionaryCategoryMapper.getDictionaryCategorys(start, size);
	}
	
	@Transactional(readOnly=true)
	public List<DictionaryCategory> getDictionaryCategorys(){
		
		return dictionaryCategoryMapper.getAllDictionaryCategorys();
	}
	
	@Transactional(readOnly=true)
	public boolean isDictionaryCategoryExist(String categorycode,String categoryDesc, int handleOrgId){
		
		return dictionaryCategoryMapper.getCategoryByCategoryCode(categorycode, categoryDesc, handleOrgId)!=null;
	}
	
	@Transactional(readOnly=true)
	public int getTotalDictionaryCategoryRecord(){
		
		return dictionaryCategoryMapper.getDictionaryCategoryCount();
	}
	
}
