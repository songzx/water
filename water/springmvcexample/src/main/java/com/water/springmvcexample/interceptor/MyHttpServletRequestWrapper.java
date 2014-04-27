package com.water.springmvcexample.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import net.sf.xsshtmlfilter.HTMLFilter;

/**
 * 处理request获取参数值
 * @author Administrator
 *
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {
	
	public MyHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String val = super.getParameter(name);
		return new HTMLFilter(true).filter(val);
	}

	@Override
	public String[] getParameterValues(String name) {
		String strs[] = super.getParameterValues(name);
		for (int i = 0; i < strs.length; i++) {
			strs[i] = new HTMLFilter(true).filter(strs[i]);
		}
		return strs;
	}
}
