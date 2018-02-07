package com.leyidai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.leyidai.entity.Dictionary;


public interface DictionaryMapper {
	
	@Insert("insert into p_system_dictionary(`dictionaryCategoryid`, `dictionaryValue`) values (#{dictionaryCategoryid}, #{dictionaryValue})")
	@SelectKey(statement="SELECT last_insert_id() as id", keyProperty="id", before=false, resultType=Integer.class)
	public void addDictionary(Dictionary dictionary);

	@Update("update p_system_dictionary set `dictionaryCategoryid`=#{dictionaryCategoryid}, `dictionaryValue` =#{dictionaryValue} where `id`=#{id}")
	public void updateDictionary(Dictionary dictionary); 
	
	@Select("select * from p_system_dictionary where dictionaryValue=#{dictionaryValue}")
	public Dictionary getDictionaryByDictionaryValue(String dictionaryValue);
	
	@Select("select * from p_system_dictionary where `id`=#{id}")
	public Dictionary getDictionaryById(int id);
	
	@Select("select count(*) from p_system_dictionary")
	public int getDictionaryCount();
	
	@Select("select d.*,(select categoryDesc from p_system_dictionary_category c where c.id=d.dictionaryCategoryid) dictionaryCategoryName from p_system_dictionary d limit #{start}, #{size}")
	public List<Dictionary> getDictionarys(@Param("start")int start, @Param("size")int size);
	
	@Delete("delete from p_system_dictionary where `id`=#{id}")
	public void deleteDictionaryById(int id);
	
	@Select("select * from p_system_dictionary")
	public List<Dictionary> getAllDictionarys();
	
	@Select("select * from p_system_dictionary d where d.dictionaryCategoryid=(select id from p_system_dictionary_category c where c.categoryDesc=#{categoryDesc})")
	public List<Dictionary> getDictionarysByCategory(@Param("categoryDesc")String categoryDesc);
	
	@Select("select * from p_system_dictionary d where d.dictionaryCategoryid=(select id from p_system_dictionary_category c where c.categoryDesc=#{categoryDesc} and c.handleOrgId in (#{handleOrgId}))")
	public List<Dictionary> getDictionarysByCategoryAndHandId(@Param("categoryDesc")String categoryDesc, @Param("handleOrgId")String handleOrgId);
	
}
