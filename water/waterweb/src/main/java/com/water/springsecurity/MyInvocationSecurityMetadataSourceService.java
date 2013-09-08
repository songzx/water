package com.water.springsecurity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.views.freemarker.tags.URLModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.config.http.MatcherType;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AnyRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
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
@Service("myInvocationSecurityMetadataSourceService")
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
	private Logger logger = LoggerFactory.getLogger(MyInvocationSecurityMetadataSourceService.class);

	@PersistenceContext(unitName = "authorityunit")
	private EntityManager entityManager;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

	// private UrlMatcher urlMatcher = new AntUrlPathMatche();
	public MyInvocationSecurityMetadataSourceService() {
		loadResourceDefine();
	}

	/**
	 * 初始化资源
	 */
	private void loadResourceDefine() {

		logger.info("初始化权限资源starting ----------------------");
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		ConfigAttribute ca = new SecurityConfig("ROLE_GUEST");
		atts.add(ca);
		resourceMap.put("/admin/singleindex.jsp", atts);

		Collection<ConfigAttribute> atts2 = new ArrayList<ConfigAttribute>();
		ConfigAttribute ca2 = new SecurityConfig("ROLE_ADMIN");
		atts2.add(ca2);
		resourceMap.put("/admin/singleindex.jsp", atts2);
		resourceMap.put("/account/login", atts2);

	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}


	/**
	 * As Spring Security 3.1, UrlMatcher, RegexUrlPathMatcher, and AntUrlPathMatcher are removed and their functions are replaced by another class org.springframework.security.web.util.RequestMatcher and org.springframework.security.config.http.MatcherType
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// guess object is a URL.
		String url = ((FilterInvocation) object).getRequestUrl();
		if(logger.isDebugEnabled()){
			logger.debug("access url : "+url);
		}
		Iterator<String> ite = resourceMap.keySet().iterator();
		//RequestMatcher requestMatcher = new RegexRequestMatcher();
		while (ite.hasNext()) {
			String resURL = ite.next();
			// if (urlMatcher.pathMatchesUrl(url, resURL)) {
			//logger.debug(requestMatcher.);
			return resourceMap.get(resURL);
			// }
		}
		return null;
		//return resourceMap.get("/index.jsp");
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
