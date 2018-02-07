package com.leyidai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.leyidai.entity.User;



/**
 * 用户mapper
 * @author Administrator
 *
 */
public interface LogicUserMapper {
	@Insert("insert into `estatedb_user`(`openid`,`name`,`mobile`,`idcardType`,`idcard`,`idcardmd5`,`idcardImgUrl`,`idcardImgUrl2`,`createTime`,`status`,`lastLoginTime`) values"
			+ "(#{openid}, #{name}, #{mobile}, #{idcardType}, #{idcard}, #{idcardmd5}, #{idcardImgUrl}, #{idcardImgUrl2}, #{createTime}, #{status}, #{lastLoginTime})")
	@SelectKey(statement="SELECT last_insert_id() as id", keyProperty="id", before=false, resultType=Integer.class)
	public void addUser(User user);
	
	@Update("update `estatedb_user` set `name` = #{name}, `mobile` = #{mobile}, `idcard` = #{idcard}, `status` = #{status}," +
			" `idcardmd5` = #{idcardmd5}, `idcardImgUrl`=#{idcardImgUrl}, `idcardImgUrl2`=#{idcardImgUrl2}" +
			" where `openid` = #{openid}")
	public void update(User user);
	
	//拉黑用户
	@Update("update `estatedb_user` set isForbidden=1 where `id` = #{id}")
	public void updateForbidden(User user);
	
	@Select("select * from estatedb_user where idcardmd5=#{idcardmd5}")
	public User getUserInfoByIdCardMd5(String idcardmd5);
	
	@Select("select * from estatedb_user where openid=#{openId} limit 1")
	public User getUserInfoByOpenId(String openId);
	
	@Select("select * from estatedb_user where id=#{id}")
	public User getUserInfoById(long id);
	
	
	
//	
//	@Update("update lyd_user set `password`=#{password}, `loginIp`=#{loginIp}, "
//			+ "`lastLoginTime`=#{lastLoginTime}, `updateTime`=#{updateTime}, "
//			+ "`loginTimes`=#{loginTimes}, "
//			+ "`loginType`=#{loginType, typeHandler=com.leyidai.entity.enumtype.handler.RegisterTypeHandler}"
//			+ " where id=#{id}")
//	public void updateUser(User user);
//	
//	@Delete("delete from lyd_user where id=#{userId}")
//	public void deleteUser(int userId);
//	
	@Select("select * from estatedb_user where name=#{username}")
	public User getUserByUsername(String username);

	@Select("select * from estatedb_user where mobile=#{mobile}")
	public User getUserInfoByMobile(long mobile);
	
	@Select("select * from estatedb_user where idcard=#{idcard}")
	public User getUserInfoByIdcard(String idcard);
	
	@Select("select * from estatedb_user where idcard=#{idcard}")
	public List<User> getUserListByIdcard(String idcard);
	
	
	
	
//	==================下面的均为注释========================================
//	@Select("select * from lyd_user_info where identification=#{identification}")
//	public UserInfo getUserInfoByIdCard(String identification);
//	
//	
//	@Select("select * from lyd_user where id=#{userId}")
//	@Results({
//		@Result(property="type", column="type", typeHandler=com.leyidai.entity.enumtype.handler.UserTypeHandler.class),
//		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.UserStatusHandler.class),
//		@Result(property="registerType", column="registerType", typeHandler=com.leyidai.entity.enumtype.handler.RegisterTypeHandler.class),
//		@Result(property="loginType", column="loginType", typeHandler=com.leyidai.entity.enumtype.handler.RegisterTypeHandler.class)
//	})
//	public User getUserByUserId(int userId);
//	
//	@Select("select * from lyd_user where recommenderMobile=#{recommenderMobile}")
//	@Results({
//		@Result(property="type", column="type", typeHandler=com.leyidai.entity.enumtype.handler.UserTypeHandler.class),
//		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.UserStatusHandler.class),
//		@Result(property="registerType", column="registerType", typeHandler=com.leyidai.entity.enumtype.handler.RegisterTypeHandler.class),
//		@Result(property="loginType", column="loginType", typeHandler=com.leyidai.entity.enumtype.handler.RegisterTypeHandler.class)
//	})
//	public List<User> getUserRecommendeds(String recommenderMobile);
//	
//	@Select("select * from lyd_user a, lyd_user_info b where a.id=b.userId AND "
//			+ "a.recommenderMobile=#{recommenderMobile} AND b.realName!='null' AND b.mobile!='null'")
//	@Results({
//		@Result(property="type", column="type", typeHandler=com.leyidai.entity.enumtype.handler.UserTypeHandler.class),
//		@Result(property="status", column="status", typeHandler=com.leyidai.entity.enumtype.handler.UserStatusHandler.class),
//		@Result(property="registerType", column="registerType", typeHandler=com.leyidai.entity.enumtype.handler.RegisterTypeHandler.class),
//		@Result(property="loginType", column="loginType", typeHandler=com.leyidai.entity.enumtype.handler.RegisterTypeHandler.class)
//	})
//	public List<User> getUserRecommendedsComplete(String recommenderMobile);
//	
//	@Insert("insert into lyd_user_info(`userId`, `email`, `realName`, `identification`, `mobile`, `tranPassword`, `createTime`, "
//			+ "`updateTime`) values"
//			+ "(#{userId}, #{email}, #{realName}, "
//			+ "#{identification}, #{mobile}, "
//			+ "#{tranPassword}, #{createTime}, #{updateTime})")
//	@SelectKey(statement="SELECT last_insert_id() as userId", keyProperty="userId", before=false, resultType=Integer.class)
//	public void addUserInfo(UserInfo userInfo);
//	
//	@Update("update lyd_user_info set `email`=#{email}, `realName`=#{realName}, "
//			+ "`identification`=#{identification}, `mobile`=#{mobile}, "
//			+ "`tranPassword`=#{tranPassword}, `updateTime`=#{updateTime}, "
//			+ "`checkIdentityNum`=#{checkIdentityNum}, `checkIdentityTodayNum`=#{checkIdentityTodayNum}, "
//			+ "`checkIdentityLastTime`=#{checkIdentityLastTime} where userId=#{userId}")
//	public void updateUserInfo(UserInfo userInfo);
//	
//	@Delete("delete from lyd_user_info where userId=#{userId}")
//	public void deleteUserInfo(int userId);
//	
//	@Select("select * from lyd_user_info where userId=#{userId}")
//	public UserInfo getUserInfoByUserId(int userId);
//	
//	@Select("select * from lyd_user_info where email=#{email}")
//	public UserInfo getUserInfoByEmail(String email);
//	
		
}
