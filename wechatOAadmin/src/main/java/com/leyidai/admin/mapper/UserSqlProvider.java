package com.leyidai.admin.mapper;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.leyidai.entity.UserQueryCondition;


public class UserSqlProvider {
	   private static final Logger log = LoggerFactory.getLogger(UserSqlProvider.class);
	   
	   /**
	    * 根据用户名查询user数量
	    * @param user
	    * @return
	    */
	   public String selectUserCountByUserName(Map<String, Object> data){
			
			return buildUserSql(true,data);
		}
	   /**
	    * 多状态、类型查找
	    * @param data
	    * @return
	    */
	   public String selectUser(Map<String, Object> data){
			
			return buildUserSql(false, data);
		}
	  
	   /**
	    * user sql
	    * @param user
	    * @return
	    */
	   private String buildUserSql(boolean isSelectCount, Map<String, Object> data){
		   StringBuilder builder = new StringBuilder();
		   if(isSelectCount){
				
				builder.append("select count(*) ");
			} else{
				
				builder.append("select * ");
			}
		   builder.append("from estatedb_user where 1=1");
		   Object conditionObj = data.get("condition");
		   
		   if(conditionObj != null){
			   UserQueryCondition condition = (UserQueryCondition)conditionObj;
			   
			   String name = condition.getName();
			   if(StringUtils.isNotEmpty(name)){
				   
					builder.append(" and `name`='").append(name).append("'");
			   }
			   String mobile = condition.getMobile();
			   if(StringUtils.isNotEmpty(mobile)){
				   
					builder.append(" and `mobile`='").append(mobile).append("'");
			   }
			   String openid = condition.getOpenid();
			   if(StringUtils.isNotEmpty(openid)){
				   
					builder.append(" and `openid`='").append(openid).append("'");
			   }
			   String idcard = condition.getIdcard();
			   if(StringUtils.isNotEmpty(idcard)){
				   
					builder.append(" and `idcard`='").append(idcard).append("'");
			   }
			   
			   int status = condition.getStatus();
			   if(status != 99){
				   
					builder.append(" and `status`='").append(status).append("'");
			   }
			   int isForbidden = condition.getIsForbidden();
			   if(isForbidden != 99){
				   
					builder.append(" and `isForbidden`='").append(isForbidden).append("'");
			   }
			   
			   builder.append(" order by createTime desc");
			   
			   if(condition.isPreparePage()){
					
					builder.append(" limit " + condition.getStart());
					
					if(condition.getPageSize() > 0){
						builder.append(", " + condition.getPageSize());
					}
			    }
		    }
		   
		   String sql = builder.toString();
		   log.debug("SQL for UserSqlProvider is {}", sql);
		   return sql;
	    }
		   
}