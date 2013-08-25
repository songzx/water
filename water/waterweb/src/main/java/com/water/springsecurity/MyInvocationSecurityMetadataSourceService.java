package com.water.springsecurity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

/**
 * 最核心的地方，就是提供某个资源对应的权限定义， 即getAttributes方法返回的结果。 此类在初始化时， 应该取到所有资源及其对应角色的定义。
 * 
 * 资源源数据定义，将所有的资源和权限对应关系建立起来，即定义某一资源可以被哪些角色去访问。
 * 
 * @author 0000
 * @date Aug 17, 2013
 * @version V1.0
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
	private Logger logger = LoggerFactory.getLogger(MyInvocationSecurityMetadataSourceService.class);
	
	@PersistenceContext(unitName = "authorityunit")
	private EntityManager entityManager;

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public MyInvocationSecurityMetadataSourceService() {
		loadResourceDefine();
	}

	/**
	 * 初始化资源
	 */
	private void loadResourceDefine() {
		logger.info("初始化权限资源starting ----------------------");
		
		String sql = "select authority_name from pub_authorities";
		List<String> query = entityManager.createNativeQuery(sql).getResultList();
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

		for (String auth : query) {
			ConfigAttribute ca = new SecurityConfig(auth);
			List<String> result = entityManager.createNativeQuery("select b.resource_string from Pub_Authorities_Resources a, Pub_Resources b, " + "Pub_authorities c where a.resource_id = b.resource_id and a.authority_id=c.authority_id and c.Authority_name= ?").setParameter(1, auth).getResultList();
			for (String url : result) {
				if (resourceMap.containsKey(url)) {
					Collection<ConfigAttribute> value = resourceMap.get(url);
					value.add(ca);
					resourceMap.put(url, value);
				} else {
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(ca);
					resourceMap.put(url, atts);
				}
			}
		}
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// object 是一个URL，被用户请求的url。
		String url = ((FilterInvocation) object).getRequestUrl();
		int firstQuestionMarkIndex = url.indexOf("?");
		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			//if (urlMatcher.pathMatchesUrl(url, resURL)) {
				//return resourceMap.get(resURL);
			//}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
