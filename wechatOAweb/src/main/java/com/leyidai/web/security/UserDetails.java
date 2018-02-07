package com.leyidai.web.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.leyidai.entity.User;

/**
 * 用户信息及用户权限信息
 * @author Administrator
 *
 */
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
	private static final long serialVersionUID = 1L;
	private User user;
	
	public UserDetails(User user){
		this.user = user;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
	}


	public boolean isAccountNonExpired() {
		
		return true;
	}


	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
