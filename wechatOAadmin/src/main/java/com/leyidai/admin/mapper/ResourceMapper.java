package com.leyidai.admin.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.leyidai.entity.Resource;

public interface ResourceMapper {

	@Insert("insert into lyd_resource(`url`,`description`,`menu`) values "
			+ "(#{url}, #{description}, #{menu, typeHandler=com.leyidai.entity.enumtype.handler.ResourceMenuHandler})")
	@SelectKey(statement="SELECT last_insert_id() as resourceId", keyProperty="resourceId", before=false, resultType=Integer.class)
	public void addResource(Resource resource);
	
	@Update("update lyd_resource set `url`=#{url}, "
			+ "`description`=#{description}, "
			+ "`menu`=#{menu, typeHandler=com.leyidai.entity.enumtype.handler.ResourceMenuHandler} where `resourceId`=#{resourceId}")
	public void updateResource(Resource resource);
	
	@Select("select * from lyd_resource")
	public List<Resource> getAllResources();
	
	@Select("select A.* from lyd_resource A, lyd_roleresource B where A.resourceId=B.resourceId and B.roleId=#{roleId}")
	public Set<Resource> getRoleResources(int roleId);
	
	@Select("select * from lyd_resource where `resourceId`=#{resourceId}")
	public Resource getResource(int resourceId);
	@Select("select * from lyd_resource where `url`=#{url}")
	public Resource getResourceByUrl(String url);
	
	@Select("select count(*) from lyd_resource")
	public int getResourceCount();
	@Select("select * from lyd_resource limit #{start}, #{size}")
	public List<Resource> getResources(@Param("start")int start, @Param("size")int size);
	
	@Delete("delete from lyd_resource where `resourceId`=#{resourceId}")
	public void deleteResource(int roleId);
	
	@Select("select A.* from lyd_resource A, lyd_categoryresource B where A.resourceId=B.resourceId and B.rcategoryId=#{rcategoryId}")
	public Set<Resource> getResourcesByResourceCategoryId(int rcategoryId);
	
}
