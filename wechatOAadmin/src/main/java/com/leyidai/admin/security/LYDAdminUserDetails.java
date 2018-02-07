package com.leyidai.admin.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.leyidai.entity.BackstageAdminUser;

public class LYDAdminUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	private Collection<GrantedAuthority> authories;
	private String password;
	private String username;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private BackstageAdminUser adminUser;
	
	LYDAdminUserDetails(Collection<GrantedAuthority> authories, String password, String username){
		this.authories = authories;
		this.password = password;
		this.username = username;
	}
	
	public LYDAdminUserDetails build(boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled){
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		
		return this;
	}
	
	public LYDAdminUserDetails build(BackstageAdminUser adminUser){
		this.adminUser = adminUser;
		
		return this;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authories;
	}

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.username;
	}

	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	public boolean isEnabled() {
		return this.enabled;
	}
	public BackstageAdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(BackstageAdminUser adminUser) {
		this.adminUser = adminUser;
	}
}
