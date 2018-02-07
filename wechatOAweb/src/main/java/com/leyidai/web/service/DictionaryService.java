package com.leyidai.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.leyidai.entity.Dictionary;
import com.leyidai.web.mapper.DictionaryMapper;
import com.leyidai.web.util.CategoryConstants;
import com.leyidai.web.util.DateUtil;

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
	public boolean isDictionaryExist(String dictionaryValue){
		
		return dictionaryMapper.getDictionaryByDictionaryValue(dictionaryValue)!=null;
	}
	
	@Transactional(readOnly=true)
	public int getTotalDictionaryRecord(){
		
		return dictionaryMapper.getDictionaryCount();
	}
	
	@Transactional(readOnly=true)
	public List<Dictionary> getDictionarysByCatetory(String categoryDesc, String handleOrgId){
		
		if(StringUtils.isEmpty(handleOrgId)){
			return dictionaryMapper.getDictionarysByCategory(categoryDesc);
		}
		return dictionaryMapper.getDictionarysByCategoryAndHandId(categoryDesc, handleOrgId);
	}
	
	
	@Transactional(readOnly=true)
	public List<Dictionary> getDictionarysByCatetory(String categoryDesc, int handleOrgId){
		
		if(handleOrgId > 0){
			return dictionaryMapper.getDictionarysByCategoryAndHandId(categoryDesc, String.valueOf(handleOrgId));
		}
		return dictionaryMapper.getDictionarysByCategory(categoryDesc);
	}
	
	/**
	 * 根据orgid查询是否是需要排队的区县
	 * 
	 * @param record
	 * @return
	 */
	public boolean queryIsQueueArea(int orgId) {
		List<Dictionary> arrQueueArea = getDictionarysByCatetory(CategoryConstants.QUEUEAREA,
						String.valueOf(0));
		String queueArea = "";
		if (arrQueueArea.size() > 0) {
			queueArea = arrQueueArea.get(0).getDictionaryValue();
		}
		boolean flagIsQueue=false;
		String[] queueAreaList = queueArea.split(",");
		for (String item : queueAreaList) {
			if (orgId == Integer.parseInt(item)) {
				flagIsQueue=true;
				break;
			}
		}
		return flagIsQueue;
	}
	
	/**
	 * 根据orgid查询是否是需要冷却的区县
	 * 
	 * @param orgId
	 * @return 是冷却区县则为true
	 */
	public boolean queryIsCoolingArea(int orgId) {
		List<Dictionary> arrCoolArea = getDictionarysByCatetory(CategoryConstants.COOLAREA,
						String.valueOf(0));
		String coolArea = "";
		if (arrCoolArea.size() > 0) {
			coolArea = arrCoolArea.get(0).getDictionaryValue();
		}
		boolean flagIsCool=false;
		String[] coolAreaList = coolArea.split(",");
		for (String item : coolAreaList) {
			if (orgId == Integer.parseInt(item)) {
				flagIsCool=true;
				break;
			}
		}
		return flagIsCool;
	}
	
	
	/**
	 * 根据orgid查询是否是使用自动审核的区县
	 * 
	 * @param record
	 * @return
	 */
	public boolean queryIsUseSocket(int orgId) {
		List<Dictionary> arrUseSocket = getDictionarysByCatetory(CategoryConstants.USESOCKET,
						String.valueOf(0));
		String socketArea = "";
		if (arrUseSocket.size() > 0) {
			socketArea = arrUseSocket.get(0).getDictionaryValue();
		}
		boolean flagIsUseSocket=false;
		String[] socketAreaList = socketArea.split(",");
		for (String item : socketAreaList) {
			if (orgId == Integer.parseInt(item)) {
				flagIsUseSocket=true;
				break;
			}
		}
		return flagIsUseSocket;
	}
	
	/**
	 * 根据typeid查询不使用自动审核的业务类型
	 * 
	 * @param record
	 * @return
	 */
	public boolean queryIsNotUseSocketType(int typeid) {
		List<Dictionary> arrNotUseSocket = getDictionarysByCatetory(CategoryConstants.noUseSocketTypeId,
						String.valueOf(0));
		String socketType = "";
		if (arrNotUseSocket.size() > 0) {
			socketType = arrNotUseSocket.get(0).getDictionaryValue();
		}
		boolean flagIsNotUseSocket=false;
		String[] socketTypeList = socketType.split(",");
		for (String item : socketTypeList) {
			if (typeid == Integer.parseInt(item)) {
				flagIsNotUseSocket=true;
				break;
			}
		}
		return flagIsNotUseSocket;
	}
	
	
	/**
	 * 根据orgid查询是否是需要使用现场预约的区县
	 * 
	 * @param record
	 * @return
	 */
	public boolean queryNotUseHard(int orgId) {
		List<Dictionary> arrNotUseHardArea = getDictionarysByCatetory(CategoryConstants.noUseHardArea,
						String.valueOf(0));
		String NotUseHardArea = "";
		if (arrNotUseHardArea.size() > 0) {
			NotUseHardArea = arrNotUseHardArea.get(0).getDictionaryValue();
		}
		boolean flagNotUseHardArea=false;
		String[] NotUseHardAreaList = NotUseHardArea.split(",");
		for (String item : NotUseHardAreaList) {
			if (orgId == Integer.parseInt(item)) {
				flagNotUseHardArea=true;
				break;
			}
		}
		return flagNotUseHardArea;
	}
	
	
}
