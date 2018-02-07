package com.leyidai.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leyidai.entity.User;
import com.leyidai.web.service.UserService;
/**
 * spring security获取用户及用户权限信息
 * @author Administrator
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserService userService;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		log.info("load user by username {}", username);
		
		User user = userService.getUserByUsername(username);
		
		if(user == null){
			log.error("username {} not found", username);
			throw new UsernameNotFoundException("username " + username + " not found");
		}
		
		return new com.leyidai.web.security.UserDetails(user); 
	}
}
