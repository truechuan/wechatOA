package com.leyidai.admin.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.leyidai.entity.Role;

public interface RoleMapper {

	@Insert("insert into lyd_role(`name`,`description`,`createTime`) values "
			+ "(#{name}, #{description}, #{createTime})")
	@SelectKey(statement="SELECT last_insert_id() as roleId", keyProperty="roleId", before=false, resultType=Integer.class)
	public void addRole(Role role);
	
	@Update("update lyd_role set `name`=#{name}, "
			+ "`description`=#{description}, `updateTime`=#{updateTime} where `roleId`=#{roleId}")
	public void updateRole(Role role);
	
	@Select("select A.* from lyd_role A, lyd_grouprole B where A.roleId=B.roleId and B.groupId=#{groupId}")
	public Set<Role> getGroupRoles(int groupId);
	
	@Select("select A.* from lyd_role A, lyd_roleresource B where A.roleId=B.roleId and B.resourceId=#{resourceId}")
	public Set<Role> getResourceRoles(int resourceId);
	
	@Delete("delete from lyd_roleresource where `roleId`=#{roleId}")
	public void deleteRoleResource(int roleId);
	@Delete("delete from lyd_role where `roleId`=#{roleId}")
	public void deleteRole(int roleId);
	
	@Insert("insert into lyd_roleresource(`roleId`,`resourceId`) values "
			+ "(#{roleId}, #{resourceId})")
	public void addRoleResource(@Param("roleId")int roleId, @Param("resourceId")int resourceId);
	
	@Select("select * from lyd_role where `roleId`=#{roleId}")
	public Role getRole(int roleId);
	@Select("select * from lyd_role where `name`=#{name}")
	public Role getRoleByName(String name);
	
	@Select("select count(*) from lyd_role")
	public int getRoleCount();
	@Select("select * from lyd_role order by createTime desc limit #{start}, #{size}")
	public List<Role> getRoles(@Param("start")int start, @Param("size")int size);
	@Select("select * from lyd_role")
	public List<Role> getAllRoles();
}
