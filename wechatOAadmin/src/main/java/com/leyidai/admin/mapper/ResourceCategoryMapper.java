package com.leyidai.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.leyidai.entity.ResourceCategory;

public interface ResourceCategoryMapper {
	
	@Insert("insert into lyd_rcategory(`name`) values "
			+ "(#{name})")
	@SelectKey(statement="SELECT last_insert_id() as rcategoryId", keyProperty="rcategoryId", before=false, resultType=Integer.class)
	public void addResourceCategory(ResourceCategory rcategory);
	
	@Update("update lyd_rcategory set `name`=#{name} where `rcategoryId`=#{rcategoryId}")
	public void updateResourceCategory(ResourceCategory rcategory);
	
	@Select("select * from lyd_rcategory where `name`=#{name}")
	public ResourceCategory getResourceCategoryByName(String name);
	@Select("select * from lyd_rcategory where `rcategoryId`=#{rcategoryId}")
	public ResourceCategory getResourceCategoryById(int rcategoryId);
	
	@Select("select count(*) from lyd_rcategory")
	public int getResourceCategoryCount();
	@Select("select * from lyd_rcategory limit #{start}, #{size}")
	public List<ResourceCategory> getResourceCategorys(@Param("start")int start, @Param("size")int size);
	
	@Delete("delete from lyd_rcategory where `rcategoryId`=#{rcategoryId}")
	public void deleteResourceCategory(int rcategoryId);
	
	@Select("select A.* from lyd_rcategory A, lyd_categoryresource B where A.rcategoryId=B.rcategoryId and B.resourceId=#{resourceId}")
	public List<ResourceCategory> getResourceCategoryByResourceId(int resourceId);
	
	@Delete("delete from lyd_categoryresource where `rcategoryId`=#{rcategoryId}")
	public void deleteRelatedResourceCategry(int rcategoryId);
	
	@Insert("insert into lyd_categoryresource(`rcategoryId`,`resourceId`) values "
			+ "(#{rcategoryId}, #{resourceId})")
	public void addRelatedResourceCategory(@Param("rcategoryId")int rcategoryId, @Param("resourceId")int resourceId);

}
