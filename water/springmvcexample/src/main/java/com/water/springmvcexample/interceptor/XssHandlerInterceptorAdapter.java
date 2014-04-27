package com.water.springmvcexample.interceptor;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import net.sf.xsshtmlfilter.HTMLFilter;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.water.springmvcexample.controllers.rest.UcapController;

/**
 * xss处理 允许基本的html标签,过滤有<,>,<>,&,\ 特殊字符
 * 
 * @author Administrator
 * 
 */
public class XssHandlerInterceptorAdapter extends HandlerInterceptorAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(XssHandlerInterceptorAdapter.class);
	private HttpServletRequestWrapper httpServletRequestWrapper ;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		LOGGER.debug(request.toString());
		request = new HttpServletRequestWrapper(request);
		LOGGER.debug(request.toString());
		return super.preHandle(request, response, handler);
	}


	public static void main(String[] args) {
		System.out.println(StringEscapeUtils.escapeHtml("<script>112<%2Fscript>&oq=<script>112<%2Fscript>"));
		System.out.println(StringEscapeUtils.unescapeHtml("<script>22</script>"));
		System.out.println(StringEscapeUtils.escapeSql("' or '1'='1 "));
		System.out.println(new HTMLFilter(true).filter("中文"));
	}

}
