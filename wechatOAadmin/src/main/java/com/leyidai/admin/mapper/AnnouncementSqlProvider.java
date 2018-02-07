package com.leyidai.admin.mapper;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leyidai.entity.CommonQueryCondition;

public class AnnouncementSqlProvider{
	private static final Logger log = LoggerFactory.getLogger(AnnouncementSqlProvider.class);
	
	/**
	 * 多状态、类型查找
	 * @param userId
	 * @param statuses
	 * @return
	 */
	public String selectAnnouncement(Map<String, Object> data){
		
		return buildSql(data);
	}
	private String buildSql(Map<String, Object> data){
		StringBuilder builder = new StringBuilder();			
			builder.append("select * from lyd_announcement where 1=1");	
		Object conditionObj = data.get("condition");
		if(conditionObj != null){
			CommonQueryCondition condition = (CommonQueryCondition)conditionObj;
			
			String area = condition.getArea();
			if(StringUtils.isNotEmpty(area)){
				builder.append(" and `area` in(").append(area).append(")");;
			}
			
			if(condition.isPreparePage()){
				builder.append(" order by createTime desc");
				builder.append(" limit " + condition.getStart());
				
				if(condition.getPageSize() > 0){
					builder.append(", " + condition.getPageSize());
				}
			}
		}	
		String sql = builder.toString();
		
		log.debug("SQL for AnnouncementSqlProvider is {}", sql);
		
		return sql;
		
	}

}
