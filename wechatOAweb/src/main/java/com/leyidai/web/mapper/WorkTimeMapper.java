package com.leyidai.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.leyidai.entity.WorkTime;

/**
 * 工作时间mapper
 * @author zst
 *
 */
public interface WorkTimeMapper {
	@Insert("INSERT INTO estatedb_work_time (`startTime`, `endTime`, `members`, `deadTime`, `status`, `createTime`, `areaId`, `areaName`) " +
			"VALUES (#{startTime},#{endTime},#{members}, #{deadTime}, #{status}, #{createTime}, #{areaId}, #{areaName})")
	@SelectKey(statement="SELECT last_insert_id() as id", keyProperty="id", before=false, resultType=Integer.class)
	public int insert(WorkTime workTime);
//	
//	@Update("update lyd_user set `type`=#{type, typeHandler=com.leyidai.entity.enumtype.handler.UserTypeHandler},"
//			+ "`status`=#{status, typeHandler=com.leyidai.entity.enumtype.handler.UserStatusHandler}, "
//			+ "`loginIp`=#{loginIp}, "
//			+ "`lastLoginTime`=#{lastLoginTime}, `updateTime`=#{updateTime}, "
//			+ "`loginTimes`=#{loginTimes} where id=#{id}")
	public int update(WorkTime workTime);
	
	@Select("Select * from estatedb_work_time where areaId = #{areaId} order by startTime")
	public List<WorkTime> findByAreaId(Integer areaId);
	
	@Select("Select * from estatedb_work_time where Id = #{id}")
	public WorkTime findById(Integer id);
//	
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
//			+ "`tranPassword`=#{tranPassword}, `updateTime`=#{updateTime} where userId=#{userId}")
//	public void updateUserInfo(UserInfo userInfo);
//	
//	@Select("select * from lyd_user_info where userId=#{userId}")
//	public UserInfo getUserInfoByUserId(int userId);
//	
//	@Select("select * from lyd_user_info where email=#{email}")
//	public UserInfo getUserInfoByEmail(String email);
//	
//	@Select("select * from lyd_user_info where mobile=#{mobile}")
//	public UserInfo getUserInfoByMobile(String mobile);
//	
//	@Select("select * from lyd_user_info where identification=#{identification}")
//	public UserInfo getUserInfoByIdentification(String identification);
//	
//	@Select("select * from lyd_user_info where realName=#{realName}")
//	public List<UserInfo> getUserInfoByRealName(String realName);
//	
//	@SelectProvider(type=UserSqlProvider.class, method="selectUserCountByUserName")
//	public int getUserCount(@Param("condition")CommonQueryCondition condition);
//	
//	@Select("select count(*) from lyd_user_info where realName=#{realName}")
//	public int getUserInfoCount(String realName);
//	
//	@Select("select count(*) from lyd_user_info where phone=#{phone}")
//	public int getUserInfoCountByPhone(String phone);
    

}
