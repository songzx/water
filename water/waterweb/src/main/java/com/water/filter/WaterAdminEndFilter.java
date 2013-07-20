package com.water.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.water.basictool.WaterSysteConfigConst;

/**
 * 请求参数编码设置
 * 
 * @author 0000
 * @date Feb 13, 2013 9:48:10 PM
 * @version 1.0
 * @classname EncodeFilter
 */
public class WaterAdminEndFilter implements Filter {
	private Filter otherfilter;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void setOtherfilter(Filter otherfilter) {
		this.otherfilter = otherfilter;
	}

}
