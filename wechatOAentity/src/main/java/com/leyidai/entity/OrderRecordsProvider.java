/**
 * 
 */
package com.leyidai.entity;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SqlBuilder;


public class OrderRecordsProvider{
		
	public String update(OrderRecords item){
		SqlBuilder.BEGIN();
		SqlBuilder.UPDATE("estatedb_order_records");
		if(item.getNoticeFlg() != 0){
			SqlBuilder.SET("noticeFlg = #{noticeFlg}");
		}
		if(StringUtils.isNotBlank(item.getArea())){
			SqlBuilder.SET("area = #{area}");
		}
		if(StringUtils.isNotBlank(item.getHourseNumber())){
			SqlBuilder.SET("hourseNumber = #{hourseNumber}");
		}
		if(StringUtils.isNotBlank(item.getHourseAddress())){
			SqlBuilder.SET("hourseAddress = #{hourseAddress}");
		}
		if(item.getStatus() != 0){
			SqlBuilder.SET("status = #{status}");
		}
		if(item.getDel() != 0){
			SqlBuilder.SET("del = #{del}");
		}
		if(StringUtils.isNotBlank(item.getFailReason())){
			SqlBuilder.SET("failReason = #{failReason}");
		}
		
		SqlBuilder.WHERE("id = #{id}");
		return SqlBuilder.SQL();
	}
	
//	private String whereBuilder(User user){
//		StringBuilder sqlTemp = new StringBuilder();
//		sqlTemp.append(" 1=1");
//		if(user.getId() != 0){
//			sqlTemp.append(" AND c1.user_id = #{user.id}");
//		}
//		if(StringUtils.isNotBlank(user.getUserName())){
//			sqlTemp.append(" AND c1.userName like #{user.userName}");
//		}
//		if(StringUtils.isNotBlank(user.getEmail())){
//			sqlTemp.append(" AND c1.email like #{user.email}");
//		}
//		if(StringUtils.isNotBlank(user.getPhoneNum())){
//			sqlTemp.append(" AND c1.phoneNum like #{user.phoneNum}");
//		}
//		if(user.getStatus() != -1){
//			
//			sqlTemp.append(" AND c1.status = #{user.status}");
//		}
//		if(StringUtils.isNotBlank(user.getStartTime()) && StringUtils.isBlank(user.getEndTime())){
//			sqlTemp.append(" AND c1.createTime > #{user.startTime}");
//		}else if(StringUtils.isBlank(user.getStartTime()) && StringUtils.isNotBlank(user.getEndTime())){
//			sqlTemp.append(" AND c1.createTime < #{user.endTime}");
//		}else if(StringUtils.isNotBlank(user.getStartTime()) && StringUtils.isNotBlank(user.getEndTime())){
//			sqlTemp.append(" AND c1.createTime BETWEEN  #{user.startTime} AND #{user.endTime}");
//		}
//		return sqlTemp.toString();
//	}

}
