package com.leyidai.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.leyidai.entity.CommonQueryCondition;
import com.leyidai.entity.User;
import com.leyidai.entity.UserQueryCondition;

/**
 * 用户mapper
 * 
 * @author Administrator
 * 
 */
public interface UserMapper {

	@Update("update estatedb_user set `status`= #{status},`isForbidden`=#{isForbidden}, reason = #{reason}, `updateTime`=#{updateTime}  where id=#{id}")
	public void updateUser(User user);

	@SelectProvider(type = UserSqlProvider.class, method = "selectUser")
	public List<User> getUsers(
			@Param("condition") UserQueryCondition condition);

	@SelectProvider(type = UserSqlProvider.class,
			method = "selectUserCountByUserName")
	public int getUserCount(@Param("condition") UserQueryCondition condition);

	@Select("select * from estatedb_user where id=#{userId}")
	public User getUserByUserId(int userId);

	@Delete("delete from estatedb_user where id=#{userId}")
	public void deleteUser(int userId);
	
	
	/**********************2017-01-17 许云强  add ***************************/
	@Update("update estatedb_user set `stoptime_status`= #{stoptime_status},`stoptime_start`=#{stoptime_start}, stoptime_end = #{stoptime_end}, `checked_cancel_num`=#{checked_cancel_num},`no_promise_num`=#{no_promise_num}  where id=#{id}")
	public void updateUserLimitData(User user);
	@Select("select * from estatedb_user where openid=#{openid}")
	public User getUserByUserOpenid(String openid);
	
	//获取封停的用户列表
	@Select("SELECT *, TIMESTAMPDIFF(DAY,CURRENT_TIMESTAMP(), u.stoptime_end ) AS 'istime' FROM estatedb_user u WHERE u.stoptime_end IS NOT NULL AND u.stoptime_status = 1")
	public List<User> getUserByJiefeng();
	
	//解封
	@Update("update estatedb_user set `stoptime_status`= 0 where openid=#{openid} ")
	public void jiefengById(String openid);
	/**********************2017-01-17 许云强  end ***************************/
	
	/*********************2017-04-12 史晓昊 add start*********************/
	//解封unLockedByUserId
		@Update("update estatedb_user set `stoptime_status`= 0 where id=#{userId} ")
		public void unLockedByUserId(int userId);
		
	/*********************2017-04-12 史晓昊 add end*********************/
}
