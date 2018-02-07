package com.leyidai.admin.mapper;

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
	@SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id",
			before = false, resultType = Integer.class)
	public void addDictionary(Dictionary dictionary);

	@Update("update p_system_dictionary set `dictionaryCategoryid`=#{dictionaryCategoryid}, `dictionaryValue` =#{dictionaryValue} where `id`=#{id}")
	public void updateDictionary(Dictionary dictionary);

	@Select("select * from p_system_dictionary where dictionaryValue=#{dictionaryValue} and dictionaryCategoryid=#{dictionaryCategoryid}")
	public Dictionary getDictionaryByDictionaryValue(
			@Param("dictionaryValue") String dictionaryValue,
			@Param("dictionaryCategoryid") int dictionaryCategoryid);

	@Select("select * from p_system_dictionary where `id`=#{id}")
	public Dictionary getDictionaryById(int id);

	@Select("select count(*) from p_system_dictionary")
	public int getDictionaryCount();

	@Select("select d.*,(select categoryDesc from p_system_dictionary_category c where c.id=d.dictionaryCategoryid) dictionaryCategoryName,"
			+ "(select handleOrgId from p_system_dictionary_category c where c.id=d.dictionaryCategoryid) handleOrgId "
			+ "from p_system_dictionary d limit #{start}, #{size}")
	public List<Dictionary> getDictionarys(@Param("start") int start,
			@Param("size") int size);

	@Delete("delete from p_system_dictionary where `id`=#{id}")
	public void deleteDictionaryById(int id);

	@Select("select * from p_system_dictionary")
	public List<Dictionary> getAllDictionarys();

	@Select("select * from p_system_dictionary d where d.dictionaryCategoryid=(select id from p_system_dictionary_category c where c.categoryDesc=#{categoryDesc})")
	public List<Dictionary> getDictionarysByCategory(
			@Param("categoryDesc") String categoryDesc);

	/*********************************** 2017-04-12 start *************/

	@Select("select * from p_system_dictionary d where d.dictionaryCategoryid=(select id from p_system_dictionary_category c where c.categoryDesc=#{categoryDesc} and c.handleOrgId=#{handleOrgId})")
	public List<Dictionary> getDictionarysByCategoryAndHandId(
			@Param("categoryDesc") String categoryDesc,
			@Param("handleOrgId") int handleOrgId);

	// 通过id查找区县，放入list目的是为了前台遍历使用
	@Select("select * from p_system_dictionary d where d.Id=#{handleOrgId}")
	public List<Dictionary> getDictionarysByHandId(
			@Param("handleOrgId") int handleOrgId);
	/*********************************** 2017-04-12 end *************/

}
