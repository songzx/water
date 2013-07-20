package com.water.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class WaterAdminFilter implements Filter{

	private EncodeFilter encodeFilter;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encodeFilter = new EncodeFilter();
		XssFilter xssFilter = new XssFilter();
		WaterAdminEndFilter waterAdminEndFilter = new WaterAdminEndFilter();
		
		encodeFilter.setOtherfilter(xssFilter);
		xssFilter.setOtherfilter(waterAdminEndFilter);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		encodeFilter.doFilter(request, response, chain);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
