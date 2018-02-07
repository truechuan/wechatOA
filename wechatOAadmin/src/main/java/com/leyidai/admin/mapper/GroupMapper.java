package com.leyidai.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.leyidai.entity.Group;

public interface GroupMapper {
	
	@Insert("insert into lyd_igroup(`name`,`description`,`enable`,`createTime`) values "
			+ "(#{name}, #{description}, #{enable},  #{createTime})")
	@SelectKey(statement="SELECT last_insert_id() as groupId", keyProperty="groupId", before=false, resultType=Integer.class)
	public void addGroup(Group group);
	
	@Update("update lyd_igroup set `name`=#{name}, "
			+ "`description`=#{description}, "
			+ "`enable`=#{enable}, `updateTime`=#{updateTime} where `groupId`=#{groupId}")
	public void updateGroup(Group group);
	
	@Insert("insert into lyd_grouprole(`groupId`,`roleId`) values "
			+ "(#{groupId}, #{roleId})")
	public void addGroupRole(@Param("groupId")int groupId, @Param("roleId")int roleId);
	
	@Delete("delete from lyd_grouprole where `groupId`=#{groupId}")
	public void deleteGroupRole(int groupId);
	
	@Select("select A.* from lyd_igroup A, lyd_usergroup B where A.groupId=B.groupId and B.userId=#{userId}")
	public List<Group> getUserGroups(int userId);
	
	@Select("select * from lyd_igroup where `groupId`=#{groupId}")
	public Group getGroup(int groupId);
	
	@Select("select * from lyd_igroup where `name`=#{name}")
	public Group getGroupByName(String name);
	
	@Select("select * from lyd_igroup")
	public List<Group> getAllGroups();
	
	@Select("select groupId from lyd_usergroup where userId=#{userId} limit 1")
	public int getGrupsByAdminUserId(int userId);
	
	@Select("select count(*) from lyd_igroup")
	public int getGroupCount();
	@Select("select * from lyd_igroup order by createTime desc limit #{start}, #{size}")
	public List<Group> getGroups(@Param("start")int start, @Param("size")int size);
	
	@Delete("delete from lyd_igroup where `groupId`=#{groupId}")
	public void deleteGroup(int groupId);
	
	@Insert("insert into lyd_usergroup(`userId`,`groupId`) values "
			+ "(#{userId}, #{groupId})")
	public void addUserGroup(@Param("userId")int userId, @Param("groupId")int groupId);
	
	@Delete("delete from lyd_usergroup where userId=#{userId}")
	public void deleteUserGroupByUserId(int userId);
	
}
