package com.water.springmvcexample.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.xsshtmlfilter.HTMLFilter;

/**
 * 处理request获取参数值
 * 
 * @author Administrator
 * 
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private static final Logger logger = LoggerFactory.getLogger(XssHttpServletRequestWrapper.class);

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String val = super.getParameter(name);

		return val == null ? null : new HTMLFilter(logger.isDebugEnabled()).filter(val);
	}

	@Override
	public String[] getParameterValues(String name) {
		String strs[] = super.getParameterValues(name);
		if(strs == null){
			return null;
		}
		for (int i = 0; i < strs.length; i++) {
			strs[i] = new HTMLFilter(logger.isDebugEnabled()).filter(strs[i]);
		}
		return strs;
	}
}
