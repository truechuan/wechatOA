package com.leyidai.admin.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leyidai.admin.mapper.BackstageAdminUserMapper;
import com.leyidai.admin.mapper.GroupMapper;
import com.leyidai.admin.util.DateUtil;
import com.leyidai.admin.util.DateUtil.DateFormat;
import com.leyidai.entity.BackstageAdminUser;
import com.leyidai.entity.Group;

/**
 * 用户信息Service
 * @author song
 *
 */
@Service
public class BackstageAdminUserService {
	private static final Logger log = LoggerFactory.getLogger(BackstageAdminUserService.class);
	@Autowired
	private BackstageAdminUserMapper backstageAdminUserMapper;
	@Autowired
	private DateUtil dateUtil;
	@Autowired
	private	GroupMapper groupMapper;
	
	@Transactional(readOnly = true)
	public List<BackstageAdminUser> loadAllAdminUsers(int start, int size) {
		
		List<BackstageAdminUser> adminUsers = backstageAdminUserMapper.getAdminUsers(start, size);
		if(adminUsers == null){
			adminUsers = Collections.emptyList();
		}
		return adminUsers;
	}
	
	@Transactional
	public void deleteAdminUser(int adminUserId) {
		
		backstageAdminUserMapper.deleteAdminUser(adminUserId);
	}
	
	@Transactional(readOnly=true)
	public int totalAdminUserCount(){
		
		return backstageAdminUserMapper.getAdminUserCount();
	}
	
	@Transactional
	public void addAdminUser(BackstageAdminUser adminUser){
		
		adminUser.setCreateTime(dateUtil.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));
		backstageAdminUserMapper.addAdminUser(adminUser);
		if(adminUser.getGroups() != null){
			for(Group group: adminUser.getGroups()){
				groupMapper.addUserGroup(adminUser.getId(), group.getGroupId());
			}
		}
	}
	
	@Transactional
	public void updateAdminUser(BackstageAdminUser adminUser){
		
		adminUser.setUpdateTime(dateUtil.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));
		backstageAdminUserMapper.updateAdminUser(adminUser);
		
		groupMapper.deleteUserGroupByUserId(adminUser.getId());
		if(adminUser.getGroups() != null){
			for(Group group: adminUser.getGroups()){
				groupMapper.addUserGroup(adminUser.getId(), group.getGroupId());
			}
		}
		
	}
	
	/**
	 * 用户名获取LoginUsers
	 * @param loginName
	 * @return
	 */
	@Transactional(readOnly = true)
	public BackstageAdminUser getAdminUserByUsername(String loginName){
		
		BackstageAdminUser loginUser = backstageAdminUserMapper.getAdminUserByUsername(loginName);
		
		if(loginUser != null){
			
			log.info("user {} exist", loginName);
		} else{
			
			log.info("user {} doesn't exist", loginName);
		}
		
		return loginUser;
	}
	
	@Transactional(readOnly = true)
	public BackstageAdminUser getAdminUserById(int adminUserId){
		
		BackstageAdminUser loginUser = backstageAdminUserMapper.getAdminUserById(adminUserId);
		return loginUser;
	}
	
	
	/**
	 * 更新登录信息
	 * @param loginName
	 */
	@Transactional
	public void updateLoginInfo(String loginName){
		
		BackstageAdminUser loginUser = backstageAdminUserMapper.getAdminUserByUsername(loginName);
		if(loginUser == null){
			
			log.info("please don't update null object loginUser");
			throw new RuntimeException("please don't update null object loginUser");
		}
		
		loginUser.setUpdateTime(dateUtil.getCurrentTime(DateFormat.YYYY_MM_DD_HH_mm_ss));
		loginUser.setLoginTimes(loginUser.getLoginTimes() + 1);
		log.info("update login info, username {}", loginName);
		
		backstageAdminUserMapper.updateAdminUser(loginUser);
	}
	
	/**
	 * 更改密码
	 * @param adminUserId
	 * @param newPassword
	 */
	@Transactional
	public void updateAdminUserPassword(int adminUserId, String newPassword){
		
		BackstageAdminUser adminUser = backstageAdminUserMapper.getAdminUserById(adminUserId);
		
		if(adminUser != null){
			
			adminUser.setPassWord(newPassword);
			backstageAdminUserMapper.updateAdminUser(adminUser);
		}
	}
	
}
