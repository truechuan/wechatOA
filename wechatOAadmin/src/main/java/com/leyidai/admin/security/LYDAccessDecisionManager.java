package com.leyidai.admin.security;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class LYDAccessDecisionManager implements AccessDecisionManager {
	private static final Logger log = LoggerFactory.getLogger(LYDAccessDecisionManager.class);

	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		
		log.debug("decide access authority.");
		
		if(configAttributes == null || configAttributes.size() <= 0){
			log.debug("no config attributes for this user, mybe not login.");
			throw new AccessDeniedException("No access authorization!");
		}
		
		//所请求的资源拥有的权限(一个资源对多个权限)  
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while(iterator.hasNext()) {
        	
            ConfigAttribute configAttribute = iterator.next();
            
            //访问所请求资源所需要的权限
            String needPermission = configAttribute.getAttribute();
            log.debug("needs permission {}", needPermission);
            
            //用户所拥有的权限authentication
            for(GrantedAuthority ga : authentication.getAuthorities()) {
                if(needPermission.equals(ga.getAuthority())) {
                	log.debug("have permission {}", ga.getAuthority());
                    return;
                }
            }
        }
        //没有权限
        throw new AccessDeniedException("No access authorization!");
		
	}

	public boolean supports(ConfigAttribute attribute) {
		
		return true;
	}

	public boolean supports(Class<?> clazz) {
		
		return true;
	}

}
