package com.leyidai.admin.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.leyidai.admin.service.AuthorizationService;
import com.leyidai.admin.util.SiteUtil;
import com.leyidai.entity.Resource;
import com.leyidai.entity.Role;
/**
 * 加载资源与权限的对应关系
 * @author Administrator
 *
 */
public class LYDSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	private static final Logger log = LoggerFactory.getLogger(LYDSecurityMetadataSource.class);
	private AuthorizationService authorizationService;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	@Autowired
	public LYDSecurityMetadataSource(AuthorizationService authorizationService){
		this.authorizationService = authorizationService;
		this.loadResourceDefine(false);
	}

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		
		String requestUrl = ((FilterInvocation)object).getRequestUrl();
		requestUrl = SiteUtil.filterUrlNumber(requestUrl);
		log.debug("request url {}", requestUrl);
		
		if(resourceMap == null){
			this.loadResourceDefine(false);
		}
		
		return resourceMap.get(requestUrl);
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	public void reloadResourceDefine(){
		
		this.loadResourceDefine(true);
	}
	
	private void loadResourceDefine(boolean forceReload){
		
		if(resourceMap == null || forceReload){
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Resource> resources = this.authorizationService.loadAllResources();
			
			for(Resource r : resources){
				
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				
				for(Role role : r.getRoles()){
					ConfigAttribute ca = new SecurityConfig(String.valueOf(role.getRoleId()));
					configAttributes.add(ca);
				}
				
				resourceMap.put(r.getUrl(), configAttributes);
			}
		}
	}
	
}
