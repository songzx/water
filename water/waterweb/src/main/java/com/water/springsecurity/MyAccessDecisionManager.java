package com.water.springsecurity;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

/**
 * 访问决策器，决定某个用户具有的角色，是否有足够的权限去访问某个资源。s
 * 
 * @author 0000
 * @date Aug 17, 2013
 * @version V1.0
 */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {
	private static Logger logger = LoggerFactory.getLogger(MyAccessDecisionManager.class);

	@Override
	public void decide(Authentication authentication, Object obj, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute configAttribute = ite.next();
			String needRole = ((SecurityConfig) configAttribute).getAttribute();
			// ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needRole.trim().equals(ga.getAuthority().trim())) {
					return;
				}
			}
		}
		if(logger.isDebugEnabled()){
			System.out.println("用户没有角色权限");
		}
		throw new AccessDeniedException("用户没有角色权限");
	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
