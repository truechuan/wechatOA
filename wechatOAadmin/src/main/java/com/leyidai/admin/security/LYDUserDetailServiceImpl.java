package com.leyidai.admin.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.leyidai.admin.service.AuthorizationService;
import com.leyidai.admin.service.BackstageAdminUserService;
import com.leyidai.entity.BackstageAdminUser;
import com.leyidai.entity.Group;
import com.leyidai.entity.Resource;
import com.leyidai.entity.ResourceCategory;
import com.leyidai.entity.Role;

public class LYDUserDetailServiceImpl implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(LYDUserDetailServiceImpl.class);
	@Autowired
	private BackstageAdminUserService adminUserService;
	@Autowired
	private AuthorizationService authorizationService;
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		log.debug("load user by username {}", username);
		
		log.debug("encoded password admin {}", bcryptEncoder.encode("admin"));
		BackstageAdminUser adminUser = adminUserService.getAdminUserByUsername(username);
		if(adminUser == null){
			throw new UsernameNotFoundException(username);
		}
		
		Set<Group> groups = authorizationService.getUserGroups(adminUser.getId());
		adminUser.setGroups(groups);//用户角色
		
		loadMenus(adminUser);//用户菜单
		
		Collection<GrantedAuthority> authories = this.loadGrantedAuthories(adminUser);
		
		boolean accountNonExpired = true;
		boolean accountNonLocked = true;
		boolean credentialsNonExpired = true;
		boolean enabled = true;
		
		LYDAdminUserDetails userDetails = new LYDAdminUserDetails(authories,
				adminUser.getPassWord(), username).build(accountNonExpired,
				accountNonLocked, credentialsNonExpired, enabled).build(adminUser);
		log.debug("admin user password {}", userDetails.getPassword());
		
		return userDetails;
	}
	
	/**
	 * 用户权限
	 * @param adminUser
	 * @return
	 */
	private Set<GrantedAuthority> loadGrantedAuthories(BackstageAdminUser adminUser){
		Set<GrantedAuthority> authories = new HashSet<GrantedAuthority>();
		
		Set<Group> groups = adminUser.getGroups();
		
		for(Group group : groups){
			
			for(Role role : group.getRoles()){
				
				authories.add(new SimpleGrantedAuthority(String.valueOf(role.getRoleId())));
			}
		}
		
		return authories;
	}
	
	/**
	 * 用户菜单
	 * @param adminUser
	 */
	private void loadMenus(BackstageAdminUser adminUser){
		
		Map<ResourceCategory, Collection<Resource>> menues = new TreeMap<ResourceCategory, Collection<Resource>>();
		
		log.debug("loading user {} menus through its group", adminUser.getLoginName());
		
		Set<Group> groups = adminUser.getGroups();
		log.debug("user {} has groups {}", adminUser.getLoginName(), groups);
		
		for(Group group : groups){
			
			Set<Role> roles = group.getRoles();
			log.debug("group {} has roles {}", group.getName(), roles);
			for(Role role : roles){
				
				Set<Resource> resources = authorizationService.getRoleResources(role.getRoleId());
				for(Resource resource : resources){
					log.debug("load resource {}", resource);
					
					this.setMenu(resource, menues);
				}
			}
		}
		
		log.debug("load user {} menues done", adminUser.getLoginName());
		
		adminUser.setMenues(menues);
	}
	
	/**
	 * 设置菜单概括名称
	 * @param resource
	 * @param menues
	 */
	private void setMenu(Resource resource, Map<ResourceCategory, Collection<Resource>> menues){
		assert(resource != null);
		if(resource.isMenu()){
			
			List<ResourceCategory> rcs = authorizationService.getResourceCategoryByResourceId(resource.getResourceId());
			if(rcs != null){
				
				for(ResourceCategory rc: rcs){
					
					Collection<Resource> resourceCollection = menues.get(rc);
					if(resourceCollection == null){
						
						resourceCollection = new TreeSet<Resource>();
						menues.put(rc, resourceCollection);
						log.debug("add menu head {}", rc.getName());
					}
					
					String url = resource.getUrl();
					resource.setUrl(url.replaceAll("\\*", "1"));
					log.debug("add menu link {} to head {}", resource, rc.getName());
					resourceCollection.add(resource);
				}
			}
		}
	}
}
