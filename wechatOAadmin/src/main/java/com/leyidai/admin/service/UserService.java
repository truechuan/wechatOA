package com.leyidai.admin.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leyidai.admin.mapper.UserMapper;
import com.leyidai.admin.util.DateUtil;
import com.leyidai.admin.util.DateUtil.DateFormat;
import com.leyidai.entity.User;
import com.leyidai.entity.UserQueryCondition;


/**
 * 用户信息
 * @author song
 *
 */
@Service
public class UserService {
	private static final Logger logs = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private DateUtil dateUtil;
	
	
	/**
	 * 按条件查询用户
	 * @param username
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<User> getUsers(int start, int size, String username, String mobile,String openid, String idcard, String statusString,String isForbidden){
		logs.debug("get users start");
		
        UserQueryCondition condition = new UserQueryCondition();
        condition.setPreparePage(true);
        condition.setName(username);
        condition.setMobile(mobile);
        condition.setOpenid(openid);
        condition.setIdcard(idcard);
        if(start > 0){
			condition.setStart(start);
		}
		if(size > 0){
			condition.setPageSize(size);
		}
		if(StringUtils.isNotEmpty(statusString)){
			int status = Integer.valueOf(statusString);
			if(status != 99){
				condition.setStatus(status);
			}
		}        
        if(StringUtils.isNotEmpty(isForbidden)){
        	int isForbiddenInt = Integer.valueOf(isForbidden);
			if(isForbiddenInt != 99){
				condition.setIsForbidden(isForbiddenInt);
			}
        }
		return userMapper.getUsers(condition);
	}
	
	/**
	 * 用户数量
	 * @param 
	 * @param
	 * @return
	 */
	@Transactional(readOnly=true)
	public int getUserCount(String name, String mobile, String openid,String idcard, String statusString,String isForbidden){
        UserQueryCondition condition = new UserQueryCondition();
        int count = 0;
        condition.setPreparePage(false);
        condition.setName(name);
        condition.setMobile(mobile);
        condition.setOpenid(openid);
        condition.setIdcard(idcard);
        
       
        if(StringUtils.isNotEmpty(statusString)){
			int status = Integer.valueOf(statusString);
			if(status != 99){
				condition.setStatus(status);
			}
		}
        if(StringUtils.isNotEmpty(isForbidden)){
			int isForbiddenInt = Integer.valueOf(isForbidden);
			if(isForbiddenInt != 99){
				condition.setIsForbidden(Integer.valueOf(isForbiddenInt));
			}
		}


        count = userMapper.getUserCount(condition);
		return count;
	}
	
	/**
	 * get user by id
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly=true)
	public User getUserByUserId(int userId){
		return userMapper.getUserByUserId(userId);
	}
	
	/**
	 * 修改用户
	 */
	@Transactional
	public void updateUser(User user){
		user.setUpdateTime(dateUtil.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));
		logs.info(String.valueOf(user.getIsForbidden())+"这是黑名单状态");
        userMapper.updateUser(user);
	}
	
	/**
	 * 修改用户
	 */
	@Transactional
	public void deleteUser(int userId){
        userMapper.deleteUser(userId);
	}
	
	

	/**********************2017-01-17 许云强  add ***************************/
	@Transactional
	public void updateUserLimitData(User user){
        userMapper.updateUserLimitData(user);
	}
	
	public User getUserByOpenid(String openid){
		
		return userMapper.getUserByUserOpenid(openid);
	}
	
	/**
	 * 得到解封数据列表
	 * @return
	 */
	public List<User> getUserByJiefeng(){
		
		List<User> userListRes  = new ArrayList<User>();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
		List<User> userList = userMapper.getUserByJiefeng();
		
		if(userList != null){
			for (int i = 0; i < userList.size(); i++) {
			    
				try {
					long l1 = df.parse(df.format(new Date())).getTime();
					long l2 = df.parse(userList.get(i).getStoptime_end().toString()).getTime();
					
				    if(l1 == l2){
				    	userListRes.add(userList.get(i));
				    }
				} catch (ParseException e) {
					e.printStackTrace();
				}
			    
			}
		}
		
		return userListRes;
	}
	
	//解封
	public void jiefengById(String openid){
		
		userMapper.jiefengById(openid);
	}
	/**********************2017-01-17 许云强  end ***************************/
	
	/*********************2017-04-12 史晓昊 add start*********************/
	//解封
	public void unLockedByUserId(int userId){
		
		userMapper.unLockedByUserId(userId);
	}
	
	/*********************2017-04-12 史晓昊 add end*********************/
	
	
}