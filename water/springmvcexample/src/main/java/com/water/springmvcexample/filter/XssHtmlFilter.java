package com.water.springmvcexample.filter;

import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;


import net.sf.xsshtmlfilter.HTMLFilter;

/**
 * @Title: XssHtmlFilter.java
 * @Description: TODO
 * @author 0000
 * @date 2014年4月13日 下午9:19:04
 * @version V1.0
 */
public class XssHtmlFilter implements Filter{
	private String strfilterurls = null;
	private String strfilterfloder = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		strfilterurls = filterConfig.getInitParameter("strfilterurls") == null ? "" : filterConfig.getInitParameter("strfilterurls").trim();
		strfilterfloder = filterConfig.getInitParameter("strfilterfloder") == null ? "" : filterConfig.getInitParameter("strfilterfloder").trim();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request2 = (HttpServletRequest) req;
		
		String accessurl = request2.getServletPath().substring(1);
		boolean isfloder = false;
		for(String url : strfilterfloder.split("\\|")){
			isfloder = accessurl.contains(url)?true:false;
			if(isfloder){
				break;
			}
		}
		if (isfloder || strfilterurls.contains(accessurl) || "all".equals(strfilterurls)) {
			chain.doFilter(new XssHttpServletRequestWrapper(request2), response);
			return;
		}
		chain.doFilter(request2, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
