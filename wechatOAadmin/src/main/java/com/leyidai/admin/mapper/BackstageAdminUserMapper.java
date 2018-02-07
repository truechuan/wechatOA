package com.leyidai.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.leyidai.entity.BackstageAdminUser;


/**
 * 后台管理员mapper
 * @author song
 *
 */
public interface BackstageAdminUserMapper {
	
	@Insert("insert into lyd_backstage_admin_user(`loginName`, `realName`, `phone`, `passWord`, `createTime`, `userStatus`, "
			+ "`loginTimes`, `comment`, `area`) values"
			+ "(#{loginName}, #{realName}, #{phone}, #{passWord}, #{createTime}, "
			+ "#{userStatus}, #{loginTimes}, #{comment}, #{area})")
	@SelectKey(statement="SELECT last_insert_id() as id", keyProperty="id", before=false, resultType=Integer.class)
	public void addAdminUser(BackstageAdminUser adminUser);

	@Select("select * from lyd_backstage_admin_user where loginName=#{loginName}")
	public BackstageAdminUser getAdminUserByUsername(@Param("loginName")String loginName);
	
	@Select("select * from lyd_backstage_admin_user where `id`=#{adminUserId}")
	public BackstageAdminUser getAdminUserById(@Param("adminUserId")int adminUserId);
	
	@Update("update lyd_backstage_admin_user set `phone`=#{phone}, `realName` =#{realName},"
			+ "`passWord`=#{passWord}, `userStatus`=#{userStatus}, `comment`=#{comment},"
			+ "`area`=#{area}, `loginTimes`=#{loginTimes}, `updateTime`=#{updateTime}, "
			+ "`passWord`=#{passWord} where `id`=#{id}")
	public void updateAdminUser(BackstageAdminUser adminUser); 
	
	@Select("select count(*) from lyd_backstage_admin_user")
	public int getAdminUserCount();
	@Select("select * from lyd_backstage_admin_user order by createTime desc limit #{start}, #{size}")
	public List<BackstageAdminUser> getAdminUsers(@Param("start")int start, @Param("size")int size);
	
	@Delete("delete from lyd_backstage_admin_user where `id`=#{adminUserId}")
	public void deleteAdminUser(int adminUserId);
	
}
