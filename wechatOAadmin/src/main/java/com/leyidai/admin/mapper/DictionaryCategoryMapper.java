package com.leyidai.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.leyidai.entity.DictionaryCategory;


public interface DictionaryCategoryMapper {
	
	@Insert("insert into p_system_dictionary_category(`categoryCode`, `categoryDesc`, `handleOrgId`) values (#{categoryCode}, #{categoryDesc}, #{handleOrgId})")
	@SelectKey(statement="SELECT last_insert_id() as id", keyProperty="id", before=false, resultType=Integer.class)
	public void addDictionaryCategory(DictionaryCategory dictionaryCategory);

	@Update("update p_system_dictionary_category set `categoryCode`=#{categoryCode}, `categoryDesc` =#{categoryDesc}, `handleOrgId` =#{handleOrgId} where `id`=#{id}")
	public void updateDictionaryCategory(DictionaryCategory dictionaryCategory); 
	
	@Select("select * from p_system_dictionary_category where categoryCode=#{categoryCode} and categoryDesc=#{categoryDesc} and handleOrgId=#{handleOrgId}")
	public DictionaryCategory getCategoryByCategoryCode(@Param("categoryCode")String categoryCode, @Param("categoryDesc")String categoryDesc, @Param("handleOrgId")int handleOrgId);
	
	@Select("select * from p_system_dictionary_category where `id`=#{id}")
	public DictionaryCategory getDictionaryCategoryById(int id);
	
	@Select("select count(*) from p_system_dictionary_category")
	public int getDictionaryCategoryCount();
	
	@Select("select * from p_system_dictionary_category limit #{start}, #{size}")
	public List<DictionaryCategory> getDictionaryCategorys(@Param("start")int start, @Param("size")int size);
	
	@Delete("delete from p_system_dictionary_category where `id`=#{id}")
	public void deleteDictionaryCategoryById(int id);
	
	@Select("select * from p_system_dictionary_category")
	public List<DictionaryCategory> getAllDictionaryCategorys();
	
}
